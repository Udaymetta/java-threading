package com.java.threading;

public class MyExtraThread extends Thread {
    int count = 10;
    boolean isEven = true;

    @Override
    public void run() {
        for (int i = isEven ? 2 : 1; i <= count; i += 2) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {
            }
            System.out.println(getName() + " -> " + i);
        }
    }

    public static void main(String[] args) {
        task1();
    }

    private static void task1() {
        MyExtraThread myExtraThread1 = new MyExtraThread();
        myExtraThread1.start(); // prints even numbers

        MyExtraThread myExtraThread2 = new MyExtraThread();
        myExtraThread2.isEven = false; // prints odd numbers
        myExtraThread2.start();
    }
}