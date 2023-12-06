package edu.fau.eng.cop4331.ttt3d.app.chat;

import edu.fau.eng.cop4331.ttt3d.app.Handler;

import java.util.*;

import static java.lang.Thread.sleep;

public class ChatBotController extends ChatController {

    ArrayList<String> chatBotRecievedMsgBufffer;

    public ChatBotController(ChatModel chatModel, ChatView chatView) {
        super(chatModel, chatView);
        this.chatBotRecievedMsgBufffer = new ArrayList<>();
        runBot();
    }
    
    //event handlers///////////

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
            this.chatBotRecievedMsgBufffer.add(message);
        };
    }


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
                for (int i = 0; i < this.chatBotRecievedMsgBufffer.size(); i++) {
                    //allow the bot to respond
                    getBotResponse(this.chatBotRecievedMsgBufffer.get(i));
                    //remove from buffer
                    this.chatBotRecievedMsgBufffer.remove(i);
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
