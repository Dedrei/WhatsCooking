/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package whatscooking;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A class to test the User class methods
 * 
 * @author Dedrei Lombard
 * @version v1.0 - 2014.08: Created
 */
public class UserTest {
    
    private User user; 
    
    public UserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {   
    }
    
    @AfterClass
    public static void tearDownClass() {
      
    }
    
    @Before
    public void setUp() {
         user = new User("Dedrei");
    }
    
    @After
    public void tearDown() {
          user = null;
    }

    /**
     * Test of getUserName method, of class User.
     */
    @Test
    public void testGetUserName_goodName() {
        System.out.println("getUserName");
        //User instance = new User("Dedrei");
        String expResult = "Dedrei";
        String result = user.getUserName();
        assertEquals(expResult, result);
    }
    
    @Test(expected=NullPointerException.class)
    public void testGetUserName_userIsNull() {
        System.out.println("getUserName");
        user = null;
        String expResult = "";
        String result = user.getUserName();
    }

    /**
     * Test of setUserName method, of class User.
     */
    @Test
    public void testSetUserName() {
        System.out.println("setUserName");
        String name = "Sam";
        //User instance = new User("Sam");
        user.setUserName(name);
    }
    
}
