package edu.fau.eng.cop4331.ttt3d.app.chat;

import edu.fau.eng.cop4331.ttt3d.app.Controller;
import edu.fau.eng.cop4331.ttt3d.app.Handler;

import java.util.ArrayList;
import java.util.Stack;
import java.util.UUID;

public abstract class ChatController extends Controller {

    ChatModel model;
    ChatView view;
    ArrayList<String> sentChatMessageBuffer;

    public ChatController(ChatModel chatModel, ChatView chatView) {
        this.model = chatModel;
        this.view = chatView;
        this.view.registerController(this);
        this.sentChatMessageBuffer = new ArrayList<>();

        runHandlers();
        setup();
    }

    void setup() {
        handlers.put(this.model.SEND_MESSAGE_BUTTON, sendChatButtonHandler());
        handlers.put(this.model.MESSAGE_BOX, messageBoxEventHandler());

        //init the chat log datastrcture
        Stack<String> s = new Stack<String>();
        s.push("");
        this.model.setData(this.model.CHAT_LOG, new ChatModel.chatLog(s));

        this.model.setData(this.model.MESSAGE_BOX, new ChatModel.messageBox(""));
    }

    //event handlers//////////

    /**
     * Handles what happens when the send chat button is pressed
     *
     * @author Abir Faisal
     * @return
     */
    Handler sendChatButtonHandler() {
        UUID messageBoxUUID = this.model.MESSAGE_BOX;

        return value -> {
            System.out.println("send button pressed");
            //get the text from the message
            ChatModel.messageBox mb =
                    (ChatModel.messageBox) this.model.getData(messageBoxUUID);
            String message = mb.message();

            //clear the message in the model
            this.model.setData(messageBoxUUID, new ChatModel.messageBox(""));

            //append the message to the chat
            appendChatLog("Player 1: " + message);

            //put the message in the message buffer for the chat bot
            this.sentChatMessageBuffer.add(message);
        };
    }


    /**
     * Updates the data in the model
     * when the text in the message box changes
     *
     * @author Abir Faisal
     * @return
     */
    Handler messageBoxEventHandler() {
        UUID uuid = this.model.MESSAGE_BOX;
        return actionEvent -> {
            //update the model
            this.model.setData(uuid,
                    new ChatModel.messageBox(actionEvent.getActionCommand())
            );
        };
    }



    //controller logic////////////

    /**
     * Append a message to the chatLog data structure in the model
     *
     * @author Abir Faisal
     * @param message String message you want to append
     */
    void appendChatLog(String message) {
        UUID chatLogUUID = this.model.CHAT_LOG;

        //append the message to the chat
        ChatModel.chatLog cl =
                (ChatModel.chatLog) this.model.getData(chatLogUUID);
        Stack<String> messages = cl.messages();

        //put the new message on the top of the stack
        messages.push(message);

        //update the chatlog datastructure in the model
        this.model.setData(chatLogUUID, new ChatModel.chatLog(messages));
    }

}
