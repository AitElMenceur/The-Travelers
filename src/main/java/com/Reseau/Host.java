package com.Reseau;

public class Host {
    public static void main(String arg[]) {
        AbstractServer server = new Server();
        server.connect();
    }

}