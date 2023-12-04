import edu.fau.eng.cop4331.ttt3d.util.SettingsManager;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class SettingsManagerTest {


    /**
     * Test of settings load/save successfully.
     */
    @Test
    void settingsManagerTest() {
        SettingsManager sm = SettingsManager.getInstance();
        sm.loadSettings();

        int customPort = sm.getSettings().getInt("customPort");
        System.out.println("before: " + customPort);


        Random r = new Random();
        sm.setValue("customPort", r.nextInt(65535));

        sm.loadSettings();
        int newCustomPort = sm.getSettings().getInt("customPort");
        System.out.println("after: " + newCustomPort);

        assert (customPort != newCustomPort): "Settings did not change";
    }


}
