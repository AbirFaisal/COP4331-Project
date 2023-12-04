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
//        super(gameModel);
        this.model = gameModel; //make view aware of model
        this.model.register(this); //make model aware of view
        setup(); //setup the view
    }

    @Override
    public void setup() {
        JPanel mainJPanel = new JPanel();
        this.jFrames.put(model.MAIN, mainJPanel);
        mainJPanel.setLayout(new BoxLayout(mainJPanel, BoxLayout.Y_AXIS));

//        this.jFrames.get(model.MAIN).add(helloWorld());

        this.jFrames.get(model.MAIN).add(winLossTieCounter());

        this.jFrames.get(model.MAIN).add(new JLabel("Layer1"));
        this.jFrames.get(model.MAIN).add(xyButtonGrid(0));

        this.jFrames.get(model.MAIN).add(new JLabel("Layer2"));
        this.jFrames.get(model.MAIN).add(xyButtonGrid(1));

        this.jFrames.get(model.MAIN).add(new JLabel("Layer3"));
        this.jFrames.get(model.MAIN).add(xyButtonGrid(2));

    }




    /////UI elements/////////

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


    JLabel winLossTieCounter() {
        JLabel jLabel = new JLabel("Win: 0 Loss: 0 Tie: 0");
        UUID uuid = this.model.STAT_COUNTER;

        Updater updater = new Updater() {
            @Override
            public void update() {
                GameModel.stats stats = (GameModel.stats) model.getData(uuid);
                int win = stats.wins();
                int loss = stats.losses();
                int tie = stats.ties();
                String statStr = "Win:" + win + " Loss:" + loss + " Tie:"+ tie;
                jLabel.setText(statStr);
            }
        };
        updateMethods.put(model.STAT_COUNTER, updater);

        return jLabel;
    }


    /**
     * Grid that contains 3x3 button array
     * The l value is used to deterimine
     * which layer of the cube this grid corresponds to
     *
     * @param layer the layer also known as the z axis
     * @return the grid
     */
    JPanel xyButtonGrid(int layer) {
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(3,3));
        UUID gameGridUUID = this.model.GAME_GRID;
        UUID[][][] buttonUUIDS = this.model.GAME_GRID_BUTTONS;
        int index = 0;

        //generate the buttons
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                grid.add(gameButton(x, y, layer, index));
                index +=1;
            }
        }

        //refreshes the buttons
        Updater updater = new Updater() {
            @Override
            public void update() {
                for (int z = 0; z < 3; z++) {
                    for (int y = 0; y < 3; y++) {
                        for (int x = 0; x < 3; x++) {
                            UUID uuid = buttonUUIDS[x][y][z];
                            GameModel.gameState3D gs3d = (GameModel.gameState3D) model.getData(model.GAME_GRID);
                            int [][][] gs = gs3d.gameState3D();
                            gs[x][y][z] = 0;
                            model.setData(uuid, new GameModel.gameState3D(gs));
                        }
                    }
                }
            }
        };
        updateMethods.put(gameGridUUID, updater);

        return grid;
    }

    /**
     * generates the game button given the x,y,z cordinates and index
     *
     * @param x cordinate
     * @param y cordinate
     * @param z layer
     * @param index counter
     * @return
     */
    JButton gameButton(int x, int y, int z, int index) {
        UUID gameGridUUID = this.model.GAME_GRID;
        UUID buttonUUID = this.model.GAME_GRID_BUTTONS[x][y][z];

        JButton jButton = new JButton("-");
        jButton.setPreferredSize(new Dimension(50,50));
        jButton.setFont(new Font(null, Font.PLAIN, 40));

        //event handler will recieve this string "x,y"
        //optionally it can use index to identify which button was pressed
        String coordinates = x + "," + y + "," + z;

        //action event to be passed to the controller
        ActionEvent ae = new ActionEvent(jButton, index, coordinates);
        jButton.addActionListener(e -> this.controller.handle(gameGridUUID, ae));

        //if model is updated with a new gameState then do this
        int xf = x; //final
        int yf = y; //final
        int zf = z; //final
        Updater updater = new Updater() {
            @Override
            public void update() {
//                System.out.println("xyz" + xf + yf + zf);
                //read the state from the game state record int the model datastructures
                GameModel.gameState3D gs3d = (GameModel.gameState3D) model.getData(gameGridUUID);
                int[][][] gs = gs3d.gameState3D();
                int state = gs[xf][yf][zf];

                //if 1 then "X" if -1 then "O" else "-"
                if (state == 1) jButton.setText("X");
                else if (state == -1) jButton.setText("O");
                else jButton.setText("-");
            }
        };
        updateMethods.put(buttonUUID, updater);

        return jButton;
    }


    //get and set
    public GameModel getGameModel() {
        return model;
    }
}