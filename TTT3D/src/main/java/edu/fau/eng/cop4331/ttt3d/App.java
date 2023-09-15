package edu.fau.eng.cop4331.ttt3d;

import javax.swing.*;

public class App {

    JFrame mainWindow;

    public App() {
        this.mainWindow = new JFrame("Main Window");
    }
    public void run(){
        this.mainWindow.setSize(800,600);//400 width and 500 height
        this.mainWindow.setLayout(null);
        this.mainWindow.setVisible(true);
    }
}

