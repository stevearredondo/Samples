package com.arredondo.samples.exercises;

/* Created by Stephen on 1/27/2015. */
public class StringReverse
{
    /**
     * Reverses the given string. Preserves HTML entities of the form '&...;'
     * @param s    The string to reverse
     * @return The reversed string, with any HTML entities preserved.
     */
    public static String reverse(String s)
    {
        int l = s.length();
        char[] result = new char[l];
        for (int i = 0; i < l; i++)
        {
            char c = s.charAt(i);
            if (c=='&') //start of an HTML entity
            {
                //get the length of the entity. Already know first char is '&'
                int j=1;
                do
                {
                    j++;
                } while (s.charAt(i+j-1)!=';');
                //Add the entity to the result in order
                for (int k=0; k < j; k++)
                {
                    result[(l-1)-(i+(j-1)-k)]=s.charAt(i+k);
                }
                //Bump index ahead of entity
                i+=j-1;
            }
            else
            {
                result[(l-1)-i] = c;
            }
        }

        return new String(result);
    }

}
