/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package whatscooking;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


/**
 * The class runs the GUI of the WhatsCooking program and all it's functionality  
 * 
 * @author Dedrei Lombard 1313685
 * @version v1.0 - 2014.09: Created
 */
public class WhatsCookingUI extends JFrame
{
    private JPanel panelNorth, panelCentre, panelEast, panelSouth;
    private JLabel whosCooking, recipeSelection, image, ingredient,
                    directions;
    private JTextField userInput;
    private JButton login, logout, addRecipe, next, favourites, previous,
                    addFavourite;
    private String userNameInput, recipeGroup;
    private int index;
    private User user;
    private JComboBox recipeComboBox;
    private WhatsCooking file, updatedFile;
    private JTextArea titleDisplay, ingredientsDisplay, directionsDisplay;
    private DBOperations db = new DBOperations();
    private Statement statement;
    private Connection conn;
    private int totalFavId;

    /**
     * Creates a new GUI for the WhatsCooking program. A new window opens which
     * allows the user to use use the functionality of the program.
     */
    public WhatsCookingUI()
    {
        try 
        {
            panelNorth =  new JPanel();
            whosCooking = new JLabel("Who's Cooking?");
            recipeSelection = new JLabel("View Recipe");
            ingredient = new JLabel("INGREDIENTS");
            directions = new JLabel("DIRECTIONS");
            userInput = new JTextField(15);
            login = new JButton("Login");
            logout = new JButton("Log Out");
            favourites = new JButton("My Favourites");
            favourites.setEnabled(true);
            addRecipe = new JButton("Add Recipe");
            previous = new JButton("Previous");
            previous.setEnabled(false);
            next = new JButton("Next");
            next.setEnabled(false);
            addFavourite = new JButton("Add Favourite");
            addFavourite.setEnabled(false);
            panelCentre = new JPanel();
            panelSouth = new JPanel();
            panelEast = new JPanel();
            image = new JLabel();
            titleDisplay = new JTextArea(2,48);
            titleDisplay.setEditable(false);
            titleDisplay.setBorder(BorderFactory.createLineBorder(Color.black));
            ingredientsDisplay = new JTextArea(10,48) ;
            ingredientsDisplay.setEditable(false);            
            ingredientsDisplay.setLineWrap(true);
            ingredientsDisplay.setBorder(BorderFactory.createLineBorder(Color.black));
            directionsDisplay = new JTextArea(18,48);
            directionsDisplay.setEditable(false);
            directionsDisplay.setLineWrap(true);
            directionsDisplay.setBorder(BorderFactory.createLineBorder(Color.black));
            file = new WhatsCooking();
            recipeComboBox = new JComboBox();
            setSize(950,700);
            setResizable(false);
            setTitle("WHAT'S COOKING?");
            add(panelNorth,BorderLayout.NORTH);
            add(panelCentre,BorderLayout.CENTER);
            add(panelEast,BorderLayout.EAST);
            add(panelSouth,BorderLayout.SOUTH);
            panelEast.setLayout(null);
            panelEast.setLayout(new BoxLayout(panelEast, BoxLayout.X_AXIS));
            panelEast.add(new JLabel(new ImageIcon(getClass().getResource("/Resources/Cookingpic.png"))));
            
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            
            panelNorth.setLayout(new FlowLayout(FlowLayout.LEFT));
            panelNorth.add(whosCooking);
            panelNorth.add(userInput);
            panelNorth.add(login);
            panelNorth.add(logout);
            
            logout.setEnabled(false);
            login.addActionListener(new ActionListener(){
                
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    userNameInput = userInput.getText();
                    user = new User(userNameInput);
                    user.addUser(user);
                    setUser(user);
                    userInput.setEditable(false);
                    userInput.setText("Welcome " + userNameInput + "!");
                    login.setEnabled(false);
                    logout.setEnabled(true);
                }
            });
            
            logout.addActionListener(new ActionListener(){
                
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    userInput.setText("");
                    userInput.setEditable(true);
                    directionsDisplay.setText("");
                    titleDisplay.setText("");
                    ingredientsDisplay.setText("");
                    user = null;
                    login.setEnabled(true);
                    logout.setEnabled(false);
                }
            });
            
            panelSouth.add(previous);
            panelSouth.add(next);
            panelSouth.add(addFavourite);
            panelCentre.setLayout(new FlowLayout(FlowLayout.LEFT));
            panelCentre.add(recipeSelection);
            panelCentre.add(recipeComboBox);
            recipeComboBox.addItem("Select");
            recipeComboBox.addItem("Chicken");
            recipeComboBox.addItem("Beef");
            recipeComboBox.addItem("Seafood");
            recipeComboBox.addItem("Vegetarian");
            panelCentre.add(favourites);
            panelCentre.add(addRecipe);
            panelCentre.add(titleDisplay);
            panelCentre.add(ingredient);
            panelCentre.add(ingredientsDisplay);
            panelCentre.add(directions);
            panelCentre.add(directionsDisplay);
            addRecipe.setEnabled(true);
            
