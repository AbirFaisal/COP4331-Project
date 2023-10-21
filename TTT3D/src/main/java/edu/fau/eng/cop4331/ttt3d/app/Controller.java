package edu.fau.eng.cop4331.ttt3d.app;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.Consumer;

import static java.lang.Thread.sleep;

public abstract class Controller {


    public Model model;

    public interface Handler{ void handle(ActionEvent value);}
    public HashMap<UUID, Handler> handlers = new HashMap<>();


    /**
     * When the user interacts with the View,
     * the View will notify the Controller that an (UUID, actionEvent) has occurred,
     * then the (UUID, ActionEvent) will go into a handlerBuffer
     * later it will be handled by a Thread launched by runHandlers().
     */
    public ArrayList<SimpleEntry> handlerBuffer = new ArrayList<>();
    public void handle(UUID uuid, ActionEvent actionEvent) {
        SimpleEntry<UUID, ActionEvent> tuple = new SimpleEntry<>(uuid, actionEvent);
        handlerBuffer.add(tuple);
    }

    public void runHandlers() {
        new Thread(() -> {
            while (true) {
                for (int i = 0; i < handlerBuffer.size(); i++) {
                    //TODO Try Catch Finally
                    //get the UUID and ActionEvent
                    SimpleEntry simpleEntry = handlerBuffer.get(i);

                    //Handle the event
                    UUID uuid = (UUID) simpleEntry.getKey();
                    ActionEvent actionEvent = (ActionEvent) simpleEntry.getValue();
                    System.out.println(uuid + "IT WORKS!" + actionEvent);

                    //remove from buffer
                    handlerBuffer.remove(i);
                }

                //prevent using CPU cycles for no reason.
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }).start();

    }




    public void updateModel(UUID key, Record data) {
        this.model.setData(key, data);
    }

}
