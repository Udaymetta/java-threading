package com.java.multithreading;

public class Counter {
    int count;

    public synchronized void increment(){
        System.out.println(Thread.currentThread().getName());
        count++;
    }
}
