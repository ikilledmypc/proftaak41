/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

import Controller.HttpController;
import frontend.FrontEnd;
import java.io.IOException;
import java.net.Socket;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author tim
 */
public class ConnectionTest {
    
    /**
     *
     */
    public ConnectionTest() {
    }
    
    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {
    }
    
    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     *
     */
    @Before
    public void setUp() {
        
    }
    
    /**
     *
     */
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    /**
     *
     */
        
    @Test
    public void testServerUp()  {
        try {
            String server = FrontEnd.HOST.substring(7);
            String[] serversplit = server.split(":");
            Socket s = new Socket(serversplit[0],Integer.parseInt(serversplit[1]));
        } catch (IOException ex) {
           fail("host unreachable");
        }
    }

    /**
     *
     */
    @Test
    public void testGet(){
        String test = HttpController.excuteGet(FrontEnd.HOST+"/helloWorld");
        test = test.trim();
        assertEquals(test, "HELLO WORLD");
    }

    /**
     *
     */
    @Test
    public void testPost(){
        String test = HttpController.excutePost(FrontEnd.HOST+"/helloWorld","");
        test = test.trim();
        assertEquals(test, "HELLO WORLD");
    }
}

