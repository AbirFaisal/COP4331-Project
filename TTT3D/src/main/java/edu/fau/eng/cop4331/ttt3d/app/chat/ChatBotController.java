package edu.fau.eng.cop4331.ttt3d.app.chat;

import edu.fau.eng.cop4331.ttt3d.app.Handler;

import java.util.*;

import static java.lang.Thread.sleep;

public class ChatBotController extends ChatController {

//    ArrayList<String> chatBotRecievedMsgBufffer;

    public ChatBotController(ChatModel chatModel, ChatView chatView) {
        super(chatModel, chatView);
//        this.chatBotRecievedMsgBufffer = new ArrayList<>();
        runBot();
    }

    //event handlers///////////


    //controller logic

    /**
     * Monitors the message buffer for any messages from the user
     * if so then it responds to it
     *
     * @author Abir Faisal
     */
    void runBot() {
        new Thread(() -> {
            while (true) {
                for (int i = 0; i < this.sentChatMessageBuffer.size(); i++) {
                    //allow the bot to respond
                    getBotResponse(this.sentChatMessageBuffer.get(i));
                    //remove from buffer
                    this.sentChatMessageBuffer.remove(i);
                }

                try {
                    sleep(100);
                }catch (InterruptedException e) {
                }
            }
        }).start();
    }

    /**
     * gets a computer generated response and puts it into the chat
     *
     * @author Abir Faisal
     */
    void getBotResponse(String message) {
        //TODO make more advanced
        String[] responses = {"Ok", "I understand", "Sure"};
        Random r = new Random();
        int i = r.nextInt(responses.length);

        appendChatLog("Bot: " + responses[i]);
    }

}
