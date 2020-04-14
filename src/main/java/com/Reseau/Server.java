package com.Reseau;

import java.io.*;
import java.net.*;

public class Server {
    private String ip;
    private ServerSocket serversocket;

    public Server(String ip, int port) {
        try {
            this.serversocket = new ServerSocket(port);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public void connect() {
        try {
            Socket socket = serversocket.accept(); // établit la connexion
            System.out.println("Connected as " + socket.getInetAddress());

            System.out.println("Server waiting for connection...");
            while (true) {
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                if (reader.ready()) {
                    OutputStream output = socket.getOutputStream();
                    PrintWriter writer = new PrintWriter(output, true);
                    String text = reader.readLine();
                    writer.println(text);
                    writer.flush();
                }
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public static void main(String arg[]) {
        Server S = new Server("localhost", 6666);
        S.connect();

    }
}
