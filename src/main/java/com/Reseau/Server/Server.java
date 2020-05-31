package com.Reseau.Server;

import java.util.ArrayList;

import com.Reseau.Data.Group;
import com.Reseau.Data.User;
import com.dataBase.XmlHandler;

public class Server extends AbstractServer {
    private String ip;
    static protected ArrayList<Group> LIST_GROUP;
    static protected ArrayList<User> LIST_USER;

    public Server(String ip) {
        new XmlHandler("Database");
        this.ip = ip;
        Server.LIST_USER = new ArrayList<User>();

        try {
            Server.LIST_GROUP = initializeGroup();
        } catch (NullPointerException e) {
            Server.LIST_GROUP = new ArrayList<Group>();

        }
    }

    private ArrayList<Group> initializeGroup() {
        ArrayList<Group> list = new ArrayList<Group>();
        for (String g : XmlHandler.listOfGroups()) {

            list.add(new Group(g));
            System.out.println(g);
        }
        return list;

    }

    @Override
    public void connect() {
        for (int port = 6666; port < 6680; port++) {
            new Thread(new PortListener(ip, port)).start();
        }

    }

    public void addGroup(Group a) {
        LIST_GROUP.add(a);
    }

    public void removeGroup(Group a) {
        LIST_GROUP.remove(a);
    }

    public void addUser(User user) {
        LIST_USER.add(user);
    }

    public void removeUser(User user) {
        LIST_USER.remove(user);
    }
}
