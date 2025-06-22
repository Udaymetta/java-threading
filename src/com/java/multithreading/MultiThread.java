package com.java.multithreading;

public class MultiThread {

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread thread1 = new Thread(() -> {
            for (int i = 1 ; i<= 50 ; i++){
                counter.increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 1 ; i<= 50 ; i++){
                counter.increment();
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();// wait for thread1 to finish
        thread2.join();// wait for thread2 to finish
        System.out.println(counter.count);
    }
}
