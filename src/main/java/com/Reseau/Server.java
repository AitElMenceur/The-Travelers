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
            Socket socket = serversocket.accept();

            //System.out.println("Connected as " + socket.getInetAddress());

            //System.out.println("Server waiting for connection...");
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
            boolean finish = true;
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            writers.add(writer);
            while (finish) {
                if (reader.ready()) {
                    String text = reader.readLine();                    
                    switch (text.substring(0, 2)) {
                        case ("00"):
                            writer.println("Welcome to the server");
                            System.out.println("Connected in port " + socket.getLocalPort());
                            break;
                        case ("01"):
                            writer.println("You join the chat " + text.substring(3, 4));
                            break;
                        case ("02"):
                            writer.println("You leaved the chat " + text.substring(3, 4));
                            break;
                        case ("03"):
                            String Groupnumber=text.substring(3,4);
                            String Username=text.substring(4,12);
                            String message=text.substring(12);
                            for (PrintWriter writerr : writers) {
                                writerr.println(Username+" to group "+Groupnumber+": "+message);
                                System.out.println(text);
                                // writerr.flush();
                            }
                            break;
                        case ("FF"):
                            writer.println("goodbye");
                            finish = false;
                            break;
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
