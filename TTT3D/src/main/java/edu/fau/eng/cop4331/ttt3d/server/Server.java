package edu.fau.eng.cop4331.ttt3d.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {


    /**
     *
     * @author Abir Faisal, Jamilius Caesar
     */

    ArrayList threads = new ArrayList<>();

    ServerSocket server;
    int serverPort = 32034;

    public Server() throws IOException {
        server = new ServerSocket(serverPort);

        while (true){
            System.out.println("Waiting for connection");

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

    public void run() {

    }


    void nonBlockingPrintln(String str, boolean log) {

        if (log) {
            //TODO log to file
        }
    }



}
