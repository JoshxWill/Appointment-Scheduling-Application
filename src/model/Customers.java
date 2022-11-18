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

    /**Grabs Appointments**/
    public ObservableList<Appointments> associatedAppointments = FXCollections.observableArrayList();
    /**
     * Customers Constructors
     * @param customerID Customer ID
     * @param customerName Customer Name
     * @param address Customer Address
     * @param postalCode Customer Postal Code
     * @param phone Customer Phone
     * @param division Customer First Level Division
     * @param country Customer Country
     */
    public Customers(int customerID, String customerName, String address, String postalCode, String phone, String division, String country){
        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.division = division;
        this.country = country;
    }

    /**
     * Customers
     * @param ID ID
     * @param name Name
     * @param address Address
     * @param postal Postal
     * @param phone Phone
     * @param country Country
     * @param divisionID First Level ID
     */
    public Customers(int ID, String name, String address, String postal, String phone, String country, int divisionID){ }

    /**
     *Customers
     *
     * @param customerID ID
     * @param customerName Name
     * @param address Address
     * @param postalCode Postal Code
     * @param phone Phone
     * @param division Division
     */
    public Customers(int customerID, String customerName, String address, String postalCode, String phone, FirstLevelDivisions division){
        this.customerID= customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.division = String.valueOf(division);
    }

    /**
     * Customers
     *
     * @param id ID
     * @param name Name
     * @param address Address
     * @param postal Postal
     * @param phone Phone
     * @param divisionID First Level ID
     * @param country Country
     */
    public Customers(int id, String name, String address, String postal, String phone, int divisionID, String country){}

    /**
     * Customers
     * @param id ID
     * @param customerName Name
     * @param address Address
     * @param postalCode Postal Code
     * @param phone Phone
     * @param divisionID First Level ID
     */
    public Customers(int id, String customerName, String address, String postalCode, String phone, int divisionID) {
    }

    /**Return all Customers from database
     * @return All Customers from Customer Database**/
    public static ObservableList<Customers> getAllCustomers(){
        return DataBaseCustomers.getAllCustomers();
    }

    /**
     * New Customer
     * @param newCustomer New Customer
     */
    public static void addCustomer(Customers newCustomer){
    }

    /**Getter for customerID
     * @return Customer ID**/
    public int getCustomerID() {
        return customerID;
    }

    /**
     *Setter for customerID
     * @param ID ID
     **/
    public void setID(int ID){
        this.customerID = ID;
    }

    /**Getter for customerName
     * @return Customer Name**/
    public String getCustomerName(){
        return customerName;
    }

    /**
     * Setter for customerName
     * @param customerName Customer Name
     */

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**Getter for Customer Address
     * @return Customer Address**/
    public String getAddress() {
        return address;
    }

    /**
     * Setter for Customer Address
     * @param address Address
     */

    public void setAddress(String address) {
        this.address = address;
    }

    /**Getter for Customer postalCode
     * @return Postal Code**/

    public String getPostalCode() {
        return postalCode;
    }


    /**
     * Setter for Customer Postal Code
     * @param postalCode Customer Postal Code
     */

    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }

    /**Getter for Customer Phone
     * @return Customer Phone**/

    public String getPhone() {
        return phone;
    }

    /**Setter for Customer Phone
     * @param phone Customer Phone
     */

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**Getter for Division
     * @return First Level Division**/

    public String getDivision() {
        return division;
    }

    /**
     * Setter for Division
     * @param division First Level Divisions
     */

    public void setDivision(String division) {
        this.division = division;
    }

    /**Getter for Division ID
     * @return First Level ID**/

    public int getDivisionID() {
        return divisionID;
    }

    /**
     * Setter for Division ID
     * @param divisionID First Level ID
     */

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**Getter for Customer Country
     * @return Customer Country**/
    public String getCountry() {
        return country;
    }

    /**Setter for Customer Country
     * @param country Country**/

    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return Associated Appointments
     */
    public ObservableList<Appointments> getAssociatedAppointments(){
        return associatedAppointments;
    }

    /**Override toString method for Menu box**/
    @Override
    public String toString(){
        return("#" + Integer.toString(customerID) + " " + customerName);
    }

}

