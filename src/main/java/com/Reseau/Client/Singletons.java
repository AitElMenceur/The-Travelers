package com.Reseau.Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Singletons {
    private static ObjectOutputStream output;
    private static ObjectInputStream input;
    private static Socket socket;

    /**
     * Build an Socket client
     * 
     * @param ip
     * @param port
     * @return Socket
     */
    public static Socket getSocket(String ip, int port) {
        if (socket == null) {

            try {
                return new Socket(ip, port);
            } catch (IOException e) {

            }

        }
        return socket;
    }

    /**
     * Build an outputStream
     * 
     * @param socket
     * @return ObjectOutputStream
     * 
     */
    public static ObjectOutputStream getOutput(Socket socket) {
        if (output == null) {

            try {
                return new ObjectOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return output;
    }

    /**
     * Build an inputStream
     * @param socket
     * @return ObjectInputStream
     * 
     */
    public static ObjectInputStream getInput(Socket socket) {
        if (input == null) {

            try {
                return new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {

                e.printStackTrace();
            }

        }
        return input;
    }

}