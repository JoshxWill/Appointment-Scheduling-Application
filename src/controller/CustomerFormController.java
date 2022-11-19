package controller;

import dao.DataBaseAppts;
import dao.DataBaseCountries;
import dao.DataBaseCustomers;
import dao.DataBaseDivisions;
import model.Appointments;
import model.Countries;
import model.Customers;
import model.FirstLevelDivisions;
import utilities.DatabaseQuery;
import utilities.JDBC;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
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
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Customer Form Class
 *
 * @author Joshua Williams
 */
public class CustomerFormController implements Initializable {
    @FXML
    private TableView<Customers> customerTable;
    @javafx.fxml.FXML
    private TableColumn<Customers, Integer> CustomerID;
    @javafx.fxml.FXML
    private TableColumn<Customers, String> CustomerName;
    @javafx.fxml.FXML
    private TableColumn<Customers, String> CustomerPhoneNumber;
    @javafx.fxml.FXML
    private TableColumn<Customers, String> CustomerCountry;
    @javafx.fxml.FXML
    private TableColumn<Customers, Integer> CustomerDivision;
    @javafx.fxml.FXML
    private TableColumn<Customers, String> CustomerAddress;
    @javafx.fxml.FXML
    private TableColumn<Customers, String> CustomerPostalCode;
    @FXML
    private ComboBox<Countries> CustomerCountryBox;
    @FXML
    private ComboBox<FirstLevelDivisions> CustomerDivisionBox;
    @javafx.fxml.FXML
    private TextField CustomerIDTxtBox;
    @javafx.fxml.FXML
    private TextField NameTxt;
    @javafx.fxml.FXML
    private TextField Phonetxt;
    @javafx.fxml.FXML
    private TextField Addresstxt;
    @javafx.fxml.FXML
    private TextField PostalCodetxt;
    @javafx.fxml.FXML
    private Button AddCustomer;
    @javafx.fxml.FXML
    private Button SelectCustomer;
    @javafx.fxml.FXML
    private Button UpdateCustomer;
    @javafx.fxml.FXML
    private Button DeleteCustomer;
    @javafx.fxml.FXML
    private Button CancelCustomer;

    private Customers customerSelect;
    int index;

    Parent scene;
    Stage stage;


    /**
     * Country ComboBox Selection Functionality
     *
     * @param actionEvent Event
     */
    @javafx.fxml.FXML
    public void CustomerCountryBox(ActionEvent actionEvent) {
        Countries countrySelect = CustomerCountryBox.getSelectionModel().getSelectedItem();
        System.out.println("Country Box");
        System.out.println(CustomerCountryBox.getValue());

        CustomerDivisionBox.setItems(DataBaseDivisions.getAllFirstDivisions().stream().filter(divisions -> divisions.getCountryID() == countrySelect.getID()).collect(Collectors.toCollection(FXCollections::observableArrayList)));
        CustomerDivisionBox.getSelectionModel().selectFirst();
    }


    /**
     * Customer Add Button Functionality
     *
     * @param actionEvent Event
     * @throws SQLException SQLLoader
     * @throws IOException FXMLLoader
     */
    @javafx.fxml.FXML
    public void CustomerAddBtn(ActionEvent actionEvent) throws SQLException, IOException {
        if (blankChecker()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Check Entered Data");
            alert.setContentText("Must fill all fields");
            alert.showAndWait();
            return;
        }

        else {
            int id = 0;

            CustomerIDTxtBox.setText(String.valueOf(++id));
            String customerName = NameTxt.getText();
            String address = Addresstxt.getText();
            String postalCode = PostalCodetxt.getText();
            String phone = Phonetxt.getText();
            int divisionID = CustomerDivisionBox.getValue().getDivisionID();
            String country = CustomerCountry.getText();


            Customers newCustomer = new Customers(id, customerName, address, postalCode, phone, divisionID, country);


            Connection connection = JDBC.getConnection();
            DatabaseQuery.setApptStatement(connection);
            Statement statement = DatabaseQuery.getApptStatement();

            String insertStatement = "INSERT INTO customers(Customer_Name, Address, Postal_Code , Phone,  Created_By,  Last_Updated_By, Division_ID) " +
                    "VALUES('" + customerName + "', '" + address + "', '" + postalCode + "', '" + phone + "', " + "' admin', " + "  'admin' , '" + divisionID + "' )";

            System.out.println("Insert statement: " + insertStatement);
            statement.execute(insertStatement);

            if (statement.getUpdateCount() > 0)
                System.out.println(statement.getUpdateCount() + "rows affected");
            else
                System.out.println("Nothing changed");

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/CustomerForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        }
    }

