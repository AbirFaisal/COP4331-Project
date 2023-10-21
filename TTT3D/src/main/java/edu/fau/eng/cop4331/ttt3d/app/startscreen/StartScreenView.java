package edu.fau.eng.cop4331.ttt3d.app.startscreen;

import edu.fau.eng.cop4331.ttt3d.app.App;
import edu.fau.eng.cop4331.ttt3d.app.Controller;
import edu.fau.eng.cop4331.ttt3d.app.Model;
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

public class StartScreenView extends View {


    Controller controller;

    StartScreenModel model;

    public StartScreenView(StartScreenModel startScreenModel) {
        this.model = startScreenModel;
        this.model.register(this);
        setup(); //TODO this should be called elsewhere?
    }

    //set up the view
    @Override
    public void setup() {
        JPanel mainJPanel = new JPanel();
        this.jFrames.put(model.MAIN, mainJPanel);
        mainJPanel.setLayout(new BoxLayout(mainJPanel, BoxLayout.Y_AXIS));


        this.jFrames.get(model.MAIN).add(serverIPJTextField());

        this.jFrames.get(model.MAIN).add(startSinglePlayerGameButton());
        this.jFrames.get(model.MAIN).add(startMultiPlayerGameButton());
        this.jFrames.get(model.MAIN).add(startHostGameButton());

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
        updateMethods.put(model.HELLO_WORLD_JLABEL, updater);
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
            updateElement(model.HELLO_WORLD_JLABEL);
        };

        //add action listener if needed.
        jButton.addActionListener(e -> {
            updater.update();
        });

        //put a reference to the update method into the HashMap
        updateMethods.put(model.TEST_BUTTON, updater);
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



    //TODO convert to a loop
    JButton startSinglePlayerGameButton() {
        //instantiate the button
        JButton singlePlayerGameButton = new JButton("Single Player");

        singlePlayerGameButton.addActionListener(e -> {
            App.getInstance().launchGame(GameType.SINGLE_PLAYER_GAME);
        });

        return singlePlayerGameButton;
    }
    JButton startMultiPlayerGameButton() {
        //instantiate the button
        JButton multiPlayerGameButton = new JButton("Multi Player");

        multiPlayerGameButton.addActionListener(e -> {
            App.getInstance().launchGame(GameType.MULTI_PLAYER_CLIENT_GAME);
        });
        return multiPlayerGameButton;
    }
    JButton startHostGameButton() {
        //instantiate the button
        JButton multiPlayerHostGameButton = new JButton("Host Game");

        multiPlayerHostGameButton.addActionListener(e -> {
            App.getInstance().launchGame(GameType.MULTI_PLAYER_HOST_GAME);
        });

        return multiPlayerHostGameButton;
    }
}
