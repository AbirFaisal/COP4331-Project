package edu.fau.eng.cop4331.ttt3d.app.game;

import edu.fau.eng.cop4331.ttt3d.app.App;
import edu.fau.eng.cop4331.ttt3d.app.Controller;

public abstract class GameController extends Controller {
    GameModel model;
    GameView view;

    boolean isTurn = false;

    public GameController(GameModel model,GameView view){
        this.model = model;
        this.view = view;
        this.view.registerController(this);

        determinePlayers();

        runHandlers();
        System.out.println("running game event handlers");
//        handlers.put(model.PLAYER, selectGridButtonHandler(model.getPlayerName()));

    }

    abstract Handler selectGridButtonHandler();

    void gameCycle(){
//        determinePlayers();
    }

    abstract void determinePlayers();
}
