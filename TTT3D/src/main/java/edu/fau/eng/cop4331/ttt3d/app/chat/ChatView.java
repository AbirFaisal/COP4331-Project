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
        JTextArea textArea = new JTextArea("");
        textArea.setEditable(false);

        for (int i = 0; i < 100; i++) {
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

        return jScrollPane;
    }

    /**
     * @author Abir Faisal
     * @return
     */
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

    /**
     * @author Abir Faisal
     * @return
     */
    JButton sendMessageButton(){
        JButton jButton = new JButton("Send");

        return jButton;
    }



}
