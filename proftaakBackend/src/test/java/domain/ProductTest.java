/**
 * 
 */
package domain;

import static org.junit.Assert.*;

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
public class ProductTest {
	
	Product product;
	Product product2;
	Product product3;
	Photo foto;
	
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
		product = new Product(productID, name, materialPrice);
		product2 = new Product(name, materialPrice);
		
	    cal.set(2013, Calendar.JANUARY, 9, 10, 11, 12);
        uploadDate = cal;
		foto = new Photo(name, uploadDate, price, 100, 100);
		product3 = new Product(name, materialPrice, foto);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link domain.Product#Product(int, java.lang.String, float)}.
	 */
	@Test
	public void testProductIntStringFloat() {
		Product p = new Product(productID, name, materialPrice);
		assertEquals(productID, p.getProductID());
		assertEquals(name, p.getName());
		assertEquals(materialPrice, p.getMaterialPrice(), 0.0001);
	}

	/**
	 * Test method for {@link domain.Product#Product(java.lang.String, float)}.
	 */
	@Test
	public void testProductStringFloat() {
		Product p = new Product(name, materialPrice);
		assertEquals(name, p.getName());
		assertEquals(materialPrice, p.getMaterialPrice(), 0.0001);
	}

	/**
	 * Test method for {@link domain.Product#Product(java.lang.String, float, domain.Photo)}.
	 */
	@Test
	public void testProductStringFloatPhoto() {
		Product p = new Product(name, materialPrice, foto);
		assertEquals(name, p.getName());
		assertEquals(materialPrice, p.getMaterialPrice(), 0.0001);
	}

	/**
	 * Test method for {@link domain.Product#getMaterialPrice()}.
	 */
	@Test
	public void testGetMaterialPrice() {
		assertEquals(10f, product.getMaterialPrice(), 0.0001);
	}

	/**
	 * Test method for {@link domain.Product#setMaterialPrice(float)}.
	 */
	@Test
	public void testSetMaterialPrice() {
		product.setMaterialPrice(15.06f);
		assertEquals(15.06f, product.getMaterialPrice(), 0.0001);
	}

	/**
	 * Test method for {@link domain.Product#getName()}.
	 */
	@Test
	public void testGetName() {
		assertEquals("name", product.getName());
	}

	/**
	 * Test method for {@link domain.Product#setName(java.lang.String)}.
	 */
	@Test
	public void testSetName() {
		product.setName("muismat");;
		assertEquals("muismat", product.getName());
	}

	/**
	 * Test method for {@link domain.Product#getProductID()}.
	 */
	@Test
	public void testGetProductID() {
		assertEquals(1, product.getProductID());
	}

}
