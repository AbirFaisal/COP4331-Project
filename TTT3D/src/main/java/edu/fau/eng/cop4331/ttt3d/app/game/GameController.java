package edu.fau.eng.cop4331.ttt3d.app.game;

import edu.fau.eng.cop4331.ttt3d.app.Controller;

public abstract class GameController extends Controller {
    String playerX = null;
    String playerY = null;
    abstract void selectGridButton(int x, int y, int z, String player);

}