    /**
     * Select Customer Button Functionality
     *
     * @param actionEvent Event
     * @throws SQLException SQLLoader
     * @throws IOException FXMLLoader
     */
    @javafx.fxml.FXML
    public void SelectCustomerBtn(ActionEvent actionEvent) throws SQLException, IOException {
        Customers customerSelect = customerTable.getSelectionModel().getSelectedItem();
        int customerID = customerSelect.getCustomerID();
        String customerName = NameTxt.getText();
        String address = Addresstxt.getText();
        String postalCode = PostalCodetxt.getText();
        String phone = Phonetxt.getText();
        String division = String.valueOf(CustomerDivisionBox.getValue());
        String country = String.valueOf(CustomerCountryBox.getValue());

        System.out.println("Customer Button Activated");

        if (customerSelect == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Must select a Customer");
            alert.showAndWait();
            return;
        }

        else {
            stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/CustomerForm.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            CustomerFormController controller = loader.getController();
            controller.getCustomerModify(customerTable.getSelectionModel().getSelectedItem());
            stage.show();
        }

        Connection connection = JDBC.getConnection();
        DatabaseQuery.setApptStatement(connection);
        Statement statement = DatabaseQuery.getApptStatement();

        String customerSelectStatement = "SELECT Customer_Name, Address, Postal_Code, Phone, Division_ID FROM customers where Customer_ID = " + customerID + "";
        statement.execute(customerSelectStatement);
        System.out.println("SELECT Customer Statement: " + customerSelectStatement);

        if (statement.getUpdateCount() > 0)
            System.out.println(statement.getUpdateCount() + "rows affected");
        else
            System.out.println("Customer Selected");

    }

    /**
     * Customer Update Button Functionality
     *
     * @param actionEvent Event
     * @throws SQLException SQLLoader
     * @throws IOException FXMLLoader
     */
    @javafx.fxml.FXML
    public void UpdateCustomerBtn(ActionEvent actionEvent) throws SQLException, IOException {
        System.out.println("Update button Activated");

        if (blankChecker()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Check Entered Data");
            alert.setContentText("Must select an customer. Must fill out all fields!");
            alert.showAndWait();
            return;
        }

        else {
            int customerId = customerSelect.getCustomerID();

            if (customerSelect == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You must select a Customer first!");
                alert.showAndWait();

                return;
            }

            System.out.println("Update Button Activated");

            int customerID = Integer.parseInt(CustomerIDTxtBox.getText());
            String customerName = NameTxt.getText();
            String address = Addresstxt.getText();
            String postal = PostalCodetxt.getText();
            String phone = Phonetxt.getText();
            int divisionID = CustomerDivisionBox.getValue().getDivisionID();


            Customers customerModify = new Customers(customerID, customerName, address, postal, phone, divisionID);

            Connection connection = JDBC.getConnection();
            DatabaseQuery.setApptStatement(connection);
            Statement statement = DatabaseQuery.getApptStatement();

            String updateStatement = "UPDATE customers SET Customer_Name = '" + customerName + "', Address = '" + address + "', Postal_Code = '" + postal + "', Phone = '" + phone + "', Division_ID = '" + divisionID + "'  WHERE Customer_ID = " + customerID + "";
            System.out.println("Update statement: " + updateStatement);
            statement.execute(updateStatement);

            if (statement.getUpdateCount() > 0)
                System.out.println(statement.getUpdateCount() + "rows affected");
            else
                System.out.println("No Change Occurred");

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/CustomerForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

    }

    /**
     * Customer Delete Button Functionality
     *
     * @param actionEvent Event
     * @throws SQLException SQLLoader
     * @throws IOException FXMLLoader
     */
    @javafx.fxml.FXML
    public void DeleteCustomerBtn(ActionEvent actionEvent) throws SQLException, IOException {
        Customers customerSelected = customerTable.getSelectionModel().getSelectedItem();
        int customerId = customerSelected.getCustomerID();
        System.out.println("Delete Button Activated");

        if (customerSelected == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You must select a Customer first!");
            alert.showAndWait();

            return;
        }

        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setContentText("You sure you want to delete?");
            alert.showAndWait();
        }


        try {
            Connection connection = JDBC.getConnection();

            DatabaseQuery.setApptStatement(connection);
            Statement statement = DatabaseQuery.getApptStatement();

            String deleteStatement = "DELETE FROM customers where Customer_ID = " + customerId + "";

            System.out.println("Delete statement: " + deleteStatement);

            statement.execute(deleteStatement);

            if (statement.getUpdateCount() > 0)
                System.out.println(statement.getUpdateCount() + "rows affected");
            else
                System.out.println("Nothing changed");


            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning!");
            alert.setContentText("Customer Removed.");
            alert.showAndWait();

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/CustomerForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cannot Delete!");
            alert.setContentText("Customer has identical Appointment. Must Delete Customer. Try Again! ");
            alert.showAndWait();

            ObservableList<Appointments> allAppointments = DataBaseAppts.getAllAppointments();
            for (Appointments appt : allAppointments) {
                if (appt.getCustomerID() == customerSelected.getCustomerID()) {
                    DataBaseAppts.deleteAppointment(appt.getAppointmentID());
                }

            }

        }
    }

