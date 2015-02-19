package com.arredondo.samples.exercises;

/* Created by Stephen on 1/27/2015. */
public class StringReverse
{
    /**
     * Reverses the given string. Preserves HTML entities of the form {@code '&...;'}.
     * This method assumes that the character {@code '&'} is always the start of an entity
     * and therefore eventually followed by {@code ';'}. An exception is thrown if the entity
     * does not terminate.
     * @param s    The string to reverse
     * @return The reversed string, with any HTML entities preserved.
     * @throws java.lang.NullPointerException if input is null
     * @throws java.lang.IllegalArgumentException if a non-terminating entity is encountered.
     */
    public static String htmlReverse(String s)
    {
        int l = s.length();
        StringBuilder sb = new StringBuilder(l);
        char c;
        for (int i = 0; i < l; i++)
        {
            if ((c=s.charAt(i))=='&') //start of an HTML entity
            {
                //get the index in the entity of the terminating character ';'. Already know first char is '&'
                int j=0;
                try
                {
                    do
                    {
                        j++;
                    } while (s.charAt(i+j)!=';');
                } catch (IndexOutOfBoundsException e) //entire string was exhausted with no ';' found
                {
                    throw new IllegalArgumentException("Non-terminating HTML entity at index ("+i+").");
                }
                //Add entity substring in reverse
                sb.append(new StringBuilder(s.substring(i,i+j+1)).reverse());

                //Bump i ahead by the index j of the ';' so that i++ puts it after entity substring.
                i+=j;
            }
            else //Not the start of an entity. Just append.
            {
                sb.append(c);
            }
        }

        //Reverse the StringBuilder so that entity reversal is undone and non-entities are reversed.
        return sb.reverse().toString();
    }

    /**
     * Simply reverses the given string.
     * @param s    A non-null string.
     * @return The reversed string.
     * @throws java.lang.NullPointerException if input is null
     */
    public static String reverse(String s)
    {
        return new StringBuilder(s).reverse().toString();
    }
}
