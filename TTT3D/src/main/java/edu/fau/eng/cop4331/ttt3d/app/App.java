package edu.fau.eng.cop4331.ttt3d.app;

import edu.fau.eng.cop4331.ttt3d.app.game.GameModel;
import edu.fau.eng.cop4331.ttt3d.app.game.GameType;
import edu.fau.eng.cop4331.ttt3d.app.game.GameView;
import edu.fau.eng.cop4331.ttt3d.app.game.SinglePlayerGameController;
import edu.fau.eng.cop4331.ttt3d.app.startscreen.StartScreenController;
import edu.fau.eng.cop4331.ttt3d.app.startscreen.StartScreenModel;
import edu.fau.eng.cop4331.ttt3d.app.startscreen.StartScreenView;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static java.lang.System.exit;


public class App {
    JFrame mainWindow;
    private byte[] clientID; //128 bit client id
    private Model model;
    private View view;
    private Controller controller;

    //Singleton Pattern
    private static App instance;
    private App() {
        this.mainWindow = new JFrame("Main Window");
        this.clientID = getClientID();
    }
    public static synchronized App getInstance() {
        if (instance == null) instance = new App();
        return instance;
    }

    /**
     * Set up the components of the main window and/or application
     *
     */
    public void setup() {

        //TODO move into a launch(Model, View, Controller) method
        StartScreenModel startScreenModel = new StartScreenModel();
        StartScreenView startScreenView = new StartScreenView(startScreenModel);
        StartScreenController startScreenController = new StartScreenController(startScreenModel, startScreenView);

        setMainWindowContent(
                startScreenView.getContainer(startScreenModel.MAIN)
        );

        this.mainWindow.setSize(800,600);//400 width and 500 height
        this.mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ///TODO this.mainWindow.setLayout();
        this.mainWindow.setVisible(true);
    }

    //set the content of the main window
    public void setMainWindowContent(Container c) {
        this.mainWindow.getContentPane().removeAll();
        this.mainWindow.setContentPane(c);
        this.mainWindow.revalidate();
    }



    /**
     * run the application
     *
     */
    public void run() {

    }

    public void launchGame(GameType gameType) {
        GameModel gameModel = new GameModel();
        System.out.println(gameModel.MAIN); //TODO remove
        GameView gameView = new GameView(gameModel);

        switch (gameType) {
            case SINGLE_PLAYER_GAME -> {
                SinglePlayerGameController gameController = new SinglePlayerGameController(gameModel, gameView);
                setMainWindowContent(gameView.getContainer(gameModel.MAIN));
            }
            case MULTI_PLAYER_CLIENT_GAME -> {}
            case MULTI_PLAYER_HOST_GAME -> {}
        }

        System.out.println("Launching Game " + gameType);
    }


    /**
     * Generate a client ID or try to load from settings.json
     * @return 128bit Client ID
     */
    public byte[] getClientID() {
        this.clientID = new byte[16]; //TODO load from a configuration
        Random r = new Random();
        r.nextBytes(this.clientID);
        return clientID;
    }


}

