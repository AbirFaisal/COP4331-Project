package edu.fau.eng.cop4331.ttt3d.app;

import java.awt.*;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.BiConsumer;

public abstract class View {


    //Objects of the view
    public HashMap<UUID, Container> jFrames = new HashMap<>();
    public Controller controller;


    //methods that are called when update is called on a UUID mapped to jFrames
    public HashMap<UUID, Updater> updateMethods = new HashMap<>();

    //TODO remove, it seems like its not used
//    public Model model;
//    public View(Model model) {
//        this.model = model;
//        this.model.register(this);
//    }

    //used to setup the view, setup the main view and add elements to it
    public abstract void setup();


    //register a controller for the view

    /**
     * Registers a controller with the view so that the view
     * is aware of where it needs to send actions and events.
     * The view will call it's handle(UUID) method when soemthing happens.
     * @param controller A subclass that extends the abstract Controller
     */
    public void registerController(Controller controller){
        this.controller = controller;
    }


    /**
     * Updates an element of the view given its corresponding UUID
     *
     * @param uuid UUID as defined in the model of the view
     */
    public void updateElement(UUID uuid) {
        if (this.updateMethods.get(uuid) != null)
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
