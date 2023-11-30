package edu.fau.eng.cop4331.ttt3d.app.startscreen;

import edu.fau.eng.cop4331.ttt3d.app.Controller;

public class StartScreenController extends Controller {


    StartScreenModel model;
    StartScreenView view;

    public StartScreenController(StartScreenModel startScreenModel, StartScreenView startScreenView) {
        this.model = startScreenModel;
        this.view = startScreenView;
        this.view.registerController(this);

        runHandlers();
        System.out.println("running event handlers");

        //example handler
        Handler startSinglePlayerGameHandler = (value) -> {
//            App.getInstance().launchGame(GameType.SINGLE_PLAYER_GAME);
            System.out.println("Button Pressed");
        };
        handlers.put(model.START_SINGLE_PLAYER_GAME_BUTTON, startSinglePlayerGameHandler);

    }

}
