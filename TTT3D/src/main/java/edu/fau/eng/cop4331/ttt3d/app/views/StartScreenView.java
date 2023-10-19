package edu.fau.eng.cop4331.ttt3d.app.views;

import edu.fau.eng.cop4331.ttt3d.app.Controller;
import edu.fau.eng.cop4331.ttt3d.app.View;
import edu.fau.eng.cop4331.ttt3d.app.models.StartScreenModel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.Consumer;

public class StartScreenView implements View {


    //Objects of the view
    HashMap<UUID, Container> jFrames = new HashMap<>();
    Controller controller;

    public StartScreenView() {
        setup();
    }

    //keys

    //set up the view
    void setup() {
        JPanel mainJPanel = new JPanel();
//        mainJPanel.setSize(800,600);

        this.jFrames.put(StartScreenModel.Keys.MAIN, mainJPanel);

        JLabel jLabel = new JLabel("Hello World");

        this.jFrames.get(StartScreenModel.Keys.MAIN).add(jLabel);

//        this.jFrames.get(StartScreenModel.Keys.MAIN).getContentPane().add(jLabel);

//        this.jFrames.get(StartScreenModel.Keys.MAIN).setVisible(true); //TODO not sure if needed
    }

//    void addChild(UUID uuid, ) {
//    }


    //Updates the elements of the view
    @Override
    public void updateView() {
        Consumer<? super Component> consumer = (Consumer<Component>) jFrame -> {
            jFrame.update(jFrame.getGraphics());
        };
//        jComponents.forEach(consumer);
    }

    @Override
    public Container getJFrame(UUID uuid) {
        return this.jFrames.get(uuid);
    }
}
