package edu.fau.eng.cop4331.ttt3d.app.startscreen;

import edu.fau.eng.cop4331.ttt3d.app.App;
import edu.fau.eng.cop4331.ttt3d.app.Updater;
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

    //The model that this view will reference
    //when it needs to update
    StartScreenModel model;

    /**
     * Instantiate and setup the View
     *
     * @author Abir Faisal
     * @param startScreenModel StartScreenModel
     */
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


        //put centering panel in mainJpanel
        this.jFrames.get(model.MAIN).add(centeringPanel);
    }

    /**
     * Example function for refrence purposes
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
     * Text field where the user enters the server IP
     *
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
     *
     * Text field where the user enters the server port number
     *
     * @author Abir Faisal
     * @return
     */
    JTextField serverPortJTextField() {
        JTextField jTextField = new JTextField("1234");
        jTextField.setMaximumSize(new Dimension(300, 25));
        UUID uuid = this.model.SERVER_PORT_TEXT_FIELD;

        //when the text field is changed
        //notify the controller of the change
        DocumentListener dl = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                controller.handle(uuid,
                        new ActionEvent(jTextField, 0, jTextField.getText())
                );
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                controller.handle(uuid,
                        new ActionEvent(jTextField, 0, jTextField.getText())
                );
            }
            @Override
            public void changedUpdate(DocumentEvent e) {}
        };
        jTextField.getDocument().addDocumentListener(dl);


        //updates the UI if there is a change in the Model
        Updater updater = () -> {

            //get the data from the model as ServerInfo
            StartScreenModel.ServerPort port =
                    (StartScreenModel.ServerPort) this.model.getData(uuid);

            //if the text is different then update it, else do nothing
            if (!jTextField.getText().equals(port.port())) {
                //set text without triggering event
                jTextField.getDocument().removeDocumentListener(dl);
                jTextField.setText(port.port());
                //restore the change listener
                jTextField.getDocument().addDocumentListener(dl);
            }


        };
        this.updateMethods.put(uuid, updater);

        return jTextField;
    }



    //TODO convert to a loop

    /**
     *  Button that starts a single player game
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
     * Button that starts a multi player game
     *
     * @author Abir Faisal
     * @return
     */
    JButton startMultiPlayerGameButton() {
        //instantiate the button
        JButton jButton = new JButton("Multi Player");
        UUID uuid = this.model.START_MULTI_PLAYER_GAME_BUTTON;

        jButton.addActionListener(actionEvent -> {
            this.controller.handle(uuid, actionEvent);
        });
        return jButton;
    }

    /**
     * Button that starts a hosting a game for one other player
     *
     * @author Abir Faisal
     * @return
     */
    JButton startHostGameButton() {
        //instantiate the button
        JButton jButton = new JButton("Host Game");
        UUID uuid = this.model.START_MULTI_HOST_GAME_BUTTON;

        jButton.addActionListener(actionEvent -> {
            this.controller.handle(uuid, actionEvent);
        });

        return jButton;
    }
}
