package model;

import dao.DataBaseContacts;
import javafx.collections.ObservableList;

/**Contacts Model Class**/
public class Contacts {
    private int contactID;
    private String contactName;
    private String email;


    /**
     * Contacts Constructor
     * @param contactID ID
     * @param contactName Name
     * @param email Email
     */
    public Contacts (int contactID, String contactName, String email){
    this.contactID = contactID;
    this.contactName = contactName;
    this.email = email;
}

/**Return all Contacts from database
 * @return All Contacts**/
public static ObservableList<Contacts> getAllContacts(){
    return DataBaseContacts.getAllContacts();
}

/**Getter for contactID
 * @return Contact ID**/
public int getContactID(){
    return contactID;
}

/**Getter for contactName
 * @return Contact Name**/
public String getContactName(){
    return contactName;
}

/**Getter for Contact Email
 * @return Contact Email**/
public String getEmail(){
    return email;
}

/**Overrides method for Menu box
 * @return Contact Name**/
@Override
    public String toString(){
    return (contactName);
}

}
