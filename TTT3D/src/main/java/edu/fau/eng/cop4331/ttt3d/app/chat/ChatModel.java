package edu.fau.eng.cop4331.ttt3d.app.chat;

import edu.fau.eng.cop4331.ttt3d.app.Model;

import java.util.ArrayList;
import java.util.Stack;
import java.util.UUID;

public class ChatModel extends Model {


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
    public UUID CHAT_LOG = UUID.randomUUID();
    public UUID MESSAGE_BOX = UUID.randomUUID();
    public UUID SEND_MESSAGE_BUTTON = UUID.randomUUID();

    //data structures

    /**
     * Holds an stack array of String messages
     * to be displayed by the view or updated by the controller
     *
     * @param messages Stack<String>
     */
    public record chatLog(Stack<String> messages){}

    /**
     * Holds the text that the user types into the message box
     *
     * @param message String
     */
    public record messageBox(String message){}


}
