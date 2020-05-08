package com.Reseau;

public class Host {
    public static void main(String arg[]) {
        Group G1 = new Group("AA");
        Group G2 = new Group("BB");
        String port = "localhost";
        AbstractServer server = new Server();
        server.connect();
    }

}