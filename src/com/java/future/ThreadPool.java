package com.java.future;

import java.util.concurrent.*;

public class ThreadPool {

    public static Executor taskExecutor(){
        return new ThreadPoolExecutor(
                3,
                5,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>()
        );

    }
}
