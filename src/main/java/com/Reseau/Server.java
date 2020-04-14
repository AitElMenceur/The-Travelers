package com.Reseau;

import java.io.*;
import java.net.*;
import java.util.HashSet;
import java.util.Set;

public class Server implements Runnable {
    private String ip;
    private ServerSocket serversocket;
    private static Set<PrintWriter> writers = new HashSet<>();

    public Server(String ip, int port) {
        try {
            this.serversocket = new ServerSocket(port);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public Socket connect() {
        try {
            Socket socket = serversocket.accept(); // établit la connexion
            System.out.println("Connected as " + socket.getInetAddress());

            System.out.println("Server waiting for connection...");
            return socket;
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        return null;
    }

    @Override
    public void run() {
        try {
            Socket socket = connect();
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            writers.add(writer);
            while (true) {
                if (reader.ready()) {
                    String text = reader.readLine();
                    for (PrintWriter writerr : writers) {
                        
                        writerr.println(text);
                        System.out.println(text);
                       // writerr.flush();
                    }
                }
            }
           
        } catch (IOException ioe) { 
            System.out.println(ioe.getMessage());
            
        }
    }

    public static void main(String arg[]) {
        for (int i = 6666; i < 6680; i++) {
            new Thread(new Server("localhost", i), "client-" + Integer.toString(i)).start();
        }
    }
}
