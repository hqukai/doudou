package com.hqukai.learning.java;


/**
 * Created by hankai on 2015/12/28.
 */
public class Main {
    public static void main(String args[]) {
//        System.out.println("Gradle hello");
//        LocalTime currentTime = new LocalTime();
//        System.out.println("now time is:" + currentTime);
//
//        TestService testService = new TestService();
//        testService.test();


//        int[] a = {3, 2, 3, 5, 1, 2, 3};


//        int[] a = {2, 1, 1};
//        ArrayUtils.soutArray(a);
//
//        QucikSort.quickSort(a, 0, a.length - 1);
//
//        ArrayUtils.soutArray(a);

        int h = 0;
        Main m = new Main();

        int x = m.hashCode();
        System.out.println(x);

        int r = (h = m.hashCode()) ^ (h >>> 16);
        System.out.println(r);



    }


}
