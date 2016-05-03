
package whatscooking;

/**
 * A class to represent a Recipe that can be created and added to the recipe
 * file.
 * 
 * @author Dedrei Lombard 1313685
 * @version v1.0 - 2014.08: Created
 */
  public class Recipe
{
    private String type;
    private String title;
    private String ingredients;
    private String directions;
    
    /**
     * Creates a new instance of a Recipe.
     * 
     * @param title the title of the recipe
     * @param ingredients the ingredients of the recipe
     * @param directions the directions to make the recipe
     */
    
    public Recipe(String title, String ingredients, String directions)
    {
        this.type = type;
        this.title = title;
        this.ingredients = ingredients;
        this.directions = directions;
    }
    
     /**
     * Returns the string representation of the recipe
     * 
     * @return the recipe as a string
     */
    public String getStringRep()
    {
            return (this.title + this.ingredients + this.directions);
    }
    
     /**
     * Returns the title of the recipe.
     * 
     * @return the title of the recipe
     */
    public String getType() 
    {
        return this.type;
    }
    
    /**
     * Changes the title of the recipe.
     * 
     * @param title the new title
     */
    public void setType(String type) 
    {
        this.type = type;
    }
    /**
     * Returns the title of the recipe.
     * 
     * @return the title of the recipe
     */
    public String getTitle() 
    {
        return this.title;
    }
    
    /**
     * Changes the title of the recipe.
     * 
     * @param title the new title
     */
    public void setTitle(String title) 
    {
        this.title = title;
    }
    
    /**
     * Returns the ingredients of the recipe.
     * 
     * @return the ingredients of the recipe
     */
    public String getIngredients() 
    {
        return this.ingredients;
    }
    
    /**
     * Changes the ingredients of the recipe.
     * 
     * @param ingredients the new title
     */
    public void setIngredients(String ingredients) 
    {
        this.ingredients = ingredients;
    }
    
    /**
     * Returns the directions of the recipe.
     * 
     * @return the directions of the recipe
     */
    public String getDirections() 
    {
        return this.directions;
    }
    
    /**
     * Changes the directions of the recipe.
     * 
     * @param directions the new directions
     */
    public void setDirections(String directions) 
    {
        this.directions = directions;
    }    
}

