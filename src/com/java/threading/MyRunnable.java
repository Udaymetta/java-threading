package com.java.threading;

import java.util.function.Consumer;

public class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("Runnable running: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        basic();
        task1();
    }

    private static void basic() {
        Thread thread1 = new Thread(new MyRunnable());
        Thread thread2 = new Thread(new MyRunnable());
        thread1.start();
        thread2.start();
    }

    private static void task1() {
        Consumer<Boolean> threadFunc = isEven -> {
            for (int i = Boolean.TRUE.equals(isEven) ? 2 : 1; i <= 10; i+=2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {
                }
                System.out.println(Thread.currentThread().getName() + " -> " + i);
            }
        };
      new Thread(() -> threadFunc.accept(true)).start();
      new Thread(() -> threadFunc.accept(false)).start();
    }
}
