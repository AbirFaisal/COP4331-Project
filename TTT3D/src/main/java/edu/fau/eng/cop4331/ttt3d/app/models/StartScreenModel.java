package edu.fau.eng.cop4331.ttt3d.app.models;

import edu.fau.eng.cop4331.ttt3d.app.Model;

import java.util.HashMap;
import java.util.UUID;


public class StartScreenModel implements Model {

    public class Keys {
        public static UUID MAIN = UUID.randomUUID();
        public static UUID HELLO_WORLD_JLABEL = UUID.randomUUID();
    }

    HashMap<UUID, Object> dataStructures;

    //The view will request a dataStructure from the Model
    @Override
    public Object getData(UUID key) {
        return dataStructures.get(key);
    }


    //The controller will set a dataStructure and the model will notify the view
    @Override
    public void setData(UUID key, Object data) {

        if (dataStructures.containsKey(key)){
            //replace the object
            dataStructures.replace(key, data);

        } else {
            //add the object
            this.dataStructures.put(key, data);
        }

    }
}
