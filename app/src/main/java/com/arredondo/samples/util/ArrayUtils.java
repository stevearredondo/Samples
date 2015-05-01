package com.arredondo.samples.util;

import com.arredondo.samples.sorting.MergeSort;
import com.arredondo.samples.sorting.QuickSort;

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

    /**
     * Performs a quick-sort of <var>a</var>.
     * @param a      The array to sort
     * @param <T>    The base type of the array
     */
    public static <T extends Comparable<T>> void qsort(T[] a)
    {
        QuickSort.sort(a);
    }

    /**
     * Performs a merge-sort of <var>a</var>.
     * @param a      The array to sort
     * @param <T>    The base type of the array
     */
    public static <T extends Comparable<T>> void msort(T[] a)
    {
        MergeSort.sort(a);
    }
}
