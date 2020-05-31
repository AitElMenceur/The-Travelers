package com.Reseau.Server;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import com.Reseau.Data.Group;
import com.Reseau.Data.User;
import com.dataBase.XmlHandler;

public class Server extends AbstractServer {
    private String ip;
    static protected CopyOnWriteArrayList<Group> LIST_GROUP;
    static protected  ArrayList<User> LIST_USER;

    public Server(String ip) {
        new XmlHandler("Database");
        this.ip = ip;
        Server.LIST_USER = new ArrayList<User>();

        try {
            Server.LIST_GROUP = initializeGroup();
        } catch (NullPointerException e) {
            Server.LIST_GROUP = new CopyOnWriteArrayList<Group>();

        }
    }

    private CopyOnWriteArrayList<Group> initializeGroup() {
        CopyOnWriteArrayList<Group> list = new CopyOnWriteArrayList<Group>();
        for (String g : XmlHandler.listOfGroups()) {

            list.add(new Group(g));
            System.out.println(g);
        }
        return (CopyOnWriteArrayList<Group>) list;

    }

    @Override
    public void connect() {
        for (int port = 6666; port < 6680; port++) {
            new Thread(new PortListener(ip, port)).start();
        }

    }

    public synchronized void addGroup(Group a) {
        LIST_GROUP.add(a);
    }

    public synchronized void removeGroup(Group a) {
        LIST_GROUP.remove(a);
    }

    public synchronized void addUser(User user) {
        LIST_USER.add(user);
    }

    public synchronized void removeUser(User user) {
        LIST_USER.remove(user);
    }
}
