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

    private TileState[][][] gameState3D; //used to state of game

    private static int GRID_RANGE_MIN = 0;
    private static int GRID_RANGE_MAX = 2;
    private String playerName = "Player";
    private String opponentName = "CPU";


    public GameModel() {


//        for (int z = 0; z < gameState3D.length; z++) {
//            for (int x = 0; x < gameState3D[z].length; x++) {
//                for (int y = 0; y < gameState3D[z][x].length; y++) {
//                    gameState3D[x][y][z] = 0;
//                }
//            }
//        }

        gameState3D = new TileState[GRID_RANGE_MAX+1][GRID_RANGE_MAX+1][GRID_RANGE_MAX+1];
        for (int z = GRID_RANGE_MIN; z < GRID_RANGE_MAX; z++) {
            for (int x = GRID_RANGE_MIN; x < GRID_RANGE_MAX; x++) {
                for (int y = GRID_RANGE_MIN; y < GRID_RANGE_MAX; y++) {
                    gameState3D[x][y][z] = TileState.EMPTY;
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

    public int gridSize(){return ((GRID_RANGE_MAX + 1)*(GRID_RANGE_MAX + 1)*(GRID_RANGE_MAX + 1));}

    public void createGameBoard(){
        int gameBoardSize = gridSize();
        int i = 0;

        while(i<gameBoardSize){
            i++;

        }
    }
}
