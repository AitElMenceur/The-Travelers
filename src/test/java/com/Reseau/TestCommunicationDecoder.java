package com.Reseau;

import static org.junit.Assert.assertEquals;

import org.junit.Test;



public class TestCommunicationDecoder {
    private static String Rawtext="03ABUser::::Message";
    CommunicationDecoder dec = new CommunicationDecoder(Rawtext);

    @Test
    public void TestGetCommandCode() {
        assertEquals(dec.GetCommandCode(Rawtext), "03");
    }

    @Test
    public void TestGetGroupcode() {
        assertEquals(dec.GetGroupcode(Rawtext), "AB");
    }

    @Test
    public void TestGetMessage() {
        assertEquals(dec.GetMessage(Rawtext),"Message");
    }

    @Test
    public void TestGetUsername() {
        assertEquals(dec.GetUsername(Rawtext), "User");
    }

}

