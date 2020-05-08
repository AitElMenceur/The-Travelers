package com.Reseau;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server extends AbstractServer implements Runnable {
    private String ip;
    private ServerSocket serversocket;
    // private static Set<PrintWriter> writers = new HashSet<>();

    private static ArrayList<Group> listGroup = new ArrayList<Group>();
    public Server(String ip, int port) {
        super(ip, port);
        listGroup.add(new Group("AA"));
        listGroup.add(new Group("BB"));
    }

    public Socket connect() {
        try {
            Socket socket = serversocket.accept();
            System.out.println("connected to " + socket.getLocalPort());
            return socket;
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        return null;
    }

    public synchronized void remove(String groupNumber, ObjectOutputStream output) {
        for (Group p : listGroup) {
            if (p.getGroupcode().equals(groupNumber)) {
                p.leave(output);
            }
        }
    }

    public synchronized void add(String groupNumber, ObjectOutputStream output) {
        for (Group p : listGroup) {
            if (p.getGroupcode().equals(groupNumber)) {
                System.out.println(p.getGroupcode() + "ok");
                p.join(output);
            }
        }
    }

    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Data recieved;

    @Override
    public void run() {
        try {
            Socket socket = connect();
            output = new ObjectOutputStream(socket.getOutputStream()); // ouvre un flux de sortie vers le socket //
            input = new ObjectInputStream(socket.getInputStream()); // ouvre un flux d’entrée vers le socket
            // writers.add(writer);
            while (true) {

                recieved = (Message) input.readObject();
                System.out.println(recieved.toString());
                switch (recieved.GetCommand()) {
                    case ("connect"):

                        System.out.println("Connected in port " + socket.getLocalPort());
                        break;
                    case ("join"):
                        add(recieved.GetGroupCode(), output);
                        output.writeObject(new Message(recieved.GetUsername(), recieved.GetGroupCode(),
                                recieved.GetCommand(), "You join the chat " + recieved.GetGroupCode()));
                        break;
                    case ("leave"):
                        remove(recieved.GetGroupCode(), output);
                        output.writeObject(new Message(recieved.GetUsername(), recieved.GetGroupCode(),
                                recieved.GetCommand(), "You leave the chat " + recieved.GetGroupCode()));
                        break;
                    case ("send"):

                        for (Group p : listGroup) {
                            if (p.getGroupcode().equals(recieved.GetGroupCode())) {
                                p.send((Message) recieved);
                            }
                        }
                        break;
                    case ("disconnect"):
                        output.writeObject(new Message(recieved.GetUsername(), recieved.GetGroupCode(),
                                recieved.GetCommand(), "bye " + recieved.GetUsername()));
                        output.close();
                        input.close();
                        break;

                }

            }

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
