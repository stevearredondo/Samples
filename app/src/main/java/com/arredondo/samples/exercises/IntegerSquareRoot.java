package com.arredondo.samples.exercises;

/* Created by Stephen on 1/26/2015. */
public class IntegerSquareRoot
{
    /**
     * Computes the integer square root of <var>n</var> using a binary search.
     * @param n    A positive integer.
     * @return The integer (floor of the) square root of <var>n</var>
     * @throws java.lang.IllegalArgumentException if n &le; 0
     */
    public static int isqrt(int n)
    {
        if (n <= 0) throw new IllegalArgumentException("Input must be a positive integer.");
        if (n == 1) return 1;
        int lb = 0, ub = n/2 + 1; //lower and upper bounds for guessing range. Sqrt(n) <= n/2 for all n>1.
        int guess = lb + (ub-lb)/2; //let guess be the midpoint between ub and lb
        //Check (guess+1)^2 <= n < guess^2. Cast as guess as long to avoid (int^2 < 0) if n is very large.
        while ((long)guess*guess > n || ((long)guess+1)*(guess+1) <= n)
        {
            if ((long)guess*guess > n) //too high
            {
                ub = guess; //set upper bound as this guess
                guess = lb + (ub-lb)/2;
            }
            else if (((long)guess+1)*(guess+1) < n) //too low
            {
                lb = guess; //set lower bound as this guess
                guess = lb + (ub-lb)/2;
            }
            else //(guess+1)^2 == n.
            {
                return guess+1;
            }
        }

        return guess;
    }

}
