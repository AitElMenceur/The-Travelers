package com.Reseau;

public class CommunicationDecoder implements CommunicationDecoderIT {
    private String commandCode;
    private String groupCode;
    private String username;
    private String message;

    public CommunicationDecoder(String Rawtext) {

        this.commandCode = GetCommandCode(Rawtext);
        this.groupCode = GetGroupcode(Rawtext);
        this.username = GetUsername(Rawtext);
        this.message = GetMessage(Rawtext);

    }

    public String GetCommandCode(String Rawtext) {
        return Rawtext.substring(0, 2);
    }

    public String GetGroupcode(String Rawtext) {
        try {
            return Rawtext.substring(2, 4);
        } catch (StringIndexOutOfBoundsException e) {
            return "";
        }
    }

    public String GetUsername(String Rawtext) {
        try {
            return Rawtext.substring(4, 12).replaceAll(":", "");
        } catch (StringIndexOutOfBoundsException e) {
            return "";
        }
    }

    public String GetMessage(String Rawtext) {
        try {
            return Rawtext.substring(12);
        } catch (StringIndexOutOfBoundsException e) {
            return "";
        }
    }

}