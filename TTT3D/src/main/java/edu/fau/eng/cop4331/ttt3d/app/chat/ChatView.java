package edu.fau.eng.cop4331.ttt3d.app.chat;

import edu.fau.eng.cop4331.ttt3d.app.View;

import javax.swing.*;
import java.awt.*;
import java.time.Instant;

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
        this.jFrames.put(this.model.MAIN, mainJPanel);

//        this.jFrames.get(this.model.MAIN).add(helloWorld());


        this.jFrames.get(this.model.MAIN).add(messageBox());
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




    JTextArea messageBox() {
        JTextArea jTextArea = new JTextArea();
        jTextArea.setPreferredSize(new Dimension(100,50));
        Updater updater = new Updater() {
            @Override
            public void update() {

            }
        };
        updateMethods.put(model.MESSAGE_BOX, updater);
        return jTextArea;
    }

    JButton sendMessageButton(){
        JButton jButton = new JButton("Send");

        return jButton;
    }



}