    /**
     * Table Refresh Listing Customers
     *
     * @param actionEvent Event
     */
    @FXML
    public void customerData (ActionEvent actionEvent){
        customerTable.refresh();
    }

    /**
     * Customer Cancel Button Functionality
     *
     * @param actionEvent Event
     * @throws IOException FXMLLoader
     */
    @javafx.fxml.FXML
    public void CancelCustomerBtn(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning!");
        alert.setContentText("Your sure you want to cancel?");
        alert.showAndWait();

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainFormScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Fills out fields/ Aids selected Customer Table
     * @param customers Customers
     */
    public void getCustomerModify(Customers customers){
        CustomerIDTxtBox.setText(Integer.toString(customers.getCustomerID()));
        NameTxt.setText(customers.getCustomerName());
        Addresstxt.setText(customers.getAddress());
        PostalCodetxt.setText(customers.getPostalCode());
        Phonetxt.setText(customers.getPhone());
        customerSelect = customers;

        ObservableList<Countries> allCountries = Countries.getAllCountries();
        CustomerCountryBox.setItems(allCountries);
        FirstLevelDivisions firstLevelDivisions = null;
        for (FirstLevelDivisions FLD : DataBaseDivisions.getAllFirstDivisions()){
            if (FLD.getDivisionName().equals(customers.getDivision()))
                firstLevelDivisions = FLD;
        }

        CustomerDivisionBox.setValue(firstLevelDivisions);
        Countries countries = null;
        for (Countries CC : DataBaseCountries.getAllCountries()){
            if (CC.getID() == (firstLevelDivisions.getCountryID()))
                countries = CC;
        }
        CustomerCountryBox.setValue(countries);
    }

    /**Blank Checker**/
    private boolean blankChecker(){
        if (NameTxt.getText().isEmpty() || Addresstxt.getText().isEmpty() || CustomerCountryBox.getValue() == null ||
        Phonetxt.getText().isEmpty() || PostalCodetxt.getText().isEmpty() || CustomerDivisionBox.getValue() == null){
            return true;
        }

        else {
            return false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Countries> allCountries = Countries.getAllCountries();
        CustomerCountryBox.setItems(allCountries);
        CustomerDivisionBox.setPromptText("Select Division");
        CustomerCountryBox.setPromptText("Select Country");
        //Lambda Code #3
        CustomerID.setCellValueFactory((new PropertyValueFactory<>("customerID")));
        CustomerName.setCellValueFactory(customer -> new SimpleStringProperty(customer.getValue().getCustomerName()));
        CustomerPhoneNumber.setCellValueFactory(customer -> new SimpleStringProperty(customer.getValue().getPhone()));
        CustomerCountry.setCellValueFactory(customer -> new SimpleStringProperty(customer.getValue().getCountry()));
        CustomerDivision.setCellValueFactory((new PropertyValueFactory<>("division")));
        CustomerAddress.setCellValueFactory(customer -> new SimpleStringProperty(customer.getValue().getAddress()));
        CustomerPostalCode.setCellValueFactory(customer -> new SimpleStringProperty(customer.getValue().getPostalCode()));
        customerTable.setItems(DataBaseCustomers.getAllCustomers());
        customerSelect = new Customers(0, null, null, null, null, null, null);
    }

    @FXML
    public void txtCustomerID(ActionEvent actionEvent) {
    }

    @FXML
    public void txtCustomerName(ActionEvent actionEvent) {
    }

    @FXML
    public void txtCustomerPhone(ActionEvent actionEvent) {
    }

    @FXML
    public void txtCustomerAddress(ActionEvent actionEvent) {
    }

    @FXML
    public void txtCustomerPostalCode(ActionEvent actionEvent) {
    }

    @FXML
    public void CustomerDivisionBox(ActionEvent actionEvent) {
    }
}
