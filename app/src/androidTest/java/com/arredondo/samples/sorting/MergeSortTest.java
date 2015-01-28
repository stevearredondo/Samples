package com.arredondo.samples.sorting;

import junit.framework.TestCase;

import java.util.Arrays;

public class MergeSortTest extends TestCase
{

    public void testSort() throws Exception
    {
        Integer[] a = new Integer[]{2,3,1,5,7,7,5,1,9,5,5,2,6,8,1};
        Integer[] b = new Integer[a.length];
        System.arraycopy(a,0,b,0,b.length);
        MergeSort.sort(a);
        Arrays.sort(b);
        assertTrue(Arrays.equals(a,b));
    }
}