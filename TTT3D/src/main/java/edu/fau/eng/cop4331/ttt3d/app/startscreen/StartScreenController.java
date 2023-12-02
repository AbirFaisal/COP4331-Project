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
    }


    //Action handlers
    /**
     *
     * @return a Handler that launches a single player game
     */
    Handler startSinglePlayerGameHandler() {
        return new Handler() {
            @Override
            public String handle(ActionEvent value) {
                System.out.println("Start Single Player Button Pressed");
                App.getInstance().launchGame(GameType.SINGLE_PLAYER_GAME);
                return null;
            }
        };
    }


}
