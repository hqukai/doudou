package com.hqukai.learning.java.service;

/**
 * Created by hankai on 2015/12/28.
 */
public class QucikSort {

    public static void soutArray(int[] a) {
        System.out.println();

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
        }
        System.out.println();
    }

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

            swap(a, i, j);
            soutArray(a);

            j--;
        }
        if (a[i] < min) {
            swap(a, s, i);
        }
        soutArray(a);

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

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;

    }
}
