
package whatscooking;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class to represent a user that uses the recipe file.
 * 
 * @author Dedrei Lombard 1313685
 * @version v1.0 - 2014.08: Created
 */

public class User {
        
    private String name;
    private int userId;
    private DBOperations db = new DBOperations();
    private Connection conn;
    private Statement statement;
    
    /**
     * Creates a new instance of a user
     */
    public User(String name)
    {
       this.name = name; 
    }
    
    /**
     * Returns the name of the user.
     * 
     * @return the name of the user
     */
    public String getUserName()
    {
        return this.name;
    }
    
    /**
     * Returns the userID of the user.
     * 
     * @return the userID of the user
     */
    public int getUserId()
    {
        return userId;
    }
    
    /**
     * Changes the name of the user.
     * 
     * @param name the name of the user
     */
    public void setUserName(String name)
    {
        this.name = name;
    }
    
     /**
     * Sets the userID of the user.
     * 
     * @param userId the userID of the user
     */
    public void setUserName(int userId)
    {
        this.userId = userId;
    }
    
    /**
     * Checks the user database to see if the user exists 
     * 
     * @param user the user to validate
     * 
     * @return <code>true</code> if the user is a new user and does not exist
     * in the database
     *         <code>false</code> if the user already exists
     */
    public boolean isNewUser(User user)
    {
        boolean newUser = true;
        ResultSet result = null;
        
        try 
        {
            db.establishConnection();
            conn = db.getConnection();
            statement = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);

            String sqlQuery = "select user_name from users";
            
            result = statement.executeQuery(sqlQuery);
            result.beforeFirst();
            while(result.next())
            {
                String username = result.getString("user_name"); 
                
               if(username.equalsIgnoreCase(user.getUserName()))
               {
                   newUser = false;
               }             
            }
            conn.close();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newUser; 
    }
         
    /**
     * Adds the user to the user table in the database if it's a new user
     * 
     * @param user the user to add to the user file
     */
    public void addUser(User user)
    {   
       if(isNewUser(user))
       try 
           {
                String username = user.getUserName();
                db.establishConnection();
                conn = db.getConnection();
                int totalUsers = db.getAmountUsers();
                userId = totalUsers +1;
                statement = conn.createStatement();
                String tableName = "users";
                String sqlInsert = "INSERT INTO " + tableName + " (user_id, user_name) "
                    + "VALUES ("+userId+",'" + username + "')";
                statement.executeUpdate(sqlInsert);
                conn.close();   
        } 
           catch (SQLException ex) 
           {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
