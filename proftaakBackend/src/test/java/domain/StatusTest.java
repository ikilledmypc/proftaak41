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
public class StatusTest {

	Status status1;
	Status status2;
	
	int statusID = 1;
	String status = "status";
	
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
		status1 = new Status(status);
		status2 = new Status(statusID, status);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link domain.Status#Status(java.lang.String)}.
	 */
	@Test
	public void testStatusString() {
		Status s = new Status(status);
		assertEquals(status, s.getStatus());
	}

	/**
	 * Test method for {@link domain.Status#Status(int, java.lang.String)}.
	 */
	@Test
	public void testStatusIntString() {
		Status s = new Status(statusID, status);
		assertEquals(statusID, s.getStatusID());
		assertEquals(status, s.getStatus());
	}

	/**
	 * Test method for {@link domain.Status#getStatus()}.
	 */
	@Test
	public void testGetStatus() {
		assertEquals("status", status2.getStatus());
	}

	/**
	 * Test method for {@link domain.Status#setStatus(java.lang.String)}.
	 */
	@Test
	public void testSetStatus() {
		status2.setStatus("in behandeling");
		assertEquals("in behandeling", status2.getStatus());
	}

	/**
	 * Test method for {@link domain.Status#getStatusID()}.
	 */
	@Test
	public void testGetStatusID() {
		assertEquals(1, status2.getStatusID());
	}

	/**
	 * Test method for {@link domain.Status#setStatusID(int)}.
	 */
	@Test
	public void testSetStatusID() {
		status2.setStatusID(2);
		assertEquals(2, status2.getStatusID());
	}

}
