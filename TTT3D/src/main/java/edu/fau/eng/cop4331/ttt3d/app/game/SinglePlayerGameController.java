package edu.fau.eng.cop4331.ttt3d.app.game;

import java.util.Random;

public class SinglePlayerGameController extends GameController {


    public SinglePlayerGameController(GameModel model, GameView view) {
        super(model, view);
    }

    @Override
    void determinePlayers(String user1, String user2) {
        Random flip = new Random();

        if (flip.nextInt(2) == 0) {
            this.playerX = user1;
            System.out.println("You are 'X'\nYou are first");
        }else{
            this.playerX = user2;
            System.out.println("You are 'O'\nYou are second");
        }
    }

    @Override
    String selectGridButton(String player) {
        if (playerX.equals(player)){
            return "X";
        }else{
            return "O";
        }
    }


    //controller that connects the view with a single player game model



}
