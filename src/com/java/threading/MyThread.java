package com.java.threading;

public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread running: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        basic();
        task1();
    }

    private static void basic() {
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();
        thread1.start();
        thread2.start();
    }

    private static void task1() {
        for(int i = 0; i< 5 ; i++) {
            MyThread thread1 = new MyThread();
            thread1.start();
        }
    }

}
