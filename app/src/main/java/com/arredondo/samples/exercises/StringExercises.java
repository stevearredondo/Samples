package com.arredondo.samples.exercises;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* Created by Stephen on 1/27/2015. */
public class StringExercises
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
    public static String htmlReverseNoRegex(String s)
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
     * Reverses <var>s</var> while preserving HTML entities. For example, "I like &amp;pi;"
     * yields "&amp;pi; ekil I".
     * <p>
     *     Unlike {@link #htmlReverseNoRegex(String)}, this method will not throw an exception
     *     if an incomplete HTML entity is added. For example, "&amp;theta" would simply be
     *     treated as a normal string.
     * </p>
     * <p>
     *     This method considers an HTML entity to be of the form {@code &amp;X;}, where
     *     {@code X} can be a sequence containing alphanumeric characters and/or the '#'
     *     character, up to 10 characters in length. For example, "&amp;sigma;" and
     *     "&amp;#0045;" would be treated as entities, but "&amp;asdfasdfasdf;" would not.
     * </p>
     * @param s    The string to reverse
     * @return The reversed string with HTML entities preserved, or null if <var>s</var> is null.
     */
    public static String htmlReverseWithRegex(String s)
    {
        if (s==null || s.isEmpty()) return s;

        StringBuilder sb = new StringBuilder(s.length()); //main string builder
        StringBuilder nonEntity = new StringBuilder(); //used to reverse non-html-entity strings

        /*Split s using look-ahead and look-behind regex, where the split method's delimiter
        * is the html entity. We're looking for strings that start with an '&', have some
        * non-empty sequence of alphanumeric characters and potentially a '#', and end with ';'.
        * We'll assume the body of the entity has a maximum length of 10 so the look-behind
        * group will have an explicit maximum (Java does not support variable length in look-behind).
        * So we want "&[#\\w]+{1,10};". To capture the entity as a result element in
        * String.split(), we want to select an empty character before (?<=X) or after (?=X)
        * the entity as a delimiter, so we use the following regex pattern: "((?<=X)|(?=X))". */

        String entityRegex = "&[#\\w]{1,10};";

        /*Extracted string format pattern to improve readability. This is the look-behind/ahead grouping.*/
        String withDelimiter = "((?<=(%s))|(?=(%s)))";

        String[] split = s.split(String.format(withDelimiter, entityRegex, entityRegex));

        /*Iterate through split array in reverse, checking each element for a match
        * to the entity regex. If there is a match, append it to the StringBuilder;
        * otherwise, it is a regular string and should be reversed and then appended.*/
        for (int i = split.length - 1; i >= 0; i--)
        {
            String ss = split[i];
            if (ss.matches(entityRegex)) //html entity
            {
                sb.append(ss);
            }
            else //normal string
            {
                sb.append(nonEntity.append(ss).reverse());
            }

            /*To clear nonEntity StringBuilder, reset the length instead of creating new instance,
            * since we don't know how long the next string will be, and the backing array will only
            * grow if necessary.*/
            nonEntity.setLength(0);
        }

        return sb.toString();
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

    /**
     * Lists all palindromes in <var>f</var>. Assumptions: case-insensitive,
     * a-z characters only, one word per line.
     * @param f    The file to read
     * @return A list of the strings in <var>f</var> that are palindromes
     * @throws IOException
     * @see #getPalindromes(java.util.List)
     */
    public static List<String> getPalindromes(File f) throws IOException
    {
        //FileReader constructor throws FileNotFoundException (IOException)
        BufferedReader br = new BufferedReader(new FileReader(f));
        List<String> strings = new ArrayList<String>();
        String s;
        while ((s=br.readLine())!=null)
        {
            strings.add(s.toLowerCase());
        }

        br.close();
        return getPalindromes(strings);
    }

    /**
     * Lists all palindromes in the given list. Assumptions: case-insensitive,
     * a-z characters only, one word per line.
     * @param strings    The list of strings to search
     * @return A list of the strings in <var>strings</var> that are palindromes.
     */
    public static List<String> getPalindromes(List<String> strings)
    {
        StringBuilder sb = new StringBuilder(); //used to reverse s
        List<String> result = new ArrayList<String>();

        /*Read the next line and strip case*/
        for (String s : strings)
        {
            sb.append(s).reverse(); //append the string to the (empty) StringBuilder and reverse it.
            if (s.equals(sb.toString()))
            {
                result.add(s);
            }
            /*We don't know the expected capacity, so we'll reuse the same StringBuilder instance
            * and reset the length. The backing array will only get bigger if more space needs to
            * be allocated.*/
            sb.setLength(0);
        }
        return result;
    }

    /**Cached mapping from primes to characters 'a' through 'z'.
     * @see com.arredondo.samples.exercises.PrimeNumber#getPrime(int) */
    private static final HashMap<Character, Integer> sCharToPrimeMap = new HashMap<Character, Integer>(26);

    /**
     * Retrieves an internal static mapping from the characters 'a' through 'z'
     * to the first 26 primes. The internal mapping is empty until the first time
     * this method is called, at which point it puts the key-value pairs
     * @return Mapping from the characters 'a' through 'z' to the first 26 primes.
     * For example: 'a' -> 2, 'b' -> 3, etc.
     */
    public static HashMap<Character, Integer> getCharToPrimeMap()
    {
        if (sCharToPrimeMap.isEmpty())
        {
            for (int i = 0; i < 26; i++)
            {
                sCharToPrimeMap.put((char) ('a' + i), (int) PrimeNumber.getPrime(i));
            }
        }
        return sCharToPrimeMap;
    }

    /**
     * Lists all anagrams in the file <var>f</var>. Assumptions: case-insensitive,
     * a-z characters only, one word per line.
     * @param f    The file to read
     * @return A list of the strings in <var>f</var> that are anagrams of each other.
     * @throws IOException
     * @see #getAnagrams(java.util.List)
     */
    public static List<String> getAnagrams(File f) throws IOException
    {
        //FileReader constructor throws FileNotFoundException (IOException)
        BufferedReader br = new BufferedReader(new FileReader(f));
        List<String> strings = new ArrayList<String>();
        String s;
        while ((s=br.readLine())!=null)
        {
            strings.add(s);
        }

        br.close();

        return getAnagrams(strings);
    }

    /**
     * Lists all anagrams in the given list. Assumptions: case-insensitive,
     * a-z characters only, one word per line.
     * @param strings    The list of strings to search
     * @return A list of the strings in <var>strings</var> that are anagrams of each other.
     */
    public static List<String> getAnagrams(List<String> strings)
    {
        /* Each character 'a'-'z' can be mapped to the nth prime number, where n is the index of
        the character in the alphabet. E.g. 'a': prime(0)=2, 'b': prime(1)=2, etc. Compute the
        product of the prime number mappings of the characters in s. Anagrams will have the same
        character product. Thus we can use the product as a key to a set of anagrams.*/

        Map<Long, Set<String>> map = new HashMap<Long, Set<String>>();

        for (String s : strings)
        {
            long prod = 1;
            for (char c : s.toCharArray())
            {
                prod *= (long) getCharToPrimeMap().get(c);
            }
            if (!map.containsKey(prod))
            {
                /*Key-value pair doesn't exist, so for the new key, create a new HashSet instance
                * for the new word (not yet an anagram match)*/
                map.put(prod, new HashSet<String>());
            }
            map.get(prod).add(s);
        }

        /*Add only the sets in the mapping with 2 or more elements (anagram match exists) to the result list*/
        List<String> result = new ArrayList<String>(map.size());
        for (Set<String> set : map.values())
        {
            if (set.size() > 1) result.addAll(set);
        }

        return result;
    }

    /**
     * Removes duplicate characters from <var>s</var>, including spaces.
     * @param s    The string to read
     * @return A string with any duplicate characters from <var>s</var> removed.
     */
    public static String removeDuplicates(String s)
    {
        if (s==null||s.isEmpty()) return s;
        Set<Character> charSet = new HashSet<Character>(s.length());//maximum possible capacity
        //Set.add will only add characters not already in the list
        for (char c : s.toCharArray())
        {
            charSet.add(c);
        }
        StringBuilder sb = new StringBuilder(charSet.size());
        for (Character c : charSet)
        {
            sb.append(c);
        }
        return sb.toString();
    }

}
