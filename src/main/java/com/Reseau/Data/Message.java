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
    public Message(String username,String groupcode,String command,String message){
      super(command);
      this.Username=username;
      this.GroupCode=groupcode;
      this.Message=message;
    }
    
    /** 
     * @return String
     */
    public String toString() {
        return Username+GroupCode+getCommand()+Message;
      }
    
    /** 
     * @return String
     * Getter for the Username
     */
    public String getUsername(){
        return Username;
    }
    
    /** 
     * @return String
     * Getter for the GroupCode
     */
    public String getGroupCode(){
        return GroupCode;
    }
    
    /** 
     * @param GC
     * Setter for the Username
     */
    public void setGroupCode(String GC){
        GroupCode = GC;
    }
    
    /** 
     * @return String
     * Getter for the Message
     */
    public String getMessage(){
        return Message;
    }
}