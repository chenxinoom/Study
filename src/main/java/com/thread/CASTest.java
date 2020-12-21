package com.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class CASTest {

    static AtomicInteger i = new AtomicInteger();
    static AtomicReference a = new AtomicReference();

    static class Tast implements Runnable {
        @Override
        public void run(){
            for (int j = 0; j < 10000; j++) {
                i.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i1 = 0; i1 < 10; i1++) {
            threads[i1] = new Thread(new Tast());
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println(i.get());
    }
}
