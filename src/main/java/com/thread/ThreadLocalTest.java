package com.thread;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalTest {
    static ThreadLocal<SimpleDateFormat> integerThreadLocal = new ThreadLocal<SimpleDateFormat>();

    public static void main(String[] args) throws InterruptedException {


//        integerThreadLocal.set(new SimpleDateFormat("yyyy-MM-dd"){
//            @Override
//            protected void finalize() throws Throwable{
//                System.out.println("is gc");
//            }
//        });
//
//        System.out.println("结束");
//        integerThreadLocal.remove();
//        System.out.println("tuichu");
//        integerThreadLocal = null;
//        Thread.sleep(10000);
//        System.gc();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"){
            @Override
            protected void finalize() throws Throwable{
                System.out.println("is gc");
            }
        };

        WeakReference<SimpleDateFormat> simpleDateFormatWeakReference = new WeakReference<SimpleDateFormat>(simpleDateFormat);


//        simpleDateFormat = null;

        System.out.println(simpleDateFormatWeakReference.get());
        System.gc();

        System.out.println(simpleDateFormatWeakReference.get());

    }
}
