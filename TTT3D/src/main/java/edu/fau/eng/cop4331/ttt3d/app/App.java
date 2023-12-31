package edu.fau.eng.cop4331.ttt3d.app;

import edu.fau.eng.cop4331.ttt3d.app.chat.*;
import edu.fau.eng.cop4331.ttt3d.app.game.*;
import edu.fau.eng.cop4331.ttt3d.app.startscreen.StartScreenController;
import edu.fau.eng.cop4331.ttt3d.app.startscreen.StartScreenModel;
import edu.fau.eng.cop4331.ttt3d.app.startscreen.StartScreenView;
import edu.fau.eng.cop4331.ttt3d.util.SettingsManager;
import org.json.JSONArray;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class App {
    JFrame mainWindow;
    private byte[] clientID; //128 bit client id

    //Singleton Pattern
    private static App instance;
    private App() {
        this.mainWindow = new JFrame("TTT3D");
        this.clientID = getClientID();
    }
    public static synchronized App getInstance() {
        if (instance == null) instance = new App();
        return instance;
    }

    /**
     * Set up the components of the main window and/or application
     * @author Abir Faisal
     */
    public void setup() {
        initStartScreen();
        this.mainWindow.setSize(800,600);//400 width and 500 height
        this.mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ///TODO this.mainWindow.setLayout();
        this.mainWindow.setVisible(true);
    }

    /**
     * run the application
     *
     * @author Abir Faisal
     */
    public void run() {
        //TODO this might be useless...
    }

    /**
     * Setup the initial MVC you want to show the user
     *
     * @author Abir Faisal
     */
    public void initStartScreen(){
        StartScreenModel startScreenModel = new StartScreenModel();
        StartScreenView startScreenView = new StartScreenView(startScreenModel);
        StartScreenController startScreenController = new StartScreenController(startScreenModel, startScreenView);
        setMainWindowContent(startScreenView.getContainer(startScreenModel.MAIN));
    }

    /**
     *  Launch the application specified by the initial screen
     *
     * @author Abir Faisal
     * @param gameType the type of game you want to launch
     */
    public void launchGame(GameType gameType) {
        GameModel gameModel = new GameModel();
        GameView gameView = new GameView(gameModel);

        ChatModel chatModel = new ChatModel();
        ChatView chatView = new ChatView(chatModel);

        switch (gameType) {
            case SINGLE_PLAYER_GAME -> {
                SinglePlayerGameController gameController = new SinglePlayerGameController(gameModel, gameView);
                ChatController chatController = new ChatBotController(chatModel, chatView);

                //show game and chat side by side
                JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
                jSplitPane.add(gameView.getContainer(gameModel.MAIN));
                jSplitPane.add(chatView.getContainer(chatModel.MAIN));
                jSplitPane.setDividerLocation(250); //Needed for Windows bug

                setMainWindowContent(jSplitPane);
            }
            case MULTI_PLAYER_CLIENT_GAME -> {
                MultiPlayerClientController gameController = new MultiPlayerClientController(gameModel, gameView);
                ChatController chatController = new ChatClientController(chatModel, chatView);

                //TODO ChatClientController

                //show game and chat side by side
                JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
                jSplitPane.add(gameView.getContainer(gameModel.MAIN));
                jSplitPane.add(chatView.getContainer(chatModel.MAIN));
                jSplitPane.setDividerLocation(250); //Needed for Windows bug

                setMainWindowContent(jSplitPane);

            }
            case MULTI_PLAYER_HOST_GAME -> {}
        }

        System.out.println("Launching Game " + gameType);
    }


    /**
     * Generate a client ID or try to load from settings
     *
     * @author Abir Faisal
     * @return 128bit Client ID as byte[16], 16 * 8bit = 128bits
     */
    public byte[] getClientID() {
        if (this.clientID == null) {
            this.clientID = new byte[16];
            SettingsManager sm = SettingsManager.getInstance();

            //if no clientID in settings.json then generate and save
            //else load from configureation
            if (sm.getSettings().opt("clientID") == null) {
                Random r = new Random();
                r.nextBytes(this.clientID);
                //save to settings
                sm.setValue("clientID", this.clientID);
            } else {
                //load from settings
                JSONArray clientIDJSONArray = sm.getSettings().getJSONArray("clientID");

                for (int i = 0; i < clientIDJSONArray.length(); i++) {
                    this.clientID[i] = (byte) clientIDJSONArray.getInt(i);
                }
            }
        }
        return clientID;
    }

    /**
     * set the content of the main window, replace existing content
     *
     * @author Abir Faisal
     * @param c a JPanel that contains the contents you want to display
     */
    public void setMainWindowContent(Container c) {
        this.mainWindow.getContentPane().removeAll();
        this.mainWindow.setContentPane(c);
        this.mainWindow.revalidate();
    }

    /**
     * add the content to the main window
     * 
     * @author Abir Faisal
     * @param c a JPanel that contains the contents you want to display
     */
    public void addMainWindowContent(Container c) {
        this.mainWindow.add(c);
        this.mainWindow.revalidate();
    }

}

