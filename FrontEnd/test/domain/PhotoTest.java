/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

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
public class PhotoTest {
    
    public PhotoTest() {
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
     * Test of getPrice method, of class Photo.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        Photo instance = null;
        float expResult = 0.0F;
        float result = instance.getPrice();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPrice method, of class Photo.
     */
    @Test
    public void testSetPrice() {
        System.out.println("setPrice");
        float price = 0.0F;
        Photo instance = null;
        instance.setPrice(price);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUploadDate method, of class Photo.
     */
    @Test
    public void testGetUploadDate() {
        System.out.println("getUploadDate");
        Photo instance = null;
        Date expResult = null;
        Date result = instance.getUploadDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUploadDate method, of class Photo.
     */
    @Test
    public void testSetUploadDate() {
        System.out.println("setUploadDate");
        Date uploadDate = null;
        Photo instance = null;
        instance.setUploadDate(uploadDate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPhotoID method, of class Photo.
     */
    @Test
    public void testGetPhotoID() {
        System.out.println("getPhotoID");
        Photo instance = null;
        int expResult = 0;
        int result = instance.getPhotoID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPhotoID method, of class Photo.
     */
    @Test
    public void testSetPhotoID() {
        System.out.println("setPhotoID");
        int photoID = 0;
        Photo instance = null;
        instance.setPhotoID(photoID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
