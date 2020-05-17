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
    public String toString() {
        return Username+GroupCode+getCommand()+Message;
      }
    public String getUsername(){
        return Username;
    }
    public String getGroupCode(){
        return GroupCode;
    }
    public void setGroupCode(String GC){
        GroupCode = GC;
    }
    public String getMessage(){
        return Message;
    }
}