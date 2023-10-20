package edu.fau.eng.cop4331.ttt3d.app;

import edu.fau.eng.cop4331.ttt3d.app.controllers.StartScreenController;
import edu.fau.eng.cop4331.ttt3d.app.models.StartScreenModel;
import edu.fau.eng.cop4331.ttt3d.app.views.StartScreenView;

import javax.swing.*;
import java.util.Random;

import static java.lang.System.exit;


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
        StartScreenView startScreenView = new StartScreenView(startScreenModel);

        StartScreenController startScreenController = new StartScreenController(startScreenModel, startScreenView);

        this.mainWindow.add(
                startScreenView.getContainer(startScreenModel.MAIN)
        );


        StartScreenModel startScreenModel0 = new StartScreenModel();
        StartScreenModel startScreenModel1 = new StartScreenModel();

        System.out.println(startScreenModel0.MAIN.toString());
        System.out.println(startScreenModel0.MAIN.toString());
        System.out.println(startScreenModel1.MAIN.toString());

//        exit(0);

        //TODO move into a test class
//        startScreenView.refreshView();
//        startScreenView.updateView(StartScreenModel.Keys.HELLO_WORLD_JLABEL);

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

