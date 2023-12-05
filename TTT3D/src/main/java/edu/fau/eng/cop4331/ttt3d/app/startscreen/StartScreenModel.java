package edu.fau.eng.cop4331.ttt3d.app.startscreen;

import edu.fau.eng.cop4331.ttt3d.app.Model;

import java.util.UUID;


public class StartScreenModel extends Model {

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
    public UUID SERVER_IP_TEXT_FIELD = UUID.randomUUID();
    public UUID SERVER_PORT_TEXT_FIELD = UUID.randomUUID();
    public UUID START_SINGLE_PLAYER_GAME_BUTTON = UUID.randomUUID();
    public UUID START_MULTI_PLAYER_GAME_BUTTON = UUID.randomUUID();
    public UUID START_MULTI_HOST_GAME_BUTTON = UUID.randomUUID();


    //example data strcuture holding some information to be
    //used by the view or updated by the controller
    public record ExampleDataStruct(
            String s,
            double n,
            int i,
            int[] arrayList
    ){}

    public record ServerIP(String ipAddress){}
    public record ServerPort(String port){}

}
