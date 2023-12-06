package edu.fau.eng.cop4331.ttt3d.app.chat;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class ChatClientController extends ChatController {

    ArrayList<String> receivedMessageBuffer;

    /**
     * Constructor
     * @param chatModel ChatModel
     * @param chatView ChatView
     */
    public ChatClientController(ChatModel chatModel, ChatView chatView) {
        super(chatModel, chatView);
        sentMessageBufferHandler();
    }

    /**
     * Handles the messages in the message buffer
     * Sends the message to the server
     */
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

    /**
     * Handles sending the message to the server
     * @param message String
     */
    void sendMessage(String message) {

    }

    /**
     * Handles recieved messages in the recieved message buffer
     */
    void receivedMessageBufferHandler() {
        new Thread(() -> {
            while (true) {
                for (int i = 0; i < this.receivedMessageBuffer.size(); i++) {
                    System.out.println("Recieved Message: " + receivedMessageBuffer.get(i));
                    //put the recieved message into the view

                    //remove from buffer
                    this.receivedMessageBuffer.remove(i);
                }
                try {
                    sleep(100); //prevent using CPU cycles for no reason.
                }catch (InterruptedException e) {
                }
            }
        }).start();
    }

}
