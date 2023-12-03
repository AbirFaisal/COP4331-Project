package edu.fau.eng.cop4331.ttt3d.app.game;

import edu.fau.eng.cop4331.ttt3d.app.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.Instant;
import java.util.UUID;

public class GameView extends View {

    GameModel model;

    public GameView(GameModel gameModel) {
        super(gameModel);
        this.model = gameModel; //make view aware of model
        this.model.register(this); //make model aware of view
        setup(); //setup the view
    }

    @Override
    public void setup() {
        JPanel mainJPanel = new JPanel();
        this.jFrames.put(model.MAIN, mainJPanel);
//        mainJPanel.setLayout(new BoxLayout(mainJPanel, BoxLayout.Y_AXIS));

        this.jFrames.get(model.MAIN).add(helloWorld());
        this.jFrames.get(model.MAIN).add(xyButtonGrid(0));
    }




    //UI elements
    JLabel helloWorld() {
        JLabel jLabel = new JLabel("Hello World");
        //called by the model when it's corresponding values change
        Updater updater = () -> {
            String currentTime = Instant.now().toString();
            jLabel.setText(currentTime);
        };
        updateMethods.put(model.HELLO_WORLD_JLABEL, updater);
        return jLabel;
    }

    /**
     * Grid that contains 3x3 button array
     * The l value is used to deterimine
     * which layer of the cube this grid corresponds to
     *
     * @param layer the layer
     * @return the grid
     */
    JPanel xyButtonGrid(int layer) {
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(3,3));
        UUID uuid = this.model.BUTTON_GRID;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonText = i + "," + j;
                JButton jButton = new JButton(buttonText);
                jButton.setPreferredSize(new Dimension(50,50));

                jButton.addActionListener(e -> {
                    //event to be passed to the controller
                    ActionEvent ae = new ActionEvent(jButton, 123, buttonText);

                    this.controller.handle(uuid, ae);
                });

                //TODO style the buttons
                //TODO updaters
                //TODO event handlers
                grid.add(jButton);
            }
        }


        return grid;
    }


    //get and set
    public GameModel getGameModel() {
        return model;
    }
}