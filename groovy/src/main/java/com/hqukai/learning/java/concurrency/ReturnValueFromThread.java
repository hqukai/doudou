package com.hqukai.learning.java.concurrency;

import java.util.ArrayList;

/**
 * Created by hankai on 2016/2/22.
 */
public class ReturnValueFromThread {


    public static void main(String[] args) throws InterruptedException {
        Pa pa = new Pa();
        pa.list = new ArrayList<>();
        int l = 5;

        final Integer a = 0;

        new Thread(() -> {
            System.out.println("线程1执行");
            synchronized (pa) {

                for (int i = 0; i < l; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    pa.list.add(String.valueOf(i));
                }

            }

        }).start();

        new Thread(() -> {
            System.out.println("线程2执行");
//            synchronized (pa) {

                for (int i = 0; i < l; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    pa.list.add(String.valueOf(i + "a"));
                }

//            }

        }).start();

        Thread.sleep(1000);
//        synchronized (pa) {

        pa.list.add("test");

        for (int j = 0; j < 12; j++) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(pa.list.size());
            System.out.println(pa.list.toString());
        }
//        }

    }


}


