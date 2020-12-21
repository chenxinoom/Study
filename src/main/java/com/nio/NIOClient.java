package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;

public class NIOClient {

    private Selector selector;

    private void init(String ip,int port) throws IOException {
        selector = SelectorProvider.provider().openSelector();
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress(ip,port));
        channel.register(selector, SelectionKey.OP_CONNECT);
    }

    private void working() throws IOException, InterruptedException {
        init("127.0.0.1",8000);
        while(true){
            if (!selector.isOpen()){
                return;
            }
            selector.select();
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            while(keys.hasNext()){
                SelectionKey key = keys.next();
                keys.remove();
                if (key.isConnectable()){
                    doConnect(key);
                }else if (key.isReadable()){
                    doread(key);
                }
            }
        }
    }

    private void doConnect(SelectionKey key) throws IOException, InterruptedException {
        SocketChannel channel = (SocketChannel)key.channel();
        //正在连接 则连接
        if (channel.isConnectionPending()){
            channel.finishConnect();
        }

        channel.configureBlocking(false);
        channel.write(ByteBuffer.wrap("Hello JAVANIO".getBytes()));
//        Thread.sleep(10000);
        channel.write(ByteBuffer.wrap("Hello JAVANIO".getBytes()));
        channel.write(ByteBuffer.wrap("Hello JAVANIO".getBytes()));
        channel.register(selector,SelectionKey.OP_READ);

    }

    private void doread(SelectionKey key){
        SocketChannel channel = (SocketChannel)key.channel();
        ByteBuffer bb = ByteBuffer.allocate(8192);

        try {
            int len = channel.read(bb);
            byte[] b = bb.array();
            String msg = new String(b).trim();
            System.out.println("客户端收到的信息:" + msg);
            channel.close();
            key.selector().close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        NIOClient nioClient = new NIOClient();
        nioClient.working();
    }
}
