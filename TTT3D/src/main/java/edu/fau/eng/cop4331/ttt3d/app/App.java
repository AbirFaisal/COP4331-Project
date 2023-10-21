package edu.fau.eng.cop4331.ttt3d.app;

import edu.fau.eng.cop4331.ttt3d.app.startscreen.StartScreenController;
import edu.fau.eng.cop4331.ttt3d.app.startscreen.StartScreenModel;
import edu.fau.eng.cop4331.ttt3d.app.startscreen.StartScreenView;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class App {
    JFrame mainWindow;
    private byte[] playerID; //128 bit player id
    private Model model;
    private View view;
    private Controller controller;

    //TODO make into singleton pattern
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

        //TODO move into a launch(Model, View, Controller) method
        StartScreenModel startScreenModel = new StartScreenModel();
        StartScreenView startScreenView = new StartScreenView(startScreenModel);
        StartScreenController startScreenController = new StartScreenController(startScreenModel, startScreenView);

        setMainWindow(
                startScreenView.getContainer(startScreenModel.MAIN)
        );


        //TODO move into a test
        StartScreenModel startScreenModel0 = new StartScreenModel();
        StartScreenModel startScreenModel1 = new StartScreenModel();
        System.out.println(startScreenModel0.MAIN.toString());
        System.out.println(startScreenModel1.MAIN.toString());
        assert !(startScreenModel0.equals(startScreenModel1)) : "UUID's not unique";

        this.mainWindow.setSize(800,600);//400 width and 500 height
        this.mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ///TODO this.mainWindow.setLayout();
        this.mainWindow.setVisible(true);
    }

    //set the content of the main window
    public void setMainWindow(Container c) {
        this.mainWindow.setContentPane(c);
    }

    /**
     * run the application
     *
     */
    public void run() {

    }

    public App getInstance(){
        return this;
    }


    public byte[] getPlayerID() {
        return playerID;
    }
}

