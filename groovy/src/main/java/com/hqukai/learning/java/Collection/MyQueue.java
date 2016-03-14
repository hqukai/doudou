package com.hqukai.learning.java.Collection;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by hankai on 2016/3/2.
 */
public class MyQueue {

    public static void main(String[] args) {
        testQueue();
    }


    public static void testQueue() {



        Queue<String> queue = new LinkedList<>();
        System.out.println(queue.poll());  // poll 空队列则返回null
        queue.remove();   // remove 空队列抛异常
        boolean b = queue.add("1");  //如果队列已满，则抛异常
        b = queue.offer("2");
        String s = queue.peek();  //  返回对头元素，如果为空则放回null
        System.out.println(queue);

        s = queue.poll();  //返回并删除队头元素，如果为空则，返回null

        s = queue.remove();  // 返回并删除队头元素，如果为空，则抛出异常

        s = queue.element();  //  返回对头元素，如果为空，则抛出异常



//        Thread


    }

}
