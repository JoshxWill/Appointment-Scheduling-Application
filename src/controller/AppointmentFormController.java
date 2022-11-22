package controller;

import dao.DataBaseAppts;
import dao.DataBaseContacts;
import dao.DataBaseCustomers;
import dao.DataBaseUsers;
import model.Appointments;
import model.Contacts;
import model.Customers;
import model.Users;
import utilities.DatabaseQuery;
import utilities.JDBC;
import utilities.TimeConversions;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;



import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.time.*;
import java.util.Date;
import java.util.ResourceBundle;


/**
 * Appointment Screen Class
 *
 * @author Joshua Williams
 */
public class AppointmentFormController implements Initializable {
    private Appointments appointmentSelect;
    int index;
    Parent scene;
    Stage stage;

    @FXML
    private TableView<Appointments> ApptTableView;
    @FXML
    private TableColumn<Appointments, Integer> ApptIDColumn;
    @FXML
    private TableColumn<Appointments, String> TitleColumn;
    @FXML
    private TableColumn<Appointments, String> DescriptionColumn;
    @FXML
    private TableColumn<Appointments, String> LocationColumn;
    @FXML
    private TableColumn<Appointments, String> ContactColumn;
    @FXML
    private TableColumn<Appointments, String> TypeColumn;
    @FXML
    private TableColumn<Appointments, Date> StartDTColumn;
    @FXML
    private TableColumn<Appointments, Date> EndDTColumn;
    @FXML
    private TableColumn<Customers, Integer> CustomerIDColumn;
    @FXML
    private TableColumn<Users, Integer> UserIDColumn;
    @FXML
    private TextField ApptIDGen;
    @FXML
    private TextField txtTitleBox;
    @FXML
    private TextField txtDescriptionBox;
    @FXML
    private TextField txtLocationBox;
    @FXML
    private ComboBox<Contacts> ContactMenu;
    @FXML
    private TextField txtTypeBox;
    @FXML
    private ComboBox<Customers> CustomerIDMenu;
    @FXML
    private ComboBox<Users> UserIDMenu;
    @FXML
    private RadioButton ApptViewWeekBtn;
    @FXML
    private ToggleGroup radioApptTG;
    @FXML
    private RadioButton ApptViewMonthBtn;
    @FXML
    private RadioButton ApptViewAllBtn;
    @FXML
    private Button ApptShowViewBtn;
    @FXML
    private DatePicker ApptStartDate;
    @FXML
    private ComboBox<String> StartTimeMenu;
    @FXML
    private DatePicker ApptEndDate;
    @FXML
    private ComboBox<String> EndTimeMenu;
    @FXML
    private Button ApptAdd;
    @FXML
    private Button SelectAppt;
    @FXML
    private Button UpdateAppt;
    @FXML
    private Button DeleteAppt;
    @FXML
    private Button CancelAppt;



    /**
     * Show Button Functionality (Week, Month, All)
     *
     * @param actionEvent Event
     */
    @FXML
    public void ApptShowViewBtn(ActionEvent actionEvent) {
        if (ApptViewAllBtn.isSelected()) {
            System.out.println("View All Button Activated");
            ApptTableView.setItems(DataBaseAppts.getAllAppointments());
        } else {
            if (ApptViewWeekBtn.isSelected()) {
                System.out.println("View Week Button Activated");
                ApptTableView.setItems(DataBaseAppts.getCurrentWeekAppts());
            }
        }

        if (ApptViewMonthBtn.isSelected()) {
            System.out.println("View Month Button Activated");
            ApptTableView.setItems(DataBaseAppts.getCurrentMonthAppts());
        }
    }


