
package whatscooking;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class to represent the Recipe File that stores all the recipes.
 * 
 * @author Dedrei Lombard 1313685
 * @version v1.0 - 2014.08: Created
 */

public final class RecipeFile {
  
    private ArrayList<Recipe> allRecipes;
    private ArrayList<Recipe> beefRecipes;
    private ArrayList<Recipe> chickenRecipes;
    private ArrayList<Recipe> fishRecipes;
    private ArrayList<Recipe> vegRecipes;
    private ArrayList<Recipe> myFavourites;
    private Recipe newRecipe;
    private DBOperations db = new DBOperations();
    private Connection conn;
    private Statement statement;
    private ResultSet result;
    private User user;
    
    /**
     * Creates a new instance of the recipe file and adds all recipes to the
     * file from the database
     */
    public RecipeFile()
    {
        allRecipes = new ArrayList<>();
        
          try 
        {
            int recipeId = 1;
            db.establishConnection();
            conn = db.getConnection();
            statement = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
            String type;
            String title;
            String ingredients;
            String directions;
            //get amount of recipes in the database and add all recipes to the recipe file
            int amountRecipes = db.getAmountRecipes();
                while(recipeId <= amountRecipes)
                {
                    String query = "SELECT recipe_type, recipe_title, recipe_ingredients"
                    + ", recipe_directions FROM recipes WHERE recipe_id = " +
                    recipeId;
                    ResultSet result = statement.executeQuery(query);
                    while(result.next())
                    {
                        type = result.getString("recipe_type");
                        title = result.getString("recipe_title");
                        ingredients = result.getString("recipe_ingredients");
                        directions = result.getString("recipe_directions");
                        allRecipes.add(addRecipe(type,title,ingredients,directions));  
                        recipeId++;
                    }   
                }
            conn.close();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
     /**
     * Gets the collection of beef recipes from the recipe file
     * 
     * @return the collection of beef recipes
     */
    public ArrayList<Recipe> getBeefRecipes()
    {
        beefRecipes = new ArrayList<>();
        
        for(int index = 0; index < allRecipes.size(); ++index)
        {
            if(allRecipes.get(index) instanceof BeefRecipe)
            {
                beefRecipes.add(allRecipes.get(index));
            }
        }
        return beefRecipes;
    }
    
     /**
     * Gets the collection of chicken recipes from the recipe file
     * 
     * @return the collection of chicken recipes
     */
     public ArrayList<Recipe> getChickenRecipes()
    {
        chickenRecipes = new ArrayList<>();
        
        for(int index = 0; index < allRecipes.size(); ++index)
        {
            if(allRecipes.get(index) instanceof ChickenRecipe)
            {
                chickenRecipes.add(allRecipes.get(index));
            }
        }
        return chickenRecipes;
    }
     
    /**
     * Gets the collection of fish recipes from the recipe file
     * 
     * @return the collection of fish recipes
     */
      public ArrayList<Recipe> getFishRecipes()
    {
        fishRecipes = new ArrayList<>();
        for(int index = 0; index < allRecipes.size(); ++index)
        {
            if(allRecipes.get(index) instanceof SeafoodRecipe)
            {
                fishRecipes.add(allRecipes.get(index));
            }
        }
        return fishRecipes;
    }
      
     /**
     * Gets the collection of vegetarian recipes from the recipe file
     * 
     * @return the collection of vegetarian recipes
     */
       public ArrayList<Recipe> getVegRecipes()
    {
        vegRecipes = new ArrayList<>();
        
        for(int index = 0; index < allRecipes.size(); ++index)
        {
            if(allRecipes.get(index) instanceof VegetarianRecipe)
            {
                vegRecipes.add(allRecipes.get(index));
            }
        }
        return vegRecipes;
    }
       
     /**
     * Gets the collection of user's favourite recipes from the recipe file
     * 
     * @param user the user who's favourite recipes should be returned
     * @return the collection of favourite recipes
     */
    public ArrayList<Recipe> getMyFavouriteRecipes(User user)
    {
        try 
        {
            myFavourites = new ArrayList<>();
            db.establishConnection();
            conn = db.getConnection();
            statement = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
            String type;
            String title;
            String ingredients;
            String directions;
            
            //get current user's favorite recipeid's from the database
            int userId= user.getUserId();
            String firstQuery = "SELECT recipe_id FROM favourites WHERE" +
                    " user_id = " + userId;
            ResultSet firstResult = statement.executeQuery(firstQuery);
            //create an array with all favorite recipe id's
            ArrayList<Integer> favIds = new ArrayList<>();
            while(firstResult.next())
            {
                int favId = firstResult.getInt("recipe_id");
                favIds.add(favId);
            }
            //get all favorite recipes from the database
            for(int i =0; i<favIds.size(); ++i)
            {
                String secondQuery = "SELECT recipe_type, recipe_title, recipe_ingredients"
                        + ", recipe_directions FROM recipes WHERE recipe_id = " +
                        favIds.get(i);
                ResultSet secondResult = statement.executeQuery(secondQuery);
                while(secondResult.next())
                {
                    type = secondResult.getString("recipe_type");
                    title = secondResult.getString("recipe_title");
                    ingredients = secondResult.getString("recipe_ingredients");
                    directions = secondResult.getString("recipe_directions");
                    myFavourites.add(addRecipe(type,title,ingredients,directions));
                }
            }
            conn.close();
           
        } catch (SQLException ex) {
            Logger.getLogger(RecipeFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myFavourites;
    }
    
       
    /**
     * A method that adds a recipe to the recipe file
     * 
     * @param type the type of recipe
     * @param title the title of the recipe
     * @param ingredients the recipe ingredients
     * @param directions the directions for the recipe
     * 
     * @return the new recipe to add
     */
    public Recipe addRecipe(String type,String title,String ingredients,String directions) 
    {

        switch (type) 
        {
            case "Chicken":
                newRecipe = new ChickenRecipe(title, ingredients, directions);
                break;
            case "Beef":
                newRecipe = new BeefRecipe(title, ingredients, directions);
                break;
            case "Seafood":
                newRecipe = new SeafoodRecipe(title, ingredients, directions);
                break;
            case "Vegetarian":
                newRecipe = new VegetarianRecipe(title, ingredients, directions);
                break;
        }
        return newRecipe;
    }             
} 
     


