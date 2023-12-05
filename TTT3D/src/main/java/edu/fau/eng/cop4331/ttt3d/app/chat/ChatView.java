package edu.fau.eng.cop4331.ttt3d.app.chat;

import edu.fau.eng.cop4331.ttt3d.app.View;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Stack;
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

        Updater updater = () -> {
            //get record from model
                ChatModel.chatLog cl =
                        (ChatModel.chatLog) this.model.getData(uuid);
            Stack<String> messages = cl.messages();
            textArea.append(messages.peek() + "\n\n");
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

        DocumentListener dl = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                controller.handle(uuid,
                        new ActionEvent(jTextArea, 0, jTextArea.getText())
                );
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                controller.handle(uuid,
                        new ActionEvent(jTextArea, 0, jTextArea.getText())
                );
            }
            @Override
            public void changedUpdate(DocumentEvent e) {}
        };
        jTextArea.getDocument().addDocumentListener(dl);


        Updater updater = () -> {
            ChatModel.messageBox message =
                    (ChatModel.messageBox) this.model.getData(this.model.MESSAGE_BOX);
            String strMessage = message.message();

            //if the text is different then update it, else do nothing
            if (!jTextArea.getText().equals(strMessage)) {
                //set text without triggering event
                jTextArea.getDocument().removeDocumentListener(dl);
                jTextArea.setText(strMessage);
                //restore the change listener
                jTextArea.getDocument().addDocumentListener(dl);
            }

        };
        this.updateMethods.put(uuid, updater);

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
