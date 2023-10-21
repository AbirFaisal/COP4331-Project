package edu.fau.eng.cop4331.ttt3d.app;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public interface View {

    /**
     * Updates an element of the view given its corresponding UUID
     * @param uuid UUID as defined in the model of the view
     */
    public void updateElement(UUID uuid);

    //Refresh/Update the whole view.
    public void refreshView();

    public void init();

    /**
     * Get a component of the view
     * @param uuid UUID as defined in the model of the view
     */
    Container getContainer(UUID uuid);

    public static void main(String[] args) {

    }

}
