package edu.fau.eng.cop4331.ttt3d.app;

import java.awt.event.ActionEvent;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import static java.lang.Thread.sleep;

public abstract class Controller {


    public Model model;

    public interface Handler {
        void handle(ActionEvent value);
    }

    public HashMap<UUID, Handler> handlers = new HashMap<>();


    /**
     * When the user interacts with the View,
     * the View will notify the Controller that a (UUID, actionEvent) has occurred,
     * then the (UUID, ActionEvent) will go into a handlerBuffer
     * later it will be handled by a Thread launched by runHandlers().
     */
    public ArrayList<SimpleEntry<UUID, ActionEvent>> eventBuffer = new ArrayList<>();

    public void handle(UUID uuid, ActionEvent actionEvent) {
        SimpleEntry<UUID, ActionEvent> tuple = new SimpleEntry<>(uuid, actionEvent);
        eventBuffer.add(tuple);
    }

    /**
     * This will monitor the event buffer and handle any events
     */
    public void runHandlers() {
        new Thread(() -> {
            while (true) {
                int i = 0;
                try {
                    for (i = 0; i < eventBuffer.size(); i++) {
                        //get the UUID and ActionEvent
                        SimpleEntry<UUID, ActionEvent> simpleEntry = eventBuffer.get(i);

                        //Handle the event
                        UUID uuid = simpleEntry.getKey();
                        ActionEvent actionEvent = simpleEntry.getValue();
                        handlers.get(uuid).handle(actionEvent);

                        //remove from buffer
                        eventBuffer.remove(i);
                    }
                    sleep(100); //prevent using CPU cycles for no reason.
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
