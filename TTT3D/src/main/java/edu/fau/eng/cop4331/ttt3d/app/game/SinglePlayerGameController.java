package edu.fau.eng.cop4331.ttt3d.app.game;

import edu.fau.eng.cop4331.ttt3d.app.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class SinglePlayerGameController extends Controller {
    //controller that connects the view with a single player game model

    GameModel model;
    GameView view;

    public SinglePlayerGameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        this.view.registerController(this);

        runHandlers();
        setup();
    }


    void setup() {
        this.handlers.put(model.BUTTON_GRID, gridButtonPressedHandler());
    }


    /**
     * This handler recieves x,y cordinates of the button that was pressed
     * @return
     */
    Handler gridButtonPressedHandler(){
        return new Handler() {
            @Override
            public void handle(ActionEvent value) {
                System.out.println(value.getActionCommand());
            }
        };
    }

}
