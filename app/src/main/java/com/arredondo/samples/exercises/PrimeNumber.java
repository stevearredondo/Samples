package com.arredondo.samples.exercises;

import android.util.SparseArray;

/* Created by Stephen on 2/25/2015. */
public class PrimeNumber
{
    /**
     * Determines whether the input is prime.
     * @param n    An integer. Values less than 2 will yield false.
     * @return True if <var>n</var> is prime, false otherwise.
     */
    public static boolean isPrime(long n)
    {
        if (n < 2) return false; //2 is smallest prime. Don't count negatives
        if (n % 2==0) return true; //n is even
        long p=0;
        int j;
        /*Check divisibility by cached list of primes.*/
        for (j=1; j <= sPrimeIndex; j++)
        {
            p = sPrimes.get(j);
            if (n % p==0) return false;
        }

        /*Exhausted cached list of primes. Go up to n/2, since p > n/2 will be redundant.
        * We can skip evens, since n is odd.*/
        for (p+=2; p <= n/2; p+=2)
            if (n % p==0) return false;
        return true;
    }

    /**
     * Computes {@code &pi;(x)}, i.e. the number of primes less or equal to <var>x</var>
     * @param x    An integer. Values less than 2 will yield 0.
     * @return The number of primes less or equal to x
     */
    public static int pi(int x)
    {
        if (x < 2) return 0; //no primes less than 2
        int ct=1;
        long p=3;
        /*First count cached primes less or equal to x*/
        for (; ct<= sPrimeIndex; ct++)
        {
            if ((p=sPrimes.get(ct)) > x) return ct;
        }
        /*Cache exhausted. Check primes the hard way.*/
        while (p<=x)
        {
            if (isPrime(p)) ct++;
            p+=2;
        }
        return ct;
    }

    /**
     * @param n    An integer greater than or equal to 0. Negative values will result in an
     *             {@link IllegalArgumentException}.
     * @return The <var>n</var>th prime number. For example, {@code getPrime(0) = 2},
     * {@code getPrime(1) = 3}, and so on.
     */
    public static long getPrime(int n)
    {
        if (n < 0) throw new IllegalArgumentException("Input must be non-negative.");

        long p = sPrimes.get(n, 0l);
        if (p!=0) return p;

        if (n==0) return 2; //special case of 0th prime = 2 (even)

        /*Start at current prime (odd) and increment by 2 to skip evens*/
        p = sPrimes.get(sPrimeIndex);
        while (sPrimeIndex < n)
        {
            p+=2;
            if (isPrime(p))
            {
                sPrimes.put(++sPrimeIndex,p);
            }
        }
        return p;
    }

    private static SparseArray<Long> sPrimes = new SparseArray<Long>(32);
    private static int sPrimeIndex=0;

    static
    {
        sPrimes.put(0,2l);
        sPrimes.put(1,3l);
        sPrimeIndex=1;
        for (int i=2; i < 32; i++)
        {
            getPrime(i);
        }
    }

    public static long getCachedPrime(int i)
    {
        return sPrimes.get(i);
    }

    public static long[] getCachedPrimes()
    {
        long[] res = new long[sPrimes.size()];
        for (int i=0; i<res.length; i++)
        {
            res[i] = sPrimes.get(i);
        }
        return res;
    }
}
