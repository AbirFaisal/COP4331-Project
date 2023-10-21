package edu.fau.eng.cop4331.ttt3d.app;

import edu.fau.eng.cop4331.ttt3d.app.startscreen.StartScreenView;

import java.awt.*;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.BiConsumer;

public abstract class View {


    //Objects of the view
    public HashMap<UUID, Container> jFrames = new HashMap<>();
    public Controller controller;


    //methods that are called when update is called on a UUID mapped to jFrames
    public interface Updater { void update();}
    public HashMap<UUID, Updater> updateMethods = new HashMap<>();

    public abstract void setup();


    //register a controller for the view
    public void registerController(Controller controller){
        this.controller = controller;
    }


    /**
     * Updates an element of the view given its corresponding UUID
     *
     * @param uuid UUID as defined in the model of the view
     */
    public void updateElement(UUID uuid) {
        this.updateMethods.get(uuid).update();
    }

    /**
     * Refresh/Update the whole view.
     */
    public void refreshView(){
        BiConsumer<? super UUID, ? super Updater> biConsumer = (uuid, updater) -> updater.update();
        updateMethods.forEach(biConsumer);
    }

    /**
     * Get a component of the view
     * @param uuid UUID as defined in the model of the view
     */
    public Container getContainer(UUID uuid){
        return this.jFrames.get(uuid);
    }

}
