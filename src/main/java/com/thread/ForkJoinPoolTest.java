package com.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        ForkJoinTask<Long> submit = forkJoinPool.submit(new CountTask(1, 50001));

        Long aLong = submit.get();
        System.out.println(aLong);

    }

    /**
     * 数数 从头到尾
     */
    static class CountTask extends RecursiveTask<Long> {
        private static final int  THRESHOLD = 10000;
        private long start;
        private long end;

        public CountTask(long start,long end){
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            long sum = 0;
            boolean flag = end - start+ 1 <= THRESHOLD;
            if (flag){
                for(long i = start; i <= end; i ++){
                    sum += i;
                }
            }else {
                long step = (long)(end - start + 1) / 100;
                List<CountTask> countTaskList = new ArrayList<>();
                for (int i = 0; i < 100; i++) {
                    long startS = start + step * i;
                    long endS = (startS + step - 1) <= (end - step) ? (startS + step - 1) : end;
                    CountTask countTask = new CountTask(startS, endS);
                    countTaskList.add(countTask);
                    countTask.fork();
                }
                for (CountTask countTask : countTaskList) {
                    Long join = countTask.join();
                    sum += join;
                }
            }
            return sum;
        }
    }


}
