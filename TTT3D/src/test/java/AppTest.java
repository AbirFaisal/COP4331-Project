import edu.fau.eng.cop4331.ttt3d.app.App;


import org.junit.*;

import javax.swing.*;

public class AppTest {


    public AppTest(){

    }


    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {

    }

    @Before
    public void setUp(){

    }

    @After
    public void tearDown() {

    }



    //Test player ID generation
    @Test
    public void playerIDTest() throws Exception {
        App app = App.getInstance();
        String pidb = "";
        byte[] playerID = app.getClientID();

        for (int i = 0; i < playerID.length; i++) {
            pidb += playerID[i] + " ";
        }

        System.out.println("player ID Bytes: " + pidb);
    }


    @Test
    public void test() {
        JOptionPane.showInputDialog("hello");
        JOptionPane.showConfirmDialog(null, "message", "tiTle", 1, 2);

    }


}
