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

    
    /** 
     * @param Rawtext
     * @return String
     * Extract the command code from a received string 
     */
    public String GetCommandCode(String Rawtext) {
        return Rawtext.substring(0, 2);
    }

    
    /** 
     * @param Rawtext
     * @return String
     * Extract the group code from a received string 
     */
    public String GetGroupcode(String Rawtext) {
        try {
            return Rawtext.substring(2, 4);
        } catch (StringIndexOutOfBoundsException e) {
            return "";
        }
    }

    
    /** 
     * @param Rawtext
     * @return String
     * Extract the username from a received string 
     */
    public String GetUsername(String Rawtext) {
        try {
            return Rawtext.substring(4, 12).replaceAll(":", "");
        } catch (StringIndexOutOfBoundsException e) {
            return "";
        }
    }

    
    /** 
     * @param Rawtext
     * @return String
     * Extract the message from a received string 
     */
    public String GetMessage(String Rawtext) {
        try {
            return Rawtext.substring(12);
        } catch (StringIndexOutOfBoundsException e) {
            return "";
        }
    }

}