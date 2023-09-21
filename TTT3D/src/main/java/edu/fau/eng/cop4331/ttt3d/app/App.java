package edu.fau.eng.cop4331.ttt3d.app;

import javax.swing.*;
import java.util.Random;

public class App {
    JFrame mainWindow;
    private byte[] playerID; //128 bit player id TODO make private
    private Model model;
    private View view;
    private Controller controller;
    
    public App() {
        this.playerID = new byte[16]; //TODO load from a configuration
        Random r = new Random();
        r.nextBytes(this.playerID);

        this.mainWindow = new JFrame("Main Window");
    }
    public void run(){
        this.mainWindow.setSize(800,600);//400 width and 500 height
        this.mainWindow.setLayout(null);
        this.mainWindow.setVisible(true);
    }


    public byte[] getPlayerID() {
        return playerID;
    }
}

