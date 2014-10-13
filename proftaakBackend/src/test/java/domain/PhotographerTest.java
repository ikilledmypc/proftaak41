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
		photographer = new Photographer(username, name, address, zipcode, city, email, telephone, companyName, bankAccount);
		photographer2 = new Photographer(username, isActive);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link domain.Photographer#Photographer(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testPhotographerStringStringStringStringStringStringStringStringString() {
		Photographer p = new Photographer(username, name, address, zipcode, city, email, telephone, companyName, bankAccount);
		assertEquals(username, p.getUsername());
		assertEquals(name, p.getName());
		assertEquals(address, p.getAddress());
		assertEquals(zipcode, p.getZipcode());
		assertEquals(city, p.getCity());
		assertEquals(email, p.getEmail());
		assertEquals(telephone, p.getTelephone());
		assertEquals(companyName, p.getCompanyName());
		assertEquals(bankAccount, p.getBankAccount());
	}

	/**
	 * Test method for {@link domain.Photographer#Photographer(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean)}.
	 */
	@Test
	public void testPhotographerStringStringStringStringStringStringStringStringStringBoolean() {
		Photographer p = new Photographer(username, name, address, zipcode, city, email, telephone, companyName, bankAccount, isActive);
		assertEquals(username, p.getUsername());
		assertEquals(name, p.getName());
		assertEquals(address, p.getAddress());
		assertEquals(zipcode, p.getZipcode());
		assertEquals(city, p.getCity());
		assertEquals(email, p.getEmail());
		assertEquals(telephone, p.getTelephone());
		assertEquals(companyName, p.getCompanyName());
		assertEquals(bankAccount, p.getBankAccount());
		assertEquals(isActive, p.getActive());
	}

	/**
	 * Test method for {@link domain.Photographer#Photographer(java.lang.String, boolean)}.
	 */
	@Test
	public void testPhotographerStringBoolean() {
		Photographer p = new Photographer(username, isActive);
		assertEquals(username, p.getUsername());
		assertEquals(isActive, p.getActive());
	}

	/**
	 * Test method for {@link domain.Photographer#getActive()}.
	 */
	@Test
	public void testGetActive() {
		assertEquals(true, photographer2.getActive());
	}

	/**
	 * Test method for {@link domain.Photographer#setActive(java.lang.Boolean)}.
	 */
	@Test
	public void testSetActive() {
		photographer2.setActive(false);
		assertEquals(false, photographer2.getActive());
	}

	/**
	 * Test method for {@link domain.Photographer#getBankAccount()}.
	 */
	@Test
	public void testGetBankAccount() {
		assertEquals("name", photographer.getName());
	}

	/**
	 * Test method for {@link domain.Photographer#setBankAccount(java.lang.String)}.
	 */
	@Test
	public void testSetBankAccount() {
		photographer.setBankAccount("NL87INGB6868865");
		assertEquals("NL87INGB6868865", photographer.getBankAccount());
	}

	/**
	 * Test method for {@link domain.Photographer#getCompanyName()}.
	 */
	@Test
	public void testGetCompanyName() {
		assertEquals("companyName", photographer.getCompanyName());
	}

	/**
	 * Test method for {@link domain.Photographer#setCompanyName(java.lang.String)}.
	 */
	@Test
	public void testSetCompanyName() {
		photographer.setCompanyName("test");
		assertEquals("test", photographer.getCompanyName());
	}

}
