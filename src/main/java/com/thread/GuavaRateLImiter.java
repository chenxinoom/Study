package com.thread;


import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GuavaRateLImiter {
    static RateLimiter limiter = RateLimiter.create(2);

    static class Task implements Runnable{

        int a,b;

        public Task(int a,int b){
            this.a = a;
            this.b = b;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis());
            int c =  a/b;

        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 10, 0L, TimeUnit.MINUTES, new SynchronousQueue<Runnable>());
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            threadPoolExecutor.execute(new Task(100,i));
        }
    }

}