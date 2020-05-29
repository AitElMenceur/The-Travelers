package com.Reseau.Data;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Group implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Set<ObjectOutputStream> writers = new HashSet<>();
    private String groupCode;

    public Group(String groupCode) {
        this.groupCode = groupCode;
        this.writers = new HashSet<>();
    }

    
    /** 
     * @param output
     * add a client to a group
     */
    public void join(ObjectOutputStream output) {
        writers.add(output);
    }

    
    /** 
     * @param output
     * remove a client from a group
     */
    public void leave(ObjectOutputStream output) {
        writers.remove(output);
    }

    
    /** 
     * @param msg
     * send a message to all members of a group
     */
    public void send(Message msg) {
        try {
            for (ObjectOutputStream out : writers) {
                out.writeObject(msg);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    
    /** 
     * @return String
     * Getter for the groupcode
     */
    public String getGroupCode() {
        return groupCode;
    }

    public String toString() {
        return groupCode;
    }

}