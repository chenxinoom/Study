package com.build;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Sort {
    int[] arrs = {45,3,4,2,45,6,78,6,4,33,56,67,45,78,79,80,81,82,83,84,85,86,87,87};
    //交换算法
    @Test
    public void odd_even_sort(){
        int exchFlag = 1;

        while (exchFlag == 1){
            exchFlag = 0;
            for(int start = 0; start < 2; start ++){
                for(int i = start; i < arrs.length - 1; i+=2){
                    if(arrs[i] > arrs[i + 1]){
                        int temp = arrs[i];
                        arrs[i] = arrs[i + 1];
                        arrs[i + 1] = temp;
                        //当不需要换位置的时候 则位0
                        exchFlag = 1;
                    }
                }
            }
        }

        System.out.println(Arrays.toString(arrs));
    }

    //多线程 奇偶排序


    AtomicInteger exchFlag = new AtomicInteger(1);
    class paddevenSort implements Runnable{
        int i;
        CountDownLatch latch;

        public paddevenSort(int i, CountDownLatch latch){
            this.i = i;
            this.latch = latch;
        }

        @Override
        public void run(){
            if(arrs[i] > arrs[i + 1]){
                int temp = arrs[i];
                arrs[i] = arrs[i + 1];
                arrs[i + 1] = temp;
                //当不需要换位置的时候 则位0
                exchFlag.set(1);
            }
            latch.countDown();
        }
    }    @Test
    public void padd_even_sort() throws InterruptedException {


        ExecutorService executorService = Executors.newFixedThreadPool(arrs.length / 2);
        while (exchFlag.get() == 1){
            exchFlag.set(0);
            CountDownLatch latch = new CountDownLatch(arrs.length /2);
            for(int start = 0; start < 2; start ++){
                for (int i = start; i < arrs.length - 1; i+=2){
                    executorService.submit(new paddevenSort(i,latch));
                }
                latch.await();
            }

        }
        executorService.shutdown();
        System.out.println(Arrays.toString(arrs));
    }

    //希尔排序
    @Test
    public void test01(){
        int h = 1;
        //h肯定 大于 一半
        while (h <= arrs.length / 3){
            h = h * 3 + 1;
        }

        while(h > 0){
            for(int i = h; i < arrs.length; i++){
                int temp = arrs[i];
                int j = i - h;
                if (temp < arrs[j]){
                    //这是给temp找位置
                    while (j >= 0 && arrs[j] > temp){
                        arrs[j + h] = arrs[j];
                        j-=h;
                    }
                    arrs[j + h] = temp;
                }
            }
            h = (h - 1) / 3;
        }


        System.out.println(Arrays.toString(arrs));
//        System.out.println(arrs.length);
//        System.out.println(h);
    }
}
