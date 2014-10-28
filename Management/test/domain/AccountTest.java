/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import Controller.RegisterScreenController;
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
public class AccountTest {
    
    Account account;
    Account account2;
    Account account3;

    int accountID = 1;
    String username = "username";
    String name = "name";
    String address = "address";
    String zipcode = "zipcode";
    String city = "city";
    String email = "email";
    String telephone = "telephone";
    
    public AccountTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        account = new Account(username);
	account2 = new Account(username, name, address, zipcode, city, email, telephone);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getTelephone method, of class Account.
     */
    @Test
    public void testGetTelephone() {
        assertEquals(telephone, account2.getTelephone());
    }

    /**
     * Test of setTelephone method, of class Account.
     */
    @Test
    public void testSetTelephone() {
        account2.setTelephone("0614140203");;
	assertEquals("0614140203", account2.getTelephone());
    }

    /**
     * Test of getEmail method, of class Account.
     */
    @Test
    public void testGetEmail() {
        assertEquals(email, account2.getEmail());
    }

    /**
     * Test of setEmail method, of class Account.
     */
    @Test
    public void testSetEmail() {
        account2.setEmail("m.rooijackers@student.fontys.nl");
	assertEquals("m.rooijackers@student.fontys.nl", account2.getEmail());
    }

    /**
     * Test of getCity method, of class Account.
     */
    @Test
    public void testGetCity() {
        assertEquals(city, account2.getCity());
    }

    /**
     * Test of setCity method, of class Account.
     */
    @Test
    public void testSetCity() {
        account2.setCity("Eindhoven");
	assertEquals("Eindhoven", account2.getCity());
    }

    /**
     * Test of getZipcode method, of class Account.
     */
    @Test
    public void testGetZipcode() {
        assertEquals(zipcode, account2.getZipcode());
    }

    /**
     * Test of setZipcode method, of class Account.
     */
    @Test
    public void testSetZipcode() {
        account2.setZipcode("5656SS");
	assertEquals("5656SS", account2.getZipcode());
    }

    /**
     * Test of getAddress method, of class Account.
     */
    @Test
    public void testGetAddress() {
        assertEquals(address, account2.getAddress());
    }

    /**
     * Test of setAddress method, of class Account.
     */
    @Test
    public void testSetAddress() {
        account2.setAddress("straat 1");
	assertEquals("straat 1", account2.getAddress());
    }

    /**
     * Test of getName method, of class Account.
     */
    @Test
    public void testGetName() {
        assertEquals(name, account2.getName());
    }

    /**
     * Test of setName method, of class Account.
     */
    @Test
    public void testSetName() {
        account2.setName("mike");
	assertEquals("mike", account2.getName());
    }

    /**
     * Test of getUsername method, of class Account.
     */
    @Test
    public void testGetUsername() {
        assertEquals(username, account2.getUsername());
    }

    /**
     * Test of setUsername method, of class Account.
     */
    @Test
    public void testSetUsername() {
        account2.setUsername("mike");
	assertEquals("mike", account2.getUsername());
    }
    
}
