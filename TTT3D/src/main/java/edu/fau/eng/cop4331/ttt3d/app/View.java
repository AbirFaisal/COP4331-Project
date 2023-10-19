package edu.fau.eng.cop4331.ttt3d.app;

import java.awt.*;
import java.util.UUID;

public interface View {

    public void updateView();

    Container getJFrame(UUID uuid);

}
