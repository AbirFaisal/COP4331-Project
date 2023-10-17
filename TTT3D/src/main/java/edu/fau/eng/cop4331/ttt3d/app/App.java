package edu.fau.eng.cop4331.ttt3d.app;

import edu.fau.eng.cop4331.ttt3d.app.views.GameView;

import javax.swing.*;
import javax.swing.text.html.Option;
import java.util.Random;

import static java.lang.System.exit;


public class App {
    JFrame mainWindow;
    private byte[] playerID; //128 bit player id
    private Model model;
    private View view;
    private Controller controller;

    
    public App() {
        this.playerID = new byte[16]; //TODO load from a configuration
        Random r = new Random();
        r.nextBytes(this.playerID);
        this.mainWindow = new JFrame("Main Window");
        view = new GameView(this.mainWindow);
    }
    public void run(){
        boolean running = true;
        while(running){
            this.view.updateView();
            running = true;
        }
    }


    public byte[] getPlayerID() {
        return playerID;
    }
}

