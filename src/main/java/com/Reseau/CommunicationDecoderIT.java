package com.Reseau;

public interface CommunicationDecoderIT {

    public String GetCommandCode(String Rawtext);

    public String GetGroupcode(String Rawtext);

    public String GetUsername(String Rawtext);

    public String GetMessage(String Rawtext);
}
