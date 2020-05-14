package com.Reseau.Interface;

public interface IMessage {
    public abstract String getMessage();

    public abstract void setGroupCode(String next);
  
    public abstract String getGroupCode();
  
    public abstract String getUsername();
}
