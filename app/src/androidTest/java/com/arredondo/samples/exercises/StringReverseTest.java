package com.arredondo.samples.exercises;

import junit.framework.TestCase;

public class StringReverseTest extends TestCase
{
    public void testHtmlReverse() throws Exception
    {
        assertEquals("", StringReverse.htmlReverse(""));

        boolean exceptionThrown = false;
        try
        {
            StringReverse.htmlReverse("&123");
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
        assertEquals("bmal elttil a dah yraM", StringReverse.htmlReverse(s1));
        assertEquals(StringReverse.reverse(s1), StringReverse.htmlReverse(s1));

        String s2 = "&yen;123";
        assertEquals("321&yen;", StringReverse.htmlReverse(s2));

        String s3 = "456&yen;123&yen;&yen;";
        assertEquals("&yen;&yen;321&yen;654", StringReverse.htmlReverse(s3));
    }
}