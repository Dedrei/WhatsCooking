/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package whatscooking;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * The class that handles the operations of adding a recipe to the recipe file.  
 * 
 * @author Dedrei Lombard 1313685
 * @version v1.0 - 2014.09: Created
 */
public class AddRecipeWindow 
{
    private JFrame addRecipeMenu;
    private JPanel menuPanel;
    private BoxLayout layout;
    private JLabel selectType, recipeTitle, recipeIngredients, recipeDirections;
    private JTextField title;
    private JTextArea ingredients, directions;
    private JScrollPane ingredientsPane, directionsPane;
    private JComboBox recipeChoice;
    private JButton submit;
    private DBOperations db = new DBOperations();
    private Connection conn;
    private Statement statement;
    
    /**
     * Creates a new instance of the AddRecipeWindow. A new window opens which
     * allows the user to add the necessary information to add a recipe to the 
     * recipe file.
     */
    public AddRecipeWindow()
    {
        //Creates frame with all it's components
        addRecipeMenu = new JFrame("Add Recipe");
        addRecipeMenu.setLayout(new FlowLayout());
        menuPanel = new JPanel();
        layout = new BoxLayout(menuPanel, BoxLayout.PAGE_AXIS);
        menuPanel.setLayout(layout);
        selectType = new JLabel("Select Recipe Type:");
        selectType.setAlignmentX(Component.CENTER_ALIGNMENT);
        recipeTitle = new JLabel("Enter Recipe Title:");
        recipeTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        recipeIngredients = new JLabel("Enter Ingredients:");
        recipeIngredients.setAlignmentX(Component.CENTER_ALIGNMENT);
        recipeDirections = new JLabel("Enter Directions:");
        recipeDirections.setAlignmentX(Component.CENTER_ALIGNMENT);
        title = new JTextField(30);
        ingredients = new JTextArea(10,35);
        ingredients.setLineWrap(true);
        directions = new JTextArea(15,35);
        directions.setLineWrap(true);
        recipeChoice = new JComboBox();
        recipeChoice.setAlignmentX(Component.CENTER_ALIGNMENT);
        submit = new JButton("Submit");
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);
        addRecipeMenu.setSize(475,600);
        addRecipeMenu.setVisible(true);
        addRecipeMenu.add(menuPanel);
        menuPanel.add(selectType);
        menuPanel.add(recipeChoice);
        recipeChoice.addItem("Select");
        recipeChoice.addItem("Chicken");
        recipeChoice.addItem("Beef");
        recipeChoice.addItem("Seafood");
        recipeChoice.addItem("Vegetarian");
        menuPanel.add(recipeTitle);
        menuPanel.add(title);
        menuPanel.add(recipeIngredients);
        menuPanel.add(ingredients);
        menuPanel.add(recipeDirections);
        menuPanel.add(directions);
        menuPanel.add(submit);
        
        //submits the new recipe added
            submit.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    WhatsCooking file = new WhatsCooking();
                    RecipeFile recipes = file.getRecipeFile();
                    //get selected recipe type
                    String type = getRecipeType();
                    //get entered recipe title
                    String title = getRecipeTitle();
                    //get entered ingredients
                    String ingredients = getRecipeIngredients();
                    //get entered directions
                    String directions = getRecipeDirections();
                    
                try 
                {
                db.establishConnection();
                conn = db.getConnection();
                //generate new recipeId and add new recipe to the database
                int totalRecipes = db.getAmountRecipes();
                int recipeId = totalRecipes +1;
                Statement statement = conn.createStatement();
                String sqlInsert = "INSERT INTO recipes (recipe_id, recipe_type, recipe_title, " +
                        "recipe_ingredients, recipe_directions) VALUES (" +
                        recipeId + ", '" + type + "', '" + title + "', '" +
                        ingredients + "', '" + directions +"')";                
                statement.executeUpdate(sqlInsert);
                conn.commit();
                conn.close();
             } 
           catch (SQLException ex) 
           {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
                    addRecipeMenu.dispose();
                } catch (SQLException ex) {
                    Logger.getLogger(AddRecipeWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    /**
     * Returns the type of recipe that the user selected.
     * 
     * @return the type of the recipe
     */
    public String getRecipeType()
    {
        return (String) recipeChoice.getSelectedItem();
    }
    
    /**
     * Returns the title of recipe that the user entered.
     * 
     * @return the title of the recipe
     */
    public String getRecipeTitle()
    {
        return title.getText();  
    }
    
    /**
     * Returns the ingredients of recipe that the user entered.
     * 
     * @return the ingredients of  the recipe
     */
    public String getRecipeIngredients()
    {
        return ingredients.getText();
    }
    
    /**
     * Returns the directions of recipe that the user entered.
     * 
     * @return the directions of  the recipe
     */
     public String getRecipeDirections()
    {
        return directions.getText();
    }
}
