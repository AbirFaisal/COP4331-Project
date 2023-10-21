package edu.fau.eng.cop4331.ttt3d.app.startscreen;

import edu.fau.eng.cop4331.ttt3d.app.App;
import edu.fau.eng.cop4331.ttt3d.app.Controller;
import edu.fau.eng.cop4331.ttt3d.app.View;
import edu.fau.eng.cop4331.ttt3d.app.game.GameType;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.time.Instant;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.BiConsumer;

public class StartScreenView implements View {


    //Objects of the view
    HashMap<UUID, Container> jFrames = new HashMap<>();

    //methods that are called when update is called on a UUID mapped to jFrames
    interface Updater { void update();}
    HashMap<UUID, Updater> updateMethods = new HashMap<>();

    Controller controller;

    StartScreenModel startScreenModel;

    public StartScreenView(StartScreenModel startScreenModel) {
        this.startScreenModel = startScreenModel;
        setup();
    }


    //set up the view
    void setup() {
        JPanel mainJPanel = new JPanel();
        this.jFrames.put(startScreenModel.MAIN, mainJPanel);
        mainJPanel.setLayout(new BoxLayout(mainJPanel, BoxLayout.Y_AXIS));


        this.jFrames.get(startScreenModel.MAIN).add(serverIPJTextField());

        this.jFrames.get(startScreenModel.MAIN).add(startSinglePlayerGameButton());
        this.jFrames.get(startScreenModel.MAIN).add(startMultiPlayerGameButton());
        this.jFrames.get(startScreenModel.MAIN).add(startHostGameButton());

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

            String currentTime = Instant.now().toString();
            jLabel.setText(currentTime);
        };

        //put a reference to the update method into the HashMap
        updateMethods.put(startScreenModel.HELLO_WORLD_JLABEL, updater);
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

            //update another element
            updateElement(startScreenModel.HELLO_WORLD_JLABEL);
        };

        //add action listener if needed.
        jButton.addActionListener(e -> {
            updater.update();
        });

        //put a reference to the update method into the HashMap
        updateMethods.put(startScreenModel.TEST_BUTTON, updater);
        return jButton;
    }



    JTextField serverIPJTextField() {
        JTextField serverIPTextField = new JTextField("000.000.000.000:1234");
        serverIPTextField.setMaximumSize(new Dimension(300, 25));


        Updater updater = () -> {



            StartScreenModel.ServerInfo serverInfo =
                    new StartScreenModel.ServerInfo(
                            serverIPTextField.getText(),
                            "1234"
                    );
            System.out.println(serverInfo);
        };
        serverIPTextField.getDocument().addDocumentListener(
                new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) { updater.update();}
                    @Override
                    public void removeUpdate(DocumentEvent e) { updater.update();}
                    @Override
                    public void changedUpdate(DocumentEvent e) {}
                }
        );
        return serverIPTextField;
    }

    JTextField serverPortJTextField() {
        JTextField serverPortTextField = new JTextField("0.0.0.0");

        Updater updater = () -> {
            StartScreenModel.ServerInfo serverInfo =
                    new StartScreenModel.ServerInfo(
                            serverPortTextField.getText(),
                            "1234"
                    );
            System.out.println(serverInfo);
        };
        serverPortTextField.getDocument().addDocumentListener(
                new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) { updater.update();}
                    @Override
                    public void removeUpdate(DocumentEvent e) { updater.update();}
                    @Override
                    public void changedUpdate(DocumentEvent e) {}
                }
        );
        return serverPortTextField;
    }


    JButton startSinglePlayerGameButton() {
        //instantiate the object
        JButton singlePlayerGameButton = new JButton("Single Player");

        singlePlayerGameButton.addActionListener(e -> {
            App.getInstance().launchGame(GameType.SINGLE_PLAYER_GAME);
        });
        
        return singlePlayerGameButton;
    }

    JButton startMultiPlayerGameButton() {
        //instantiate the object
        JButton multiPlayerGameButton = new JButton("Multi Player");

        //create an update method
        Updater updater = () -> {
//            App.getInstance().setMainContent();
            //TODO launch single player game
        };
        multiPlayerGameButton.addActionListener(e -> updater.update());

        //put a reference to the update method into the HashMap
        updateMethods.put(startScreenModel.TEST_BUTTON, updater);
        return multiPlayerGameButton;
    }


    JButton startHostGameButton() {
        //instantiate the object
        JButton multiPlayerGameButton = new JButton("Host Game");

        //create an update method
        Updater updater = () -> {
//            App.getInstance().setMainContent();
            //TODO launch single player game
        };
        multiPlayerGameButton.addActionListener(e -> updater.update());

        //put a reference to the update method into the HashMap
        updateMethods.put(startScreenModel.TEST_BUTTON, updater);
        return multiPlayerGameButton;
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