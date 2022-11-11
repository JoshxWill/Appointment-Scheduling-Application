package code.model;

import code.dao.DataBaseAppts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.sql.Timestamp;

/**Appointments Model Class**/
public class Appointments {

    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private String type;
    private Timestamp startDT;
    private Timestamp endDT;
    private int customerID;
    private int userID;
    private int contactID;
    private String customerName;
    private String userName;
    private String contactName;


    /**Appointments Constructor**/
    public Appointments(int appointmentID, String title, String description, String location, String type, Timestamp startDT, Timestamp endDT, int customerID, int userID, int contactID, String customerName, String userName, String contactName) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDT = startDT;
        this.endDT = endDT;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
        this.customerName = customerName;
        this.userName = userName;
        this.contactName = contactName;

    }

    public Appointments (int appointmentID, String title, String description, String location, String type, Timestamp startDT, Timestamp endDT, int customerID, int userID, int contactID) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDT = startDT;
        this.endDT = endDT;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

   /**Return all Appointments from database**/
   public static ObservableList<Appointments> getAllAppointments(){
       return DataBaseAppts.getAllAppointments();
   }

   public void Appointments(String location){
       this.location = location;
   }

   /**Getter for appointmentID**/
   public int getAppointmentID(){
       return appointmentID;
   }

   /**
    * Setter for appointmentID
    * @param appointmentID
    */
   public void setAppointmentID(Integer appointmentID){
       this.appointmentID = appointmentID;
   }

   /**Getter for Appointment title**/
   public String getTitle(){
       return title;
   }

    /**
     * Setter for Appointment title
     * @param title
     */
   public void setTitle(String title){
       this.title = title;
   }

   /**Getter for Appointment description**/
   public String getDescription(){
       return description;
   }

    /**
     * Setter for Appointment description
     * @param description
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**Getter dor Appointment location**/
    public String getLocation(){
        return location;
    }

    /**
     * Setter for Appointment location
     * @param location
     */
    public void setLocation(String location){
        this.location = location;
    }

    /**Getter for Appointment type**/
    public String getType(){
        return type;
    }

    /**
     * Setter for Appointment type
     * @param type
     */
    public void setType(String type){
        this.type = type;
    }

    /**Getter for Appointment Start Date/Time**/
    public Timestamp getStartDT(){
        return startDT;
    }

    /**
     * Setter for Appointment Start Date/Time
     * @param startDT
     */
    public void setStartDT(Timestamp startDT){
        this.startDT = startDT;
    }

    /**Getter for Appointment End Date/Time**/
    public Timestamp getEndDT(){
        return endDT;
    }

    /**
     * Setter for Appointment End Date/Time
     * @param endDT
     */
    public void setEndDT(Timestamp endDT){
        this.endDT = endDT;
    }

    /**Getter for Appointment customerID**/
    public int getCustomerID(){
        return customerID;
    }

    /**
     * Setter for Appointment customerID
     * @param customerID
     */
    public void setCustomerID(int customerID){
        this.customerID = customerID;
    }

    /**Getter for Appointment userID**/
    public int getUserID(){
        return userID;
    }

    /**
     * Setter for Appointment userID
     * @param userID
     */
    public void setUserID(int userID){
        this.userID = userID;
    }

    /**Getter for Appointment contactID**/
    public int getContactID(){
        return contactID;
    }

    /**
     * Setter for Appointment contactID
     * @param contactID
     */

    public void setContactID(int contactID){
        this.contactID = contactID;
    }

    /**Getter for Appointment customerName**/
    public String getCustomerName(){
        return customerName;
    }

    /**
     * Setter for Appointment customerName
     * @param customerName
     */
    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }

    /**Getter for Appointment userName**/
    public String getUserName(){
        return userName;
    }

    /**
     * Setter for Appointment userName
     * @param userName
     */
    public void setUserName(String userName){
        this.userName = userName;
    }

    /**Getter for Appointment contactName**/
    public String getContactName(){
        return contactName;
    }

    /**
     * Setter for Appointment contactName
     * @param contactName
     */
    public void setContactName(String contactName){
        this.contactName = contactName;
    }

    /**Overrides method for Menu box**/
    @Override
    public String toString(){
        return (type);
    }
    public static ObservableList<Appointments> getContactAppointments (int contactID) throws SQLException{
        ObservableList<Appointments> contactAppointmentLists = FXCollections.observableArrayList();
        DataBaseAppts dataBaseAppts = new DataBaseAppts();

        for (Appointments appointments : DataBaseAppts.getAllAppointments()) {
            if (appointments.getContactID() == contactID){
                contactAppointmentLists.add(appointments);
            }
        }
        return contactAppointmentLists;
    }
}
