package com.hqukai.learning.java.concurrency.producersAndConsumers;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by hankai on 2016/3/11.
 */
public class ProdecersAndConsumersMain {

    public static final BlockingQueue<Product> queue = new ArrayBlockingQueue<Product>(10, true);

    public static void main(String[] args) {

        Thread productThread = new Thread(() -> {
            while (true) {
                try {
                    queue.put(new Product("--"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumerThread = new Thread(() -> {
            while (true) {
                try {
                    Product p = queue.take();
//                Product p = queue.remove();
//                Product p = queue.poll();
                if (null != p) {
                    p.doSomething();
                }
                System.out.println("队列长度：" + queue.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        productThread.start();
        consumerThread.start();


    }
}
