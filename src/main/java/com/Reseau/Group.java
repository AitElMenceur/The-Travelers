package com.Reseau;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

public class Group {
    private Set<ObjectOutputStream> writers = new HashSet<>();
    private String GroupCode;

    public Group(String Groupcode) {
        this.GroupCode = Groupcode;
        this.writers = new HashSet<>();
    }

    public void join(ObjectOutputStream output) {
        writers.add(output);
    }

    public void leave(ObjectOutputStream output) {
        writers.remove(output);
    }

    public void send(Message msg) {
        try {
            for (ObjectOutputStream out : writers) {
                out.writeObject(msg);
                //System.out.println(msg.toString());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public String getGroupcode() {
        return GroupCode;
    }

}