package edu.fau.eng.cop4331.ttt3d.app.game;

import edu.fau.eng.cop4331.ttt3d.app.Controller;
import edu.fau.eng.cop4331.ttt3d.util.Solver;

import java.awt.event.ActionEvent;
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
        //empty game grid
        //should init to zeros automatically
        int[][][] newGameState = new int[3][3][3];
        this.model.setData(model.GAME_GRID,
                new GameModel.gameState3D(newGameState)
        );

        this.handlers.put(model.GAME_GRID, gridButtonPressedHandler());
    }

    //Event Handlers////////////////

    /**
     * This handler recieves x,y cordinates of the button that was pressed
     * @return
     */
    Handler gridButtonPressedHandler(){
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
                interpretMove(x, y, z);
            }
        };
    }


    //Game logic/////////////
    Solver solver = new Solver();

    void makeMove(int x, int y, int z){

    };

    void interpretMove(int x, int y, int z) {
        System.out.println("interpreting move");
        //make sure the postion was empty
//        boolean b = solver.isValidMove(x,y,z, gameState);

        GameModel.gameState3D gs3d = (GameModel.gameState3D) this.model.getData(this.model.GAME_GRID);
        boolean isValidMove = isValidMove(x, y, z, gs3d.gameState3D());

        UUID buttonUUID = this.model.GAME_GRID_BUTTONS[x][y][z];

        //if valid then update model
        if (isValidMove) {
            System.out.println("validMove");
            //update the model TODO move to makeMove()
            int [][][] gs = gs3d.gameState3D();
            gs[x][y][z] = 1;
            this.model.setData(buttonUUID, new GameModel.gameState3D(gs));
        }
        else System.out.println("invalidMove");

        //check if there is a winner
        gs3d = (GameModel.gameState3D) this.model.getData(this.model.GAME_GRID);
        solver.solve(gs3d.gameState3D());

        //make the next move

    }

    boolean isValidMove(int x, int y, int z, int gameState[][][]) {
        if (gameState[x][y][z] == 0) return true;
        else return false;
    }

    /**
     * Single player mode
     */
    void makeNextMove(){
        //select random position

        //make sure move is valid
        //interpretMove();

        //
    }

}
