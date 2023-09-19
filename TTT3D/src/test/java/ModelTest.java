import edu.fau.eng.cop4331.ttt3d.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ObjectOutputStream;
import java.util.zip.CRC32;
import java.util.zip.Checksum;


public class ModelTest {

    //Serialize Deserialize test
    @Test
    public void serDeserTest() throws Exception {
        Model m1 = new Model("Model1");
//        System.out.println("Player ID: " + m1.getplayerIDasString());

//        ObjectOutputStream objectOutputStream = new ObjectOutputStream();

//        System.out.println(m1.getplayerID());

        assertEquals(m1.hashCode(), m1.hashCode());
    }


}


