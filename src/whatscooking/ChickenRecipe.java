/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package whatscooking;

/**
 * A class to represent a Recipe that is a Chicken Recipe.
 * 
 * @author Dedrei Lombard 1313685
 * @version v1.0 - 2014.08: Created
 */
public class ChickenRecipe extends Recipe
{
    String title;
    String ingredients;
    String directions;
    
/**
 * Constructor to construct an instance of a chicken recipe
 * 
 * @param title the title of the recipe
 * @param ingredients the ingredients of the recipe
 * @param directions the directions of the recipe
 */    
    public ChickenRecipe(String title, String ingredients, String directions)
    {
        super(title, ingredients, directions);
    }    
}
