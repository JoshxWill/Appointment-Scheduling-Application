package model;

import dao.DataBaseUsers;
import javafx.collections.ObservableList;

/**Users Model Class**/
public class Users {
    private int userID;
    private String userName;
    private String password;

    /**Users Constructors
     * @param userID ID
     * @param userName Name
     * @param password Password **/
    public Users(int userID, String userName, String password){
        this.userID = userID;
        this.userName = userName;
        this.password = password;
    }

    /**Return all Users from database
     * @return All Users **/
    public static ObservableList<Users> getAllUsers(){
        return DataBaseUsers.getAllUsers();
    }

    /**Getter for userID
     * @return User ID**/
    public int getUserID() {
        return userID;
    }

    /**
     * Setter for userID
     * @param userID User ID
     */
    public void setUserID(int userID){
        this.userID = userID;
    }

    /**Getter for userName
     * @return User Name**/
    public String getUserName() {
        return userName;
    }

    /**Setter for userName
     * @param userName User Name
     */

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**Getter for password
     * @return Password**/
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password
     * @param password Password
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
