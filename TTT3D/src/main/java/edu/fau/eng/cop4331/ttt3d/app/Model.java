package edu.fau.eng.cop4331.ttt3d.app;

import javax.swing.*;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public abstract class Model {

    HashMap<UUID, Record> dataStructures = new HashMap<>();
    View view;

    /**
     * The view will request a dataStructure from the Model
     * @param key UUID as defined in a subclass of this Model
     */
    public Record getData(UUID key) {
        return dataStructures.get(key);
    }

    /**
     * Register a view with the model so that setData()
     * can call its notify method after updating a value
     * @param view
     */
    public void register(View view) {
        this.view = view;
    }


    /**
     * Allows the controller will set a dataStructure
     * and the model to notify the view
     * @param key UUID as defined in a subclass of this Model
     * @param data record object as defined a subclass of this Model
     */
    public synchronized void setData(UUID key, Record data) {
        if (dataStructures.containsKey(key)){
            //replace the object
            dataStructures.replace(key, data);
            //notify the view that data has changed
            this.view.updateElement(key);
        } else {
            //add the object
            this.dataStructures.put(key, data);
        }
    }

}
