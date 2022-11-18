package model;

import dao.DataBaseCustomers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**Customers Model Class**/
public class Customers {
    private int customerID;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private String division;
    private String country;
    private int divisionID;

    public ObservableList<Appointments> associatedAppointments = FXCollections.observableArrayList();

    /**Customers Constructors**/
    public Customers(int customerID, String customerName, String address, String postalCode, String phone, String division, String country){
        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.division = division;
        this.country = country;
    }
    public Customers(int ID, String name, String address, String postal, String phone, String country, int divisionID){ }

    public Customers(int customerID, String customerName, String address, String postalCode, String phone, FirstLevelDivisions division){
        this.customerID= customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.division = String.valueOf(division);
    }

    public Customers(int id, String name, String address, String postal, String phone, int divisionID, String country){}

    public Customers(int id, String customerName, String address, String postalCode, String phone, int divisionID) {
    }

    /**Return all Customers from database**/
    public static ObservableList<Customers> getAllCustomers(){
        return DataBaseCustomers.getAllCustomers();
    }

    public static void addCustomer(Customers newCustomer){
    }

    /**Getter for customerID**/
    public int getCustomerID() {
        return customerID;
    }

    /**
     *Setter for customerID
     * @param ID
     **/
    public void setID(int ID){
        this.customerID = ID;
    }

    /**Getter for customerName**/
    public String getCustomerName(){
        return customerName;
    }

    /**
     * Setter for customerName
     * @param customerName
     */

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**Getter for Customer Address**/
    public String getAddress() {
        return address;
    }

    /**
     * Setter for Customer Address
     * @param address
     */

    public void setAddress(String address) {
        this.address = address;
    }

    /**Getter for Customer postalCode**/

    public String getPostalCode() {
        return postalCode;
    }


    /**
     * Setter for Customer Postal Code
     * @param postalCode
     */

    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }

    /**Getter for Customer Phone**/

    public String getPhone() {
        return phone;
    }

    /**Setter for Customer Phone
     * @param phone
     */

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**Getter for Division**/

    public String getDivision() {
        return division;
    }

    /**
     * Setter for Division
     * @param division
     */

    public void setDivision(String division) {
        this.division = division;
    }

    /**Getter for Division ID**/

    public int getDivisionID() {
        return divisionID;
    }

    /**
     * Setter for Division ID
     * @param divisionID
     */

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**Getter for Customer Country**/
    public String getCountry() {
        return country;
    }

    /**Setter for Customer Country**/

    public void setCountry(String country) {
        this.country = country;
    }

    public ObservableList<Appointments> getAssociatedAppointments(){
        return associatedAppointments;
    }

    /**Override toString method for Menu box**/
    @Override
    public String toString(){
        return("#" + Integer.toString(customerID) + " " + customerName);
    }

}

