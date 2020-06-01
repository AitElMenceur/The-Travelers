package com.Reseau.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import com.Reseau.Data.*;
import com.Reseau.Interface.IConnectionHandler;
import com.dataBase.XmlHandler;

public class ConnectionHandler implements IConnectionHandler {
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Data recieved;
    private static ArrayList<User> LIST_USER = Server.LIST_USER;

    public ConnectionHandler(Socket socket) {
        this.socket = socket;
    }

    /**
     * @param groupNumber
     * @param output      Remove a group from the list
     */
    public synchronized void removeGroup(String groupNumber, ObjectOutputStream output) {
        for (Group p : Server.LIST_GROUP) {
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
        for (Group p : Server.LIST_GROUP) {
            if (p.getGroupCode().equals(groupNumber)) {
                System.out.println(p.getGroupCode() + " ok");
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
    public synchronized void handle() {

        try {
            boolean finish = false;
            boolean isConnected = false;
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
                            output.writeObject(new Message("Server", "", user.getCommand(),
                                    "Welcome to the server " + user.getUsername()));
                            System.out.println("Connected in port " + socket.getLocalPort());
                            /*
                             * for (String grp : XmlHandler.listOfGroupsOfAUser(user.getUsername())) {
                             * addGroup(grp, output); }
                             */
                        } else {
                            output.writeObject(new Message("Server", "", user.getCommand(),
                                    "Wrong password or username"));
                            isConnected = false;
                        }
                        break;
                    case ("update password"):
                        if (XmlHandler.updatePassword(((Message) recieved).getUsername(),
                                ((Message) recieved).getMessage(), ((Message) recieved).getGroupCode())) {
                            output.writeObject(new Message("Server", "", "", "Password has been updated"));
                            isConnected = false;
                        } else {
                            output.writeObject(new Message("Server", "", "", "Error"));
                        }

                        break;
                    case ("update username"):
                            if (XmlHandler.updateUserName(((Message) recieved).getUsername(),
                                    ((Message) recieved).getMessage())) {
                                output.writeObject(new Message("Server", "", "", "Username has been updated"));
                                isConnected = false;
                            } else {
                                output.writeObject(new Message("Server", "", "", "Error"));
                            }

                        break;
                    case ("display list"):
                        ArrayList<String> listgroup = new ArrayList<String>();
                        for (Group g : Server.LIST_GROUP) {
                            listgroup.add(g.getGroupCode());
                            // System.out.println(g.getGroupCode());
                        }
                        for (String g : listgroup) {
                            System.out.println(g);

                        }

                        output.writeObject(listgroup);
                        break;
                    case ("join"):
                        if (isConnected) {

                            addGroup(((Message) recieved).getGroupCode(), output);
                            
                            if (XmlHandler.addGroupCodeToUser(((Message) recieved).getGroupCode(),
                                    ((Message) recieved).getUsername())) {
                                output.writeObject(new Message("Server",
                                        ((Message) recieved).getGroupCode(), recieved.getCommand(),
                                        "You join the chat " + ((Message) recieved).getGroupCode()));

                            }
                        } else {
                            System.out.println("Not connected");
                        }
                        break;
                    case ("leave"):
                        if (isConnected) {
                            removeGroup(((Message) recieved).getGroupCode(), output);

                            if (XmlHandler.deleteGroupcodeOfAUser(((Message) recieved).getGroupCode(),
                                    ((Message) recieved).getUsername())) {
                                output.writeObject(new Message("Server",
                                        ((Message) recieved).getGroupCode(), recieved.getCommand(),
                                        "You leave the chat " + ((Message) recieved).getGroupCode()));

                            } else {
                                output.writeObject(new Message("Server",
                                        ((Message) recieved).getGroupCode(), recieved.getCommand(), "Error"));

                            }
                        }
                        break;
                    case ("send"):
                        for (Group p : Server.LIST_GROUP) {
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
                                    new Message("Server", ((Message) recieved).getGroupCode(),
                                            recieved.getCommand(), "GoodBye! " + ((Message) recieved).getUsername()));
                            output.flush();
                            isConnected = false;
                            finish = true;
                        }
                        break;
                    case ("create group"):
                        if (isConnected) {
                            if (XmlHandler.createGroup(((Message) recieved).getGroupCode())) {
                                Server.LIST_GROUP.add(new Group(((Message) recieved).getGroupCode()));
                                output.writeObject(new Message("Server",
                                        ((Message) recieved).getGroupCode(), recieved.getCommand(),
                                        "Group " + ((Message) recieved).getGroupCode() + " has been created"));
                            } else {
                                output.writeObject(new Message("Server",
                                        ((Message) recieved).getGroupCode(), recieved.getCommand(), "Error"));
                            }
                            break;
                        }
                    case ("create user"):
                        user = (User) input.readObject();
                        System.out.println(user.getUsername());
                        if (XmlHandler.addUser(user.getUsername(), user.getPassword())) {
                            output.writeObject(
                                    new Message("Server", ((Message) recieved).getGroupCode(),
                                            recieved.getCommand(), "User has been created"));

                        } else {
                            output.writeObject(new Message("Server",
                                    ((Message) recieved).getGroupCode(), recieved.getCommand(), "Error"));

                        }
                        break;
                    case ("delete user"):
                        user = (User) input.readObject();
                        if (XmlHandler.deleteUser(user.getUsername())) {
                            output.writeObject(
                                    new Message("Server", ((Message) recieved).getGroupCode(),
                                            recieved.getCommand(), "User has been erased"));
                        } else {
                            output.writeObject(new Message("Server",
                                    ((Message) recieved).getGroupCode(), recieved.getCommand(), "Error"));
                        }
                        break;
                    case ("delete group"):
                        if (isConnected) {

                            if (XmlHandler.deleteGroup(((Message) recieved).getGroupCode())) {
                                for(Group g: Server.LIST_GROUP){
                                    if(g.getGroupCode().equals(((Message) recieved).getGroupCode())){
                                         Server.LIST_GROUP.remove(g);
                                    }
                                }
                               
                                output.writeObject(new Message("Server",
                                        ((Message) recieved).getGroupCode(), recieved.getCommand(),
                                        "Group " + ((Message) recieved).getGroupCode() + " has been deleted"));
                            } else {
                                output.writeObject(new Message("Server",
                                        ((Message) recieved).getGroupCode(), recieved.getCommand(), "Error"));
                            }

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
