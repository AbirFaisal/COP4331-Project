package edu.fau.eng.cop4331.ttt3d.app.game;

import edu.fau.eng.cop4331.ttt3d.app.Controller;

import java.awt.event.ActionEvent;
import java.util.UUID;

public class MultiPlayerHostController extends GameController {


    public MultiPlayerHostController(GameModel model, GameView view) {
        super(model, view);
    }

    @Override
    void determinePlayers(String user1, String user2) {

    }

    @Override
    String selectGridButton(String player) {
        return null;
    }



    //used when hosting a game for another player and yourself

}
