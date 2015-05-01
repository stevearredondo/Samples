package com.arredondo.samples.sorting;

/* Created by Stephen on 12/1/2014. */


import com.arredondo.samples.util.ArrayUtils;

public class QuickSort
{
    /**
     * Performs an in-place quick-sort on all of <var>a</var>.
     * @param a      The array to sort
     * @param <T>    The base type of the array. This must implement {@link java.lang.Comparable}
     * @see #sort(Comparable[], int, int)
     */
    public static <T extends Comparable<T>> void sort(T[] a)
    {
        sort(a, 0, a.length-1);
    }

    /**
     * Performs an in-place quick-sort on the subarray of <var>a</var> from indices <var>lo</var>
     * to <var>hi</var>, inclusive. This is done by obtaining the partition index obtained from
     * {@link #partition(Comparable[], int, int)} and sorting the subarrays <var>a[lo,p-1]</var>
     * and <var>a[p+1,hi]</var>.
     * @param a      An array of {@link java.lang.Comparable} values
     * @param lo     The lower index defining the subarray of <var>a</var> to sort
     * @param hi     The higher index defining the subarray of <var>a</var> to sort
     * @param <T>    The base type of the array. This must implement {@link java.lang.Comparable}
     */
    public static <T extends Comparable<T>> void sort(T[] a, int lo, int hi)
    {
        if (lo < hi)
        {
            int p = partition(a, lo, hi); //partition index
            sort(a, lo, p-1);
            sort(a, p+1, hi);
        }
    }

    /**
     * Finds a pivot in the subarray of <var>a</var> from indices <var>lo</var> to <var>hi</var>,
     * inclusive. This pivot is initially moved to index <var>hi</var>. Remaining elements of
     * <var>a</var> are swapped such that elements less than the pivot come before it, and those
     * greater come after the pivot.
     * @param a      An array of {@link java.lang.Comparable} values
     * @param lo     The lower index defining the subarray of <var>a</var> to partition
     * @param hi     The higher index defining the subarray of <var>a</var> to partition
     * @param <T>    The base type of the array. This type must implement {@link java.lang.Comparable}.
     * @return The index of the pivot after all values greater than it have been moved after it.
     * @see #getPivotIndex(Comparable[], int, int)
     */
    public static <T extends Comparable<T>> int partition(T[] a, int lo, int hi)
    {
        int pivotIndex = getPivotIndex(a, lo, hi);
        T pivot = a[pivotIndex];

        //Put the pivotIndex at the end of a[lo:hi] (get it out of the way from swaps below)
        ArrayUtils.swap(a, pivotIndex, hi); //pivot == a[hi]

        int newPivotIndex = lo; //starting index for final pivot position

        //Compare remaining array elements against pivot, now at a[hi]
        for (int i=lo; i < hi; i++)
        {
            /* If a[i] is less than the pivot, swap that element and the element at newPivotIndex,
             * incrementing newPivotIndex with each swap. */
            if (a[i].compareTo(pivot) < 0)
            {
                ArrayUtils.swap(a, i, newPivotIndex);
                newPivotIndex++;
            }
        }

        //Move pivot to final place
        ArrayUtils.swap(a, newPivotIndex, hi);
        return newPivotIndex;
    }

    /**
     * Gets a pivot index for the array <var>a</var> from indices <var>lo</var> to <var>hi</var>,
     * inclusive. Currently, this is simply the average of <var>lo</var> and <var>hi</var>.
     * @param a      An array of values.
     * @param lo     The lower index defining the subarray of <var>a</var> in question
     * @param hi     The higher index defining the subarray of <var>a</var> in question
     * @param <T>    The base type of the array. This must implement {@link java.lang.Comparable}
     * @return The pivot index. Currently this is the average of <var>lo</var> and <var>hi</var>
     */
    @SuppressWarnings("unused")
    public static <T extends Comparable<T>> int getPivotIndex(T[] a, int lo, int hi)
    {
        return lo + (hi-lo)/2;
    }

}
