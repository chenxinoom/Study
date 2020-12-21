package com.thread;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: why技术
 * @Date: 2019/9/6 22:25
 */
public class ExecutorsTest {

    public static void main(String[] args) {
        ThreadPoolTaskExecutor executorService = buildThreadPoolTaskExecutor();
//        executorService.execute(() -> sayHi("execute"));
        executorService.execute(() -> sayHi("execute"));
        executorService.submit(() -> sayHi("submit"));
        executorService.submit(() -> sayHi("submit"));
    }

    private static void sayHi(String name) {
        String printStr = "【thread-name:" + Thread.currentThread().getName() + ",执行方式:" + name+"】";
        System.out.println(printStr);
//        throw new RuntimeException(printStr + ",我异常啦!哈哈哈!");
    }

    private static ThreadPoolTaskExecutor buildThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executorService = new ThreadPoolTaskExecutor();
        executorService.setThreadNamePrefix("(公众号-why技术)-");
        executorService.setCorePoolSize(5);
        executorService.setMaxPoolSize(10);
        executorService.setQueueCapacity(1000);
        executorService.setKeepAliveSeconds(30);
        executorService.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executorService.initialize();
        return executorService;
    }


}