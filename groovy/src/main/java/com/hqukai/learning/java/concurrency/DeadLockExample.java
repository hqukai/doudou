package com.hqukai.learning.java.concurrency;

/**
 * Created by hankai on 2016/2/18.
 */
public class DeadLockExample {


    synchronized public static void lock1() {
        System.out.println("I am lock1");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock2();
    }

    synchronized public static void lock2() {
        System.out.println("I am lock2");
        lock1();
    }

    public static void main(String[] args) {

        new Thread(() -> {
            System.out.println("Thread 1");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock1();

        }).start();

        new Thread(() -> {
            System.out.println("Thread 2");
            lock2();

        }).start();

//        System.identityHashCode();

    }

}

