package com.Reseau.Data;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

public class Group {
    private Set<ObjectOutputStream> writers = new HashSet<>();
    private String groupCode;

    public Group(String groupCode) {
        this.groupCode = groupCode;
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
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public String getGroupCode() {
        return groupCode;
    }

}