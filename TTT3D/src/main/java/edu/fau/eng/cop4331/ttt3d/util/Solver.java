package edu.fau.eng.cop4331.ttt3d.util;

import jdk.jshell.spi.ExecutionControl;

public class Solver {
    //class that contains game solvers

    public Solver() {
    }

    /**
     * Solves the game given a 1D representation of the gameState
     * @param gameState1D
     */
    public void solve(int[] gameState1D) {
        //TODO check winner given a 1D game state array
    }

    /**
     * Solves the game given a 2D representation of the gameState
     * @param gameState2D
     */
    public void solve(int[][] gameState2D) {
        //TODO check winner given a 2D game state array
    }

    /**
     * Solves the game given a 3D representation of the gameState
     *
     * @param gameState3D
     */
    public int solve(int[][][] gameState3D) {
        int y0;
        int y1;
        int y2;

        int x0;
        int x1;
        int x2;

        //solve for horizontal and vertical wins
        for (int z = 0; z < 3; z++) { //layer
            for (int i = 0; i < 3; i++) {
                y0 = gameState3D[i][0][z];
                y1 = gameState3D[i][1][z];
                y2 = gameState3D[i][2][z];
                int hSum = y0 + y1 + y2;

                x0 = gameState3D[0][i][z];
                x1 = gameState3D[1][i][z];
                x2 = gameState3D[2][i][z];
                int vSum = x0 + x1 + x2;

                if (hSum == 3) return hSum;
                if (hSum == -3) return hSum;
                if (vSum == 3) return vSum;
                if (vSum == -3) return vSum;
            }
        }

        //check for diagonal wins
        for (int z = 0; z < 3; z++) {
            int topLeft = gameState3D[0][0][z]; int topRight = gameState3D[2][0][z];
                              int center = gameState3D[1][1][z];
            int bottomLeft = gameState3D[0][2][z];int bottomRight = gameState3D[2][2][z];

            int diag1 = topLeft + center + bottomRight;
            int diag2 = bottomLeft + center + topRight;

            if (diag1 == 3) return diag1;
            if (diag1 == -3) return diag1;
            if (diag2 == 3) return diag2;
            if (diag2 == -3) return diag2;
        }

        //check for orthogonal wins TODO

        //no winners found
        return 0;
    }

}
