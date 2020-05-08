package com.Reseau;

public class Message extends Data {
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
        return Username+GroupCode+GetCommand()+Message;
      }
    public String GetUsername(){
        return Username;
    }
    public String GetGroupCode(){
        return GroupCode;
    }
    public void SetGroupCode(String GC){
        GroupCode = GC;
    }
    public String GetMessage(){
        return Message;
    }
}