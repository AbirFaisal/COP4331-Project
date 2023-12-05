package edu.fau.eng.cop4331.ttt3d.app.chat;

import edu.fau.eng.cop4331.ttt3d.app.Controller;

import java.awt.event.ActionEvent;

public class ChatBotController extends Controller {


    ChatModel model;
    ChatView view;

    public ChatBotController(ChatModel chatModel, ChatView chatView) {
        this.model = chatModel;
        this.view = chatView;
        this.view.registerController(this);

        runHandlers();
        setup();
    }


    void setup() {
        handlers.put(this.model.SEND_MESSAGE_BUTTON, sendChatButtonHandler());
    }



    //event handlers

    Handler sendChatButtonHandler(){
        return new Handler() {
            @Override
            public void handle(ActionEvent value) {
                System.out.println("send button pressed");

            }
        };
    }


    //application logic

    void getBotResponse() {
        String[] responses = {"Ok", "I understand", "Sure"};

    }

}
