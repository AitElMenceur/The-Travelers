package com.Reseau.Server;

import java.io.*;
import java.net.*;
import com.Reseau.Interface.IPortListener;


public class MockPortListener implements Runnable,IPortListener {

	private ServerSocket ss;
	private int port;
	private String ip;
	private Socket socket;
	public MockPortListener(String ip, int port) {
		this.ip = ip;
		this.port = port;

	}
	/** 
     * @param 
	 * Test a connection for a client and a server port.
     */
	public void run() {
		try {
			ss = new ServerSocket(port);
			while (true) {
				socket = ss.accept();// establishes connection
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