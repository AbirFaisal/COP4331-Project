package edu.fau.eng.cop4331.ttt3d.app.startscreen;

import edu.fau.eng.cop4331.ttt3d.app.App;
import edu.fau.eng.cop4331.ttt3d.app.View;
import edu.fau.eng.cop4331.ttt3d.app.game.GameType;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.Instant;
import java.util.UUID;

public class StartScreenView extends View {

    //The model that this view will refrence
    StartScreenModel model;

    public StartScreenView(StartScreenModel startScreenModel) {
        this.model = startScreenModel; //make view aware of model
        this.model.register(this); //make model aware of view
        setup(); //setup the view
    }

    //set up the view
    @Override
    public void setup() {
        JPanel mainJPanel = new JPanel();
        this.jFrames.put(model.MAIN, mainJPanel);

        //centering panel for asthetic purposes.
        JPanel centeringPanel = new JPanel();
        centeringPanel.setLayout(new BoxLayout(centeringPanel, BoxLayout.Y_AXIS));

        centeringPanel.add(new JLabel("Server IP"));
        centeringPanel.add(serverIPJTextField());

        centeringPanel.add(new JLabel("Server Port"));
        centeringPanel.add(serverPortJTextField());

        centeringPanel.add(startSinglePlayerGameButton());
        centeringPanel.add(startMultiPlayerGameButton());
        centeringPanel.add(startHostGameButton());


        this.jFrames.get(model.MAIN).add(centeringPanel);

        //add elemets of this view
//        this.jFrames.get(model.MAIN).add(serverIPJTextField());
//        this.jFrames.get(model.MAIN).add(serverPortJTextField());
//        this.jFrames.get(model.MAIN).add(startSinglePlayerGameButton());
//        this.jFrames.get(model.MAIN).add(startMultiPlayerGameButton());
//        this.jFrames.get(model.MAIN).add(startHostGameButton());
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

    //example button
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

    //NOTE: Try to keep these methods in order as they appear visually

    /**
     * @author Abir Faisal
     * @return a JTextField for the user to type in the server IP and port
     */
    JTextField serverIPJTextField() {
        JTextField serverIPTextField = new JTextField("0.0.0.0");
        serverIPTextField.setMaximumSize(new Dimension(300, 25));
        UUID uuid = this.model.SERVER_IP_TEXT_FIELD;

        //when the text field is changed
        //notify the controller of the change
        DocumentListener dl1 = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                controller.handle(uuid,
                        new ActionEvent(serverIPTextField, 0, serverIPTextField.getText())
                );
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                controller.handle(uuid,
                        new ActionEvent(serverIPTextField, 0, serverIPTextField.getText())
                );
            }
            @Override
            public void changedUpdate(DocumentEvent e) {}
        };
        serverIPTextField.getDocument().addDocumentListener(dl1);

        //updates the UI if there is a change in the Model
        Updater updater = () -> {

            //get the data from the model as ServerInfo
            StartScreenModel.ServerIP ip = (StartScreenModel.ServerIP) this.model.getData(uuid);

            //if the text is different then update it, else do nothing
            if (!serverIPTextField.getText().equals(ip.ipAddress())) {
                //set text without triggering listner
                serverIPTextField.getDocument().removeDocumentListener(dl1);
                serverIPTextField.setText(ip.ipAddress());
                //restore the change listener
                serverIPTextField.getDocument().addDocumentListener(dl1);
            }
        };
        this.updateMethods.put(uuid, updater);

        return serverIPTextField;
    }

    /**
     * @author Abir Faisal
     * @return
     */
    JTextField serverPortJTextField() {
        JTextField serverPortTextField = new JTextField("1234");
        serverPortTextField.setMaximumSize(new Dimension(300, 25));
        UUID uuid = this.model.SERVER_PORT_TEXT_FIELD;

        //when the text field is changed
        //notify the controller of the change
        DocumentListener dl = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                controller.handle(uuid,
                        new ActionEvent(serverPortTextField, 0, serverPortTextField.getText())
                );
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                controller.handle(uuid,
                        new ActionEvent(serverPortTextField, 0, serverPortTextField.getText())
                );
            }
            @Override
            public void changedUpdate(DocumentEvent e) {}
        };
        serverPortTextField.getDocument().addDocumentListener(dl);


        //updates the UI if there is a change in the Model
        Updater updater = () -> {

            //get the data from the model as ServerInfo
            StartScreenModel.ServerPort port = (StartScreenModel.ServerPort) this.model.getData(uuid);

            //if the text is different then update it, else do nothing
            if (!serverPortTextField.getText().equals(port.port())) {
                //set text without triggering event
                serverPortTextField.getDocument().removeDocumentListener(dl);
                serverPortTextField.setText(port.port());
                //restore the change listener
                serverPortTextField.getDocument().addDocumentListener(dl);
            }


        };
        this.updateMethods.put(uuid, updater);

        return serverPortTextField;
    }



    //TODO convert to a loop

    /**
     *
     * @author Abir Faisal
     * @return
     */
    JButton startSinglePlayerGameButton() {
        //instantiate the button
        JButton jButton = new JButton("Single Player");
        UUID uuid = this.model.START_SINGLE_PLAYER_GAME_BUTTON;

        jButton.addActionListener(actionEvent -> {
            this.controller.handle(uuid, actionEvent);
        });

        return jButton;
    }

    /**
     *
     * @author Abir Faisal
     * @return
     */
    JButton startMultiPlayerGameButton() {
        //instantiate the button
        JButton multiPlayerGameButton = new JButton("Multi Player");
        UUID uuid = this.model.START_MULTI_PLAYER_GAME_BUTTON;

        //TODO change to proper implimentation
        multiPlayerGameButton.addActionListener(e -> {
            App.getInstance().launchGame(GameType.MULTI_PLAYER_CLIENT_GAME);
        });
        return multiPlayerGameButton;
    }

    /**
     *
     * @author Abir Faisal
     * @return
     */
    JButton startHostGameButton() {
        //instantiate the button
        JButton multiPlayerHostGameButton = new JButton("Host Game");
        UUID uuid = this.model.START_MULTI_HOST_GAME_BUTTON;

        //TODO change to proper implimentation
        multiPlayerHostGameButton.addActionListener(e -> {
            App.getInstance().launchGame(GameType.MULTI_PLAYER_HOST_GAME);
        });

        return multiPlayerHostGameButton;
    }
}
