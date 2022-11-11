package code.model;

import code.dao.DataBaseUsers;
import javafx.collections.ObservableList;

/**Users Model Class**/
public class Users {
    private int userID;
    private String userName;
    private String password;

    /**Users Constructors**/
    public Users(int userID, String userName, String password){
        this.userID = userID;
        this.userName = userName;
        this.password = password;
    }

    /**Return all Users from database**/
    public static ObservableList<Users> getAllUsers(){
        return DataBaseUsers.getAllUsers();
    }

    /**Getter for userID**/
    public int getUserID() {
        return userID;
    }

    /**
     * Setter for userID
     * @param userID
     */
    public void setUserID(int userID){
        this.userID = userID;
    }

    /**Getter for userName**/
    public String getUserName() {
        return userName;
    }

    /**Setter for userName
     * @param userName
     */

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**Getter for password**/
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password
     * @param password
     */

    public void setPassword(String password) {
        this.password = password;
    }

    /**Overrides toString method for Menu box**/
    @Override
    public String toString(){
        return ("#" + Integer.toString(userID) + " " + userName);
    }
}
