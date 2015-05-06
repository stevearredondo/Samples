package com.arredondo.samples.exercises;

import android.util.Log;

import junit.framework.TestCase;

import java.util.Random;

public class HammingDistanceTest extends TestCase
{

    public void testHammingDist() throws Exception
    {
        assertEquals(0, HammingDistance.hammingDist(0,0));
        assertEquals(0, HammingDistance.hammingDist(12345,12345));
        assertEquals(Integer.SIZE-1, HammingDistance.hammingDist(Integer.MAX_VALUE,0));
        assertEquals(Integer.SIZE-1, HammingDistance.hammingDist(-1,1));
        int i1 = Integer.valueOf("11001001010",2); int i2 = Integer.valueOf("10011100010",2);
        assertEquals(4, HammingDistance.hammingDist(i1,i2));
    }

    public void testSumHammingDist() throws Exception
    {
        int[] a = new int[20];
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < a.length; i++)
        {
            a[i] = (int) r.nextLong();
        }

        int d1=0;
        for (int i=0; i < a.length-1; i++)
        {
            for (int j = i+1; j < a.length; j++)
            {
                d1+= HammingDistance.hammingDist(a[i],a[j]);
            }
        }

        int d2 = HammingDistance.sumHammingDist(a);

        assertEquals(d1, d2);

        Log.i("HammingDistanceTest", "sumHammingDist(a) = "+d2);
    }
}