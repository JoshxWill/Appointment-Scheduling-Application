package controller;

import dao.DataBaseAppts;
import dao.DataBaseContacts;
import model.Appointments;
import model.Contacts;
import model.Customers;
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
import java.sql.SQLException;
import java.sql.Date;
import java.util.ResourceBundle;

/**
 * Contact Report Class
 *
 * @author Joshua Williams
 */
public class ContactReportController implements Initializable {

    @FXML
    private TableView<Appointments> contactRT;
    @javafx.fxml.FXML
    private TableColumn<Appointments, Integer> CRApptID;
    @javafx.fxml.FXML
    private TableColumn<Appointments, String> CRTitle;
    @javafx.fxml.FXML
    private TableColumn<Appointments, String> CRType;
    @javafx.fxml.FXML
    private TableColumn<Appointments, String> CRDescription;
    @javafx.fxml.FXML
    private TableColumn<Appointments, Date> CRStartDT;
    @javafx.fxml.FXML
    private TableColumn<Appointments, Date> CREndDT;
    @javafx.fxml.FXML
    private TableColumn<Customers, Integer> CRCustomerID;
    @javafx.fxml.FXML
    private Button CRBackBtn;
    @javafx.fxml.FXML
    private ComboBox<Contacts> ContactSelect;
    @javafx.fxml.FXML
    private Button Results;

    Parent scene;
    Stage stage;

    /**
     * Returns to Main Screen
     *
     * @param actionEvent
     * @throws IOException
     */
    @javafx.fxml.FXML
    public void ReturnReportScreen(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning!");
        alert.setContentText("Your sure you want to return home screen?");
        alert.showAndWait();

        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainReportScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @javafx.fxml.FXML
    public void CRContactMenu(ActionEvent actionEvent) {
    }

    /**
     * Show Results Button
     *
     * @param actionEvent
     * @throws SQLException
     */
    @javafx.fxml.FXML
    public void ResultBtn(ActionEvent actionEvent) throws SQLException {
        int contactSelect = ContactSelect.getSelectionModel().getSelectedItem().getContactID();
        contactRT.setItems(DataBaseContacts.getContactsAppts(contactSelect));

        System.out.println("Results Button Activated");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactRT.setItems(DataBaseAppts.getAllAppointments());
        ContactSelect.setItems(DataBaseContacts.getAllContacts());
        ContactSelect.getSelectionModel().selectFirst();

        CRApptID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        CRTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        CRDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        CRType.setCellValueFactory(new PropertyValueFactory<>("type"));
        CRStartDT.setCellValueFactory(new PropertyValueFactory<>("start"));
        CREndDT.setCellValueFactory(new PropertyValueFactory<>("end"));
        CRCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
    }
}



