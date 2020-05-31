package com.Reseau.Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import com.Reseau.Data.Message;
import com.Reseau.Data.User;
import com.gui.Globals;
import com.Reseau.Data.Data;

public class Client implements Runnable {
    private User user;
    private Socket socket;
    private String send_data = null;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    public String list[];
    private Object message;

    public Client(String ip, int port) {
        this.user = new User("Username", "Password");
        this.socket = Singletons.getSocket(ip, port);
        this.output = Singletons.getOutput(socket);
        this.input = Singletons.getInput(socket);
    }

    /**
     * @param groupCode Create a new group in the server
     * @return
     */
    public boolean createGroup(String groupCode) {
        try {
            Message message = new Message(user.getUsername(), groupCode, "create group", "");

            output.writeObject(message);
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (IOException e) {

            e.printStackTrace();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
        if (getMessage().getMessage().equals("Group " + getMessage().getGroupCode() + " has been created")) {
            return true;

        } else {
            return false;
        }

    }

    /**
     * @param username
     * @param np
     * @param op
     * @return boolean
     */
    public boolean updatePassword(String username, String np, String op) {
        try {

            Message message = new Message(username, op, "update password", np);

            output.writeObject(message);
            TimeUnit.MILLISECONDS.sleep(100);

        } catch (IOException e) {

            e.printStackTrace();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
        if (getMessage().getMessage().equals("Password has been updated")) {
            return true;

        } else {
            return false;
        }

    }

    /**
     * @param username
     * @param nu
     * @param op
     * @return boolean
     */
    public boolean updateUsername(String username, String nu, String op) {
        try {

            Message message = new Message(username, op, "update password", nu);

            output.writeObject(message);
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (IOException e) {

            e.printStackTrace();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
        if (getMessage().getMessage().equals("Username has been updated")) {
            return true;

        } else {
            return false;
        }

    }

    /**
     * @param groupCode delete group from the server
     * @return
     */
    public boolean deleteGroup(String groupCode) {

        Message message = new Message(user.getUsername(), groupCode, "delete group", "");
        try {
            output.writeObject(message);
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException | IOException e) {

            e.printStackTrace();
        }
        if (getMessage().getMessage().equals("Group " + getMessage().getGroupCode() + " has been deleted")) {
            return true;

        } else {
            return false;
        }
    }

    /**
     * @param Username
     * @param Password Send a request to create a user
     * @return
     */
    public boolean createUser(String Username, String Password) {
        try {
            Message message = new Message(user.getUsername(), "", "create user", "");
            output.writeObject(message);
            User user = new User(Username, Password);

            output.writeObject(user);
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException | IOException e) {

            e.printStackTrace();
        }
        if (getMessage().getMessage().equals("User has been created")) {
            return true;

        } else {
            return false;
        }
    }

    /**
     * @param username Send a request to delete a user
     * @return
     */
    public boolean deleteUser(String username) {
        try {
            Message message = new Message(username, "", "delete user", "");

            output.writeObject(message);
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
        if (getMessage().getMessage().equals("User has been erased")) {
            return true;

        } else {
            return false;
        }

    }

    /**
     * 
     * connect to the server
     */
    public boolean connect(String Username, String Password) {
        try {
            output.writeObject(new Message("", "", "connect", ""));
            TimeUnit.MILLISECONDS.sleep(100);
            output.writeObject(new User(Username, Password));
            user = new User(Username, Password);

            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException | IOException e) {

            e.printStackTrace();
        }
        if (getMessage().getMessage().equals("Welcome to the server " + user.getUsername())) {
            return true;

        } else {
            return false;
        }
    }

    /**
     * 
     * disconnect from the server
     */
    public void diconnect() {
        try {
            output.writeObject(new Message(user.getUsername(), "", "disconnect", ""));
            output.close();
            input.close();
        } catch (SocketTimeoutException exc) {
            System.out.println(exc.getMessage());
        } catch (UnknownHostException uhe) {
            System.out.println(uhe.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    /**
     * @param Groupcode Join a group
     * @return
     */
    public boolean join(String Groupcode) {

        try {
            TimeUnit.MILLISECONDS.sleep(100);

            /*
             * for (Group group : list) { System.out.println(group.toString()); }
             */

            output.writeObject(new Message(user.getUsername(), Groupcode, "join", ""));
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        // System.out.println(getMessage().getMessage().equals("You join the chat " +
        // getMessage().getGroupCode()));
        if (getMessage().getMessage().equals("You join the chat " + getMessage().getGroupCode())) {
            return true;

        } else {
            return false;
        }
    }

    /**
     * @param Groupcode leave a group
     * @return
     */
    public boolean leave(String Groupcode) {
        try {
            output.writeObject(new Message("", "", "leave", Groupcode));

            TimeUnit.MILLISECONDS.sleep(100);
        } catch (SocketTimeoutException exc) {
        } catch (UnknownHostException uhe) {
            System.out.println(uhe.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
        if (getMessage().getMessage().equals("You leave the chat " + getMessage().getGroupCode())) {
            return true;

        } else {
            return false;
        }
    }

    /**
     * @param Groupcode
     * @param Name
     * @param message   Sending a message to the server
     */
    public void send(String Groupcode, String Name, String message) {
        try {
            output.writeObject(new Message(user.getUsername(), Groupcode, "send", message));
        } catch (SocketTimeoutException exc) {
        } catch (UnknownHostException uhe) {
            System.out.println(uhe.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    /**
     * @param Send Message and command to the server,
     * @deprecated used for terminals
     */
    public void writing() {
        try {
            Scanner scan = new Scanner(System.in);
            String groupcode = null;
            getLisGroup();
            while (true) {
                send_data = scan.nextLine();
                if (send_data != null) {
                    Data message;
                    if (send_data.equalsIgnoreCase("connect")) {
                        System.out.println("username?");
                        String Username = scan.next();
                        System.out.println("password");
                        String Password = scan.next();
                        System.out.println(connect(Username, Password));
                    }
                    if (send_data.equalsIgnoreCase("join")) {
                        for(String s : list){
                            System.out.println(s);
                        }
                        System.out.println("Which Group?");
                        System.out.println(join(scan.next()));

                    } else if (send_data.equalsIgnoreCase("leave")) {

                        System.out.println("Which Group?");
                        System.out.println(scan.next());

                    } else if (send_data.equalsIgnoreCase("disconnect")) {
                        output.writeObject(new Message(user.getUsername(), groupcode, "disconnect", ""));
                        scan.close();
                        output.close();
                        input.close();
                        return;

                    } else if (send_data.equalsIgnoreCase("create group")) {
                        System.out.println("Which Group?");
                        System.out.println(createGroup(scan.next()));

                    } else if (send_data.equalsIgnoreCase("delete group")) {

                        System.out.println("Which Group?");
                        System.out.println(deleteGroup(scan.next()));

                    } else if (send_data.equalsIgnoreCase("create user")) {
                        System.out.println("Which User?");
                        String username = scan.next();
                        System.out.println("Which Password?");
                        String password = scan.next();

                        System.out.println(createUser(username, password));

                    } else if (send_data.equalsIgnoreCase("delete user")) {
                        try {
                            System.out.println("Which User?");
                            String username = scan.next();
                            System.out.println("Which Password?");
                            System.out.println(deleteUser(username));

                            output.writeObject(user);
                        } catch (IOException e) {

                            e.printStackTrace();
                        }

                    } else if (send_data.equalsIgnoreCase("update password")) {
                        System.out.println("Which User?");
                        String username = scan.next();
                        System.out.println("new password?");
                        String np = scan.next();
                        System.out.println("old password?");
                        String op = scan.next();
                        System.out.println(updatePassword(username, op, np));
                    } else if (send_data.equalsIgnoreCase("update username")) {
                        System.out.println("Which User?");
                        String username = scan.next();
                        System.out.println("new username?");
                        String nu = scan.next();
                        System.out.println("old password?");
                        String op = scan.next();
                        message = new Message(username, op, "update password", nu);
                        System.out.println(updateUsername(username, op, nu));
                    }

                    else {
                        message = new Message(user.getUsername(), groupcode, "send", send_data);
                        output.writeObject(message);
                    }
                    send_data = null;
                }

            }
        } catch (SocketTimeoutException exc) {
            System.out.println("la");
        } catch (UnknownHostException uhe) {
            System.out.println(uhe.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    /**
     * @return Message Return the last message recieved
     */
    public Message getMessage() {
        return (Message) message;
    }

    /**
     * @return ArrayList<Group> return the list of group
     */
    public String[] getLisGroup() {
        try {
            output.writeObject(new Message(user.getUsername(), "", "display list", ""));

            TimeUnit.MILLISECONDS.sleep(100);

        } catch (IOException e) {

            e.printStackTrace();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void run() {
        while (true) {
            try {

                Object recieved = input.readObject();
                if (recieved instanceof ArrayList<?>) {
              
                    list= new String[((ArrayList<String>) recieved).size()];

                    // ArrayList to Array Conversion
                    for (int j = 0; j < ((ArrayList<String>)recieved).size(); j++) {

                        // Assign each value to String array
                        list[j] = ((ArrayList<String>) recieved).get(j);
                    }

                } else {
                    recieved = (Message) recieved;
                    message = recieved;
                    System.out.println(((Message) message).getUsername() + " [" + ((Message) message).getGroupCode()
                            + "] " + " >" + ((Message) message).getMessage());
                    if (((Message) recieved).getCommand().equals("send")) {
                        Globals.message = (Message) recieved;

                    }

                    try {
                        Globals.message = Globals.clnt.getMessage();
                        Globals.chatGUI.PutTextToChatTextArea(Globals.message.getGroupCode(),
                                Globals.message.getUsername(), Globals.message.getMessage());
                    } catch (NullPointerException e) {

                    }

                }

            } catch (ClassNotFoundException e) {
            } catch (SocketException se) {
                System.out.println("Goodbye !");
                return;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param arg[]
     */
    public static void main(String arg[]) {
        int i = 6668;

        Client client = new Client("localhost", i);
        new Thread(client).start();

        client.writing();

    }
}