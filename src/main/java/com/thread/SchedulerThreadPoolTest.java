package com.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedulerThreadPoolTest {
    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);

        System.out.println(System.currentTimeMillis());
        pool.scheduleAtFixedRate(() ->{
            try {
                Thread.sleep(22000);
                System.out.println(System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },0,2, TimeUnit.SECONDS);


    }
}
