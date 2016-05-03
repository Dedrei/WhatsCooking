/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package whatscooking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The class that handles the main operations of the database.  
 * 
 * @author Dedrei Lombard 1313685
 * @version v1.0 - 2014.09: Created
 */
public class DBOperations 
{
    String url = "jdbc:derby://localhost:1527/WhatsCookingDB";
    String usernameDB="pdc";
    String passwordDB="pdc";
    Connection conn;
    
    /**
     * Method to create a new connection to the database
     */
     public void establishConnection()
    {
        try 
        {
            conn = DriverManager.getConnection(url, usernameDB, passwordDB);
            //System.out.println(url+"   connected....");
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     /**
     * Returns the current database connection
     * 
     * @return the current database connection
     */ 
    public Connection getConnection()
    {
        return conn;
    }
    
    /**
     * Method to close the current database connection
     */
     public void closeConnections()
    {
        if(conn!=null)
        {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Method to determine the amount of users saved in the database
     * 
     * @return the amount of users in the database
     */ 
    public int getAmountUsers() throws SQLException 
    {
       int amountUsers = 0;
       Statement statment = conn.createStatement(
                                      ResultSet.TYPE_SCROLL_INSENSITIVE,
                                      ResultSet.CONCUR_UPDATABLE);
       ResultSet result = statment.executeQuery("SELECT COUNT(user_id) FROM users");
       while (result.next())
       {
           amountUsers = result.getInt(1);
       }
       return amountUsers;
    }
    
     /**
     * Method to determine the amount of recipes saved in the database
     * 
     * @return the amount of recipes in the database
     */ 
    public int getAmountRecipes() throws SQLException 
    {
       int amountRecipes = 0;
       Statement statment = conn.createStatement(
                                      ResultSet.TYPE_SCROLL_INSENSITIVE,
                                      ResultSet.CONCUR_UPDATABLE);
       ResultSet result = statment.executeQuery("SELECT COUNT(recipe_id) FROM "
               + "recipes");
       while (result.next())
       {
           amountRecipes = result.getInt(1);
       }
       return amountRecipes;
    }
    
     /**
     * Method to determine the amount of favourite recipes saved in the database
     * 
     * @return the amount of favourites in the database
     */ 
    public int getAmountFavourites() throws SQLException 
    {
       int amountFavourites = 0;
       Statement statment = conn.createStatement(
                                      ResultSet.TYPE_SCROLL_INSENSITIVE,
                                      ResultSet.CONCUR_UPDATABLE);
       ResultSet result = statment.executeQuery("SELECT COUNT(fav_id) FROM "
               + "favourites");
       while (result.next())
       {
           amountFavourites = result.getInt(1);
       }
       return amountFavourites;
    }
}
