package com.hqukai.learning.java.algorithm;

/**
 * Created by hankai on 2015/12/28.
 */
public class BubbleSort {


    public static void sort(int[] a) {
        int l = a.length;
        int t;
        for (int i = 0; i < l - 1; i++) {
            for (int j = 0; j < l - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    t = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = t;
                }
            }
        }

    }


    public static void main(String args[]) {
//        System.out.println("Gradle hello");
//        LocalTime currentTime = new LocalTime();
//        System.out.println("now time is:" + currentTime);
//
//        TestService testService = new TestService();
//        testService.test();


//        int[] a = {3, 2, 3, 5, 1, 2, 3};


        int[] a = {2, 1, 1};
        ArrayUtils.soutArray(ArrayUtils.a);

        BubbleSort.sort(ArrayUtils.a);

        ArrayUtils.soutArray(ArrayUtils.a);


    }
}
