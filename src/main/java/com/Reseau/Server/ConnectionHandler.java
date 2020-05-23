package com.Reseau.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream; 
import java.net.Socket;
import java.util.ArrayList;

import com.Reseau.Data.Message;
import com.Reseau.Data.Data;
import com.Reseau.Data.Group;
import com.Reseau.Interface.IConnectionHandler;

public class ConnectionHandler implements IConnectionHandler {
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Data recieved;
    private static ArrayList<Group> LIST_GROUP = Server.LIST_GROUP;

    public ConnectionHandler(Socket socket) {
        this.socket = socket;
    }

    /**
     * @param groupNumber
     * @param output      Remove a group from the list
     */
    public synchronized void remove(String groupNumber, ObjectOutputStream output) {
        for (Group p : LIST_GROUP) {
            if (p.getGroupCode().equals(groupNumber)) {
                p.leave(output);
            }
        }
    }

    /**
     * @param groupNumber
     * @param output      Add a group from the list
     */
    public synchronized void add(String groupNumber, ObjectOutputStream output) {
        for (Group p : LIST_GROUP) {
            if (p.getGroupCode().equals(groupNumber)) {
                System.out.println(p.getGroupCode() + "ok");
                p.join(output);
            }
        }
    }

    /**
     * 
     * 
     * Check for incoming message, and give an appropriate answer
     */
    public void handle() {
        try {
            boolean finish = false;
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            while (!finish) {
                recieved = (Message) input.readObject();
                System.out.println(recieved.toString()); 
                switch (recieved.getCommand()) {
                    case ("connect"):
                        output.writeObject(new Message(((Message) recieved).getUsername(),
                                ((Message) recieved).getGroupCode(), recieved.getCommand(),
                                "Welcome to the server " + ((Message) recieved).getUsername()));
                        System.out.println("Connected in port " + socket.getLocalPort());
                        break;
                    case ("join"):
                        add(((Message) recieved).getGroupCode(), output);
                        output.writeObject(new Message(((Message) recieved).getUsername(),
                                ((Message) recieved).getGroupCode(), recieved.getCommand(),
                                "You join the chat " + ((Message) recieved).getGroupCode()));
                        break;
                    case ("leave"):
                        remove(((Message) recieved).getGroupCode(), output);
                        output.writeObject(new Message(((Message) recieved).getUsername(),
                                ((Message) recieved).getGroupCode(), recieved.getCommand(),
                                "You leave the chat " + ((Message) recieved).getGroupCode()));
                        break;
                    case ("send"):

                        for (Group p : LIST_GROUP) {
                            if (p.getGroupCode().equals(((Message) recieved).getGroupCode())) {
                                p.send((Message) recieved);
                            }
                        }
                        break;
                    case ("disconnect"):
                        output.writeObject(
                                new Message(((Message) recieved).getUsername(), ((Message) recieved).getGroupCode(),
                                        recieved.getCommand(), "bye " + ((Message) recieved).getUsername()));
                        output.flush();
                        finish = true;
                        break;
                    case ("create group"):

                        LIST_GROUP.add(((Message) recieved).getGroupCode());
                    break;
                    case ("delete group"):
                        LIST_GROUP.remove(((Message) recieved).getGroupCode());
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
