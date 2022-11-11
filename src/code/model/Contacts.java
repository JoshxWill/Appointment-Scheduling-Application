package code.model;

import code.dao.DataBaseContacts;
import javafx.collections.ObservableList;

/**Contacts Model Class**/
public class Contacts {
    private int contactID;
    private String contactName;
    private String email;


/**Contacts Constructor**/
public Contacts (int contactID, String contactName, String email){
    this.contactID = contactID;
    this.contactName = contactName;
    this.email = email;
}

/**Return all Contacts from database**/
public static ObservableList<Contacts> getAllContacts(){
    return DataBaseContacts.getAllContacts();
}

/**Getter for contactID**/
public int getContactID(){
    return contactID;
}

/**Getter for contactName**/
public String getContactName(){
    return contactName;
}

/**Getter for Contact Email**/
public String getEmail(){
    return email;
}

/**Overrides method for Menu box**/
@Override
    public String toString(){
    return (contactName);
}

}
