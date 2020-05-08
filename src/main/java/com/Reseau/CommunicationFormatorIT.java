package com.Reseau;
public interface CommunicationFormatorIT {
    public String join(String GroupCode);
    public String leave(String GroupCode);
    public String connect();
    public String disconnect();
    public String send(String Message, String GroupCode,String Username);
}