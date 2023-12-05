package edu.fau.eng.cop4331.ttt3d.util;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.lang.System.exit;

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
        //check if settings.json exists
        File file = new File(settingsFileName);

        try {
            //if exist then load from file
            if (file.exists()) {
                String jsonString = Files.readString(Path.of(file.getPath()));
                this.settings = new JSONObject(jsonString);

            } else {
                //get default settings
                String jsonString = new String(
                        SettingsManager.class.getClassLoader().getResourceAsStream(settingsFileName).readAllBytes()
                );
                //load into this and save to file
                this.settings = new JSONObject(jsonString);
                saveSettingsToFile();
            }
        }catch (IOException e) {
            System.out.println("Failed to load settings");
        }
        System.out.println(this.settings);//TODO remove?
    }

    public JSONObject getSettings() {
        return settings;
    }

    public synchronized void setValue(String key, Object value) {
        this.settings.put(key, value);
        saveSettingsToFile();
    }

    synchronized void saveSettingsToFile() {
        //save the changes to settings.json
        File file = new File(settingsFileName);

        try {
            PrintWriter writer = new PrintWriter(file);
            writer.write(this.settings.toString());
            writer.close();
        }catch (IOException e) {
            System.out.println(e);
            System.out.println("failed to save settings");
        }
    }

    @Override
    public String toString() {
        return this.settings.toString();
    }
}
