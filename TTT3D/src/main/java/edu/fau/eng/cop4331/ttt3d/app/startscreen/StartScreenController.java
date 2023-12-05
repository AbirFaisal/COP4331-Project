package edu.fau.eng.cop4331.ttt3d.app.startscreen;

import edu.fau.eng.cop4331.ttt3d.app.App;
import edu.fau.eng.cop4331.ttt3d.app.Controller;
import edu.fau.eng.cop4331.ttt3d.app.game.GameType;
import edu.fau.eng.cop4331.ttt3d.util.SettingsManager;

import java.awt.event.ActionEvent;
import java.util.UUID;

public class StartScreenController extends Controller {

    StartScreenModel model;
    StartScreenView view;

    public StartScreenController(StartScreenModel scm, StartScreenView scv) {
        this.model = scm;
        this.view = scv;
        this.view.registerController(this);

        runHandlers();
        System.out.println("running event handlers");
        setup();
    }

    void setup() {
        handlers.put(model.START_SINGLE_PLAYER_GAME_BUTTON, startSinglePlayerGameHandler());
        handlers.put(model.START_MULTI_PLAYER_GAME_BUTTON, startMultiPlayerGameHandler());
        handlers.put(model.START_MULTI_HOST_GAME_BUTTON, startHostGameHandler());

        handlers.put(model.SERVER_IP_TEXT_FIELD, serverIPInfoUpdateHandler());
        handlers.put(model.SERVER_PORT_TEXT_FIELD, serverPortUpdateHandler());

        //load settings
        SettingsManager sm = SettingsManager.getInstance();
        String ipAddress = sm.getSettings().getString("userDefinedServer");
        String port = sm.getSettings().getString("userDefinedPort");

        //data type used by model
        StartScreenModel.ServerIP serverIP = new StartScreenModel.ServerIP(ipAddress);
        StartScreenModel.ServerPort serverPort = new StartScreenModel.ServerPort(port);

        //set the data in the model
        this.model.setData(model.SERVER_IP_TEXT_FIELD, serverIP);
        this.model.setData(model.SERVER_PORT_TEXT_FIELD, serverPort);

    }


    //Action handlers
    /**
     *
     * @return a Handler that launches a single player game
     */
    Handler startSinglePlayerGameHandler() {
        StartScreenController instance = StartScreenController.this;

        return new Handler() {
            @Override
            public void handle(ActionEvent value) {
                System.out.println("Start Single Player Button Pressed");
                //save the settings
                saveUserSettings();
                //launch the game
                App.getInstance().launchGame(GameType.SINGLE_PLAYER_GAME);
            }
        };
    }

    /**
     *
     * @return a Handler that launches a single player game
     */
    Handler startMultiPlayerGameHandler() {
        StartScreenController instance = StartScreenController.this;

        return new Handler() {
            @Override
            public void handle(ActionEvent value) {
                System.out.println("Start Multi Player Button Pressed");
                //save the settings
                saveUserSettings();
                //launch the game
                App.getInstance().launchGame(GameType.MULTI_PLAYER_CLIENT_GAME);
            }
        };
    }

    /**
     *
     * @return a Handler that launches a single player game
     */
    Handler startHostGameHandler() {
        StartScreenController instance = StartScreenController.this;

        return new Handler() {
            @Override
            public void handle(ActionEvent value) {
                System.out.println("Start Host Game Button Pressed");
                //save the settings
                saveUserSettings();
                //launch the game
                App.getInstance().launchGame(GameType.MULTI_PLAYER_HOST_GAME);
            }
        };
    }



    /**
     * When the user changes the server IP
     * @return A handler that updates the model with a new value
     */
    Handler serverIPInfoUpdateHandler(){
        UUID uuid = model.SERVER_IP_TEXT_FIELD;
        StartScreenController instance = StartScreenController.this;

        return new Handler() {
            @Override
            public void handle(ActionEvent value) {
                String serverIP = value.getActionCommand(); //get the IP:Port
                //Update the model with the IP
                StartScreenController.this.model.setData(uuid, new StartScreenModel.ServerIP(serverIP));
                System.out.println(instance.model.getData(uuid));
            }
        };
    }

    /**
     * When the user changes the server Port
     * @return A handler that updates the model with a new value
     */
    Handler serverPortUpdateHandler(){
        UUID uuid = model.SERVER_PORT_TEXT_FIELD;
        StartScreenController instance = StartScreenController.this;

        return new Handler() {
            @Override
            public void handle(ActionEvent value) {
                String serverPort = value.getActionCommand();
                //update the model with the port
                model.setData(uuid, new StartScreenModel.ServerPort(serverPort));
                System.out.println(instance.model.getData(uuid));
            }
        };
    }


    //controller logic

    void saveUserSettings() {
        StartScreenController instance = StartScreenController.this;


        //save the settings
        StartScreenModel.ServerIP serverIPRecord =
                (StartScreenModel.ServerIP) instance.model.getData(model.SERVER_IP_TEXT_FIELD);

        StartScreenModel.ServerPort serverPortRecord =
                (StartScreenModel.ServerPort) instance.model.getData(model.SERVER_PORT_TEXT_FIELD);

        SettingsManager.getInstance().setValue("userDefinedServer", serverIPRecord.ipAddress());
        SettingsManager.getInstance().setValue("userDefinedPort", serverPortRecord.port());


    }

}
