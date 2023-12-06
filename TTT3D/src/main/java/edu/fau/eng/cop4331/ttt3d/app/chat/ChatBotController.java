package edu.fau.eng.cop4331.ttt3d.app.chat;

import edu.fau.eng.cop4331.ttt3d.app.Handler;

import java.util.*;

import static java.lang.Thread.sleep;

public class ChatBotController extends ChatController {

    /**
     * Constructor
     * @param chatModel ChatModel
     * @param chatView ChatView
     */
    public ChatBotController(ChatModel chatModel, ChatView chatView) {
        super(chatModel, chatView);
        sentMessageBufferHandler();
    }

    //controller logic//////

    /**
     * Monitors the message buffer for any messages from the user
     * if so then it responds to it
     *
     * @author Abir Faisal
     */
    @Override
    public void sentMessageBufferHandler() {
        new Thread(() -> {
            while (true) {
                for (int i = 0; i < this.sentMessageBuffer.size(); i++) {
                    //allow the bot to respond
                    getBotResponse(this.sentMessageBuffer.get(i));
                    //remove from buffer
                    this.sentMessageBuffer.remove(i);
                }

                try {
                    sleep(100); //prevent using CPU cycles for no reason.
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
