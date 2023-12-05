package edu.fau.eng.cop4331.ttt3d.app;

import java.util.HashMap;
import java.util.UUID;

public abstract class  Model <E> {

    // Contains data structures that will be
    // updated by the controller or read by the view
    HashMap<UUID, E> dataStructures = new HashMap<>();

    //Just a refrence to the view that should be notified
    //when data is updated in of this model
    View view;

    /**
     * The view will request a dataStructure from the Model
     *
     * @author Abir Faisal
     * @param key UUID as defined in a subclass of this Model
     */
    public Record getData(UUID key) {
        return (Record) dataStructures.get(key);
    }

    /**
     * Register a view with the model so that setData()
     * can call its notify method after updating a value
     *
     * @author Abir Faisal
     * @param view the view that should be notified of changes to this model
     */
    public void register(View view) {
        this.view = view;
    }


    /**
     * Allows the controller to set/update a dataStructure
     * and the model to notify the view
     *
     * @author Abir Faisal
     * @param key UUID as defined in a subclass of this Model
     * @param data record object as defined a subclass of this Model
     */
    public synchronized void setData(UUID key, Record data) {
        if (dataStructures.containsKey(key)){
            //replace the object
            dataStructures.replace(key, (E) data);
            //notify the view that data has changed
            this.view.updateElement(key);
        } else {
            //add the object
            this.dataStructures.put(key, (E) data);

            //notify the view that data has changed
            this.view.updateElement(key);
        }
    }

}
