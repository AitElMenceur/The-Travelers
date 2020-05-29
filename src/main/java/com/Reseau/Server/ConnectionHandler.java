package com.Reseau.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import com.Reseau.Data.*;
import com.Reseau.Client.*;
import com.Reseau.Interface.IConnectionHandler;
import com.dataBase.XmlHandler;

public class ConnectionHandler implements IConnectionHandler {
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Data recieved;
    private static ArrayList<Group> LIST_GROUP = Server.LIST_GROUP;
    private static ArrayList<User> LIST_USER = Server.LIST_USER;

    public ConnectionHandler(Socket socket) {
        this.socket = socket;
    }

    /**
     * @param groupNumber
     * @param output      Remove a group from the list
     */
    public synchronized void removeGroup(String groupNumber, ObjectOutputStream output) {
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
    public synchronized void addGroup(String groupNumber, ObjectOutputStream output) {
        for (Group p : LIST_GROUP) {
            if (p.getGroupCode().equals(groupNumber)) {
                System.out.println(p.getGroupCode() + "ok");
                p.join(output);
            }
        }
    }

    public synchronized void addUser(User username) {
        LIST_USER.add(username);
    }

    /**
     * Check for incoming message, and give an appropriate answer
     */
    public void handle() {
        new XmlHandler("Database");
        try {
            boolean finish = false;
            boolean isConnected = true;
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            while (!finish) {
                recieved = (Message) input.readObject();
                System.out.println(recieved.toString());
                switch (recieved.getCommand()) {
                    case ("connect"):

                        User user = (User) input.readObject();
                        if (XmlHandler.checkingLogins(XmlHandler.getDocument(), user.getUsername(),
                                user.getPassword())) {
                            isConnected = true;
                            output.writeObject(new Message(user.getUsername(), "", user.getCommand(),
                                    "Welcome to the server " + user.getUsername()));
                            System.out.println("Connected in port " + socket.getLocalPort());
                            for (String grp : XmlHandler.listOfGroupsOfAUser(user.getUsername())) {
                                addGroup(grp, output);
                            }
                        } else {
                            output.writeObject(
                                    new Message(user.getUsername(), "", user.getCommand(), "Wrong password"));
                            isConnected = false;
                        }
                        break;
                    case ("display list"):
                        output.writeObject(LIST_GROUP);
                        break;
                    case ("join"):
                        if (isConnected) {

                            addGroup(((Message) recieved).getGroupCode(), output);
                            output.writeObject(new Message(((Message) recieved).getUsername(),
                                    ((Message) recieved).getGroupCode(), recieved.getCommand(),
                                    "You join the chat " + ((Message) recieved).getGroupCode()));
                            XmlHandler.addGroupCodeToUser(((Message) recieved).getGroupCode(),
                                    ((Message) recieved).getUsername());
                        } else {
                            System.out.println("No");
                        }
                        break;
                    case ("leave"):
                        if (isConnected) {
                            removeGroup(((Message) recieved).getGroupCode(), output);
                            output.writeObject(new Message(((Message) recieved).getUsername(),
                                    ((Message) recieved).getGroupCode(), recieved.getCommand(),
                                    "You leave the chat " + ((Message) recieved).getGroupCode()));
                            XmlHandler.deleteGroupcodeOfAUser(((Message) recieved).getGroupCode(),
                                    ((Message) recieved).getUsername());
                        }
                        break;
                    case ("send"):
                        for (Group p : LIST_GROUP) {
                            if (p.getGroupCode().equals(((Message) recieved).getGroupCode())) {
                                p.send((Message) recieved);
                            }
                            XmlHandler.addMessage(((Message) recieved).getGroupCode(),
                                    ((Message) recieved).getUsername(), ((Message) recieved).getMessage());
                        }
                        break;
                    case ("disconnect"):
                        if (isConnected) {
                            output.writeObject(
                                    new Message(((Message) recieved).getUsername(), ((Message) recieved).getGroupCode(),
                                            recieved.getCommand(), "bye " + ((Message) recieved).getUsername()));
                            output.flush();
                            finish = true;
                        }
                        break;
                    case ("create group"):
                        if (isConnected) {
                            LIST_GROUP.add(new Group(((Message) recieved).getGroupCode()));
                            output.writeObject(new Message(((Message) recieved).getUsername(),
                                    ((Message) recieved).getGroupCode(), recieved.getCommand(),
                                    "Group " + ((Message) recieved).getGroupCode() + " has been created"));
                            XmlHandler.createGroup(((Message) recieved).getGroupCode());
                            break;
                        }
                    case ("create user"):
                        user = (User) input.readObject();
                        XmlHandler.addUser(user.getUsername(), user.getPassword());
                        break;
                    case ("delete user"):
                        user = (User) input.readObject();
                        XmlHandler.deleteUser(user.getUsername());
                        break;
                    case ("delete group"):
                        if (isConnected) {

                            LIST_GROUP.remove(((Message) recieved).getGroupCode());
                            output.writeObject(new Message(((Message) recieved).getUsername(),
                                    ((Message) recieved).getGroupCode(), recieved.getCommand(),
                                    "Group " + ((Message) recieved).getGroupCode() + " has been deleted"));
                            XmlHandler.deleteGroup(((Message) recieved).getGroupCode());

                        }
                        break;
                }
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
