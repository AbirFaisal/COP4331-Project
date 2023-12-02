package edu.fau.eng.cop4331.ttt3d.app.game;

public class MultiPlayerClientController extends GameController {


    public MultiPlayerClientController(GameModel model, GameView view) {
        super(model, view);
    }

    @Override
    void determinePlayers() {

    }

    @Override
    Handler selectGridButtonHandler() {
        return null;
    }




    //Use cases
    //When the user wants to connect to a multipleyer server
    //When the user wants to connect to single host

}
