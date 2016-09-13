package com.jeremysule.progtest;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilsTest {
    @Test
    public void isEqualTo() throws Exception {
        assertTrue(Utils.isEqual(0.1f,0.1f));
        assertTrue(Utils.isEqual(0.000001f,0.000001f));
        assertTrue(Utils.isEqual(0.000001f,0.00000099f));
        assertTrue(Utils.isEqual(0.0f,0.0f));
        assertTrue(Utils.isEqual(0.0f,-0.0f));
        assertFalse(Utils.isEqual(0.0f,1.0f));
        assertFalse(Utils.isEqual(0.000001f,0.000002f));

    }

    @Test
    public void testCompare() throws Exception {
        assertEquals(0, Utils.compare(0.1f,0.1f));
        assertEquals(0, Utils.compare(0.1f,0.1f));
        assertEquals(0, Utils.compare(0.000001f,0.000001f));
        assertEquals(0, Utils.compare(0.000001f,0.00000099f));
        assertEquals(0, Utils.compare(0.0f,0.0f));
        assertEquals(0, Utils.compare(0.0f,-0.0f));
        assertEquals(-1, Utils.compare(0.0f,1.0f));
        assertEquals(1, Utils.compare(0.0001f,0.000002f));

    }

}