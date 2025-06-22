package com.java.future;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.stream.IntStream;

public class CompleteFuture {
    public static void main(String[] args) {
        Executor executor = ThreadPool.taskExecutor();
        multipleFutures(executor);
        applyFutures(executor);
        runFutures(executor);
        ExecutorService executorService = (ExecutorService) executor;
        executorService.shutdown();
    }

    private static void multipleFutures(Executor executor) {
        List<CompletableFuture<String>> completableFuture = IntStream.range(0, 100).mapToObj(i -> CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100);
                return "Task Id -> " + i + " running by " + Thread.currentThread().getName() + " at "+ LocalDateTime.now();
            }catch (Exception e){
                throw new RuntimeException();
            }
        }, executor)).toList();
        List<String> result = completableFuture.stream()
                .map(CompletableFuture::join)
                .toList();
        result.forEach(System.out::println);
    }

    private static void applyFutures(Executor executor) {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()-> "Hello", executor).thenApply(x->x+" Apply").thenApply(y-> y+" world");
        System.out.println(completableFuture.join());
    }

    private static void runFutures(Executor executor) {
        CompletableFuture.runAsync(() -> System.out.print("Hello"), executor).thenRunAsync(()-> System.out.print(" Run"), executor).thenRun(() -> System.out.print(" world"));
    }
}
