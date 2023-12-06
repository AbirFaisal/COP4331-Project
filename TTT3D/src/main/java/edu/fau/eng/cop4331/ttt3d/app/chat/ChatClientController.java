package edu.fau.eng.cop4331.ttt3d.app.chat;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class ChatClientController extends ChatController {

    ArrayList<String> receivedMessageBuffer;

    public ChatClientController(ChatModel chatModel, ChatView chatView) {
        super(chatModel, chatView);
        sentMessageBufferHandler();
    }

    @Override
    public void sentMessageBufferHandler() {
        new Thread(() -> {
            while (true) {
                for (int i = 0; i < this.sentMessageBuffer.size(); i++) {
                    //send the message
                    System.out.println("Sending Message: " + sentMessageBuffer.get(i));
                    sendMessage(this.sentMessageBuffer.get(i));

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


    void sendMessage(String message) {

    }

    void receivedMessageBufferHandler() {

    }

}
