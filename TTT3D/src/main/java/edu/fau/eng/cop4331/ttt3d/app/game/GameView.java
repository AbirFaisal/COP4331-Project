package edu.fau.eng.cop4331.ttt3d.app.game;

import edu.fau.eng.cop4331.ttt3d.app.View;

import javax.swing.*;

public class GameView extends View {


    GameController controller;

    GameModel gameModel;


    public GameView(GameModel gameModel) {
        this.gameModel = gameModel;
        this.gameModel.register(this);
        //TODO register updaters with model

        setup();
    }

    @Override
    public void setup() {
//        gameModel.createGameBoard();
        JPanel gameJPanel = new JPanel();
        this.jFrames.put(gameModel.MAIN, gameJPanel);
        gameJPanel.setLayout(new BoxLayout(gameJPanel, BoxLayout.Y_AXIS));

        this.jFrames.get(gameModel.MAIN).add(gridButton(0,0,0));

    }

    JButton gridButton(int x, int y, int z) {
        //instantiate the button
        JButton gridButton = new JButton("⬜");

        gridButton.addActionListener(e -> {
            gridButton.setText(controller.selectGridButton(gameModel.getPlayerName()));
        });
        return gridButton;
    }

}