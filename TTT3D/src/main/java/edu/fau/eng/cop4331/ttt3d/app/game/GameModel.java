package edu.fau.eng.cop4331.ttt3d.app.game;

import edu.fau.eng.cop4331.ttt3d.app.Model;

import java.util.UUID;

public class GameModel implements Model {

    public static UUID MAIN = UUID.randomUUID();

    private int[][][] gameState3D; //used to state of game

    public GameModel() {


//        for (int z = 0; z < gameState3D.length; z++) {
//            for (int x = 0; x < gameState3D[z].length; x++) {
//                for (int y = 0; y < gameState3D[z][x].length; y++) {
//                    gameState3D[x][y][z] = 0;
//                }
//            }
//        }

    }

    @Override
    public Record getData(UUID key) {
        return null;
    }

    @Override
    public void setData(UUID key, Record data) {

    }
}
