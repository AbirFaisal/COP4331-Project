package edu.fau.eng.cop4331.ttt3d.app.game;

import edu.fau.eng.cop4331.ttt3d.app.Controller;
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

        this.handlers.put(model.GAME_GRID, gridButtonPressedHandler());
    }



    //Event Handlers////////////////

    /**
     * This handler recieves x,y cordinates of the button that was pressed
     * @return
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
            gs[x][y][z] = (player == 1) ? 1 : -1;
            this.model.setData(buttonUUID, new GameModel.gameState3D(gs));

            //check if there is a winner
            gs3d = (GameModel.gameState3D) this.model.getData(this.model.GAME_GRID);
            int winner = solver.solve(gs3d.gameState3D());

            //no winner, make next move
            if (winner == 3) { //X
                System.out.println("X wins");
                JOptionPane.showMessageDialog(null, "You won");
                newGame();
            } else if (winner == -3) { //O
                System.out.println("O wins");
                JOptionPane.showMessageDialog(null, "You lost");
                newGame();
            }
            else if (tiedGame()) {
                System.out.println("Tied Game");
                JOptionPane.showMessageDialog(null, "The game was tied");
                newGame();
            }
            else if (player == 1) makeNextMove(gs);

        } else System.out.println(" invalidMove");
    }

    boolean tiedGame(){
        return false;
    }


    void newGame() {
        //empty game grid
        //should init to zeros automatically
        int[][][] newGameState = new int[3][3][3];
        this.model.setData(model.GAME_GRID,
                new GameModel.gameState3D(newGameState)
        );
    }

    boolean isValidMove(int x, int y, int z, int[][][] gameState) {
        if (gameState[x][y][z] == 0) return true;
        else return false;
    }

    /**
     * Single player mode
     * Computer makes next move
     */
    void makeNextMove(int[][][] gameState) {
        //select random position
        Random r = new Random();
        int x = r.nextInt(3);
        int y = r.nextInt(3);
        int z = r.nextInt(3);
        z = 0; // force 2d game

        System.out.println("\ncomputer " + x + "," + y + "," + z);
        System.out.println("gs3d=" + gameState[x][y][z]);

        while (gameState[x][y][z] != 0) {
            System.out.println("NOT VALID RECALCULATING");
            x = r.nextInt(3);
            y = r.nextInt(3);
            z = r.nextInt(3);
            z = 0; // force 2d game
        }


        makeMove(x, y, z, 0);

    }
}
