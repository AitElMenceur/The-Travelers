package com.Reseau.Data;

public class User extends Data {

    private static final long serialVersionUID = 1L;
    private String Username;
    private String Password;

    public User(String Username, String Password) {
        super("");
        this.Username = Username;
        this.Password = Password;
    }

    
    /** 
     * @return String
     * Getter for username
     */
    public String getUsername() {
        return Username;
    }

    
    /** 
     * @return String
     * getter for password
     */
    public String getPassword() {
        return Password;
    }
}
