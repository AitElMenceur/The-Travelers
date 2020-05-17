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
			ss = new ServerSocket(port);
			while (true) {
				Socket socket = ss.accept();// establishes connection
				System.out.println("Connected as " + ip);
				new ConnectionHandler(socket).handle();
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