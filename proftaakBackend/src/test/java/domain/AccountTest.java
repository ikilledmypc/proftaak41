/**
 * 
 */
package domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Mike
 *
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
	

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		account = new Account(username);
		account2 = new Account(accountID, username, name, address, zipcode, city, email, telephone);
		account3 = new Account(username, name, address, zipcode, city, email, telephone);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link domain.Account#Account(java.lang.String)}.
	 */
	@Test
	public void testAccountString() {
		Account a = new Account(username);
		assertEquals(username, a.getUsername());
	}

	/**
	 * Test method for {@link domain.Account#Account(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAccountStringStringStringStringStringStringString() {
		Account a = new Account(username, name, address, zipcode, city, email, telephone);
		assertEquals(username, a.getUsername());
		assertEquals(name, a.getName());
		assertEquals(address, a.getAddress());
		assertEquals(zipcode, a.getZipcode());
		assertEquals(city, a.getCity());
		assertEquals(email, a.getEmail());
		assertEquals(telephone, a.getTelephone());
	}

	/**
	 * Test method for {@link domain.Account#Account(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAccountIntStringStringStringStringStringStringString() {
		Account a = new Account(accountID, username, name, address, zipcode, city, email, telephone);
		assertEquals(username, a.getUsername());
		assertEquals(name, a.getName());
		assertEquals(address, a.getAddress());
		assertEquals(zipcode, a.getZipcode());
		assertEquals(city, a.getCity());
		assertEquals(email, a.getEmail());
		assertEquals(telephone, a.getTelephone());
		assertEquals(accountID, a.getAccountID());
	}

	/**
	 * Test method for {@link domain.Account#getTelephone()}.
	 */
	@Test
	public void testGetTelephone() {
		assertEquals(telephone, account2.getTelephone());
	}

	/**
	 * Test method for {@link domain.Account#setTelephone(java.lang.String)}.
	 */
	@Test
	public void testSetTelephone() {
		account2.setTelephone("0614140203");;
		assertEquals("0614140203", account2.getTelephone());
	}

	/**
	 * Test method for {@link domain.Account#getEmail()}.
	 */
	@Test
	public void testGetEmail() {
		assertEquals(email, account2.getEmail());
	}

	/**
	 * Test method for {@link domain.Account#setEmail(java.lang.String)}.
	 */
	@Test
	public void testSetEmail() {
		account2.setEmail("m.rooijackers@student.fontys.nl");
		assertEquals("m.rooijackers@student.fontys.nl", account2.getEmail());
	}

	/**
	 * Test method for {@link domain.Account#getCity()}.
	 */
	@Test
	public void testGetCity() {
		assertEquals(city, account2.getCity());
	}

	/**
	 * Test method for {@link domain.Account#setCity(java.lang.String)}.
	 */
	@Test
	public void testSetCity() {
		account2.setCity("Eindhoven");
		assertEquals("Eindhoven", account2.getCity());
	}

	/**
	 * Test method for {@link domain.Account#getZipcode()}.
	 */
	@Test
	public void testGetZipcode() {
		assertEquals(zipcode, account2.getZipcode());
	}

	/**
	 * Test method for {@link domain.Account#setZipcode(java.lang.String)}.
	 */
	@Test
	public void testSetZipcode() {
		account2.setZipcode("5656SS");
		assertEquals("5656SS", account2.getZipcode());
	}

	/**
	 * Test method for {@link domain.Account#getAddress()}.
	 */
	@Test
	public void testGetAddress() {
		assertEquals(address, account2.getAddress());
	}

	/**
	 * Test method for {@link domain.Account#setAddress(java.lang.String)}.
	 */
	@Test
	public void testSetAddress() {
		account2.setAddress("straat 1");
		assertEquals("straat 1", account2.getAddress());
	}

	/**
	 * Test method for {@link domain.Account#getName()}.
	 */
	@Test
	public void testGetName() {
		assertEquals(name, account2.getName());
	}

	/**
	 * Test method for {@link domain.Account#setName(java.lang.String)}.
	 */
	@Test
	public void testSetName() {
		account2.setName("mike");
		assertEquals("mike", account2.getName());
	}

	/**
	 * Test method for {@link domain.Account#getUsername()}.
	 */
	@Test
	public void testGetUsername() {
		assertEquals(username, account2.getUsername());
	}

	/**
	 * Test method for {@link domain.Account#setUsername(java.lang.String)}.
	 */
	@Test
	public void testSetUsername() {
		account2.setUsername("mike");
		assertEquals("mike", account2.getUsername());
	}

	/**
	 * Test method for {@link domain.Account#getAccountID()}.
	 */
	@Test
	public void testGetAccountID() {
		assertEquals(accountID, account2.getAccountID());
	}

}
