package edu.fau.eng.cop4331.ttt3d.app.startscreen;

import edu.fau.eng.cop4331.ttt3d.app.App;
import edu.fau.eng.cop4331.ttt3d.app.Controller;
import edu.fau.eng.cop4331.ttt3d.app.game.GameType;

import java.awt.event.ActionEvent;
import java.util.UUID;

public class StartScreenController extends Controller {


    StartScreenModel model;
    StartScreenView view;

    public StartScreenController(StartScreenModel startScreenModel, StartScreenView startScreenView) {
        this.model = startScreenModel;
        this.view = startScreenView;
        this.view.registerController(this);

        runHandlers();
        System.out.println("running event handlers");
        setup();
    }

    void setup() {
        handlers.put(model.START_SINGLE_PLAYER_GAME_BUTTON, startSinglePlayerGameHandler());
        handlers.put(model.SERVER_IP_TEXT_FIELD, serverIPInfoUpdateHandler());
        handlers.put(model.SERVER_PORT_TEXT_FIELD, serverPortUpdateHandler());
    }


    //Action handlers
    /**
     *
     * @return a Handler that launches a single player game
     */
    Handler startSinglePlayerGameHandler() {
        return new Handler() {
            @Override
            public void handle(ActionEvent value) {
                System.out.println("Start Single Player Button Pressed");
                App.getInstance().launchGame(GameType.SINGLE_PLAYER_GAME);
            }
        };
    }

    /**
     * When the user changes the server IP
     * @return
     */
    Handler serverIPInfoUpdateHandler(){
        UUID uuid = model.SERVER_IP_TEXT_FIELD;
        return new Handler() {
            @Override
            public void handle(ActionEvent value) {
                String serverIP = value.getActionCommand(); //get the IP:Port
                //Update the model with the IP
                model.setData(uuid, new StartScreenModel.ServerIP(serverIP));
                System.out.println(model.getData(uuid));
            }
        };
    }

    /**
     * When the user changes the server Port
     * @return
     */
    Handler serverPortUpdateHandler(){
        UUID uuid = model.SERVER_PORT_TEXT_FIELD;
        return new Handler() {
            @Override
            public void handle(ActionEvent value) {
                String serverPort = value.getActionCommand();
                //update the model with the port
                model.setData(uuid, new StartScreenModel.ServerPort(serverPort));
                System.out.println(model.getData(uuid));
            }
        };
    }

}
