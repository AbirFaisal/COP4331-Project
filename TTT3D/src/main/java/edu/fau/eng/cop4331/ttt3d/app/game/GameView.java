package edu.fau.eng.cop4331.ttt3d.app.game;

import edu.fau.eng.cop4331.ttt3d.app.Controller;
import edu.fau.eng.cop4331.ttt3d.app.View;

import javax.swing.*;
import java.awt.*;
import java.time.Instant;
import java.util.HashMap;
import java.util.UUID;

public class GameView extends View {


    Controller controller;

    GameModel gameModel;


    public GameView(GameModel gameModel) {
        super(gameModel);
        this.gameModel = gameModel; //make view aware of model
        this.model.register(this); //make model aware of view
        setup(); //setup the view
    }

    @Override
    public void setup() {
        JPanel mainJPanel = new JPanel();
        this.jFrames.put(gameModel.MAIN, mainJPanel);

    }

    JLabel helloWorld() {
        JLabel jLabel = new JLabel("Hello World");
        Updater updater = () -> {
            String currentTime = Instant.now().toString();
            jLabel.setText(currentTime);
        };
        updateMethods.put(gameModel.HELLO_WORLD_JLABEL, updater);
        return jLabel;
    }


    public GameModel getGameModel() {
        return gameModel;
    }
}