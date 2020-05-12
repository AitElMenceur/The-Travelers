package com.Reseau;

import java.util.ArrayList;

public class Server extends AbstractServer {
    private String ip;
    static protected ArrayList<Group> listGroup;

    public Server(String ip) {
        this.ip = ip;
        Server.listGroup = new ArrayList<Group>();
    }

    @Override
    public void connect() {
        listGroup.add(new Group("AA"));
        listGroup.add(new Group("BB"));
        for (int port = 6666; port < 6680; port++) {
            new Thread(new PortListener(ip, port)).start();
        }

    }
    public void addGroup(Group a){
        listGroup.add(a);
    }
    public void removeGroup(Group a){
        listGroup.remove(a);
    }
}
