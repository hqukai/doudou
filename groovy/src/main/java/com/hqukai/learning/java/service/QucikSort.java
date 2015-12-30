package com.hqukai.learning.java.service;

/**
 * Created by hankai on 2015/12/28.
 */
public class QucikSort {


    public static void quickSort(int[] a, int s, int e) {
        if (s >= e) {
            return;
        }

        int min = a[s];  //第一个元素左右基数
        System.out.println(min);
        int index = s;

        int i = s + 1;
        int j = e;

        while (i < j) {

            while (a[i] <= min && i < j) {
                i++;
            }

            while (a[j] > min && i < j) {
                j--;
            }
            if (i >= j) {
                break;
            }

            ArrayUtils.swap(a, i, j);
            ArrayUtils.soutArray(a);

            j--;
        }
        if (a[i] < min) {
            ArrayUtils.swap(a, s, i);
        }
        ArrayUtils.soutArray(a);

        quickSort(a, s, i - 1);
        quickSort(a, i + 1, e);


//        for (int i = s + 1, j = e; i >= j; ) {
//
//            for (; a[i] > min; i++) {
//
//            }
//
//        }

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

        QucikSort.quickSort(a, 0, a.length - 1);

        ArrayUtils.soutArray(a);


    }
}
