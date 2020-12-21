package com.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class FutureTaskTest {
    public static void main(String[] args) throws Exception {
//        Thread t = new Thread(() ->{
//            while (true){
//                System.out.println("线程开始");
////                Thread.sleep(1000);
//                Thread.yield();
//                System.out.println("重新获取到");
//            }
//        });
//
//        t.start();
//
//        t.interrupt();
//
//        while (true){}

        AtomicReference<FutureTask<Integer>> a = new AtomicReference<>();
        Runnable task = () -> {
            while (true) {
                FutureTask<Integer> f = new FutureTask<>(() -> 1);
                a.set(f);
                f.run();
            }
        };

        Supplier<Runnable> observe = () -> () -> {
            while (a.get() == null);

            int c = 0;
            int ic = 0;
            while (true) {
                c++;
                FutureTask<Integer> f = a.get();
                while (!f.isDone()) {}
                try {
                    /*
                    Set the interrupt flag of this thread.
                    The future reports it is done but in some cases a call to
                    "get" will result in an underlying call to "awaitDone" if
                    the state is observed to be completing.
                    "awaitDone" checks if the thread is interrupted and if so
                    throws an InterruptedException.
                     */
                    //这里是中断自己的线程
                    Thread.currentThread().interrupt();
                    f.get();
                }
                catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
                catch (InterruptedException e) {
                    ic ++;
                    System.out.println("InterruptedException observed when isDone() == true " + c + " " + ic + " " + Thread.currentThread());
                }
            }
        };

        CompletableFuture.runAsync(task);
        Stream.generate(observe::get)
                .limit(Runtime.getRuntime().availableProcessors() - 1)
                .forEach(CompletableFuture::runAsync);

        Thread.sleep(1000);
        System.exit(0);
    }

}
