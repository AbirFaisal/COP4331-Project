package edu.fau.eng.cop4331.ttt3d.app.game;

import edu.fau.eng.cop4331.ttt3d.app.App;
import edu.fau.eng.cop4331.ttt3d.app.Controller;

public abstract class GameController extends Controller {
    GameModel model;
    GameView view;

    String playerX = null;
    boolean isTurn = false;
    TileState grid;

    public GameController(GameModel model,GameView view){
        this.model = model;
        this.view = view;
        this.view.registerController(this);

        runHandlers();
        System.out.println("running game event handlers");

    }

    abstract void determinePlayers(String user1, String user2);
    abstract String selectGridButton(String player);

    void gameCycle(){

    }

}
