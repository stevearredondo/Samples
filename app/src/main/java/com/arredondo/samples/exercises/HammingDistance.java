package com.arredondo.samples.exercises;

/* Created by Stephen on 5/3/2015. */
public class HammingDistance
{
    /**
     * Calculates the Hamming distance between two integers in binary, i.e. the total number of bits
     * different between <var>i1</var> and <var>i2</var>. For example:
     * <pre>
     *     hammingDist(1234,1234) = 0,
     *     hammingDist(Integer.valueOf(11001001010),Integer.valueOf(10011100010)) = 4,
     *     hammingDist(1,-1) = 31
     * </pre>
     * @param i1    One integer
     * @param i2    Another integer
     * @return The total number of bits different between <var>i1</var> and <var>i2</var> (always &ge; 0).
     */
    public static int hammingDist(int i1, int i2)
    {
        int xor = i1 ^ i2; //only different bits are 1
        int d=0; //distance
        for (int i=0; i<Integer.SIZE; i++) //right-shift xor each iteration until all bits exhausted
        {
            d += (xor>>i) & 1; //distance increases with each 1
        }
        return d;
    }

    /**
     * O(n) time calculation of the sum of the Hamming distances between each pair of values in the array.
     * @param a    An array of at least 2 integers. An {@code IllegalArgumentException} is thrown if
     *             a null array or an array with fewer than 2 elements is provided.
     *
     * @return The sum of the hamming distances between each pair of values.
     */
    public static int sumHammingDist(int[] a)
    {
        /*Arrays of 0 or 1 element don't make sense.*/
        if (a==null || a.length<2)
        {
            throw new IllegalArgumentException("Input must have at least 2 elements.");
        }

        /* Our strategy will be to partition elements of a across each bit.
         * We can count the number of 0's and 1's at each bit index and infer
         * the number of pairs of non-matching elements from the number of
         * matching pairs. For a given set of size N, the number of pairs
         * (ignoring order) will be N*(N-1)/2. If a is of length L, and at the
         * ith bit there are P 0's and Q 1's, then the number of non-matching
         * pairs of elements will be (L*(L-1)-P*(P-1)-Q*(Q-1))/2.*/

        int[] cts = new int[2]; //reusable counts of 0's and 1's
        int ll = a.length*(a.length-1); //total number of unordered pairs (doubled)
        int p,q; //reusable variables for performing arithmetic on final values of counts

        /* We always add to the distance by ll and subtract (p*(p-1) + q*(q-1)).
         * Instead, let's start with the maximum total of distances and decrease.
         * This will be equal to the total number of unordered pairs of elements
         * in a, multiplied by the number of bits over which we iterate.*/
        int d=ll*Integer.SIZE;

        /*Constant-time iteration*/
        for (int i=0; i<Integer.SIZE; i++)
        {
            /*Count 0's and 1's across the ith bit (O(n)-time iteration) */
            for (int x : a)
            {
                cts[(x >> i) & 1]++; //Shortcut: if the ith bit is 0, this increments cts[0], else cts[1] is incremented
            }
            p = cts[0]; q = cts[1]; //set temporary values
            d-= (p*(p-1) + q*(q-1)); //everything gets divided by 2, so we'll do this at the end.
            cts[0] = 0; cts[1] = 0; //reset counts
        }

        return d/2;
    }

}
