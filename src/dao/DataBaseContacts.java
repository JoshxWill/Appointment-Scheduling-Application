package dao;

import model.Appointments;
import model.Contacts;
import utilities.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**Database Contacts Class**/
public class DataBaseContacts {
    /**Returns all from Contacts Database**/
    public static ObservableList<Contacts> getAllContacts(){
        ObservableList<Contacts> contactsObservableList = FXCollections.observableArrayList();

        try{
            String sql = "Select * from contacts";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int contactID = resultSet.getInt("Contact_ID");
                String contactName = resultSet.getString("Contact_Name");
                String email = resultSet.getString("Email");
                Contacts contacts = new Contacts(contactID, contactName, email);
                contactsObservableList.add(contacts);
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return contactsObservableList;
    }

    /**Finds Appointments by Contact ID**/
    public static ObservableList<Appointments> getContactsAppts(int contactID){
        ObservableList<Appointments> apptContactResult = FXCollections.observableArrayList();
        DataBaseAppts dataBaseAppts = new DataBaseAppts();

        for (Appointments appointments : DataBaseAppts.getAllAppointments()){
            if (appointments.getContactID() == contactID){
                apptContactResult.add(appointments);
            }
        }
        return apptContactResult;
    }
}
