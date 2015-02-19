package com.arredondo.samples.exercises;

import junit.framework.TestCase;

public class IntegerSquareRootTest extends TestCase
{
    public void testGoodValues() throws Exception
    {
        int[] arr = new int[]{1, 2, 4, 10, 16, Integer.MAX_VALUE};
        for (int n : arr)
            assertEquals("n=" + n, (int) Math.floor(Math.sqrt((double) n)), IntegerSquareRoot.isqrt(n));
    }

    public void testBadValues() throws Exception
    {
        int[] arr = new int[]{Integer.MIN_VALUE, -1, 0};
        boolean exceptionThrown;
        for (int n : arr)
        {
            exceptionThrown = false;
            try
            {
                IntegerSquareRoot.isqrt(n);
            }
            catch (IllegalArgumentException e)
            {
                exceptionThrown = true;
            }
            finally
            {
                assertTrue("Exception not thrown. n="+n, exceptionThrown);
            }
        }
    }
}