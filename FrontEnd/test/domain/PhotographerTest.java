/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Mike
 */
public class PhotographerTest {
    
    Photographer photographer;
	Photographer photographer2;
	
	int photographerID = 1;
	String username = "username";
	String name = "name";
	String address = "address";
	String zipcode = "zipcode";
	String city = "city";
	String email = "email";
	String telephone = "telephone";
	String companyName = "companyName";
	String bankAccount = "bankAccount";
	boolean isActive = true;
    
    /**
     *
     */
    public PhotographerTest() {
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
        photographer = new Photographer(username, name, address, zipcode, city, email, telephone, companyName, bankAccount, isActive);
	photographer2 = new Photographer(username, isActive);
    }
    
    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of getActive method, of class Photographer.
     */
    @Test
    public void testGetActive() {
        assertEquals(true, photographer2.getActive());
    }

    /**
     * Test of setActive method, of class Photographer.
     */
    @Test
    public void testSetActive() {
        photographer2.setActive(false);
        assertEquals(false, photographer2.getActive());
    }

    /**
     * Test of getBankAccount method, of class Photographer.
     */
    @Test
    public void testGetBankAccount() {
        assertEquals("name", photographer.getName());
    }

    /**
     * Test of setBankAccount method, of class Photographer.
     */
    @Test
    public void testSetBankAccount() {
        photographer.setBankAccount("NL87INGB6868865");
		assertEquals("NL87INGB6868865", photographer.getBankAccount());
    }

    /**
     * Test of getCompanyName method, of class Photographer.
     */
    @Test
    public void testGetCompanyName() {
        assertEquals("companyName", photographer.getCompanyName());
    }

    /**
     * Test of setCompanyName method, of class Photographer.
     */
    @Test
    public void testSetCompanyName() {
        photographer.setCompanyName("test");
		assertEquals("test", photographer.getCompanyName());
    }
}
