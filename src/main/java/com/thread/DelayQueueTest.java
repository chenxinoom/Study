package com.thread;

import sun.tools.tree.ThisExpression;

import java.util.Queue;
import java.util.concurrent.*;

public class DelayQueueTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        DelayQueue<CasheItem> queue = new DelayQueue<>();

        executorService.submit(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                CasheItem casheItem = new CasheItem(Integer.toString(i), 1);
                queue.offer(casheItem);
            }
        });

        executorService.submit(() ->{
            while (true){
                System.out.println(queue.poll());
                Thread.sleep(2000);
            }
        });



    }

    static class CasheItem implements Delayed {
        private String key;

        /**
         * 过期时间(单位秒)
         */
        private long exprieTime;

        private long currentTime;

        @Override
        public String toString() {
            return "CasheItem{" +
                    "key='" + key + '\'' +
                    ", exprieTime=" + exprieTime +
                    ", currentTime=" + currentTime +
                    '}';
        }

        public CasheItem(String key, long exprieTime) {
            this.key = key;
            this.exprieTime = exprieTime;
            this.currentTime = System.currentTimeMillis();
        }

        @Override
        public long getDelay(TimeUnit unit) {
            // 计算剩余的过期时间
            // 大于 0 说明未过期
            return exprieTime - TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - currentTime);
        }

        @Override
        public int compareTo(Delayed o) {
            // 过期时间长的放置在队列尾部
            if (this.getDelay(TimeUnit.MICROSECONDS) > o.getDelay(TimeUnit.MICROSECONDS)) {
                return 1;
            }
            // 过期时间短的放置在队列头
            if (this.getDelay(TimeUnit.MICROSECONDS) < o.getDelay(TimeUnit.MICROSECONDS)) {
                return -1;
            }

            return 0;

        }

        public String getKey() {
            return key;
        }
    }

}
