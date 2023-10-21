package edu.fau.eng.cop4331.ttt3d.app.startscreen;

import edu.fau.eng.cop4331.ttt3d.app.App;
import edu.fau.eng.cop4331.ttt3d.app.Controller;
import edu.fau.eng.cop4331.ttt3d.app.Model;
import edu.fau.eng.cop4331.ttt3d.app.View;
import edu.fau.eng.cop4331.ttt3d.app.game.GameType;

import java.awt.event.ActionEvent;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.Consumer;

import static java.lang.Thread.sleep;

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
        Handler startSinglePlayerGameHandler = value -> {
//            App.getInstance().launchGame(GameType.SINGLE_PLAYER_GAME);
            System.out.println("Button Pressed");
        };
        handlers.put(model.START_SINGLE_PLAYER_GAME_BUTTON, startSinglePlayerGameHandler);
        System.out.println("SSPGB=" + model.START_SINGLE_PLAYER_GAME_BUTTON);

    }

}
