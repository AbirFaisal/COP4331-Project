package edu.fau.eng.cop4331.ttt3d;

import edu.fau.eng.cop4331.ttt3d.app.App;
import edu.fau.eng.cop4331.ttt3d.server.Server;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.exit;
import static java.lang.System.in;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> argmap = new HashMap<>();

        //interpret command line arguments
        //For example --f0 1234 --f1 5678
        for (int i = 0; i < args.length; i++) {
            String argument = args[i];

            if (argument.startsWith("--")) {
                String key = argument.substring(2); //remove -- from key
                String value = args[i+1]; //get value of key
                argmap.put(key, Integer.parseInt(value)); //put key and value into map for usage
                System.out.println(key + "=" + value);//TODO remove when no longer needed.
            }
        }

        //if --server then launch game server instead of user application
        if (argmap.get("--server") != null) {
            System.out.println("Run Server");
            //port for server
            //ip and port for load balance and failover

            //TODO Server server = new Server(port, secondaryServerIP, );
            //server.run();
        } else {
            System.out.println("Launch Start Screen");
            App instance = App.getInstance();
            instance.setup();
            instance.run();
        }
    }
}