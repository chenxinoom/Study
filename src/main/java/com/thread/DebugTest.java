package com.thread;

public class DebugTest {


    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            System.out.println("进入线程");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("退出线程");
        });


        t.start();
        System.out.println("主线程");
        Thread.sleep(2000);
        System.out.println("主线程结束");
    }
}
