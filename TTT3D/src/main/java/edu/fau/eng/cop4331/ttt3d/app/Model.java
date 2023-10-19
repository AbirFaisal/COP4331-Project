package edu.fau.eng.cop4331.ttt3d.app;

import javax.swing.*;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

public interface Model {

    //get the data from the model
    Object getData(UUID key);

    void setData(UUID key, Object data);

}
