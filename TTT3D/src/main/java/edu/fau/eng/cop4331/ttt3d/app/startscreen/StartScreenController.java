package edu.fau.eng.cop4331.ttt3d.app.startscreen;

import edu.fau.eng.cop4331.ttt3d.app.Controller;
import edu.fau.eng.cop4331.ttt3d.app.Model;
import edu.fau.eng.cop4331.ttt3d.app.View;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.UUID;

public class StartScreenController implements Controller {


    View view;
    Model model;
    ArrayList<String> actionHandlers = new ArrayList<>();

    public StartScreenController(Model model, View view) {
        this.model = model;
        this.view = view;
    }


    void startGameActionHandler() {

    }


    @Override
    public void updateModel(UUID key, Record data) {
        this.model.setData(key, data);
    }
}
