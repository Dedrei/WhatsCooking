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
 * A class to test the Recipe class methods
 * 
 * @author Dedrei Lombard
 * @version v1.0 - 2014.08: Created
 */
public class RecipeTest {
    
    private Recipe testRecipe;
    
    public RecipeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
      testRecipe = new Recipe("Title","Ingredients","Directions");
    }
    
    @After
    public void tearDown() {
        testRecipe = null;
    }

    /**
     * Test of getStringRep method, of class Recipe.
     */
    @Test
    public void testGetStringRep_notNull() {
        System.out.println("getStringRep");
        String expResult = "TitleIngredientsDirections";
        String result = testRecipe.getStringRep();
        assertEquals(expResult, result);
    }
    

    /**
     * Test of getTitle method, of class Recipe.
     */
    @Test
    public void testGetTitle_notNull() {
        System.out.println("getTitle");
        String expResult = "Title";
        String result = testRecipe.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTitle method, of class Recipe.
     */
    @Test
    public void testSetTitle_notNull() {
        System.out.println("setTitle");
        String title = "New Title";
        testRecipe.setTitle(title);
    }

    /**
     * Test of getIngredients method, of class Recipe.
     */
    @Test
    public void testGetIngredients_notNull() {
        System.out.println("getIngredients");
        String expResult = "Ingredients";
        String result = testRecipe.getIngredients();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIngredients method, of class Recipe.
     */
    @Test
    public void testSetIngredients_notNull() {
        System.out.println("setIngredients");
        String ingredients = "New Ingredients";
        testRecipe.setIngredients(ingredients);
    }

    /**
     * Test of getDirections method, of class Recipe.
     */
    @Test
    public void testGetDirections_notNull() {
        System.out.println("getDirections");
        String expResult = "Directions";
        String result = testRecipe.getDirections();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDirections method, of class Recipe.
     */
    @Test
    public void testSetDirections_notNull() {
        System.out.println("setDirections");
        String directions = "New Directions";
        testRecipe.setDirections(directions);
    }
    
}
