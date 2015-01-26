package com.arredondo.samples.util;

/* Created by Stephen on 1/25/2015. */
public class ArrayUtils
{
    /**
     * Swaps the elements of <var>a</var> at indices <var>i</var> and <var>j</var>.
     * @param a      An array of values
     * @param i      The index of on value to swap
     * @param j      The index of another value to swap
     * @param <T>    The base type of the array
     */
    public static <T> void swap(T[] a, int i, int j)
    {
        T tmp = a[j];
        a[j] = a[i];
        a[i] = tmp;
    }
}
