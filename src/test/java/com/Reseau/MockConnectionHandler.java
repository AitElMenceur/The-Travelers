package com.Reseau;

import com.Reseau.Interface.IConnectionHandler;

public class MockConnectionHandler implements IConnectionHandler {
    private Data recieved;
    private Data send;

    public MockConnectionHandler(Data data) {
        this.recieved = data;
        this.send = null;
    }

    /**
     * @param Data 
     *Setter , replace Serializer for Test
     */
    public void SetInncomingMessage(Data msg) {
        recieved = msg;
    }

    public Data GetOutput() {
        return send;
    }

    public void handle() {
        System.out.println(recieved.toString());
        switch (recieved.getCommand()) {
            case ("connect"):
                send = new Message(((Message) recieved).getUsername(), ((Message) recieved).getGroupCode(),
                        recieved.getCommand(), "Welcome to the server " + ((Message) recieved).getUsername());
                break;
            case ("join"):
                send = new Message(((Message) recieved).getUsername(), ((Message) recieved).getGroupCode(),
                        recieved.getCommand(), "You join the chat " + ((Message) recieved).getGroupCode());
                break;
            case ("leave"):
                send = new Message(((Message) recieved).getUsername(), ((Message) recieved).getGroupCode(),
                        recieved.getCommand(), "You leave the chat " + ((Message) recieved).getGroupCode());
                break;
            case ("send"):
                send = recieved;
                break;
            case ("disconnect"):
                send = new Message(((Message) recieved).getUsername(), ((Message) recieved).getGroupCode(),
                        recieved.getCommand(), "bye " + ((Message) recieved).getUsername());
                break;
        }
    }
}