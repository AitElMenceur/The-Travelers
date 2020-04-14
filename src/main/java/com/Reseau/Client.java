package com.Reseau;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client implements Runnable {
    private String Nom;
    private Socket socket;
    private String send_data = null;

    public Client(String ip, int port, String nom) {
        this.Nom = nom;
        try {
            this.socket = new Socket(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        try { // ouvre le socket
            OutputStream output = socket.getOutputStream(); // ouvre un flux de sortie vers le socket PrintWriter writer
                                                            // = new PrintWriter(output, true);// écrit vers le flux de
                                                            // sortie, en
            PrintWriter writer = new PrintWriter(output, true);
            writer.println(Nom + ": " + "Enter text: ");
            InputStream input = socket.getInputStream(); // ouvre un flux d’entrée vers le socket
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line = reader.readLine(); // lit le flux d’entrée, en accord avec le protocole du serveur!
            System.out.println(line);
            input.close(); // clôt le inputStream
            output.close(); // clôt le outputStream
            socket.close(); // clôt le socket
        } catch (UnknownHostException uhe) {
            System.out.println(uhe.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public void Clientwrite() {
        Scanner scan = new Scanner(System.in);

       // System.out.print("> ");
        send_data = scan.nextLine();

    }

    @Override
    public void run() {

        try {
            while (true) {
                OutputStream output = socket.getOutputStream(); // ouvre un flux de sortie vers le socket PrintWriter
                PrintWriter writer = new PrintWriter(output, true);
                InputStream input = socket.getInputStream(); // ouvre un flux d’entrée vers le socket
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                if (send_data != null) {
                    writer.println(Nom + " : " + send_data);
                    send_data = null;
                    writer.flush();
                }
                if (reader.ready()) {
                    String line = reader.readLine(); // lit le flux d’entrée, en accord avec le protocole du serveur!
                    System.out.println(line);
                }
                // input.close(); // clôt le inputStream
                // output.close(); // clôt le outputStream

            }
        } catch (UnknownHostException uhe) {
            System.out.println(uhe.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

    }

    public static void main(String arg[]) {
        int i = 6668;
        Client client = new Client("localhost", i, "User" + i);
        Thread t1 = new Thread(client);

        t1.start();
        while (true) {
            client.Clientwrite();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    
}
