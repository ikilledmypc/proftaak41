/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mike
 */
public class PhotographerTest {
    
    public PhotographerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getActive method, of class Photographer.
     */
    @Test
    public void testGetActive() {
        System.out.println("getActive");
        Photographer instance = null;
        Boolean expResult = null;
        Boolean result = instance.getActive();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setActive method, of class Photographer.
     */
    @Test
    public void testSetActive() {
        System.out.println("setActive");
        Boolean active = null;
        Photographer instance = null;
        instance.setActive(active);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBankAccount method, of class Photographer.
     */
    @Test
    public void testGetBankAccount() {
        System.out.println("getBankAccount");
        Photographer instance = null;
        String expResult = "";
        String result = instance.getBankAccount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBankAccount method, of class Photographer.
     */
    @Test
    public void testSetBankAccount() {
        System.out.println("setBankAccount");
        String bankAccount = "";
        Photographer instance = null;
        instance.setBankAccount(bankAccount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompanyName method, of class Photographer.
     */
    @Test
    public void testGetCompanyName() {
        System.out.println("getCompanyName");
        Photographer instance = null;
        String expResult = "";
        String result = instance.getCompanyName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCompanyName method, of class Photographer.
     */
    @Test
    public void testSetCompanyName() {
        System.out.println("setCompanyName");
        String companyName = "";
        Photographer instance = null;
        instance.setCompanyName(companyName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPhotographerID method, of class Photographer.
     */
    @Test
    public void testGetPhotographerID() {
        System.out.println("getPhotographerID");
        Photographer instance = null;
        int expResult = 0;
        int result = instance.getPhotographerID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPhotographerID method, of class Photographer.
     */
    @Test
    public void testSetPhotographerID() {
        System.out.println("setPhotographerID");
        int photographerID = 0;
        Photographer instance = null;
        instance.setPhotographerID(photographerID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
