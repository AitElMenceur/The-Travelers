package com.Reseau.Server;

import java.util.ArrayList;
import com.Reseau.Data.Group;

public class Server extends AbstractServer {
    private String ip;
    static protected ArrayList<Group> LIST_GROUP;

    public Server(String ip) {
        this.ip = ip;
        Server.LIST_GROUP = new ArrayList<Group>();
    }

    @Override
    public void connect() {
        LIST_GROUP.add(new Group("AA"));
        LIST_GROUP.add(new Group("BB"));
        for (int port = 6666; port < 6680; port++) {
            new Thread(new PortListener(ip, port)).start();
        }

    }
    
    public void addGroup(Group a){
        LIST_GROUP.add(a);
    }
    public void removeGroup(Group a){
        LIST_GROUP.remove(a);
    }
}
