package com.Reseau;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server implements Runnable {
    private String ip;
    private ServerSocket serversocket;
    // private static Set<PrintWriter> writers = new HashSet<>();

    private static ArrayList<Group> listgrp = new ArrayList<Group>();

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
            return socket;
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        return null;
    }

    public synchronized void remove(String Groupnumber, PrintWriter writer) {
        for (Group p : listgrp) {
            if (p.getGroupcode().equals(Groupnumber)) {
                p.leave(writer);
            }
        }
    }

    public synchronized void add(String Groupnumber, PrintWriter writer) {
        for (Group p : listgrp) {
            if (p.getGroupcode().equals(Groupnumber)) {
                System.out.println(p.getGroupcode() + "ok");
                p.join(writer);
            }
        }
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
            // writers.add(writer);
            while (finish) {
                if (reader.ready()) {
                    String text = reader.readLine();
                    System.out.println(text);
                    String Groupnumber;
                    switch (text.substring(0, 2)) {
                        case ("00"):
                            writer.println("Welcome to the server");
                            System.out.println("Connected in port " + socket.getLocalPort());
                            break;
                        case ("01"):
                            Groupnumber = text.substring(2, 4);
                            add(Groupnumber, writer);
                            writer.println("You join the chat " + Groupnumber);
                            break;
                        case ("02"):
                            Groupnumber = text.substring(2, 4);
                            remove(Groupnumber, writer);
                            writer.println("You leaved the chat " + Groupnumber);
                            break;
                        case ("03"):
                            Groupnumber = text.substring(2, 4);
                            System.out.println(Groupnumber);
                            for (Group p : listgrp) {
                                System.out.println("p:" + p.getGroupcode());
                                System.out.println("Grpnb:" + Groupnumber);
                                if (p.getGroupcode().equals(Groupnumber)) {
                                    p.send(text);
                                }
                            }
                            break;
                        case ("FF"):
                            writer.println("goodbye");
                            finish = false;
                            reader.close();
                            writer.close();
                            output.close();
                            input.close();
                            break;

                    }
                    text = null;

                }

            }

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());

        }
    }

    public static void main(String arg[]) {
        Group G1 = new Group("AA");
        Group G2 = new Group("BB");
        listgrp.add(G1);
        listgrp.add(G2);
        for (int i = 6666; i < 6680; i++) {
            new Thread(new Server("localhost", i), "client-" + Integer.toString(i)).start();
        }
    }
}
