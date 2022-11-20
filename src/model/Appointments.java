package model;

import dao.DataBaseAppts;
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


    /**
     * Appointments Constructor
     * @param appointmentID Appointment ID
     * @param title Title
     * @param description Description
     * @param location Location
     * @param type Type
     * @param startDT Start Date/Time
     * @param endDT End Date/Time
     * @param customerID Customer ID
     * @param userID User ID
     * @param contactID Contact ID
     * @param customerName Customer Name
     * @param userName User Name
     * @param contactName Contact Name
     */
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

    /**
     * Appointments
     *
     * @param appointmentID id
     * @param title title
     * @param description description
     * @param location location
     * @param type type
     * @param startDT start date/time
     * @param endDT end date/time
     * @param customerID customer id
     * @param userID user id
     * @param contactID contact id
     */
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

   /**Return all Appointments from database
    * @return getAllAppointments Return All Appointments**/
   public static ObservableList<Appointments> getAllAppointments(){
       return DataBaseAppts.getAllAppointments();
   }

    /**
     * Appointments Location
     * @param location Location
     */
   public void Appointments(String location){
       this.location = location;
   }

   /**Getter for appointmentID
    * @return Appointment ID**/
   public int getAppointmentID(){
       return appointmentID;
   }

   /**
    * Setter for appointmentID
    * @param appointmentID Appointment ID
    */
   public void setAppointmentID(Integer appointmentID){
       this.appointmentID = appointmentID;
   }

   /**Getter for Appointment title
    * @return Title**/
   public String getTitle(){
       return title;
   }

    /**
     * Setter for Appointment title
     * @param title Title
     */
   public void setTitle(String title){
       this.title = title;
   }

   /**Getter for Appointment description
    * @return Description**/
   public String getDescription(){
       return description;
   }

    /**
     * Setter for Appointment description
     * @param description Description
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**Getter dor Appointment location
     * @return Location**/
    public String getLocation(){
        return location;
    }

    /**
     * Setter for Appointment location
     * @param location Location
     */
    public void setLocation(String location){
        this.location = location;
    }

    /**Getter for Appointment type
     * @return Type**/
    public String getType(){
        return type;
    }

    /**
     * Setter for Appointment type
     * @param type Type
     */
    public void setType(String type){
        this.type = type;
    }

    /**Getter for Appointment Start Date/Time
     * @return Start Date/Time**/
    public Timestamp getStartDT(){
        return startDT;
    }

    /**
     * Setter for Appointment Start Date/Time
     * @param startDT Start Date/Time
     */
    public void setStartDT(Timestamp startDT){
        this.startDT = startDT;
    }

    /**Getter for Appointment End Date/Time
     * @return End Date/Time**/
    public Timestamp getEndDT(){
        return endDT;
    }

    /**
     * Setter for Appointment End Date/Time
     * @param endDT End Date/Time
     */
    public void setEndDT(Timestamp endDT){
        this.endDT = endDT;
    }

    /**Getter for Appointment customerID
     * @return Appointment Customer**/
    public int getCustomerID(){
        return customerID;
    }

    /**
     * Setter for Appointment customerID
     * @param customerID Customer ID
     */
    public void setCustomerID(int customerID){
        this.customerID = customerID;
    }

    /**Getter for Appointment userID
     * @return User ID**/
    public int getUserID(){
        return userID;
    }

    /**
     * Setter for Appointment userID
     * @param userID User ID
     */
    public void setUserID(int userID){
        this.userID = userID;
    }

    /**Getter for Appointment contactID
     * @return Contact ID**/
    public int getContactID(){
        return contactID;
    }

    /**
     * Setter for Appointment contactID
     * @param contactID Contact ID
     */

    public void setContactID(int contactID){
        this.contactID = contactID;
    }

    /**Getter for Appointment customerName
     * @return Customer Name**/
    public String getCustomerName(){
        return customerName;
    }

    /**
     * Setter for Appointment customerName
     * @param customerName Customer Name
     */
    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }

    /**Getter for Appointment userName
     * @return User Name**/
    public String getUserName(){
        return userName;
    }

    /**
     * Setter for Appointment userName
     * @param userName User Name
     */
    public void setUserName(String userName){
        this.userName = userName;
    }

    /**Getter for Appointment contactName
     * @return Contact Name**/
    public String getContactName(){
        return contactName;
    }

    /**
     * Setter for Appointment contactName
     * @param contactName Contact Name
     */
    public void setContactName(String contactName){
        this.contactName = contactName;
    }

    /**
     * Overrides method for Menu box
     **/
    @Override
    public String toString(){
        return (type);
    }

    /**
     * Contact Appointments
     * @param contactID Contact ID
     * @return Contact Appointments
     * @throws SQLException SQLLoader
     */
    public static ObservableList<Appointments> getContactAppointments (int contactID) throws SQLException {
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
