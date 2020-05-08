package com.Reseau;

import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;

public abstract class AbstractServer
{
    private ServerSocket serversocket;
	public AbstractServer(String ip, int port) {
        try {
            this.serversocket = new ServerSocket(port);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
	}
	public abstract Socket connect();
} 