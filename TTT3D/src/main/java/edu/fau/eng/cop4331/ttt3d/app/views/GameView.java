package edu.fau.eng.cop4331.ttt3d.app.views;


import edu.fau.eng.cop4331.ttt3d.app.View;

import javax.swing.*;
import java.awt.*;

import static org.lwjgl.opengl.GL11.glColor4f;

public class GameView implements View {
    @Override
    public void updateView() {
//        glColor4f(1,1,1,1);
    }

    public GameView(JFrame window){
        //Tutorial suggested a pane, seeing if it's necessary
        //Container pane = frame.getContentPane();
        //pane.setLayout(new BorderLayout());
        window.setLayout(new BorderLayout());

        // panel to display render results
        JPanel renderPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.BLACK);
                g2.fillRect(0, 0, getWidth(), getHeight());

                // rendering magic will happen here
            }
        };
        window.add(renderPanel, BorderLayout.CENTER);
        window.setSize(600, 600);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    // class that defines a view of the game


}