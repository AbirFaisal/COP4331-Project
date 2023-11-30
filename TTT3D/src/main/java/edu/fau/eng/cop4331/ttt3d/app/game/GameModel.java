package edu.fau.eng.cop4331.ttt3d.app.game;

import edu.fau.eng.cop4331.ttt3d.app.Model;

import java.util.UUID;

public class GameModel extends Model {

    public static UUID MAIN = UUID.randomUUID();

    enum Tile_State {
        EMPTY,
        CIRCLE,
        CROSS
    }
    private Tile_State[][][] gameState3D; //used to state of game

    private static int GRID_RANGE_MIN = 0;
    private static int GRID_RANGE_MAX = 2;

    private String playerName = null;
    private String opponentName = "CPU";


    public GameModel() {


//        for (int z = 0; z < gameState3D.length; z++) {
//            for (int x = 0; x < gameState3D[z].length; x++) {
//                for (int y = 0; y < gameState3D[z][x].length; y++) {
//                    gameState3D[x][y][z] = 0;
//                }
//            }
//        }


        for (int z = GRID_RANGE_MIN; z < GRID_RANGE_MAX; z++) {
            for (int x = GRID_RANGE_MIN; x < GRID_RANGE_MAX; x++) {
                for (int y = GRID_RANGE_MIN; y < GRID_RANGE_MAX; y++) {
                    gameState3D[x][y][z] = Tile_State.EMPTY;
                }
            }
        }
    }

    @Override
    public Record getData(UUID key) {
        return null;
    }

    @Override
    public void setData(UUID key, Record data) {

    }

    public String getPlayerName(){return playerName;}
}
