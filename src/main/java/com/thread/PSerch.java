package com.thread;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class PSerch {
    int[] arrs = {45,3,4,2,45,6,78,6,4,33,56,67,45,78,79,80,81,82,83,84,85,86,87,87};
    AtomicInteger result = new AtomicInteger(-1);
    //一般要声明常量的东西
    int THREAD_NUM = 5;
    ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUM);

    public int serch(int targetValue, int start, int end){

        for(int i = start; i < end; i++){
            //如果是别的线程找到即返回
            if (result.get() > 0){
                return result.get();
            }
            //z这是找到的情况
            if(targetValue == arrs[i]){
                if (!result.compareAndSet(-1,i)){
                    return result.get();
                }
                return i;
            }
        }
        return -1;
    }

    public int pserch() throws ExecutionException, InterruptedException {
        ArrayList<Future<Integer>> futures = new ArrayList<>();
        int start = 0;
        int step = arrs.length / THREAD_NUM;

        for (int i = 0; i < THREAD_NUM; i++) {
            start = start + step * i;
            int end = start + step;
            if (end + step > arrs.length) end = arrs.length;
            int finalstart = start;
            int finalEnd = end;
            futures.add(executorService.submit(() -> {
                return serch(45,finalstart, finalEnd);
            }));
        }
        Thread.sleep(1000);

        for (Future future : futures) {
            if ((int)future.get() > -1){
                System.out.println(future.get());
//                return (int) future.get();
            }
        }
        return -1;

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        PSerch pSerch = new PSerch();
        int pserch = pSerch.pserch();

        System.out.println(pserch);
        pSerch.executorService.shutdown();
    }
}
