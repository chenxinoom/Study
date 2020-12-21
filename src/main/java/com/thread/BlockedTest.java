package com.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockedTest {
    public static void main(String[] args) throws InterruptedException {
        BlockedTest blockedTest = new BlockedTest();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            synchronized (blockedTest){
                while (true);
            }
        });

        Thread.sleep(100);

        executorService.submit(() -> {
            synchronized (blockedTest){
                System.out.println("我获取锁类");
            }
        });

//        executorService.shutdown();
    }
}
