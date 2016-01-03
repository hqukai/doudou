package com.hqukai.learning.java.algorithm;

/**
 * Created by hankai on 2015/12/28.
 */
public class SelectSort {


    public static void sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            int j = i + 1;
            for (; j < a.length; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }

            int t1 = a[min];
            a[min] = a[i];
            a[i] = t1;
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
        ArrayUtils.soutArray(a);

        SelectSort.sort(a);

        ArrayUtils.soutArray(a);

        ArrayUtils.soutArray(ArrayUtils.a);
        SelectSort.sort(ArrayUtils.a);
        ArrayUtils.soutArray(ArrayUtils.a);


    }
}
