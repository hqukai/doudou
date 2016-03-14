package com.hqukai.learning.java.concurrency.producersAndConsumers;

/**
 * Created by hankai on 2016/3/11.
 */
public class Product {
    private String name;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static int i = 0;

    public Product(String name) {
        this.name = name + i++;
        System.out.println("生产" + this.name + "--p");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void doSomething() {

        System.out.println("消费" + this.getName() + "--c");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
