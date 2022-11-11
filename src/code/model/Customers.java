package code.model;

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
    public Customers(int customerID, String customerName, String address, String postalCode, String phone, FirstLevelDivisions division){
        this.customerID= customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.division = String.valueOf(division);
    }

}

