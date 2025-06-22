package com.java.executor;

import java.time.LocalDateTime;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        singleThread();
        fixedThreadPool();
        cachedThreadPool();
        scheduledSingleThread();
        scheduledThreadPool();
    }

    /**
     * One thread, tasks run sequentially
     */
    private static void singleThread() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            Integer taskId = i;
            executorService.execute(() -> System.out.println("Task Id -> " + taskId + " running by " + Thread.currentThread().getName()));
        }
        executorService.shutdown();
    }

    /**
     * Fixed-size pool of n threads
     */
    private static void fixedThreadPool() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 15; i++) {
            int taskId = i;
            executorService.execute(() -> System.out.println("Task Id -> " + taskId + " running by " + Thread.currentThread().getName()));

            Future<String> future = executorService.submit(()-> "Task Id -> " + taskId + " running by " + Thread.currentThread().getName());
            System.out.println(future.get());
        }
        executorService.shutdown();
    }

    /**
     * Dynamic size, grows as needed
     */
    private static void cachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 150; i++) {
            Integer taskId = i;
            executorService.execute(() -> System.out.println("Task Id -> " + taskId + " running by " + Thread.currentThread().getName()));
        }
        executorService.shutdown();
    }

    /**
     * single background thread to schedule and execute tasks after a delay or repeatedly at fixed intervals.
     */
    private static void scheduledSingleThread() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

//      Run once after delay
        scheduler.schedule(() -> System.out.println(Thread.currentThread().getName() + " running at " + LocalDateTime.now()),2, TimeUnit.SECONDS);

//      Fixed interval (start time-based)
        scheduler.scheduleAtFixedRate(() -> System.out.println(Thread.currentThread().getName() + " running at " + LocalDateTime.now()), 0, 10, TimeUnit.SECONDS);
    }

    /**
     * Creates a thread pool that can schedule commands to run after a given delay, or to execute periodically.
     */
    private static void scheduledThreadPool() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
        AtomicInteger count = new AtomicInteger();
        Runnable task = () -> {
                count.addAndGet(1);
                System.out.println(count.get()+" -> " + Thread.currentThread().getName() + " running at " + LocalDateTime.now());
                if(count.get()==10)
                    scheduler.shutdown();
        };

//      Run once after delay
        scheduler.schedule(task,2, TimeUnit.SECONDS);

//      Fixed interval (start time-based)
        scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);

    }
}