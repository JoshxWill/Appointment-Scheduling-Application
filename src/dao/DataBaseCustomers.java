package dao;

import model.Appointments;
import model.Customers;
import utilities.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**Database Customers Class**/
public class DataBaseCustomers {
    /**Returns all data from Customer database**/
    public static ObservableList<Customers> getAllCustomers(){
        ObservableList<Customers> customersObservableList = FXCollections.observableArrayList();

        try{
            String sql ="Select customers.Customer_ID, customers.Customer_Name, customers.Address, customers.Postal_Code, customers.Phone, first_level_divisions.division, countries.Country, customers.Division_ID, first_level_divisions.COUNTRY_ID\n" +
                    "FROM customers\n" + "join first_level_divisions on customers.Division_ID = first_level_divisions.Division_ID\n" +
                    "join countries on first_level_divisions.Country_ID = countries.country_id;";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int customerID = resultSet.getInt("Customer_ID");
                String customerName =  resultSet.getString("Customer_Name");
                String address = resultSet.getString("Address");
                String postalCode = resultSet.getString("Postal_Code");
                String phone = resultSet.getString("Phone");
                String division = resultSet.getString("Division");
                String country = resultSet.getString("Country");
                Customers customers = new Customers(customerID, customerName, address, postalCode, phone, division, country);
                customersObservableList.add(customers);
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
            return null;
        }
        return customersObservableList;
    }

    public static ObservableList<Appointments> getCustomerAppt(int customerID){
        ObservableList<Appointments> customerAppointments = FXCollections.observableArrayList();
        DataBaseAppts dataBaseAppts = new DataBaseAppts();

        for (Appointments appointments : DataBaseAppts.getAllAppointments()){
            if (appointments.getCustomerID() == customerID){
                customerAppointments.add(appointments);
            }
        }
        return customerAppointments;
    }
}
