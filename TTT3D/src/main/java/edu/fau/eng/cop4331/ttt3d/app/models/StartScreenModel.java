package edu.fau.eng.cop4331.ttt3d.app.models;

import edu.fau.eng.cop4331.ttt3d.app.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


public class StartScreenModel implements Model {

    /**
     * The View uses these constants to get data from the Model
     * The Controller uses these constants to update data in the Model
     *
     * Every element in a view that needs to be updated
     * needs to have a UUID refrence to it here.
     *
     * These are non-static so the UUID will
     * be unique to each instance of the class
     *
     */
    public UUID MAIN = UUID.randomUUID();
    public UUID HELLO_WORLD_JLABEL = UUID.randomUUID();
    public UUID TEST_BUTTON = UUID.randomUUID();

    //example data strcuture holding some information to be
    //used by the view or updated by the controller
    public record ExampleDataStruct(
            String s,
            double n,
            int i,
            int[] arrayList
    ){}



    //TODO turn this into an extended class instead of an interface?

    HashMap<UUID, Record> dataStructures;
    public StartScreenModel() {
        this.dataStructures = new HashMap<>();
    }

    //The view will request a dataStructure from the Model
    @Override
    public Record getData(UUID key) {
        return dataStructures.get(key);
    }

    //The controller will set a dataStructure and the model will notify the view
    @Override
    public synchronized void setData(UUID key, Record data) {


        if (dataStructures.containsKey(key)){
            //replace the object
            dataStructures.replace(key, data);


        } else {
            //add the object
            this.dataStructures.put(key, data);
        }
    }
}
