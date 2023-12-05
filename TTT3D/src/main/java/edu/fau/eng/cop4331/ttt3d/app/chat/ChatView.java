package edu.fau.eng.cop4331.ttt3d.app.chat;

import edu.fau.eng.cop4331.ttt3d.app.View;

import javax.swing.*;
import java.awt.*;
import java.time.Instant;
import java.util.UUID;

public class ChatView extends View {


    ChatModel model;

    public ChatView(ChatModel chatModel){
        this.model = chatModel;
        this.model.register(this);
        setup();
    }


    @Override
    public void setup() {
        JPanel mainJPanel = new JPanel();
        mainJPanel.setLayout(new BoxLayout(mainJPanel, BoxLayout.Y_AXIS));
        this.jFrames.put(this.model.MAIN, mainJPanel);


        this.jFrames.get(this.model.MAIN).add(chatLog());
        this.jFrames.get(this.model.MAIN).add(messageBox());
        this.jFrames.get(this.model.MAIN).add(sendMessageButton());
    }


    /////UI elements/////////
    JLabel helloWorld() {
        JLabel jLabel = new JLabel("Hello World");
        //called by the model when it's corresponding values change
        Updater updater = () -> {
            String currentTime = Instant.now().toString();
            jLabel.setText(currentTime);
        };
        updateMethods.put(model.HELLO_WORLD_JLABEL, updater);
        return jLabel;
    }

    /**
     * @author Abir Faisal
     * @return
     */
    JScrollPane chatLog() {
        UUID uuid = this.model.CHAT_LOG;

        JTextArea textArea = new JTextArea("");
        textArea.setEditable(false);

        for (int i = 0; i < 10; i++) {
            int p = ((i % 2) == 0) ?  1 : 2;
            textArea.append("Player"+ p + ": Hello World\n\n");
        }

        JScrollPane jScrollPane = new JScrollPane(textArea);
        jScrollPane.setPreferredSize(new Dimension(800,600));
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        Updater updater = new Updater() {
            @Override
            public void update() {
                //get record from model

                //update the ui
                textArea.append("new text");
            }
        };
        updateMethods.put(uuid, updater);

        return jScrollPane;
    }

    /**
     * @author Abir Faisal
     * @return
     */
    JTextArea messageBox() {
        UUID uuid = this.model.MESSAGE_BOX;

        JTextArea jTextArea = new JTextArea();
        jTextArea.setPreferredSize(new Dimension(100,50));
        Updater updater = new Updater() {
            @Override
            public void update() {

            }
        };
        updateMethods.put(uuid, updater);
        return jTextArea;
    }

    /**
     * @author Abir Faisal
     * @return
     */
    JButton sendMessageButton(){
        UUID uuid = this.model.SEND_MESSAGE_BUTTON;
        JButton jButton = new JButton("Send");

        jButton.addActionListener(actionEvent -> {
            this.controller.handle(uuid, actionEvent);
        });

        return jButton;
    }



}
