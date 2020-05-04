package com.Reseau;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class Group {
    private Set<PrintWriter> writers = new HashSet<>();
    private String GroupCode;

    public Group(String Groupcode) {
        this.GroupCode = Groupcode;
        this.writers = new HashSet<>();
    }

    public void join(PrintWriter a) {
        writers.add(a);
    }

    public void leave(PrintWriter a) {
        writers.remove(a);
    }

    public void send(String Rawtext) {
        CommunicationDecoder dec = new CommunicationDecoder(Rawtext);
        String Groupnumber = dec.GetGroupcode(Rawtext);
        String Username = dec.GetUsername(Rawtext);
        String message = dec.GetMessage(Rawtext);
        for (PrintWriter w : writers) {
            w.println(Username + " to group " + Groupnumber + ": " + message);
            System.out.println(Username + " to group " + Groupnumber + ": " + message);
        }
        
    }
    public String getGroupcode() {
        return GroupCode;
    }

}