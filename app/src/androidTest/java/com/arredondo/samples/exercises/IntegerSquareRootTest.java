package com.arredondo.samples.exercises;

import junit.framework.TestCase;

import java.util.Random;

public class IntegerSquareRootTest extends TestCase
{

    public void testIntegerSquareRoot() throws Exception
    {
        Random r = new Random(System.currentTimeMillis());
        int n;
        for (int i=0; i<10; i++)
        {
            //Ensure test values are positive
            do
            {
                n = r.nextInt();
            }
            while (n<=0);

            assertEquals("n="+n ,(int) Math.floor(Math.sqrt((double) n)), IntegerSquareRoot.isqrt(n));
        }
    }
}