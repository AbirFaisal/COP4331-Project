package edu.fau.eng.cop4331.ttt3d.app.game;

import edu.fau.eng.cop4331.ttt3d.app.Controller;
import edu.fau.eng.cop4331.ttt3d.app.View;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.UUID;

public class GameView extends View {


    Controller controller;

    GameModel gameModel;


    public GameView(GameModel gameModel) {
        super(gameModel);
        this.gameModel = gameModel;
        //TODO register updaters with model
    }

    @Override
    public void setup() {

    }



}