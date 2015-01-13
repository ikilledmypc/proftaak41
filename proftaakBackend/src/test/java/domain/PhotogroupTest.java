/**
 * 
 */
package domain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Mike
 *
 */
public class PhotogroupTest {
	
	PhotoGroup photogroup;
	ArrayList<Photo> photo;
	
	int accountID = 1;
	String code = "123";
	String groupName = "groupName";
	boolean isPublic = true;
	int parentPhotogroupID = 2;
	
	int productID = 1;
	String name = "name";
	float materialPrice = 10f;
	float price = 5f;
	Calendar uploadDate;
	Calendar cal = Calendar.getInstance();
	

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
		photogroup = new PhotoGroup(accountID, code, groupName, isPublic, parentPhotogroupID, photo);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link domain.Photogroup#Photogroup(int, String, java.lang.String, java.lang.Boolean, int)}.
	 */
	@Test
	public void testPhotogroup() {
		PhotoGroup p = new PhotoGroup(accountID, code, groupName, isPublic, parentPhotogroupID, photo);
		assertEquals(accountID, p.getAccountID());
		assertEquals(code, p.getCode());
		assertEquals(groupName, p.getGroupName());
		assertEquals(isPublic, p.getIsPublic());
		assertEquals(parentPhotogroupID, p.getParentPhotogroupID());
	}

	/**
	 * Test method for {@link domain.Photogroup#getParentPhotogroupID()}.
	 */
	@Test
	public void testGetParentPhotogroupID() {
		assertEquals(2, photogroup.getParentPhotogroupID());
	}

	/**
	 * Test method for {@link domain.Photogroup#setParentPhotogroupID(int)}.
	 */
	@Test
	public void testSetParentPhotogroupID() {
		photogroup.setParentPhotogroupID(90);
		assertEquals(90, photogroup.getParentPhotogroupID());
	}

	/**
	 * Test method for {@link domain.Photogroup#getIsPublic()}.
	 */
	@Test
	public void testGetIsPublic() {
		assertEquals(true, photogroup.getIsPublic());
	}

	/**
	 * Test method for {@link domain.Photogroup#setIsPublic(java.lang.Boolean)}.
	 */
	@Test
	public void testSetIsPublic() {
		photogroup.setIsPublic(false);
		assertEquals(false, photogroup.getIsPublic());
	}

	/**
	 * Test method for {@link domain.Photogroup#getGroupName()}.
	 */
	@Test
	public void testGetGroupName() {
		assertEquals("groupName", photogroup.getGroupName());
	}

	/**
	 * Test method for {@link domain.Photogroup#setGroupName(java.lang.String)}.
	 */
	@Test
	public void testSetGroupName() {
		photogroup.setGroupName("test");
		assertEquals("test", photogroup.getGroupName());
	}

	/**
	 * Test method for {@link domain.Photogroup#setCode(int)}.
	 */
	@Test
	public void testSetCode() {
		photogroup.setCode("123456789");
		assertEquals("123456789", photogroup.getCode());
	}

	/**
	 * Test method for {@link domain.Photogroup#getAccountID()}.
	 */
	@Test
	public void testGetAccountID() {
		assertEquals(1, photogroup.getAccountID());
	}

	/**
	 * Test method for {@link domain.Photogroup#setAccountID(int)}.
	 */
	@Test
	public void testSetAccountID() {
		photogroup.setAccountID(2);
		assertEquals(2, photogroup.getAccountID());
	}

}
