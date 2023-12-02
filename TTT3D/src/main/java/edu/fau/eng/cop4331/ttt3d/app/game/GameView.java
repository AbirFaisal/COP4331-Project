package edu.fau.eng.cop4331.ttt3d.app.game;

import edu.fau.eng.cop4331.ttt3d.app.View;

import javax.swing.*;
import java.awt.*;
import java.time.Instant;
import java.util.HashMap;
import java.util.UUID;

public class GameView extends View {

    GameModel model;

    public GameView(GameModel gameModel) {
        super(gameModel);
        this.model = gameModel; //make view aware of model
        setup(); //setup the view
    }

    @Override
    public void setup() {
//        gameModel.createGameBoard();

        JPanel mainJPanel = new JPanel();
        this.jFrames.put(model.MAIN, mainJPanel);
        mainJPanel.setLayout(new BoxLayout(mainJPanel, BoxLayout.Y_AXIS));

//        this.jFrames.get(model.MAIN).add(helloWorld());
        this.jFrames.get(this.model.MAIN).add(gridButton(0,0,0));
    }

    JButton gridButton(int x, int y, int z) {
        //instantiate the button
        JButton gridButton = new JButton("⬜");

        gridButton.addActionListener(e -> {
            model.setActiveButton(x,y,z);
//            this.controller.handle(model.PLAYER, e);
            model.selectGridButton();
            gridButton.setText(model.getTileString());
        });
        return gridButton;
    }

    JLabel helloWorld() {
        JLabel jLabel = new JLabel("Hello World");
        Updater updater = () -> {
            String currentTime = Instant.now().toString();
            jLabel.setText(currentTime);
        };
        updateMethods.put(model.HELLO_WORLD_JLABEL, updater);
        return jLabel;
    }


    public GameModel getGameModel() {
        return model;
    }
}