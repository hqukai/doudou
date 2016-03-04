package com.hqukai.learning.java.concurrency;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hankai on 2016/2/18.
 */
public class LockExample {


    public static void main(String[] args) throws InterruptedException {
        Pa pa = new Pa();
        pa.list = new ArrayList<>();
        int l = 5;
        Lock lock = new ReentrantLock();

        new Thread(() -> {
            System.out.println("线程执行");


            lock.lock();
            try {


                for (int i = 0; i < l; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pa.list.add(String.valueOf(i));
                }
            } finally {
                lock.unlock();
            }

        }).start();

        Thread.sleep(1000);

//        lock.lock();
        try {
            for (int j = 0; j < 7; j++) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(pa.list.size());
                System.out.println(pa.list.toString());
            }
        } finally {
//            lock.unlock();
        }

    }

}

