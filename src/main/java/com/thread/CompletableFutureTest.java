package com.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        String xing = "chen";
        CompletableFuture.supplyAsync(() -> xing).thenApply(a -> a + " ze")
                .thenApply(a -> a + " xin").thenAccept(System.out::println);

//        CompletableFuture<String> future = new CompletableFuture<>();

        CompletableFuture.supplyAsync(() -> 10 / 1).exceptionally(e -> {e.printStackTrace(); return 1;})
                .thenAccept(System.out::print);

//        CompletableFuture.supplyAsync(() -> 1).thenCombine(CompletableFuture.supplyAsync(() -> 2),(i,j) -> i + j)
//                .thenAccept(System.out::println);

    }
}
