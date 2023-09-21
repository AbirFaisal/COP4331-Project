import org.junit.jupiter.api.Test;

public class FunctionPrototypes {


    //used for prototpying methods and functions before implimenting into production


    //Transforms game from 3D to 1D
    @Test
    public void transform3Dto1D(int[][][] gameState3D) {
        int[] gamestate2D = new int[27];

        for (int z = 0; z < 3; z++) {
            for (int x = 0; x < 3;x++) {
                for (int y = 0; y < 3; y++) {
                    gamestate2D[x+y+z] = gameState3D[x][y][z];
                }
            }
        }
//        return gamestate2D;
    }

}
