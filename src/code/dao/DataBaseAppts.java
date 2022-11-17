package code.dao;

import code.model.Appointments;
import code.model.MonthlyReports;
import code.utilities.JDBC;
import code.utilities.TimeConversions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**Database Appointments Class**/
public class DataBaseAppts {
    /**Returns all Appointment data**/
    public  static ObservableList<Appointments> getAllAppointments(){
        ObservableList<Appointments> appointmentsObservableList = FXCollections.observableArrayList();

        try {
            String sql = "Select appointments.Appointment_ID, appointments.Title, appointments.Description, appointments.Location, appointments.Type, appointments.Start, appointments.End, appointments.Customer_ID, appointments.User_ID, appointments.Contact_ID, customers.Customer_Name, users.User_Name, contacts.Contact_Name\n" +
                    "FROM appointments join customers on appointments.Customer_ID = customers.Customer_ID join users on appointments.User_ID = users.User_ID join contacts on appointments.Contact_ID = contacts.Contact_ID;";

            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sql);

            ResultSet resultset = preparedStatement.executeQuery();

            while (resultset.next()){
                int appointmentID = resultset.getInt("Appointment_ID");
                String title = resultset.getString("Title");
                String description = resultset.getString("Description");
                String location = resultset.getString("Location");
                String type = resultset.getString("Type");
                Timestamp startDT = TimeConversions.UTCtoLocal(resultset.getTimestamp("Start"));
                Timestamp endDT = TimeConversions.UTCtoLocal(resultset.getTimestamp("End"));
                int customerID = resultset.getInt("Customer_ID");
                int userID = resultset.getInt("User_ID");
                int contactID = resultset.getInt("Contact_ID");
                String customer = resultset.getString("Customer_ID");
                String user = resultset.getString("User_Name");
                String contact = resultset.getString("Contact_Name");

                Appointments appointments = new Appointments(appointmentID, title, description, location, type, TimeConversions.UTCtoLocal1(startDT), TimeConversions.UTCtoLocal1(endDT), customerID, userID, contactID, customer, user, contact);
                appointmentsObservableList.add(appointments);
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
            return null;
        }
        return appointmentsObservableList;
    }

    /**Get appointments from database and organize them by monthly report.**/
    public static ObservableList<MonthlyReports> getApptByMonthAndType(String monthSelected) throws SQLException{
        ObservableList<MonthlyReports> monthlyReports = FXCollections.observableArrayList();

        String appointmentSelected = "Select count(Title) as Count, Type, MONTHNAME(Start) as mn, MONTH(Start) as Month from appointments where monthname(Start) = '" + monthSelected + "' group by MONTH(Start), mn, Type order by Month;";
        PreparedStatement preparedStatement = JDBC.makeConnection().prepareStatement(appointmentSelected);
        ResultSet resultset = preparedStatement.executeQuery();

        while (resultset.next()) {
            String month = resultset.getString("Month");
            String type = resultset.getString("Type");
            int count = resultset.getInt("Count");

            MonthlyReports reports = new MonthlyReports(count, month, type);
            monthlyReports.add(reports);
        }
        return monthlyReports;
    }

    /**Checks Appointment Overlapping**/
    public static Appointments checkApptOverlap(Timestamp startDT, Timestamp endDT, int customerID) throws SQLException{
        Appointments apptOverlap = null;

        ObservableList<Appointments> appointmentsObservableList = DataBaseCustomers.getCustomerAppt(customerID);

        for (Appointments appointments : appointmentsObservableList){
            if (startDT.after(appointments.getStartDT()) && startDT.before(appointments.getEndDT()) ||
                    endDT.after(appointments.getStartDT()) && endDT.before(appointments.getEndDT()) ||
                    startDT.before(appointments.getStartDT()) && endDT.after(appointments.getStartDT()) ||
                    startDT.equals(appointments.getStartDT()) && endDT.equals(appointments.getEndDT()) ||
                    startDT.equals(appointments.getStartDT()) && endDT.equals(appointments.getStartDT())){
                apptOverlap = appointments;
            }
        }
        return apptOverlap;
    }

