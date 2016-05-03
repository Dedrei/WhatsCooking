
package whatscooking;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * The class that handles the operations of the recipe file.  
 * 
 * @author Dedrei Lombard 1313685
 * @version v1.0 - 2014.08: Created
 */

public class WhatsCooking {
    
    Scanner input = new Scanner(System.in);
    RecipeFile recipes;
    User user;
    
    
    /**
     * Creates a new instance of the WhatsCooking recipe manager.
     */
    public WhatsCooking() throws SQLException
    {
        recipes = new RecipeFile(); 
    }
    
    public RecipeFile getRecipeFile()
    {
        return recipes;
    }
    
    public void setRecipeFile(RecipeFile file)
    {
        recipes = file;
    }
    /**
     * The method that runs the recipe manager until the user chooses to quit.
     * 
     */
    public void run()
    {
        //Display welcome messge
        System.out.println("WHAT'S COOKING TONIGHT?\n");
        // Get the user name from the user
        user = new User(enterUserName());
        user.addUser(user);
        System.out.println("\nWelcome " + user.getUserName());
        boolean quit = false;
        //run the program until the user quits
        while(!quit)
        {
            displayMenu();
        }
    }
    /**
     * Display a request for the user to enter his/her name
     * and sets the user's name in CLI
     *
     * @return the user's name
     */
    public String enterUserName()
    {
        System.out.println("Please enter your name: ");
        return getUserInput();
    }
    /**
     * A method that displays the main menu on the screen in CLI 
     */
     public void displayMenu()
    {
         System.out.println("\nSelect one of the following main ingredients" +
                " to view a recipe:\n1)Chicken\n2)Beef\n3)Seafood\n4)Vegetar" +
                "ian\n5)My Favourites\n6)Add Recipe\n7)Quit");  
    }
     
   /**
     * Returns user's input from the keyboard
     * 
     * @return the user input as a String
     */
    public String getUserInput()
    {
        String selection = input.nextLine();
        return selection;
    }
    
    /**
     * Returns user's selection from the combo box
     * 
     * @return the user selection as a String
     */
    public String getUserSelection()
    {
        String selection = input.nextLine();
        return selection;
    }
    
     /**
     * Displays the title of the recipe from the user's selection and iterates through
     * recipes on the user's request.
     *  
     * @param input the type of recipe the user selected
     * @param index the position of the recipe in the array that needs to be displayed
     * @param user the current user
     * 
     * @return the recipe title to display
     */
     public String displayRecipeTitle(String input, int index, User user)
    {
            String toDisplay = "";
        switch (input) 
        {
            case "Select":
                    toDisplay = "";
            case "Chicken":
                if(index < recipes.getChickenRecipes().size())
                {
                    toDisplay = (recipes.getChickenRecipes().get(index).getTitle());
                }
                break;
            case "Beef":
                if(index < recipes.getBeefRecipes().size())
                {
                    toDisplay = (recipes.getBeefRecipes().get(index).getTitle());
                }
                break;
            case "Seafood":
                if(index < recipes.getFishRecipes().size())
                {
                    toDisplay = (recipes.getFishRecipes().get(index).getTitle());
                }
                break;
            case "Vegetarian":
                if(index < recipes.getVegRecipes().size())
                {
                    toDisplay = (recipes.getVegRecipes().get(index).getTitle());
                }
                break;
                case "myFavourites":
                if(index < recipes.getMyFavouriteRecipes(user).size())
                {
                    toDisplay = (recipes.getMyFavouriteRecipes(user).get(index).getTitle());
                }
                break;
            default:
                // no slection made
               toDisplay = "";
        } 
        return toDisplay;
    }
     
      /**
     * Displays the ingredients of the recipe from the user's selection and iterates through
     * recipes on the user's request.
     *  
     * @param input the type of recipe the user selected
     * @param index the position of the recipe in the array that needs to be displayed
     * @param user the current user
     * 
     * @return the recipe ingredients to display
     */
    public String displayRecipeIngredients(String input, int index, User user)
    {
            String toDisplay = "";
        switch (input) 
        {
            case "Select":
                    toDisplay = "";
                break;
            case "Chicken":
                if(index < recipes.getChickenRecipes().size())
                {
                    toDisplay = (recipes.getChickenRecipes().get(index).getIngredients());
                }
                break;
            case "Beef":
                if(index < recipes.getBeefRecipes().size())
                {
                    toDisplay = (recipes.getBeefRecipes().get(index).getIngredients());
                }
                break;
            case "Seafood":
                if(index < recipes.getFishRecipes().size())
                {
                    toDisplay = (recipes.getFishRecipes().get(index).getIngredients());
                }
                break;
            case "Vegetarian":
                if(index < recipes.getVegRecipes().size())
                {
                    toDisplay = (recipes.getVegRecipes().get(index).getIngredients());
                }
                break;
            case "myFavourites":
                if(index < recipes.getMyFavouriteRecipes(user).size())
                {
                    toDisplay = (recipes.getMyFavouriteRecipes(user).get(index).getIngredients());
                }
                break;
            default:
                // no slection made
               toDisplay = "";
        } 
        return toDisplay;
    }
    
