package com.arredondo.samples.exercises;

/* Created by Stephen on 2/25/2015. */
public class PrimeNumber
{
    /**
     * Determines whether the input is prime.
     * @param n    An integer. Values less than 2 will yield false.
     * @return True if <var>n</var> is prime, false otherwise.
     */
    public static boolean isPrime(int n)
    {
        if (n < 2) return false; //2 is smallest prime. Don't count negatives
        if (n % 2==0) return true; //n is even
        /*Go up to n/2, since i>n/2 will be redundant. We can skip evens, since n is odd.
        * Generally, the best approach would be to iterate through the primes up to n/2.*/
        for (int i=3; i <= n/2; i+=2)
            if (n % i==0) return false;
        return true;
    }

    /**
     * Computes {@code &pi;(x)}, i.e. the number of primes less or equal to <var>x</var>
     * @param x    An integer. Values less than 2 will yield 0.
     * @return The number of primes less or equal to x
     */
    public static int pi(int x)
    {
        int ct=0;
        if (x>=2) ct++; //2 is the only even prime, so check this case first
        int n=3;
        while (n<=x)
        {
            if (isPrime(n)) ct++;
            n+=2; //increment n to the next odd number, since no evens from here will be prime
        }
        return ct;
    }

    /**
     * @param n    An integer greater than or equal to 0. Negative values will result in an
     *             {@link java.lang.IllegalArgumentException}.
     * @return The <var>n</var>th prime number. For example, {@code getPrime(0) = 2},
     * {@code getPrime(1) = 3}, and so on.
     */
    public static int getPrime(int n)
    {
        if (n < 0) throw new IllegalArgumentException("Input must be non-negative.");

        if (n==0) return 2; //special case of 0th prime = 2 (even)
        int c = 0;
        int p = 1; //start at 1 and increment by 2 to skip evens
        while (c < n)
        {
            p+=2;
            if (isPrime(p))
            {
                c++;
            }
        }
        return p;
    }

}
