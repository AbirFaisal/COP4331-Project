package edu.fau.eng.cop4331.ttt3d.app.views;

import edu.fau.eng.cop4331.ttt3d.app.Controller;
import edu.fau.eng.cop4331.ttt3d.app.View;
import edu.fau.eng.cop4331.ttt3d.app.models.StartScreenModel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.BiConsumer;

public class StartScreenView implements View {


    //Objects of the view
    HashMap<UUID, Container> jFrames = new HashMap<>();

    //methods that are called when update is called on a UUID mapped to jFrames
    interface Updater { void update(); }
    HashMap<UUID, Updater> updateMethods = new HashMap<>();

    Controller controller;

    public StartScreenView(StartScreenModel startScreenModel) {
        setup();
    }


    //set up the view
    void setup() {
        JPanel mainJPanel = new JPanel();
        this.jFrames.put(StartScreenModel.Keys.MAIN, mainJPanel);


        this.jFrames.get(StartScreenModel.Keys.MAIN).add(helloWorld());
        this.jFrames.get(StartScreenModel.Keys.MAIN).add(testButton());

//        updateView(StartScreenModel.Keys.HELLO_WORLD_JLABEL);
    }


    /**
     * Example function
     * @return
     */
    JLabel helloWorld() {
        //instantiate the object
        JLabel jLabel = new JLabel("Hello World");

        //OPTIONAL: put reference to object into HashMap
//        this.jFrames.put(StartScreenModel.Keys.HELLO_WORLD_JLABEL, jLabel);

        //create an update method
        Updater updater = () -> {
            //handle update logic here
            //for example get some data from the corresponding model
            jLabel.setText("Text changed from update()");
        };

        //put a reference to the update method into the HashMap
        updateMethods.put(StartScreenModel.Keys.HELLO_WORLD_JLABEL, updater);
        return jLabel;
    }

    JButton testButton() {
        //instantiate the object
        JButton jButton = new JButton("button Text");

        //OPTIONAL: put reference to object into HashMap
//        this.jFrames.put(StartScreenModel.Keys.HELLO_WORLD_JLABEL, jLabel);

        //create an update method
        Updater updater = () -> {
            //handle update logic here
            //for example get some data from the corresponding model
            jButton.setText("Text changed from update()");

            //update another object by refrence
            this.updateMethods.get(StartScreenModel.Keys.HELLO_WORLD_JLABEL).update();
        };

        //add action listener if needed.
        jButton.addActionListener(e -> {
            updater.update();
        });

        //put a reference to the update method into the HashMap
        updateMethods.put(StartScreenModel.Keys.TEST_BUTTON, updater);
        return jButton;
    }






    //Updates the elements of the view
    @Override
    public void updateElement(UUID uuid) {
        this.updateMethods.get(uuid).update();
    }

    public void refreshView() {
        BiConsumer<? super UUID, ? super Updater> biConsumer = (uuid, updater) -> updater.update();
        updateMethods.forEach(biConsumer);
    }

    @Override
    public Container getContainer(UUID uuid) {
        return this.jFrames.get(uuid);
    }
}
