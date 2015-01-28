package com.arredondo.samples.exercises;

import junit.framework.TestCase;

public class StringReverseTest extends TestCase
{

    public void testReverse() throws Exception
    {
        String s1 = "Mary had a little lamb";
        assertEquals("bmal elttil a dah yraM", StringReverse.reverse(s1));

        String s2 = "&yen;123";
        assertEquals("321&yen;", StringReverse.reverse(s2));
    }
}