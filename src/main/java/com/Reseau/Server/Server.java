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
        Server.LIST_GROUP=new ArrayList<Group>();
        initializeGroup();
        Server.LIST_USER = new ArrayList<User>();
    }
    private void initializeGroup() {
        for(String g: XmlHandler.listOfGroups()){
            System.out.println(g);
            LIST_GROUP.add(new Group(g));
        }
    }

    

    @Override
    public void connect() {
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


    public void addUser(User user){
        LIST_USER.add(user);
    }
    public void removeUser(User user){
        LIST_USER.remove(user);
    }
}
