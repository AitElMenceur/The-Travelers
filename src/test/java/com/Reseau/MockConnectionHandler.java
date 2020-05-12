package com.Reseau;

import java.util.ArrayList;

public class MockConnectionHandler {
    private Data recieved;
    private Data send;
    private static ArrayList<Group> listGroup = Server.listGroup;

    public MockConnectionHandler(Data data) {
        this.recieved = data;
        this.send = null;
    }

    /**
     * Check for incoming message, and give an appropriate answer
     */
    public void SetInncomingMessage(Data msg) {
        recieved = msg;
    }

    public Data GetOutput() {
        return send;
    }

    public void Handle() {
        System.out.println(recieved.toString());
        switch (recieved.GetCommand()) {
            case ("connect"):
                send = new Message(((Message) recieved).GetUsername(), ((Message) recieved).GetGroupCode(),
                        recieved.GetCommand(), "Welcome to the server " + ((Message) recieved).GetUsername());
                break;
            case ("join"):
                send = new Message(((Message) recieved).GetUsername(), ((Message) recieved).GetGroupCode(),
                        recieved.GetCommand(), "You join the chat " + ((Message) recieved).GetGroupCode());
                break;
            case ("leave"):
                send = new Message(((Message) recieved).GetUsername(), ((Message) recieved).GetGroupCode(),
                        recieved.GetCommand(), "You leave the chat " + ((Message) recieved).GetGroupCode());
                break;
            case ("send"):
                send = recieved;
                break;
            case ("disconnect"):
                send = new Message(((Message) recieved).GetUsername(), ((Message) recieved).GetGroupCode(),
                        recieved.GetCommand(), "bye " + ((Message) recieved).GetUsername());
                break;
        }
    }
}
