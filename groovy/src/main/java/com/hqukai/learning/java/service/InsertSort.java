package com.hqukai.learning.java.service;

/**
 * Created by hankai on 2015/12/28.
 */
public class InsertSort {


    /**
     * 快速排序
     *
     * @param a
     */
    public static void insertSort(int[] a) {

        int b[];
        for (int i = 1; i < a.length; i++) {

            int t = a[i];
            int j = i - 1;
            for (; j >= 0 && t < a[j]; j--) {
                a[j + 1] = a[j];
            }
            a[j + 1] = t;
        }
    }



    public static void main(String args[]) {
        ArrayUtils.soutArray(ArrayUtils.a);
        InsertSort.insertSort(ArrayUtils.a);
        ArrayUtils.soutArray(ArrayUtils.a);

    }
}
