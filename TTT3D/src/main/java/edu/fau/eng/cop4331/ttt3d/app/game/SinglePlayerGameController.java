package edu.fau.eng.cop4331.ttt3d.app.game;

import java.awt.event.ActionEvent;
import java.util.Random;

public class SinglePlayerGameController extends GameController {


    public SinglePlayerGameController(GameModel model, GameView view) {
        super(model, view);
    }

    @Override
    void determinePlayers() {
        Random r = new Random();
        if (r.nextInt(2) == 0) {
            model.setPlayers(true);
            System.out.println("You are 'X'\nYou are first");
        }else{
            model.setPlayers(false);
            System.out.println("You are 'O'\nYou are second");
        }
    }

    @Override
    Handler selectGridButtonHandler() {
        return new Handler() {
            @Override
            public String handle(ActionEvent event) {
                return null;
            }
        };

    }



    //controller that connects the view with a single player game model



}
