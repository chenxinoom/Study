package com.queue;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class SyschronousQueueTest {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();


        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(600);
                    System.out.println(queue.take());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        int i = 0;
//        while (true){
//            queue.put(i++);
//        }

        new Thread(() -> {
            try {
                Thread.sleep(500);
                queue.put(3);
                System.out.println(3);
//                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //没有消费者阻塞
        Thread.sleep(550);
        queue.put(0);
        System.out.println(0);
        queue.put(1);
        System.out.println(1);

    }
}
