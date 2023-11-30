package edu.fau.eng.cop4331.ttt3d.app.game;

import edu.fau.eng.cop4331.ttt3d.app.Controller;
import edu.fau.eng.cop4331.ttt3d.app.View;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.UUID;

public class GameView extends View {


    GameController controller;

    GameModel gameModel;


    public GameView(GameModel gameModel, GameController controller) {
        this.gameModel = gameModel;
        //TODO register updaters with model

        this.controller = controller;
    }

    @Override
    public void setup() {

    }

    JButton gridButton(int x, int y, int z) {
        //instantiate the button
        JButton gridButton = new JButton("⬜");

        gridButton.addActionListener(e -> {
            controller.selectGridButton(x,y,z,gameModel.getPlayerName());
        });
        return gridButton;
    }

}