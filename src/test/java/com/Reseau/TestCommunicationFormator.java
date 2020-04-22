package com.Reseau;

import static org.junit.Assert.assertEquals;

import org.junit.Test;



public class TestCommunicationFormator {
    CommunicationFormator comFormator = new CommunicationFormator();

    @Test
    public void testjoin() {
        assertEquals(comFormator.join("21"), "0121");
    }

    @Test
    public void Testleave() {
        assertEquals(comFormator.leave("21"), "0221");
    }

    @Test
    public void Testsend() {
        assertEquals(comFormator.send("Message", "21", "User"),"0321User  Message");
    }

    @Test
    public void Testconnect() {
        assertEquals(comFormator.connect(), "00");
    }

    @Test
    public void Testdisconnect() {
        assertEquals(comFormator.disconnect(), "FF");
    }

}