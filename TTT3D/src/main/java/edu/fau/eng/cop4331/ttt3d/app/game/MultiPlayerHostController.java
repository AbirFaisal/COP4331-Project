package edu.fau.eng.cop4331.ttt3d.app.game;

import edu.fau.eng.cop4331.ttt3d.app.Controller;

public class MultiPlayerHostController extends Controller {

    GameModel model;
    GameView view;

    public MultiPlayerHostController(GameModel gameModel, GameView gameView) {
        this.model = gameModel;
        this.model.register(gameView);
        this.view = gameView;
        this.view.registerController(this);

        setup();
    }

    void setup(){

    };


    //used when hosting a game for another player and yourself

}
