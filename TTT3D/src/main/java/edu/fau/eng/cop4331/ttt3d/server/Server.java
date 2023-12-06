package edu.fau.eng.cop4331.ttt3d.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    /**
     * This is the server for clients
     *
     * @author Abir Faisal, Jamahl Farrington
     */
    ArrayList threads = new ArrayList<>();

    ServerSocket server;
    int serverPort = 32034;

    /**
     * Constructor
     * @throws IOException
     */
    public Server() throws IOException {
        server = new ServerSocket(serverPort);
    }

    /**
     * Run the server
     * @throws IOException
     */
    public void run() throws IOException {
        while (true) {
            System.out.println("Waiting for connection: " + server);

            //created socket waits for connection
            Socket socket = server.accept();
            System.out.println("Accepted Connection from : " + socket.getInetAddress());

            //read from socket
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            System.out.println(ois.readAllBytes());

            //process the input

            //respond to the client
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(new String("Hello World"));


            //close the connection
            ois.close();
            oos.close();
            socket.close();
        }
    }


    /**
     * TODO write debug info without blocking
     * @param str String you want to print to terminal
     * @param log true=append to log file, false=do nothing
     */
    void nonBlockingPrintln(String str, boolean log) {

        if (log) {
            //TODO log to file
        }
    }



}
