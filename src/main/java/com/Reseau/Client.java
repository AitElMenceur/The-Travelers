package com.Reseau;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client implements Runnable {
    private String Name;
    private Socket socket;
    private String send_data = null;
    private ObjectOutputStream output;
    private ObjectInputStream input;

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
     * @param Send Message and command to the server
     */
    public void writing() {
        try {
            Scanner scan = new Scanner(System.in);
            String groupcode = null;
            output.writeObject(new Message("", "", "connect", ""));
            while (true) {
                send_data = scan.nextLine();
                if (send_data != null) {
                    Data message;
                    if (send_data.equalsIgnoreCase("join")) {
                        message = new Message(Name, "groupcode", send_data, "");
                        System.out.println("Which Group?");
                        groupcode = scan.next();
                        ((Message) message).SetGroupCode(groupcode);
                        output.writeObject(message);
                    } else if (send_data.equalsIgnoreCase("leave")) {
                        message = new Message(Name, "groupcode", send_data, "");
                        System.out.println("Which Group?");
                        ((Message) message).SetGroupCode(scan.next());
                        output.writeObject(message);
                    } else if (send_data.equalsIgnoreCase("disconnect")) {
                        output.writeObject(new Message(Name, groupcode, "disconnect", ""));
                        scan.close();
                        output.close();
                        input.close();
                        return;
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
        try {
            while (true) {
                Data recieved = (Message) input.readObject();
                System.out.println(((Message) recieved).GetUsername() + " [" + ((Message) recieved).GetGroupCode()
                        + "] " + " >" + ((Message) recieved).GetMessage());
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch(SocketException se){
            System.out.println("Goodbye !");
            
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main(String arg[]) {
        int i = 6666;
        Client client = new Client("localhost", i, "User" + i);
        new Thread(client).start();
        client.writing();
    }
}
