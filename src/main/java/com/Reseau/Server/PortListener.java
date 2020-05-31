package com.Reseau.Server;

import java.io.*;
import java.net.*;

public class PortListener implements Runnable {

	private ServerSocket ss;
	private int port;
	private String ip;

	public PortListener(String ip, int port) {
		this.ip = ip;
		this.port = port;

	}
	/** 
     * @param 
	 * Create a connection for a client and a server port
     */
	public void run() {
		try {
			
			while (true) {
				ss = new ServerSocket(port);
				Socket socket = ss.accept();// establishes connection
				ss.close();
				System.out.println("Connected as " + ip);
				ConnectionHandler connectionHandler= new ConnectionHandler(socket);
				connectionHandler.handle();
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