    /**
     * When month radio button selected:
     * Organize appointments by recent week when month radio button toggled
     */

    public static ObservableList<Appointments> getCurrentWeekAppts(){
        ObservableList<Appointments> weeklyAppts = FXCollections.observableArrayList();

        try{
            String sql = "Select appointments.Appointment_ID, appointments.Title, appointments.Description, appointments.Location, appointments.Type, appointments.Start, appointments.End, appointments.Customer_ID, appointments.User_ID, appointments.Contact_ID, customers.Customer_Name, users.User_Name, contacts.Contact_Name FROM appointments join customers on appointments.Customer_ID = customers.Customer_ID join users on appointments.User_ID = users.User_ID join contacts on appointments.Contact_ID = contacts.Contact_ID where Start > curdate() and Start < CURDATE() + interval 7 day;";

            Appointments appointmentWeek;
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sql);
            ResultSet resultset = preparedStatement.executeQuery();

            while (resultset.next()) {
                appointmentWeek = new Appointments(
                        resultset.getInt("Appointment_ID"),
                        resultset.getString("Title"),
                        resultset.getString("Description"),
                        resultset.getString("Location"),
                        resultset.getString("Type"),
                        resultset.getTimestamp("Start"),
                        resultset.getTimestamp("End"),
                        resultset.getInt("Customer_ID"),
                        resultset.getInt("User_ID"),
                        resultset.getInt("Contact_ID"),
                        resultset.getString("Customer_Name"),
                        resultset.getString("User_Name"),
                        resultset.getString("Contact_Name"));

                appointmentWeek.setStartDT(TimeConversions.UTCtoLocal1(appointmentWeek.getStartDT()));
                appointmentWeek.setEndDT(TimeConversions.UTCtoLocal1(appointmentWeek.getEndDT()));

                weeklyAppts.add(appointmentWeek);

            }
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return weeklyAppts;
    }

    /**
     * When month radio selected:
     * Organize current month in appointment table
     */
    public static ObservableList<Appointments> getCurrentMonthAppts(){
        ObservableList<Appointments> monthlyAppts = FXCollections.observableArrayList();

        try {
            String sql ="Select appointments.Appointment_ID, appointments.Title, appointments.Description, appointments.Location, appointments.Type, appointments.Start, appointments.End, appointments.Customer_ID, appointments.User_ID, appointments.Contact_ID, customers.Customer_Name, users.User_Name, contacts.Contact_Name FROM appointments join customers on appointments.Customer_ID = customers.Customer_ID join users on appointments.User_ID = users.User_ID join contacts on appointments.Contact_ID = contacts.Contact_ID where Start > curdate() and Start < CURDATE() + interval 30 day;";

            Appointments appointmentMonth;
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sql);
            ResultSet resultset = preparedStatement.executeQuery();

            while (resultset.next()) {
                appointmentMonth = new Appointments(
                        resultset.getInt("Appointment_ID"),
                        resultset.getString("Title"),
                        resultset.getString("Description"),
                        resultset.getString("Location"),
                        resultset.getString("Type"),
                        resultset.getTimestamp("Start"),
                        resultset.getTimestamp("End"),
                        resultset.getInt("Customer_ID"),
                        resultset.getInt("User_ID"),
                        resultset.getInt("Contact_ID"),
                        resultset.getString("Customer_Name"),
                        resultset.getString("User_Name"),
                        resultset.getString("Contact_Name"));

                appointmentMonth.setStartDT(TimeConversions.UTCtoLocal1(appointmentMonth.getStartDT()));
                appointmentMonth.setEndDT(TimeConversions.UTCtoLocal1(appointmentMonth.getEndDT()));
                monthlyAppts.add(appointmentMonth);
            }
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
        return monthlyAppts;
    }

    /**Deletes selected Appointment.**/
    public static void deleteAppointment(int appointmentID) throws SQLException{
        String deleteStatement = "Delete FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement preparedStatement = JDBC.makeConnection().prepareStatement(deleteStatement);
        preparedStatement.setInt(1, appointmentID);
        preparedStatement.execute();
    }

}
