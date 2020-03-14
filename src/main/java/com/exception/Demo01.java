package com.exception;

import java.util.concurrent.CountDownLatch;

public class Demo01 {

    volatile static int conut = 0;

    public static void add() throws InterruptedException {
        Thread.sleep(5);

        while (!compareAndSwap(conut,conut + 1)){}
    }

    //csa做法试一次

    //比较并且替换

    /**
     * @param expectCount 期望值
     * @param newCount  已经加完的值
     * @return  当期望值和最新值一样的时候返回true
     */
    public static synchronized boolean compareAndSwap(int expectCount,int newCount){
        if (conut == expectCount){
            conut = newCount;
            return true;
        }
        return false;
    }

    //获取conut值
    public static int getConut(){
        return conut;
    }


    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        int ThreadSize = 100;
        final CountDownLatch countDownLatch = new CountDownLatch(ThreadSize);
        for (int i = 0; i < ThreadSize; i++) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        for (int j = 0; j < 10; j++) {
                            add();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            }
            );
            thread.start();
        }
        countDownLatch.await();
        long endTime = System.currentTimeMillis();
        System.out.println("用时为=" + (endTime - startTime) + "conut=" + conut);
    }
}
