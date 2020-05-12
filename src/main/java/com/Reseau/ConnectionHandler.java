package com.Reseau;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import com.Reseau.Interface.ConnectionHandlerIT;

public class ConnectionHandler implements ConnectionHandlerIT {
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Data recieved;
    private static ArrayList<Group> listGroup = Server.listGroup;

    public ConnectionHandler(Socket socket) {
        this.socket = socket;
    }

    /**
     * @param groupNumber
     * @param output      Remove a group from the list
     */
    public synchronized void remove(String groupNumber, ObjectOutputStream output) {
        for (Group p : listGroup) {
            if (p.getGroupcode().equals(groupNumber)) {
                p.leave(output);
            }
        }
    }

    /**
     * @param groupNumber
     * @param output      Add a group from the list
     */
    public synchronized void add(String groupNumber, ObjectOutputStream output) {
        for (Group p : listGroup) {
            if (p.getGroupcode().equals(groupNumber)) {
                System.out.println(p.getGroupcode() + "ok");
                p.join(output);
            }
        }
    }

    /**
     * 
     * 
     * Check for incoming message, and give an appropriate answer
     */
    public void Handle() {
        try {
            boolean finish = false;
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            while (!finish) {

                recieved = (Message) input.readObject();
                System.out.println(recieved.toString());
                switch (recieved.GetCommand()) {
                    case ("connect"):
                        output.writeObject(new Message(((Message) recieved).GetUsername(),
                                ((Message) recieved).GetGroupCode(), recieved.GetCommand(),
                                "Welcome to the server " + ((Message) recieved).GetUsername()));
                        System.out.println("Connected in port " + socket.getLocalPort());
                        break;
                    case ("join"):
                        add(((Message) recieved).GetGroupCode(), output);
                        output.writeObject(new Message(((Message) recieved).GetUsername(),
                                ((Message) recieved).GetGroupCode(), recieved.GetCommand(),
                                "You join the chat " + ((Message) recieved).GetGroupCode()));
                        break;
                    case ("leave"):
                        remove(((Message) recieved).GetGroupCode(), output);
                        output.writeObject(new Message(((Message) recieved).GetUsername(),
                                ((Message) recieved).GetGroupCode(), recieved.GetCommand(),
                                "You leave the chat " + ((Message) recieved).GetGroupCode()));
                        break;
                    case ("send"):

                        for (Group p : listGroup) {
                            if (p.getGroupcode().equals(((Message) recieved).GetGroupCode())) {
                                p.send((Message) recieved);
                            }
                        }
                        break;
                    case ("disconnect"):
                        output.writeObject(
                                new Message(((Message) recieved).GetUsername(), ((Message) recieved).GetGroupCode(),
                                        recieved.GetCommand(), "bye " + ((Message) recieved).GetUsername()));
                        output.flush();
                        finish = true;
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
}
