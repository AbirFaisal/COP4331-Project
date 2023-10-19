package edu.fau.eng.cop4331.ttt3d.app;

import edu.fau.eng.cop4331.ttt3d.app.controllers.StartScreenController;
import edu.fau.eng.cop4331.ttt3d.app.models.StartScreenModel;
import edu.fau.eng.cop4331.ttt3d.app.views.StartScreenView;

import javax.swing.*;
import java.util.Random;


public class App {
    JFrame mainWindow;
    private byte[] playerID; //128 bit player id
    private Model model;
    private View view;
    private Controller controller;
    
    public App() {
        this.playerID = new byte[16]; //TODO load from a configuration
        Random r = new Random();
        r.nextBytes(this.playerID);

        this.mainWindow = new JFrame("Main Window");
        setup();
    }

    /**
     * Set up the components of the main window
     *
     */
    public void setup() {


        StartScreenModel startScreenModel = new StartScreenModel();
        StartScreenView startScreenView = new StartScreenView();
        StartScreenController startScreenController = new StartScreenController(startScreenModel, startScreenView);

        this.mainWindow.add(
                startScreenView.getJFrame(StartScreenModel.Keys.MAIN)
        );

        this.mainWindow.setSize(800,600);//400 width and 500 height
        ///TODO this.mainWindow.setLayout();
        this.mainWindow.setVisible(true);
    }

    /**
     * run the application
     *
     */
    public void run() {

    }


    public byte[] getPlayerID() {
        return playerID;
    }
}

