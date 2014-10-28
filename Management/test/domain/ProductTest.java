/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Calendar;
import java.util.Date;
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
public class ProductTest {

    Product product;
    Product product2;
    Product product3;
    Photo foto;

    int productID = 1;
    String name = "name";
    float materialPrice = 10f;
    float price = 5f;
    Date uploadDate;
    Calendar cal = Calendar.getInstance();

    public ProductTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        product = new Product(productID, name, materialPrice);
        product2 = new Product(name, materialPrice);

        cal.set(2013, Calendar.JANUARY, 9, 10, 11, 12);
        uploadDate = cal.getTime();
        foto = new Photo(uploadDate, price);
        product3 = new Product(name, materialPrice, foto);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getMaterialPrice method, of class Product.
     */
    @Test
    public void testGetMaterialPrice() {
        assertEquals(10f, product.getMaterialPrice(), 0.0001);
    }

    /**
     * Test of setMaterialPrice method, of class Product.
     */
    @Test
    public void testSetMaterialPrice() {
        product.setMaterialPrice(15.06f);
        assertEquals(15.06f, product.getMaterialPrice(), 0.0001);
    }

    /**
     * Test of getName method, of class Product.
     */
    @Test
    public void testGetName() {
        assertEquals("name", product.getName());
    }

    /**
     * Test of setName method, of class Product.
     */
    @Test
    public void testSetName() {
        product.setName("muismat");;
        assertEquals("muismat", product.getName());
    }

    /**
     * Test of getProductID method, of class Product.
     */
    @Test
    public void testGetProductID() {
        assertEquals(1, product.getProductID());
    }
}
