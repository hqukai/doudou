package com.hqukai.learning.java.algorithm;

/**
 * Created by hankai on 2015/12/28.
 */
public class ArrayUtils {

    public static int[] a = {3, 2, 3, 5, 1, 2, 3};

    public static void soutArray(int[] a) {
        System.out.println();

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
        }
        System.out.println();
    }


    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;

    }

    public final static void main(String[] args) {

        String a = "1";
        String b = "2";
        System.out.println(b = a);

    }
}
