package edu.fau.eng.cop4331.ttt3d.app.chat;

import edu.fau.eng.cop4331.ttt3d.app.Controller;

public class ChatBotController extends Controller {


    ChatModel model;
    ChatView view;

    public ChatBotController(ChatModel chatModel, ChatView chatView) {
        this.model = chatModel;
        this.view = chatView;
        this.view.registerController(this);
    }


    void setup() {

    }



    //event handlers





}
