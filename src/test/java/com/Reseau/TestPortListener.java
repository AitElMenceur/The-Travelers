package com.Reseau;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.Socket;

import org.junit.Test;

public class TestPortListener {
    private String ip = "localhost";
    private int port = 6666;
    MockPortListener listener = new MockPortListener(ip, port);

    @Test
    public void Testconnect() {
        try {
            Socket socket = new Socket(ip, port);
            assertTrue(socket.isBound());
            socket.close();
        } catch (IOException e) {
            assertFalse("message", false);
        }
        
    }

}