    /**
     * Add New Appointment
     *
     * @param actionEvent Event
     * @throws SQLException SQLLoader
     * @throws IOException FXML Loader
     *
     */
    @FXML
    public void ApptAddBtn(ActionEvent actionEvent) throws SQLException, IOException {
        System.out.println("Add Button Activated!");
        if (blankChecker()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Check Entered Data");
            alert.setContentText("Must fill out all fields!");
            alert.showAndWait();
            return;
        } else {
            //Start Timestamp
            System.out.println(ApptStartDate.getValue());
            System.out.println(StartTimeMenu.getValue());
            LocalDate localDate = ApptStartDate.getValue();
            LocalTime localTime = LocalTime.parse(StartTimeMenu.getValue());
            Timestamp timestamp = Timestamp.valueOf(LocalDateTime.of(localDate, localTime));
            System.out.println("Start time: " + timestamp);
            //End Timestamp
            System.out.println(ApptEndDate.getValue());
            System.out.println(EndTimeMenu.getValue());
            LocalDate localDate1 = ApptEndDate.getValue();
            LocalTime localTime1 = LocalTime.parse(EndTimeMenu.getValue());
            Timestamp timestamp1 = Timestamp.valueOf(LocalDateTime.of(localDate1, localTime1));
            System.out.println("End time: " + timestamp1);

            int id = 0;

            ApptIDGen.setText(String.valueOf(++id));
            String title = txtTitleBox.getText();
            String description = txtDescriptionBox.getText();
            String location = txtLocationBox.getText();
            String type = txtTypeBox.getText();
            int customerID = Integer.parseInt(String.valueOf(CustomerIDMenu.getValue().getCustomerID()));
            int userID = UserIDMenu.getSelectionModel().getSelectedItem().getUserID();
            int contactID = Integer.parseInt(String.valueOf(ContactMenu.getValue().getContactID()));
            String customerName = String.valueOf(CustomerIDMenu.getValue());
            String userName = String.valueOf(UserIDMenu.getValue());
            String contactName = String.valueOf(ContactMenu.getValue());
            timestamp = Timestamp.valueOf(LocalDateTime.of(localDate, localTime));
            timestamp1 = Timestamp.valueOf(LocalDateTime.of(localDate1, localTime1));

            ZonedDateTime startBusiness = ZonedDateTime.of(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth(), 8, 0, 0, 0, ZoneId.of("America/New_York"));
            ZonedDateTime endBusiness = ZonedDateTime.of(localDate1.getYear(), localDate1.getMonthValue(), localDate1.getDayOfMonth(), 22, 0, 0, 0, ZoneId.of("America/New_York"));
            ZonedDateTime zonedDateTimeStart = startBusiness.withZoneSameInstant(ZoneId.systemDefault());
            ZonedDateTime zonedDateTimeEnd = endBusiness.withZoneSameInstant(ZoneId.systemDefault());

            if (localTime.isBefore(LocalTime.from(zonedDateTimeStart)) || localTime1.isAfter(LocalTime.from(zonedDateTimeEnd))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Outside Business Hours");
                alert.setContentText("Business hours is from 8:00 am to 10:00 pm");
                return;
            }

            Appointments appointmentOverlap = DataBaseAppts.checkApptOverlap(timestamp, timestamp1, customerID);
            if (appointmentOverlap != null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Appointment Overlap");
                alert.setContentText("Appointment overlapping. Try again");
                alert.showAndWait();
                return;
            }

            Appointments newAppointment = new Appointments(id, title, description, location, type, timestamp, timestamp1, customerID, userID, contactID, customerName, userName, contactName);
            Connection connection = JDBC.getConnection();
            DatabaseQuery.setApptStatement(connection);
            Statement statement = DatabaseQuery.getApptStatement();

            String statementInsert = "INSERT INTO appointments(Title, Description, Location, Type, Start, End, Created_By, Last_Updated_By, Customer_ID, User_ID, Contact_ID) " +
                    "VALUES('" + title + "', '" + description + "', '" + location + "', '" + type + "', '" + TimeConversions.UTCtoLocal(timestamp) + "', '" + TimeConversions.UTCtoLocal1(timestamp1) + "', '" + userName + "', '" + userName + "', '" + customerID + "', '" + userID + "', '" + contactID + "')";
            System.out.println("Insert statement: " + statementInsert);
            statement.execute(statementInsert);

            if (statement.getUpdateCount() > 0)
                System.out.println(statement.getUpdateCount() + "rows affected");
            else
                System.out.println("No Change Occurred");
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/AppointmentForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

    }

    /**
     * Select Appointment from database
     *
     * @param actionEvent Event
     * @throws IOException FXMLLoader
     * @throws SQLException SQLLoader
     */
    @FXML
    public void SelectApptBtn(ActionEvent actionEvent) throws IOException, SQLException {
        Appointments appointmentSelect = ApptTableView.getSelectionModel().getSelectedItem();
        int appointmentID = appointmentSelect.getAppointmentID();
        System.out.println("Appointment Button Selected");

        if (appointmentSelect == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Must select Customer Button first!");
            return;
        } else {
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AppointmentForm.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            AppointmentFormController controller = fxmlLoader.getController();
            controller.getAppointmentModify(ApptTableView.getSelectionModel().getSelectedItem());
            stage.show();

        }

        Connection connection = JDBC.getConnection();
        DatabaseQuery.setApptStatement(connection);
        Statement statement = DatabaseQuery.getApptStatement();
        String selectedStatement = "SELECT * FROM appointments where Appointment_ID = " + appointmentID + "";
        System.out.println("Select Appointment Statement: " + selectedStatement);
        statement.execute(selectedStatement);

        if (statement.getUpdateCount() > 0)
            System.out.println(statement.getUpdateCount() + "rows affected");
        else
            System.out.println("Appointment Selected!");
    }

    /**
     * Update Button Functionality
     * @param actionEvent Event
     * @throws SQLException SQLLoader
     * @throws IOException FXMLLoader
     */
    @FXML
    public void UpdateApptBtn(ActionEvent actionEvent) throws SQLException, IOException {
        System.out.println("Modify Button Activated!");
        if (blankChecker()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Check Entered Data");
            alert.setContentText("Must select an appointment. Must fill out all fields!");
            alert.showAndWait();
            return;
        }

        else {

            int appointmentId = appointmentSelect.getAppointmentID();

            //Start Timestamp
            System.out.println(ApptStartDate.getValue());
            System.out.println(StartTimeMenu.getValue());
            LocalDate localDate = ApptStartDate.getValue();
            LocalTime localTime = LocalTime.parse(StartTimeMenu.getValue());
            Timestamp timestamp = Timestamp.valueOf(LocalDateTime.of(localDate, localTime));
            System.out.println("Start time: " + timestamp);
            //End Timestamp
            System.out.println(ApptEndDate.getValue());
            System.out.println(EndTimeMenu.getValue());
            LocalDate localDate1 = ApptEndDate.getValue();
            LocalTime localTime1 = LocalTime.parse(EndTimeMenu.getValue());
            Timestamp timestamp1 = Timestamp.valueOf(LocalDateTime.of(localDate1, localTime1));
            System.out.println("End time: " + timestamp1);


            int appointmentID = Integer.parseInt(ApptIDGen.getText());
            String title = txtTitleBox.getText();
            String description = txtDescriptionBox.getText();
            String location = txtLocationBox.getText();
            String type = txtTypeBox.getText();
            int customerID = Integer.parseInt(String.valueOf(CustomerIDMenu.getValue().getCustomerID()));
            int userID = UserIDMenu.getSelectionModel().getSelectedItem().getUserID();
            int contactID = Integer.parseInt(String.valueOf(ContactMenu.getValue().getContactID()));
            String customerName = String.valueOf(CustomerIDMenu.getValue());
            String userName = String.valueOf(UserIDMenu.getValue());
            String contactName = String.valueOf(ContactMenu.getValue());
            timestamp = Timestamp.valueOf(LocalDateTime.of(localDate, localTime));
            timestamp1 = Timestamp.valueOf(LocalDateTime.of(localDate1, localTime1));

            ZonedDateTime startBusiness = ZonedDateTime.of(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth(), 8, 0, 0, 0, ZoneId.of("America/New_York"));
            ZonedDateTime endBusiness = ZonedDateTime.of(localDate1.getYear(), localDate1.getMonthValue(), localDate1.getDayOfMonth(), 22, 0, 0, 0, ZoneId.of("America/New_York"));
            ZonedDateTime zonedDateTimeStart = startBusiness.withZoneSameInstant(ZoneId.systemDefault());
            ZonedDateTime zonedDateTimeEnd = endBusiness.withZoneSameInstant(ZoneId.systemDefault());

            if (localTime.isBefore(LocalTime.from(zonedDateTimeStart)) || localTime1.isAfter(LocalTime.from(zonedDateTimeEnd))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Outside Business Hours");
                alert.setContentText("Business hours is from 8:00 am to 10:00 pm");
                return;
            }

            Appointments appointmentOverlap = DataBaseAppts.checkApptOverlap(timestamp, timestamp1, customerID);
            if (appointmentOverlap != null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Appointment Overlap");
                alert.setContentText("Appointment overlapping. Try again");
                alert.showAndWait();
                return;
            }

            Appointments modifyAppointment = new Appointments(appointmentID, title, description, location, type, timestamp, timestamp1, customerID, userID, contactID, customerName, userName, contactName);
            Connection connection = JDBC.getConnection();
            DatabaseQuery.setApptStatement(connection);
            Statement statement = DatabaseQuery.getApptStatement();

            String statementUpdate = "UPDATE appointments SET Title = '" + title + "', Description = '" + description + "', Location = '" + location + "', Type = '" + type + "', Start = '" + TimeConversions.UTCtoLocal(timestamp) + "', End = '" + TimeConversions.UTCtoLocal1(timestamp1) + "', Customer_ID = '" + customerID +"', User_ID = '" + userID + "', Contact_ID = '" + contactID + "' WHERE Appointment_ID = '" + appointmentId + "'";
            System.out.println("Update statement: " + statementUpdate);
            statement.execute(statementUpdate);

            if (statement.getUpdateCount() > 0)
                System.out.println(statement.getUpdateCount() + "rows affected");
            else
                System.out.println("No Change Occurred");
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/AppointmentForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * Delete Appointment Button
     *
     * @param actionEvent Event
     * @throws SQLException SQLLoader
     * @throws IOException FXMLLoader
     */
        @FXML
        public void DeleteApptBtn (ActionEvent actionEvent) throws SQLException, IOException {
            System.out.println("Delete Appointment Activated");
            Appointments appointmentSelect = ApptTableView.getSelectionModel().getSelectedItem();
            int appointmentId = appointmentSelect.getAppointmentID();

            if(appointmentSelect == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Must select an Appointment!");
                alert.showAndWait();
                return;
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning!");
            alert.setContentText("You sure you want to delete?");
            alert.showAndWait();

            Connection connection = JDBC.getConnection();
            DatabaseQuery.setApptStatement(connection);
            Statement statement = DatabaseQuery.getApptStatement();

            String statementDelete = "DELETE FROM appointments where Appointment_ID = " + appointmentId + "";
            System.out.println("Delete statement: " + statementDelete);
            statement.execute(statementDelete);

            if (statement.getUpdateCount() > 0)
                System.out.println(statement.getUpdateCount() + "rows affected");
            else
                System.out.println("No Change Occurred");

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning!");
            alert.setContentText("Appointment Deleted! | " + "Appointment ID: " + appointmentSelect.getAppointmentID() + " | Type: " + appointmentSelect.getType());
            alert.showAndWait();

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/AppointmentForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

        /**
         * Cancel Button Functionality
         * @param actionEvent Event
         * @throws IOException FXMLLoader
         */
        @FXML
        public void CancelApptBtn (ActionEvent actionEvent) throws IOException {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning!");
            alert.setContentText("You sure you want to cancel?");
            alert.showAndWait();

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainFormScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

        /**Check Blank Fields**/
        private boolean blankChecker () {
            if (txtTitleBox.getText().isEmpty() || txtDescriptionBox.getText().isEmpty() || ContactMenu.getValue() == null ||
                    txtLocationBox.getText().isEmpty() || txtTypeBox.getText().isEmpty() || ApptStartDate.getValue() == null || StartTimeMenu.getValue() == null ||
                    ApptEndDate.getValue() == null || EndTimeMenu.getValue() == null || CustomerIDMenu.getValue() == null || UserIDMenu.getValue() == null) {
                return true;
            } else {
                return false;
            }
        }

        /**Method that fills out boxes for selected Customer table
         * @param appointments Appointments**/
        public void getAppointmentModify (Appointments appointments){
            appointmentSelect = appointments;

            ApptIDGen.setText(Integer.toString(appointments.getCustomerID()));
            txtTitleBox.setText(appointments.getTitle());
            txtDescriptionBox.setText(appointments.getDescription());
            txtLocationBox.setText(appointments.getLocation());
            txtTypeBox.setText(appointments.getType());
            StartTimeMenu.setValue(String.valueOf(appointments.getStartDT().toLocalDateTime().toLocalTime()));
            ApptStartDate.setValue(appointments.getStartDT().toLocalDateTime().toLocalDate());
            ApptEndDate.setValue(appointments.getStartDT().toLocalDateTime().toLocalDate());
            EndTimeMenu.setValue(String.valueOf(appointments.getEndDT().toLocalDateTime().toLocalTime()));

            ObservableList<Contacts> contactsObservableList = Contacts.getAllContacts();
            ContactMenu.setItems(contactsObservableList);
            Contacts contacts = null;

            for (Contacts contacts1 : DataBaseContacts.getAllContacts())
                if (contacts1.getContactName().equals(appointmentSelect.getContactName())) {
                    contacts = contacts1;
                }

            ContactMenu.setValue(contacts);
            ObservableList<Customers> customersObservableList = Customers.getAllCustomers();
            CustomerIDMenu.setItems(customersObservableList);
            Customers customers = null;

            for (Customers customers1 : DataBaseCustomers.getAllCustomers()){
                if (customers1.getCustomerName().equals(appointmentSelect.getCustomerName()))
                    customers = customers1;
            }

            CustomerIDMenu.setValue(customers);
            ObservableList<Users> usersObservableList = Users.getAllUsers();
            UserIDMenu.setItems(usersObservableList);
            Users users = null;

            for (Users users1 : DataBaseUsers.getAllUsers()){
                if (users1.getUserName().equals(appointmentSelect.getUserName()))
                    users = users1;
            }
            UserIDMenu.setValue(users);
        }

    /**
     * Display all Appointments
     * @param url URL
     * @param resourceBundle RB
     */
        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){
            ApptTableView.setItems(DataBaseAppts.getAllAppointments());

            appointmentSelect = new Appointments(0, null, null, null, null, null, null, 0, 0, 0, null, null, null);
            ApptIDColumn.setCellValueFactory((new PropertyValueFactory<>("appointmentID")));
            TitleColumn.setCellValueFactory((new PropertyValueFactory<>("title")));
            DescriptionColumn.setCellValueFactory((new PropertyValueFactory<>("description")));
            LocationColumn.setCellValueFactory((new PropertyValueFactory<>("location")));
            ContactColumn.setCellValueFactory((new PropertyValueFactory<>("contactID")));
            TypeColumn.setCellValueFactory((new PropertyValueFactory<>("type")));
            StartDTColumn.setCellValueFactory((new PropertyValueFactory<>("startDT")));
            EndDTColumn.setCellValueFactory((new PropertyValueFactory<>("endDT")));
            CustomerIDColumn.setCellValueFactory((new PropertyValueFactory<>("customerID")));
            UserIDColumn.setCellValueFactory((new PropertyValueFactory<>("userID")));

            ContactMenu.setPromptText("Select Contact");
            UserIDMenu.setPromptText("Select User");
            CustomerIDMenu.setPromptText("Select Customer");

            ObservableList<Contacts> contacts = Contacts.getAllContacts();
            ContactMenu.setItems(contacts);

            ObservableList<Customers> customers = Customers.getAllCustomers();
            CustomerIDMenu.setItems(customers);

            ObservableList<Users> users = Users.getAllUsers();
            UserIDMenu.setItems(users);

            LocalTime start = LocalTime.of(7, 0);
            LocalTime end = LocalTime.of(23, 00);

            while (start.isBefore(end.plusSeconds(1))){
                StartTimeMenu.getItems().add(String.valueOf(start));
                start = start.plusMinutes(30);
                EndTimeMenu.getItems().add(String.valueOf(start));
            }
            StartTimeMenu.getSelectionModel().select(String.valueOf(LocalTime.of(0, 0)));
            StartTimeMenu.setPromptText("Start Time");
            StartTimeMenu.getSelectionModel().selectFirst();
            EndTimeMenu.getSelectionModel().select(String.valueOf(LocalTime.of(0, 0)));
            EndTimeMenu.setPromptText("End Time");
            EndTimeMenu.getSelectionModel().selectNext();

        }

    @FXML
    public void ApptIDGen(ActionEvent actionEvent) {
    }

    @FXML
    public void txtTitleBox(ActionEvent actionEvent) {
    }

    @FXML
    public void txtDescriptionBox(ActionEvent actionEvent) {
    }

    @FXML
    public void txtLocationBox(ActionEvent actionEvent) {
    }

    @FXML
    public void ContactMenu(ActionEvent actionEvent) {
    }

    @FXML
    public void CustomerIDMenu(ActionEvent actionEvent) {
    }

    @FXML
    public void UserIDMenu(ActionEvent actionEvent) {
    }

    @FXML
    public void RadioApptViewWeekBtn(ActionEvent actionEvent) {
    }

    @FXML
    public void RadioApptViewMonthBtn(ActionEvent actionEvent) {
    }

    @FXML
    public void RadioApptViewAllBtn(ActionEvent actionEvent) {
    }

    @FXML
    public void ApptStartDate(ActionEvent actionEvent) {
    }

    @FXML
    public void StartTimeMenu(ActionEvent actionEvent) {
    }

    @FXML
    public void ApptEndDate(ActionEvent actionEvent) {
    }

    @FXML
    public void EndTimeMenu(ActionEvent actionEvent) {
    }
}

