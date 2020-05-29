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
import com.Reseau.Data.Data;
import com.Reseau.Data.Group;

public class Client implements Runnable {
    private String Name;
    private Socket socket;
    private String send_data = null;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ArrayList<Group> list;

    public Client(String ip, int port, String nom) {
        this.Name = nom;
        try {
            this.socket = new Socket(ip, port);
            this.output = new ObjectOutputStream(socket.getOutputStream()); // ouvre un flux de sortie vers le socket
            this.input = new ObjectInputStream(socket.getInputStream()); // ouvre un flux d’entrée vers le socket
        } catch (IOException e) {
            e.printStackTrace();
            port += 1;
        }
    }

    /**
     * @param groupCode Create a new group in the server
     */
    public void createGroup(String groupCode) {

        Message message = new Message(Name, "groupcode", groupCode, "");
        try {
            output.writeObject(message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @param groupCode delete group from the server
     */
    public void deleteGroup(String groupCode) {

        Message message = new Message(Name, "groupcode", groupCode, "");
        try {
            output.writeObject(message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public void CreateUser(String username) {

        Message message = new Message(Name, "username", username, "");
        try {
            output.writeObject(message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void deleteUser(String username) {

        Message message = new Message(Name, "username", username, "");
        try {
            output.writeObject(message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }





    /**
     * 
     * connect to the server
     */
    public void connect(String Username, String Password) {
        try {
            output.writeObject(new Message("", "", "connect", ""));
            output.writeObject(new User(Username, Password));
        } catch (SocketTimeoutException exc) {
            System.out.println("la");
        } catch (UnknownHostException uhe) {
            System.out.println(uhe.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    /**
     * 
     * disconnect from the server
     */
    public void diconnect() {
        try {
            output.writeObject(new Message(Name, "", "disconnect", ""));
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
     */
    public void join(String Groupcode) {
        try {
            output.writeObject(new Message(Name, "", "display list", ""));

            TimeUnit.MILLISECONDS.sleep(100);

            for (Group group : list) {
                System.out.println(group.toString());
            }
            System.out.println("Which Group?");

            output.writeObject(new Message(Name, Groupcode, "join", ""));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @param Groupcode leave a group
     */
    public void leave(String Groupcode) {
        try {
            output.writeObject(new Message("", "", "leave", Groupcode));
        } catch (SocketTimeoutException exc) {
        } catch (UnknownHostException uhe) {
            System.out.println(uhe.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    /**
     * @param Groupcode
     * @param Name
     * @param message   Sending a message to the server
     */
    public void send(String Groupcode, String Name, String message) {
        try {
            output.writeObject(new Message(Name, Groupcode, "send", message));
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
            output.writeObject(new Message("", "", "connect", ""));
            output.writeObject(new User("", ""));
            while (true) {
                send_data = scan.nextLine();
                if (send_data != null) {
                    Data message;
                    if (send_data.equalsIgnoreCase("connect")) {
                        try {
                            output.writeObject(new Message("", "", "connect", ""));
                            System.out.println("username?");
                            String username = scan.next();
                            System.out.println("password");
                            String password = scan.next();
                            output.writeObject(new User(username, password));
                        } catch (SocketTimeoutException exc) {
                            System.out.println("la");
                        } catch (UnknownHostException uhe) {
                            System.out.println(uhe.getMessage());
                        } catch (IOException ioe) {
                            System.out.println(ioe.getMessage());

                        }
                    }
                    if (send_data.equalsIgnoreCase("join")) {
                        output.writeObject(new Message(Name, "", "display list", ""));
                        try {
                            TimeUnit.MILLISECONDS.sleep(100);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        for (Group group : list) {
                            System.out.println(group.toString());
                        }
                        System.out.println("Which Group?");
                        groupcode = scan.next();
                        output.writeObject(new Message(Name, groupcode, "join", ""));
                    } else if (send_data.equalsIgnoreCase("leave")) {
                        message = new Message(Name, "", send_data, "");
                        System.out.println("Which Group?");
                        ((Message) message).setGroupCode(scan.next());
                        output.writeObject(message);
                    } else if (send_data.equalsIgnoreCase("disconnect")) {
                        output.writeObject(new Message(Name, groupcode, "disconnect", ""));
                        scan.close();
                        output.close();
                        input.close();
                        return;

                    } else if (send_data.equalsIgnoreCase("create group")) {
                        message = new Message(Name, "groupcode", send_data, "");
                        System.out.println("Which Group?");
                        ((Message) message).setGroupCode(scan.next());
                        output.writeObject(message);
                    } else if (send_data.equalsIgnoreCase("delete group")) {
                        message = new Message(Name, "groupcode", send_data, "");
                        System.out.println("Which Group?");
                        ((Message) message).setGroupCode(scan.next());
                        output.writeObject(message);

                    } else {
                        message = new Message(Name, groupcode, "send", send_data);
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

    @Override
    public void run() {
        while (true) {
            try {
                Object recieved = input.readObject();
                if (recieved instanceof ArrayList<?>) {
                    list = (ArrayList<Group>) recieved;

                } else if (recieved instanceof Message) {
                    recieved = (Message) recieved;
                    System.out.println(((Message) recieved).getUsername() + " [" + ((Message) recieved).getGroupCode()
                            + "] " + " >" + ((Message) recieved).getMessage());
                }

            } catch (ClassNotFoundException e) {
            } catch (SocketException se) {
                System.out.println("Goodbye !");
                return;

            } catch (IOException e) {
                // TODO Auto-generate
                e.printStackTrace();
            }
        }
    }

    // to delete
    public static class Globals {
        public static String UserName = "";
        public static String Passwd = "";
        // public static String[] Chatters = {"Rebecca", "Nassim", "Marine"};
        public static String[] Chatters = {};
        // public static int totalChatters = 0;
        // public static String[] Freinds = {"Nassim", "Marine", "Karina", "Ilyes"};
        // public static int totalFreinds = 4;
        // public static String[] Users = {"Clara", "Meta", "Stella", "Neils"};
        // public static int totalUsers = 4;
        // public static String[] GroupMembers = {"Marine", "Nassim",};
        // public static int totalgroupMembers = 2;
        public static String[] GroupCode = { "AA", "BB", "CC" };
        // public static int totalgroupCode = 3;
        public static String CurrentGroup;
    }

    /**
     * @param arg[]
     */
    public static void main(String arg[]) {
        int i = 6668;
        // added by Rebecca

        /*
         * try { LogInGUI dialog = new LogInGUI();
         * dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
         * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); } //end
         */

        Client client = new Client("localhost", i, "User" + i);
        new Thread(client).start();

        client.writing();

    }
}