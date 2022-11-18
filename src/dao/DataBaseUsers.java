package dao;

import model.Appointments;
import model.Users;
import utilities.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**Users Database Class**/
public class DataBaseUsers {
    private static String userLog;

    public static ObservableList<Users> getAllUsers(){
        ObservableList<Users> usersObservableList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM users;";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int userID = resultSet.getInt("User_ID");
                String username = resultSet.getString("User_Name");
                String password = resultSet.getString("Password");
                Users users = new Users(userID, username, password);
                usersObservableList.add(users);
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return usersObservableList;
    }

    /**Get User ID from user database**/
    public static boolean getUserLogIn(String username, String password){
        try {
            String sql = "SELECT User_Name, Password from users";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                if (resultSet.getString("User_Name").equals(username) && resultSet.getString("Password").equals(password))
                    return true;
            }
            return false;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**Finds Appointments by User ID**/
    public static ObservableList<Appointments> getAppointmentsByUser(int userID){
        ObservableList<Appointments> userAppointmentResults = FXCollections.observableArrayList();
        DataBaseAppts dataBaseAppts = new DataBaseAppts();

        for (Appointments appointments : DataBaseAppts.getAllAppointments()){
            if (appointments.getUserID() == userID){
                userAppointmentResults.add(appointments);
            }
        }
        return userAppointmentResults;
    }
}
