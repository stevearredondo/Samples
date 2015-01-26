package com.arredondo.samples.sorting;
/* Created by Stephen on 12/1/2014. */

import java.util.ArrayList;

/**
 * This class contains only static methods to perform a merge sort on an array
 * whose base type implements the {@link java.lang.Comparable} interface.
 */
public class MergeSort
{
    /**
     * Performs a merge sort on all of {@code a}.
     * Equivalent to {@code sort(a, 0, a.length-1)}.
     * @param a      The array to sort.
     * @param <T>    Base type of array {@code a} that implements {@link java.lang.Comparable}
     * @see #sort(Comparable[], int, int)
     */
    public static <T extends Comparable<T>> void sort(T[] a)
    {
        sort(a,0,a.length-1);
    }

    /**
     * Sorts subarray of {@code a} from indices {@code lo} to {@code hi}, inclusive.
     * @param a      Non-null array to be sorted
     * @param lo     Lower bound of array indices to sort
     * @param hi     Upper bound of array indices to sort
     * @param <T>    Base type of array {@code a} that implements {@link java.lang.Comparable}
     * @throws IllegalArgumentException if the input array is null.
     */
    public static <T extends Comparable<T>> void sort(T[] a, int lo, int hi)
            throws IllegalArgumentException
    {
        if (a==null)
            throw new IllegalArgumentException("Input array must be non-null of positive length.");

        if (a.length==0) return;

        //Make sure that there is at least one element in a from lo to hi
        if (lo < hi)
        {
            int md = (hi-lo)/2;
            sort(a, lo, md);
            sort(a, md + 1, hi);
            merge(a, lo, md, hi);
        }
    }

    /**
     * Merges pre-sorted subarrays of {@code a} ({@code [lo->md]} and
     * {@code [md+1->hi]}) using {@link java.lang.Comparable#compareTo(Object)}.
     * @param a      Array of values to sort.
     * @param lo     First index of first subarray of {@code a}.
     * @param md     Last index of first subarray of {@code a}. {@code md+1} is
     *               the first index of the second subarray of {@code a}.
     * @param hi     Last index of the second subarray of {@code a}.
     * @param <T>    Base type implementing {@link java.lang.Comparable} interface
     */
    private static <T extends Comparable<T>> void merge(T[] a, int lo, int md, int hi)
    {
        /*Cannot directly instantiate T: unable to say T[] b = new T[hi-lo+1]*/
        ArrayList<T> b = new ArrayList<T>(hi-lo+1);

        /*Iteration variables*/
        int     i = lo, // index through subarray a[lo : md]
                j = md, // index through subarray a[md+1 : hi]
                k = 0,  // index through b
                l;      // multiple use iterative variable

        /* Compare elements of subarrays and populate b
         * until one of the subarrays is exhausted. */
        while (i <= md && j <= hi)
        {
            if (a[i].compareTo(a[j]) <= 0) //a[i] less or equal to a[j]
            {
                b.add(k,a[i]);
                i++;
            }
            else //a[i] greater than a[j]
            {
                b.add(k,a[j]);
                j++;
            }
            k++;
        }
        if (i > md) //lo through md exhausted. Continue filling in with remaining elements of [md+1 -> hi]
        {
            for (l=j; l<=hi; l++)
            {
                b.add(k,a[l]);
                k++;
            }
        }
        else //md through hi exhausted. Continue filling in with remaining elements of [lo -> md]
        {
            for (l=i; l<=md; l++)
            {
                b.add(k,a[l]);
                k++;
            }
        }
        /*Copy b (sorted) into a*/
        for (k=0; k<b.size(); k++)
        {
            a[k+lo] = b.get(k);
        }
    }
}