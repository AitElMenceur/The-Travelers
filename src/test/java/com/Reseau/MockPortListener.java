package com.Reseau;

import java.io.*;
import java.net.*;

import com.Reseau.Interface.PortListenerIT;

public class MockPortListener implements Runnable,PortListenerIT {

	private ServerSocket ss;
	private int port;
	private String ip;

	public MockPortListener(String ip, int port) {
		this.ip = ip;
		this.port = port;

	}
	/** 
     * @param 
	 * Create a connection for a client and a server port
     */
	public void run() {
		try {
			ss = new ServerSocket(port);
			while (true) {
				Socket socket = ss.accept();// establishes connection
				System.out.println("Connected as " + ip);
			}
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
			// if IOException close the server socket
			if (ss != null && !ss.isClosed()) {
				try {
					ss.close();
				} catch (IOException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

}