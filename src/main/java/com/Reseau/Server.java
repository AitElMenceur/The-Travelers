package com.Reseau;

import java.util.ArrayList;

public class Server extends AbstractServer {
    private String ip = "localhost";
    static ArrayList<Group> listGroup = new ArrayList<Group>();

    @Override
    public void connect() {
        listGroup.add(new Group("AA"));
        listGroup.add(new Group("BB"));
        for (int port = 6666; port < 6680; port++) {
            new Thread(new PortListener(ip, port)).start();
        }

    }
}
