package edu.fau.eng.cop4331.ttt3d.util;

import edu.fau.eng.cop4331.ttt3d.Main;
import edu.fau.eng.cop4331.ttt3d.app.App;
import org.json.JSONObject;

import java.io.*;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class SettingsManager {

    private JSONObject settings;
    private final String settingsFileName = "settings.json";

    //singleton pattern
    private static SettingsManager instance;
    private SettingsManager() {
    }
    public static synchronized SettingsManager getInstance(){
        if (instance == null) instance = new SettingsManager();
        return instance;
    }

    public void loadSettings() {
        //settings.json in resources folder
        try {
            String jsonString = new String(
                    SettingsManager.class.getClassLoader().getResourceAsStream(settingsFileName).readAllBytes()
            );
            this.settings = new JSONObject(jsonString);
        }catch (IOException e) {

        }
        System.out.println(this.settings);
    }

    public JSONObject getSettings() {
        return settings;
    }

    public void setValue(String key, Object value) {
        this.settings.put(key, value);
        saveSettingsToFile();
    }

    void saveSettingsToFile() {
        //save the changes to settings.json
        String path = SettingsManager.class.getClassLoader().getResource(settingsFileName).getPath();
        File file = new File(path);

        try {
            PrintWriter writer = new PrintWriter(file);
            writer.write(this.settings.toString());
            writer.close();
        }catch (IOException e) {
            System.out.println("failed to save settings");
        }
    }

    @Override
    public String toString() {
        return this.settings.toString();
    }
}
