package com.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NIOServer {
    private Selector selector;
    private ExecutorService executor = Executors.newCachedThreadPool();

    private static Map<Socket, Long> time_stat = new HashMap<>();

    private void startServer() throws IOException {
        selector = SelectorProvider.provider().openSelector();
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);

        channel.bind(new InetSocketAddress(8000));


        //事件驱动 epoll
        channel.register(selector,SelectionKey.OP_ACCEPT);
        while (true){
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            long e = 0;
            while(iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();

                if (key.isAcceptable()){
                    doAccept(key);
                }
                //这里是else
                else if (key.isValid() && key.isReadable()){
                    if (!time_stat.containsKey(((SocketChannel)key.channel()).socket())){
                        time_stat.put(((SocketChannel)key.channel()).socket(),System.currentTimeMillis());
                    }
                    doReader(key);
                }
                else if (key.isValid() && key.isWritable()){
                    doWrite(key);
                    e = System.currentTimeMillis();
                    long l = e - time_stat.get(((SocketChannel)key.channel()).socket());
                    System.out.println("spend:" + l + "ms");
                }
            }
        }
    }

    private class EchoClient{
        private LinkedList<ByteBuffer> outq;

        EchoClient (){
            this.outq = new LinkedList<>();
        }

        public LinkedList<ByteBuffer> getOutq(){
            return outq;
        }

        public void enqueue(ByteBuffer bb){
            outq.addFirst(bb);
        }
    }

    private void doAccept(SelectionKey key){
        ServerSocketChannel server = (ServerSocketChannel)key.channel();
        SocketChannel clientChannel;


        try {
            //真正的读写是这个类
            clientChannel = server.accept();
            clientChannel.configureBlocking(false);
            SelectionKey clientKey = clientChannel.register(selector, SelectionKey.OP_READ);
            EchoClient echoClient = new EchoClient();
            clientKey.attach(echoClient);

            InetAddress inetAddress = clientChannel.socket().getInetAddress();
            System.out.println(inetAddress + "Accepted connection");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doReader(SelectionKey key){
        SocketChannel channel = (SocketChannel)key.channel();
        ByteBuffer bb = ByteBuffer.allocate(8192);
        int len;

        try {
            len = channel.read(bb);
            if (len < 0){
//                disconnet(key);
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
//            disconnet(key);
            return;
        }
        //清空缓冲区
        bb.flip();
        executor.submit(new HandleMsg(key,bb));
    }

    //处理读信息的任务
    private class HandleMsg implements Runnable{

        private SelectionKey key;
        private ByteBuffer bb;

        HandleMsg(SelectionKey key,ByteBuffer bb){
            this.key = key;
            this.bb = bb;
        }

        @Override
        public void run(){
            EchoClient client = (EchoClient)key.attachment();
            client.enqueue(bb);
            key.interestOps(SelectionKey.OP_WRITE | SelectionKey.OP_READ);
            //请破selector立即返回
            selector.wakeup();
        }
    }

    private void doWrite(SelectionKey key) {

        SocketChannel channel = (SocketChannel)key.channel();
        EchoClient client = (EchoClient)key.attachment();

        LinkedList<ByteBuffer> outq = client.getOutq();
        ByteBuffer bb = outq.getLast();

        try {
            int len = channel.write(bb);
            if (len == -1){
//                disconnect(key);
                return;
            }
            if (bb.remaining() == 0){
                outq.removeLast();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //当byteBuffer已经发送完毕 则只能读
        if (outq.size() == 0){
            key.interestOps(SelectionKey.OP_READ);
        }
    }

    public static void main(String[] args) throws IOException {
        NIOServer nioServer = new NIOServer();
        nioServer.startServer();
    }
}
