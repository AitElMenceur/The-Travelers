package com.Reseau.Data;

import com.Reseau.Interface.IMessage;

public class Message extends Data implements IMessage {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String Username;
    private String GroupCode;
    private String Message;

    public Message(String username, String groupcode, String command, String message) {
        super(command);
        this.Username = username;
        this.GroupCode = groupcode;
        this.Message = message;
    }

    /**
     * @return String
     */
    public String toString() {
        return Username + GroupCode + getCommand() + Message;
    }

    /**
     * Getter for the Username
     * @return String
     */
    public String getUsername() {
        return Username;
    }

    /**
     * Getter for the GroupCode
     * @return String 
     */
    public String getGroupCode() {
        return GroupCode;
    }

    /**
     * Setter for the Username
     * @param GC 
     */
    public void setGroupCode(String GC) {
        GroupCode = GC;
    }

    /**
     * Getter for the Message
     * @return String 
     */
    public String getMessage() {
        return Message;
    }
}