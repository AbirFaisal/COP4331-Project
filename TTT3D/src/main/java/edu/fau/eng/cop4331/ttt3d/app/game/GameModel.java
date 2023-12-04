package edu.fau.eng.cop4331.ttt3d.app.game;

import edu.fau.eng.cop4331.ttt3d.app.Model;

import java.util.UUID;

public class GameModel extends Model {

    /**
     * The View uses these constants to get data from the Model
     * The Controller uses these constants to update data in the Model
     *
     * Every element in a view that needs to be updated
     * needs to have a UUID refrence to it here.
     *
     * These are non-static so the UUID will
     * be unique to each instance of the class
     *
     */
    public UUID MAIN = UUID.randomUUID();
    public UUID HELLO_WORLD_JLABEL = UUID.randomUUID();
    public UUID GAME_GRID = UUID.randomUUID();
    public UUID[][][] GAME_GRID_BUTTONS;
    public UUID STAT_COUNTER;

    /**
     * initializes the UUIDs for GAME_GRID_BUTTONS
     */
    public GameModel() {
        this.GAME_GRID_BUTTONS = new UUID[3][3][3];
        for (int z = 0; z < this.GAME_GRID_BUTTONS.length; z++) {
            for (int y = 0; y < this.GAME_GRID_BUTTONS.length; y++) {
                for (int x = 0; x < this.GAME_GRID_BUTTONS.length; x++) {
                    this.GAME_GRID_BUTTONS[x][y][z] = UUID.randomUUID();
                }
            }
        }
    }

    //    private int[][][] gameState3D; //used to state of game

    public record gameState3D(int[][][] gameState3D){}

    public record stats(
            int wins,
            int losses,
            int ties
    ){}


}
