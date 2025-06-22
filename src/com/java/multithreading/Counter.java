package com.java.multithreading;

public class Counter {
    int count;

    public synchronized void syncIncrement(){
        try {
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName());
            count++;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void increment(){
        try {
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName());
            count++;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
