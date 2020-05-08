package com.Reseau;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server implements Runnable {
    private String ip;
    private ServerSocket serversocket;
    // private static Set<PrintWriter> writers = new HashSet<>();

    private static ArrayList<Group> listGroup = new ArrayList<Group>();

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

    public synchronized void remove(String groupNumber, PrintWriter writer) {
        for (Group p : listGroup) {
            if (p.getGroupcode().equals(groupNumber)) {
                p.leave(writer);
            }
        }
    }

    public synchronized void add(String groupNumber, PrintWriter writer) {
        for (Group p : listGroup) {
            if (p.getGroupcode().equals(groupNumber)) {
                System.out.println(p.getGroupcode() + "ok");
                p.join(writer);
            }
        }
    }

    @Override
    public void run() {
        try {
            Socket socket = connect();
            boolean finish = false;
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            // writers.add(writer);
            while (!finish) {
                if (reader.ready()) {
                    String Rawtext = reader.readLine();
                    System.out.println(Rawtext);
                    CommunicationDecoder dec = new CommunicationDecoder(Rawtext);
                    String commandCode = dec.GetCommandCode(Rawtext);
                    String groupNumber = dec.GetGroupcode(Rawtext);
                    switch (commandCode) {
                        case ("00"):
                            writer.println("Welcome to the server");
                            System.out.println("Connected in port " + socket.getLocalPort());
                            break;
                        case ("01"):
                            add(groupNumber, writer);
                            writer.println("You join the chat " + groupNumber);
                            break;
                        case ("02"):
                            remove(groupNumber, writer);
                            writer.println("You leaved the chat " + groupNumber);
                            break;
                        case ("03"):
                            System.out.println(groupNumber);
                            for (Group p : listGroup) {
                                System.out.println("p:" + p.getGroupcode());
                                System.out.println("Grpnb:" + groupNumber);
                                if (p.getGroupcode().equals(groupNumber)) {
                                    p.send(Rawtext);
                                }
                            }
                            break;
                        case ("FF"):
                            writer.println("goodbye");
                            finish = true;
                            reader.close();
                            writer.close();
                            output.close();
                            input.close();
                            break;

                    }
                    Rawtext = null;
                }
            }

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());

        }
    }

    public static void main(String arg[]) {
        Group G1 = new Group("AA");
        Group G2 = new Group("BB");
        listGroup.add(G1);
        listGroup.add(G2);
        for (int i = 6666; i < 6680; i++) {
            new Thread(new Server("localhost", i), "client-" + Integer.toString(i)).start();
        }
    }
}
