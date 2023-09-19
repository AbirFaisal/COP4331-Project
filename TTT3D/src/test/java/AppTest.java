import edu.fau.eng.cop4331.ttt3d.App;
import edu.fau.eng.cop4331.ttt3d.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    //Test player ID generation
    @Test
    public void playerIDTest() throws Exception {
        App app = new App();
        String pidb = "";
        byte[] playerID = app.getPlayerID();

        for (int i = 0; i < playerID.length; i++) {
            pidb += playerID[i] + " ";
        }

        System.out.println("player ID Bytes: " + pidb);
    }


}
