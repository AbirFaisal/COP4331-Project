package edu.fau.eng.cop4331.ttt3d.app.startscreen;

import edu.fau.eng.cop4331.ttt3d.app.App;
import edu.fau.eng.cop4331.ttt3d.app.Controller;
import edu.fau.eng.cop4331.ttt3d.app.game.GameType;

import java.awt.event.ActionEvent;

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
        //example handler
//        Handler startSinglePlayerGameHandler = (value) -> {
////            App.getInstance().launchGame(GameType.SINGLE_PLAYER_GAME);
//            System.out.println("Start Single Player Button Pressed");
//        };
        handlers.put(model.START_SINGLE_PLAYER_GAME_BUTTON, startSinglePlayerGameHandler());
        handlers.put(model.SERVER_IP_TEXT_FIELD, setServerIPHandler());
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
    Handler setServerIPHandler(){
        return new Handler() {
            @Override
            public void handle(ActionEvent value) {
                String serverIP = value.getActionCommand(); //get the IP

                //Update the model with the IP
                model.setData(model.SERVER_IP_TEXT_FIELD,
                        new StartScreenModel.ServerInfo(
                                serverIP,""
                        ));

                System.out.println(serverIP);
            }
        };
    }

    /**
     * When the user changes the server Port
     * @return
     */
    Handler setServerPortHandler(){
        return new Handler() {
            @Override
            public void handle(ActionEvent value) {
                String serverPort = value.getActionCommand();
                System.out.println(serverPort);
            }
        };
    }


}
