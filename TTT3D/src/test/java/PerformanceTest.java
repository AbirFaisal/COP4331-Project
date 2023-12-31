import org.junit.jupiter.api.Test;

import java.util.Random;

public class PerformanceTest {




    //Standard array compare
    //Built in array compare
    //Arrays.equals()
    //Arrays.
    //Vector

    private int[] gameState1D = new int[27];
    private int[][][] getGameState3D = new int[3][3][3];

    private void initRandGameState() {
        Random r = new Random();

        for (int i = 0; i < gameState1D.length; i++) {
            //randomly assign a 1, 0, or -1
            int a = (r.nextBoolean()) ? 1:-1; // X or O
            this.gameState1D[i] = r.nextBoolean() ? a : 0; //played or empty
        }
    }



    private void checkWinnerAlgo3DV2(int[][][] gameState3D) {

        for (int z = 0; z < gameState3D.length; z++) {

            int xSum = 0;

            for (int x = 0; x < gameState3D[z].length; x++) {

                int ySum = 0;

                for (int y = 0; y < gameState3D[z][x].length; y++) {
                    int a = gameState3D[x][y][z];
                    xSum += a;
                    ySum += a;

                }

                if (ySum == 3) {
                    System.out.println("winner found");
                } else ySum = 0; //reset ySum
            }

            if (xSum == 3) {
                System.out.println("winner found");
            } else xSum = 0; //reset ySum
        }
    }


    //TODO try 3d array and compare performance
    private void checkWinnerAlgo3dV1(int[][][] gamestate3D) {
        //TODO find better names for these variables

        int originH;
        int originV;
        int pos2;
        int pos3;
        int pos4;
        int pos5;

        //for each layer as i
        for (int i = 0; i < 3; i++) {

            //check horizontal and vertical wins
            for (int j = 0; j < 3; j++) {
                originH = gamestate3D[0][j][i];
                pos2 = gamestate3D[1][j][i];
                pos3 = gamestate3D[2][j][i];

                originV = gamestate3D[j][0][i];
                pos4 = gamestate3D[j][1][i];
                pos5 = gamestate3D[j][2][i];

                // if these values are 3 or -3 we know
                // there is a winner
                // and that either X(3) or O(-3) has won
                int hWinner = originH + pos2 + pos3;

                int vWinner = originV + pos4 + pos5;

            }
        }

        //TODO check diagonals



    }


    //Check 1D gamestate
    private void checkWinnerAlgoV1(int[] gamestate){

        //check horizontal wins for each layer in the cube

        //TODO REFACTOR, maybe each check should be it's own method?

        int gs1 = 0;
        int gs2 = 0;
        int gs3 = 0;

        int i = 0;

        while (i < 27) {
            gs1 = gamestate[i];
            gs2 = gamestate[i+1];
            gs3 = gamestate[i+2];
            System.out.println("Game State: " + i + " " + gs1 + gs2 + gs3);

            if ((gs1 == gs2) && (gs2 == gs3)) {
                System.out.println("Horizontal win: " + gs1 + gs2 + gs3);
            }
            i += 3;
        }

        //check vertical wins
        System.out.println("check Horizontal wins");
        i = 0;
        int j = 0;
        while (i < 27) {
            j = 0;
            while (j < 3) {
                gs1 = gamestate[i+j];
                gs2 = gamestate[i+j+3];
                gs3 = gamestate[i+j+6];

                System.out.println("Game State: " + i + " " + j + " " + gs1 + gs2 + gs3);

                if ((gs1 == gs2) && (gs2 == gs3)) {
                    System.out.println("Horizontal win: " + gs1 + gs2 + gs3);
                }

                j++;
            }
            i += 9;
        }

        //check side direction wins (Z-Axis wins)
        System.out.println("check Z-Axis wins");
        i=0;
        while (i < 9) {
            gs1 = gamestate[i];
            gs2 = gamestate[i+9];
            gs3 = gamestate[i+18];

            System.out.println("Game State: " + i + " " + gs1 + gs2 + gs3);

            if ((gs1 == gs2) && (gs2 == gs3)) {
                System.out.println("Horizontal win: " + gs1 + gs2 + gs3);
            }
            i++;
        }

        //Check diagonal wins

        //Check diagonal wins from front
        System.out.println("\nCheck diagonal wins from front");
        i=0;
        while (i < 27) {
            gs1 = gamestate[i];
            gs2 = gamestate[i+4];
            gs3 = gamestate[i+8];

            System.out.println("Game State \\: " + i + " " + gs1 + gs2 + gs3);

            //check other way
            gs1 = gamestate[i+2];
            //gs2 = gamestate[i+4]; // center no need to do this twice
            gs3 = gamestate[i+6];

            System.out.println("Game State /: " + i + " " + gs1 + gs2 + gs3);

            i += 9;
        }

        //Check diagonal wins from top
        System.out.println("\nCheck diagonal wins from top");
        i=0;
        while (i < 6) {
            gs1 = gamestate[i];
            gs2 = gamestate[i+10];
            gs3 = gamestate[i+20];

            System.out.println("Game State /: " + i + " " + gs1 + gs2 + gs3);

            //check other way
            gs1 = gamestate[i+2];
            //gs2 = gamestate[i+4]; // center no need to do this twice
            gs3 = gamestate[i+18];

            System.out.println("Game State \\: " + i + " " + gs1 + gs2 + gs3);

            i += 3;
        }

        //Check diagonal wins from side
        System.out.println("\nCheck diagonal wins from side");
        i=0;
        while (i < 3) {
            gs1 = gamestate[i];
            gs2 = gamestate[i+12];
            gs3 = gamestate[i+24];

            System.out.println("Game State /: " + i + " " + gs1 + gs2 + gs3);

            //check other way
            gs1 = gamestate[i+6];
            //gs2 = gamestate[i+12]; // center no need to do this twice
            gs3 = gamestate[i+18];

            System.out.println("Game State \\: " + i + " " + gs1 + gs2 + gs3);

            i ++;
        }

        //TODO check though center 3d wins

        printGameStateLayer(1, gamestate);

    }


    public void printGameStateLayer(int layer, int[] gamestate1d) {

        String s = "";

        for (int i = (layer * 9); i < 9; i++) {
            s += gamestate1d[i] + " ";
        }

        System.out.println("Game layer:" + layer + " " + s);
    }




    @Test
    public void perfTest() {
        initRandGameState();
        //byte[][][] anotherGameState = this.gamestate.clone();

        long startTime = 0;
        long endTime = 0;
        long diff = 0;
        long i = 1;
        long avg = 0;

        for (int j = 0; j < i; j++) {
            startTime = System.nanoTime();

            checkWinnerAlgoV1(this.gameState1D);

            endTime = System.nanoTime();
            diff = endTime - startTime;
            avg = (avg + diff) / 2;
        }


        System.out.println("avg ns : " + avg);
    }


}
