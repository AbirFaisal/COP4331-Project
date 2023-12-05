import edu.fau.eng.cop4331.ttt3d.util.SettingsManager;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.Random;

public class SettingsManagerTest {


    /**
     * Test of settings load/save successfully.
     */
    @Test
    void settingsManagerTest() {
        SettingsManager sm = SettingsManager.getInstance();
        sm.loadSettings();

        String customPort = sm.getSettings().getString("userDefinedPort");
        System.out.println("before: " + customPort);

        sm.setValue("userDefinedPort", "0");

        sm.loadSettings();
        String newCustomPort = sm.getSettings().getString("userDefinedPort");
        System.out.println("after: " + newCustomPort);

        assert (!customPort.equals(newCustomPort)) : "Settings did not change";
    }


}