            recipeComboBox.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e)
                {   
                    setRecipeGroup();
                    setIndex(0);
                    titleDisplay.setText(file.displayRecipeTitle(recipeGroup,index,user));
                    ingredientsDisplay.setText(file.displayRecipeIngredients
                                    (recipeGroup,index, user));
                    directionsDisplay.setText(file.displayRecipeDirections
                                    (recipeGroup,index, user));
                    updateButtons();
                }
            });
            
            addRecipe.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    AddRecipeWindow addRecipeWindow = new AddRecipeWindow();        
                }
            });
            
            panelSouth.add(previous);
            panelSouth.add(next);
            panelSouth.add(addFavourite);
            
            
            next.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    try {
                        updatedFile = new WhatsCooking();
                        setIndex(getIndex()+1);
                        titleDisplay.setText(updatedFile.displayRecipeTitle
                                        (recipeGroup,index, user));
                        ingredientsDisplay.setText(updatedFile.displayRecipeIngredients
                                        (recipeGroup,index, user));
                        directionsDisplay.setText(updatedFile.displayRecipeDirections
                                        (recipeGroup,index, user));
                        updateButtons();
                    } catch (SQLException ex) {
                        Logger.getLogger(WhatsCookingUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            
            previous.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    try {
                        updatedFile = new WhatsCooking();
                        setIndex(getIndex()-1);
                        titleDisplay.setText(updatedFile.displayRecipeTitle
                                        (recipeGroup,index, user));
                        ingredientsDisplay.setText(updatedFile.displayRecipeIngredients
                                        (recipeGroup,index, user));
                        directionsDisplay.setText(updatedFile.displayRecipeDirections
                                        (recipeGroup,index, user));
                        updateButtons();
                    } catch (SQLException ex) {
                        Logger.getLogger(WhatsCookingUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            
            addFavourite.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    try {
                        db.establishConnection();
                        conn = db.getConnection();
                        statement = conn.createStatement(
                                ResultSet.TYPE_SCROLL_INSENSITIVE,
                                ResultSet.CONCUR_UPDATABLE);
                        totalFavId = db.getAmountFavourites();
                        int newFavId = totalFavId +1;
                        int recipeId = 0;
                        updatedFile = new WhatsCooking();
                        String currentTitle = updatedFile.displayRecipeTitle
                                                (recipeGroup, index, user);
                        int userId= user.getUserId();
                        
                        String query = "SELECT recipe_id FROM recipes WHERE " +
                                "recipe_title = '" + currentTitle + "'";
                        ResultSet result = statement.executeQuery(query);
                        
                        while(result.next())
                        {
                            recipeId = result.getInt("recipe_id");
                        }
                        String sqlInsert = "INSERT INTO favourites (fav_id, " +
                                "user_id, recipe_id) VALUES (" + newFavId + ", " 
                                + userId + ", "  + recipeId + ")";
                        statement.executeUpdate(sqlInsert);
                        conn.commit();
                        conn.close();
                    }
                    catch (SQLException ex) 
                    {
                        Logger.getLogger(WhatsCookingUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            
            
             favourites.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e)
                {   
                    RecipeFile myFile = new RecipeFile();
                    myFile.getMyFavouriteRecipes(user);
                    setRecipeGroup("myFavourites");
                    setIndex(0);
                    titleDisplay.setText(file.displayRecipeTitle(recipeGroup,index, user));
                    ingredientsDisplay.setText(file.displayRecipeIngredients
                                    (recipeGroup,index, user));
                    directionsDisplay.setText(file.displayRecipeDirections
                                    (recipeGroup,index, user));
                    updateButtons();
                    
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(WhatsCookingUI.class.getName()).log(Level.SEVERE, null, ex);
        }
            
         
    }
 
    /**
     * Sets the selected recipe group from the drop down list.
     * 
     */
    public void setRecipeGroup()
    {
        this.recipeGroup = (String) recipeComboBox.getSelectedItem();
    }
    
    public void setRecipeGroup(String group)
    {
        recipeGroup = group;
    }
    
    /**
     * Gets the recipe group selected from the drop down list
     * 
     * @return the recipe group selected
     */
    public String getRecipeGroup()
    {
        return recipeGroup;
    }
    
    /**
     * Sets the index of a selected recipe
     * 
     * @param index the index to which the recipe has to be set
     */
    public void setIndex(int index)
    {
        this.index = index;
    }
    
    /**
     * Gets the index of the selected recipe
     * 
     * @return the index of the selected recipe
     */
    public int getIndex()
    {
        return index;
    }
    
    /**
     * Gets the current user logged in
     * 
     * @return the user that is currently logged in
     */
    public User getUser()
    {
        return user;
    }
    
     /**
     * Sets the current user that has logged in
     * 
     * @param user the user to set
     */
    public void setUser(User user)
    {
        this.user = user;
    }
    
     /**
     * Method to update the buttons after a user selection has been made
     *
     */
    public void updateButtons()
    {
       //not possible to view previous recipe when first recipe is displayed
       //possible to view next recipe and to add recipe to favourites
        if(file.isFirstRecipe(getIndex()))
        {
            previous.setEnabled(false);
            next.setEnabled(true);
            addFavourite.setEnabled(true);
        }
        //not possible to view next recipe if last recipe is being displayed
        //possible to view previous recipe and to add recipe to favourites
        else if(file.isLastRecipe(recipeGroup,getIndex(), user))
                {
                    next.setEnabled(false);
                    previous.setEnabled(true);
                    addFavourite.setEnabled(true);
        
                }
        //possible to view next and previous recipes and to add recipe as a favourite
        else
        {
            next.setEnabled(true);
            previous.setEnabled(true);
            addFavourite.setEnabled(true);
        }
   } 
}
                    


