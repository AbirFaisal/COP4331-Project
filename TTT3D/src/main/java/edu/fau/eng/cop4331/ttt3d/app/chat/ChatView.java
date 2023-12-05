package edu.fau.eng.cop4331.ttt3d.app.chat;

import edu.fau.eng.cop4331.ttt3d.app.Updater;
import edu.fau.eng.cop4331.ttt3d.app.View;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultCaret;
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
     * The chat log where the user can see the send and
     * recieved messages
     *
     * @author Abir Faisal
     * @return
     */
    JScrollPane chatLog() {
        UUID uuid = this.model.CHAT_LOG;

        JTextArea jTextArea = new JTextArea("");
        jTextArea.setEditable(false);
        DefaultCaret dc = (DefaultCaret) jTextArea.getCaret();
        dc.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        jScrollPane.setPreferredSize(new Dimension(800,600));
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        Updater updater = () -> {
            //get record from model
                ChatModel.chatLog cl =
                        (ChatModel.chatLog) this.model.getData(uuid);
            Stack<String> messages = cl.messages();
            jTextArea.append(messages.peek() + "\n\n");
        };
        updateMethods.put(uuid, updater);

        return jScrollPane;
    }

    /**
     * The message box where the user
     * types in a message that they want to send.
     *
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
