package edu.fau.eng.cop4331.ttt3d;

import java.util.HashMap;
import java.util.Map;

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
                System.out.println(key + "=" + value);
            }
        }

        //if --server then launch game server instead of user application
        if (argmap.get("--server") != null) {
            System.out.println("Run Server");
        } else {
            System.out.println("Launch Game");
            App app = new App();
            app.run();
        }
    }
}