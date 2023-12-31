package edu.fau.eng.cop4331.ttt3d.app.game;

import edu.fau.eng.cop4331.ttt3d.app.Controller;
import edu.fau.eng.cop4331.ttt3d.app.Handler;
import edu.fau.eng.cop4331.ttt3d.util.Solver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.util.UUID;

public class SinglePlayerGameController extends Controller {
    //controller that connects the view with a single player game model

    GameModel model;
    GameView view;

    public SinglePlayerGameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        this.view.registerController(this);

        runHandlers();
        setup();
    }

    void setup() {
        newGame();
        resetStats();

        this.handlers.put(model.GAME_GRID, gridButtonPressedHandler());
    }

    //Event Handlers////////////////

    /**
     * This handler recieves x,y cordinates of the button that was pressed
     * @return A Handler that reacts to button presses on it's grid.
     */
    Handler gridButtonPressedHandler() {
        return new Handler() {
            @Override
            public void handle(ActionEvent value) {
//                System.out.println(value.getID());
                //get int x,y and z from String "x,y,z"
                String[] s = value.getActionCommand().split(",");
                int x = Integer.parseInt(s[0]);
                int y = Integer.parseInt(s[1]);
                int z = Integer.parseInt(s[2]);
                System.out.println(x + "," + y + "," + z);
                makeMove(x, y, z, 1);
            }
        };
    }

    //Game logic/////////////
    Solver solver = new Solver();

    /**
     *
     * Validates and makes a move and updates the model
     * Also tells user if the game was won and if so resets the game
     *
     * @author Abir Faisal
     * @param x
     * @param y
     * @param z
     * @param player
     */
    void makeMove(int x, int y, int z, int player) {
        System.out.format("interpreting move xyz=%d,%d,%d player=%d", x, y, z, player);
        GameModel.gameState3D gs3d = (GameModel.gameState3D) this.model.getData(this.model.GAME_GRID);

        //make sure the postion was empty
        boolean isValidMove = isValidMove(x, y, z, gs3d.gameState3D());

        UUID buttonUUID = this.model.GAME_GRID_BUTTONS[x][y][z];

        //if valid then update model
        if (isValidMove) {
            System.out.println(" validMove");
            //update the model
            int[][][] gs = gs3d.gameState3D();
            gs[x][y][z] = (player == 1) ? 1 : -1; //X=1 O=-1
            this.model.setData(buttonUUID, new GameModel.gameState3D(gs));

            //check if there is a winner
            gs3d = (GameModel.gameState3D) this.model.getData(this.model.GAME_GRID);
            int winner = solver.solve(gs3d.gameState3D());

            //if no winner, make next move
            if (winner == 3) { //X
                System.out.println("X wins");
                updateStats(1);
                JOptionPane.showMessageDialog(null, "You won");
                newGame();
            } else if (winner == -3) { //O
                System.out.println("O wins");
                updateStats(-1);
                JOptionPane.showMessageDialog(null, "You lost");
                newGame();
            }
            else if (tiedGame()) {
                System.out.println("Tied Game");
                updateStats(0);
                JOptionPane.showMessageDialog(null, "The game was tied");
                newGame();
            }
            else if (player == 1) makeNextMove(gs);

        } else System.out.println(" invalidMove");
    }


    /**
     * Updates the game stats in the model with the new values
     *
     * @author Abir Faisal
     * @param winLossTie 1=win -1=loss 0=tie
     */
    void updateStats(int winLossTie){
        //get data from model
        GameModel.stats stats = (GameModel.stats) this.model.getData(this.model.STAT_COUNTER);
        GameModel.stats  newStats = null;

        switch (winLossTie){
            case 1: {
                //update the stats
                newStats = new GameModel.stats(stats.wins() + 1, stats.losses(), stats.ties());
                break;
            }
            case -1: {
                //update the stats
                newStats = new GameModel.stats(stats.wins(), stats.losses() + 1, stats.ties());
                break;
            }
            case 0: {
                //update the stats
                newStats = new GameModel.stats(stats.wins(), stats.losses(), stats.ties() + 1);
                break;
            }
        }
        //update the model with the new stats
        this.model.setData(this.model.STAT_COUNTER, newStats);
    }


    /**
     * Check if the game is tied
     * @return true = tied, false = not tied
     */
    boolean tiedGame(){
        return false; //TODO
    }

    /**
     * Setup a new game
     * @author Abir Faisal
     * setup a new game
     */
    void newGame() {
        //empty game grid
        //should init to zeros automatically
        int[][][] newGameState = new int[3][3][3];
        this.model.setData(model.GAME_GRID,
                new GameModel.gameState3D(newGameState)
        );
    }

    void resetStats(){
        this.model.setData(model.STAT_COUNTER, new GameModel.stats(0,0,0));
    }

    /**
     * check if the move is a valid move
     *
     * @author Abir Faisal
     * @param x coordinate
     * @param y coordinate
     * @param z coordinate
     * @param gameState
     * @return true if the move is valid, false if it is invalid.
     */
    boolean isValidMove(int x, int y, int z, int[][][] gameState) {
        if (gameState[x][y][z] == 0) return true;
        else return false;
    }

    /**
     * Single player mode
     * Computer makes next move
     *
     * @author Abir Faisal
     */
    void makeNextMove(int[][][] gameState) {
        //select random position
        Random r = new Random();
        int x = r.nextInt(gameState.length);
        int y = r.nextInt(gameState[x].length);
        int z = r.nextInt(gameState[x][y].length);

        System.out.println("\ncomputer move " + x + "," + y + "," + z);

        //validate decision
        while (gameState[x][y][z] != 0) {
            System.out.println("NOT VALID RECALCULATING");
            x = r.nextInt(gameState.length);
            y = r.nextInt(gameState[x].length);
            z = r.nextInt(gameState[x][y].length);
        }
        makeMove(x, y, z, 0); //player 0 is always opponent

    }
}
