package edu.fau.eng.cop4331.ttt3d.app.game;

import edu.fau.eng.cop4331.ttt3d.app.Controller;

public class MultiPlayerClientController extends Controller {

    GameModel model;
    GameView view;

    public MultiPlayerClientController(GameModel gameModel, GameView gameView) {
        this.model = gameModel;
        this.model.register(gameView);
        this.view = gameView;
        this.view.registerController(this);
    }


    //Use cases
    //When the user wants to connect to a multipleyer server
    //When the user wants to connect to single host

}