    /**
     * Displays the directions of the recipe from the user's selection and iterates through
     * recipes on the user's request.
     *  
     * @param input the type of recipe the user selected
     * @param index the position of the recipe in the array that needs to be displayed
     * @param user the current user
     * 
     * @return the recipe directions to display
     */
    public String displayRecipeDirections(String input, int index, User user)
    {
            String toDisplay = "";
        switch (input) 
        {
            case "Select":
                    toDisplay = "";
            case "Chicken":
                if(index < recipes.getChickenRecipes().size())
                {
                    toDisplay = (recipes.getChickenRecipes().get(index).getDirections());
                }
                break;
            case "Beef":
                if(index < recipes.getBeefRecipes().size())
                {
                    toDisplay = (recipes.getBeefRecipes().get(index).getDirections());
                }
                break;
            case "Seafood":
                if(index < recipes.getFishRecipes().size())
                {
                    toDisplay = (recipes.getFishRecipes().get(index).getDirections());
                }
                break;
            case "Vegetarian":
                if(index < recipes.getVegRecipes().size())
                {
                    toDisplay = (recipes.getVegRecipes().get(index).getDirections());
                }
                break;
            case "myFavourites":
                if(index < recipes.getMyFavouriteRecipes(user).size())
                {
                    toDisplay = (recipes.getMyFavouriteRecipes(user).get(index).getDirections());
                }
                break;
            default:
                // no slection made
               toDisplay = "";
        }
        return toDisplay;
    }
    
    /**
     * Checks if the user's input is valid or not
     * 
     * @param input the user's input from the keyboard
     * @return <code>true</code> if the user's input is valid
     *         <code>false</code> if the user's input is invalid
     */
    public boolean isValidInput(String input)
    {
            switch (input)
            {
                case "Y":
                case "y": 
                    return true;
                case "N":
                case "n":
                    return true;
            }
        return false;
    }
    
    /**
     * Displays a request to the user to view another recipe within 
     * the same category
     * 
     * @return <code>true</code> if the user selects to view another recipe
     *         <code>false</code> if the user does not select to view another
     *                              recipe
     */
     public boolean viewNextRecipe()             
    {   
        boolean next = false;
        System.out.println("\nView next recipe (Y/N)");   
        String selection;
        
        // Requests valid input while the input is invalid
        while(!isValidInput(selection = getUserInput()))
        {
           System.out.println("\nPlease try again.\n");
           System.out.println("\nView next recipe (Y/N)"); 
        }
        
        switch (selection)
            {
                case "Y":
                case "y": 
                    next = true; 
                    break;
                case "N":
                case "n":
                    next = false;
                    break;
            }
        return next;    
    }
     
     /**
      * Displays message that there are no more recipes to view
      * 
      */
     public String endOfRecipes()
     {
          return("\nNo more recipes in this category to view\n");
     }
     
    /**
     * Determines if the current recipe being view is the first in it's group
     * 
     * @param index the position of the current recipe in the array
     * 
     * @return <code>true</code> if it is the first recipe
     *         <code>false</code> if it's not the first recipe
     */
     public boolean isFirstRecipe(int index)
     {
       boolean isFirst = false;
       if(index == 0)
       {
           isFirst = true;
       }
       return isFirst;
     }
    
     /**
     * Determines if the current recipe being view is the last in it's group
     * 
     * @param input the recipe group selected
     * @param index the position of the current recipe in the array
     * @param user the current user
     * 
     * @return <code>true</code> if it is the last recipe
     *         <code>false</code> if it's not the last recipe
     */
     public boolean isLastRecipe(String input, int index, User user)
     {
         boolean isLast = false;
         switch (input) 
        {
            case "Chicken":
                if(index == (recipes.getChickenRecipes().size())-1)
                {
                    isLast = true;
                }
                    return isLast;
            case "Beef":
                if(index == (recipes.getBeefRecipes().size())-1)
                {
                    isLast = true;
                }
                    return isLast;
            case "Seafood":
                if(index == (recipes.getFishRecipes().size())-1)
                {
                    isLast = true;
                }
                    return isLast;
            case "Vegetarian":
                if(index == (recipes.getVegRecipes().size())-1)
                {
                    isLast = true;
                }
                    return isLast;
            case "myFavourites":
                if(index == (recipes.getMyFavouriteRecipes(user).size())-1)
                {
                    isLast = true;
                }
                    return isLast;
            default:
                return isLast;
        } 
    }
}

     