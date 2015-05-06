package com.arredondo.samples.exercises;

import android.util.Log;

import junit.framework.TestCase;

public class PrimeNumberTest extends TestCase
{

    public void testIsPrime() throws Exception
    {
        assertTrue(PrimeNumber.isPrime(149));
        assertFalse(PrimeNumber.isPrime(0));
        assertFalse(PrimeNumber.isPrime(98303));
    }

    public void testGetPrime() throws Exception
    {
        assertEquals(541, PrimeNumber.getPrime(99));
        assertEquals(7919, PrimeNumber.getPrime(999));
        assertEquals(104729, PrimeNumber.getPrime(9999));
    }

    public void testGetCachedPrime() throws Exception
    {
        assertEquals(2,PrimeNumber.getCachedPrime(0));
        assertEquals(3, PrimeNumber.getCachedPrime(1));
    }

    public void testPi() throws Exception
    {
        assertEquals(4,PrimeNumber.pi(10));
        assertEquals(25,PrimeNumber.pi(100));
        assertEquals(168,PrimeNumber.pi(1000));
    }
}