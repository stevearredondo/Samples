package com.arredondo.samples.exercises;

import android.util.Log;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringExercisesTest extends TestCase
{
    public void testHtmlReverse() throws Exception
    {
        assertEquals("", StringExercises.htmlReverse(""));

        boolean exceptionThrown = false;
        try
        {
            StringExercises.htmlReverse("&123");
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
        assertEquals("bmal elttil a dah yraM", StringExercises.htmlReverse(s1));
        assertEquals(StringExercises.reverse(s1), StringExercises.htmlReverse(s1));

        String s2 = "&yen;123";
        assertEquals("321&yen;", StringExercises.htmlReverse(s2));

        String s3 = "456&yen;123&yen;&yen;";
        assertEquals("&yen;&yen;321&yen;654", StringExercises.htmlReverse(s3));
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

    public void testHtmlReverse2() throws Exception
    {
        assertEquals(null, StringExercises.htmlReverse2(null));

        assertEquals("", StringExercises.htmlReverse2(""));

        String s2 = "&yen;123";
        assertEquals("321&yen;", StringExercises.htmlReverse2(s2));

        String s3 = "456&yen;123&yen;&yen;";
        assertEquals("&yen;&yen;321&yen;654", StringExercises.htmlReverse2(s3));

        String s4 = "No Html";
        assertEquals("lmtH oN", StringExercises.htmlReverse2(s4));
    }

    public void testHtmlEntityRegex() throws Exception
    {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("&lt;Ampersand looks like &amp;&amp; &theta; test&pi;asd &gt;");
        strings.add("This sentence has no html");
        strings.add("");
        for (String s : strings)
        {
            String[] split = s.split("((?<=(&[#\\w]{1,10};))|(?=(&[#\\w]{1,10};)))");
            Log.i("testHtmlEntityRegex", "\'"+s+"\'" + ", " + Arrays.toString(split));
        }

    }
}