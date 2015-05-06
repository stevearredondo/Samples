package com.arredondo.samples.exercises;

import android.util.Log;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringExercisesTest extends TestCase
{
    public void testHtmlReverseNoRegex() throws Exception
    {
        assertEquals("", StringExercises.htmlReverseNoRegex(""));

        boolean exceptionThrown = false;
        try
        {
            StringExercises.htmlReverseNoRegex("&123");
        }
        catch (RuntimeException e)
        {
            exceptionThrown = true;
        }
        finally
        {
            assertTrue(exceptionThrown);
        }

        String s1 = "Mary had a little lamb";
        assertEquals("bmal elttil a dah yraM", StringExercises.htmlReverseNoRegex(s1));
        assertEquals(StringExercises.reverse(s1), StringExercises.htmlReverseNoRegex(s1));

        String s2 = "&yen;123";
        assertEquals("321&yen;", StringExercises.htmlReverseNoRegex(s2));

        String s3 = "456&yen;123&yen;&yen;";
        assertEquals("&yen;&yen;321&yen;654", StringExercises.htmlReverseNoRegex(s3));
    }

    public void testGetPalindromes() throws Exception
    {
        List<String> palindromes = StringExercises.getPalindromes(Arrays.asList("racecar", "the", "wow", "toot", "cat"));
        List<String> expected = Arrays.asList("racecar", "wow", "toot");
        assertTrue(palindromes.containsAll(expected));
        assertTrue(expected.containsAll(palindromes));
    }

    public void testGetAnagrams() throws Exception
    {
        List<String> anagrams = StringExercises.getAnagrams(Arrays.asList("car", "cab", "abc", "rat", "tar", "goo", "foo", "off", "bar"));
        List<String> expected = Arrays.asList("cab", "abc", "rat", "tar");
        assertTrue(anagrams.containsAll(expected));
        assertTrue(expected.containsAll(anagrams));
    }

    public void testHtmlReverseWithRegex() throws Exception
    {
        assertEquals(null, StringExercises.htmlReverseWithRegex(null));

        assertEquals("", StringExercises.htmlReverseWithRegex(""));

        String s2 = "&yen;123";
        assertEquals("321&yen;", StringExercises.htmlReverseWithRegex(s2));

        String s3 = "456&yen;123&yen;&yen;";
        assertEquals("&yen;&yen;321&yen;654", StringExercises.htmlReverseWithRegex(s3));

        String s4 = "No Html";
        assertEquals("lmtH oN", StringExercises.htmlReverseWithRegex(s4));

        String s5 = "Bad HTML &asdfasdfasdf;";
        assertEquals(";fdsafdsafdsa& LMTH daB", StringExercises.htmlReverseWithRegex(s5));
    }

    public void testHtmlEntityRegex() throws Exception
    {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("&lt;Ampersand looks like &amp;&amp; &theta; test&pi;asd &gt;");
        strings.add("This sentence has no html");
        strings.add("");
        strings.add("Trick question: Is &asdfasdfasdf; an entity?");
        for (String s : strings)
        {
            String[] split = s.split("((?<=(&[#\\w]{1,10};))|(?=(&[#\\w]{1,10};)))");
            Log.i("testHtmlEntityRegex", "\'"+s+"\'" + ", " + Arrays.toString(split));
        }

    }
}