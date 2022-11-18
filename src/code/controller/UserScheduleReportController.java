package code.controller;

import code.dao.DataBaseAppts;
import code.dao.DataBaseUsers;
import code.model.Appointments;
import code.model.Customers;
import code.model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

/**
 * User Schedule Report
 *
 * @author Joshua Williams
 */
public class UserScheduleReportController implements Initializable {
    @javafx.fxml.FXML
    private TableColumn<Appointments, Integer> USRApptColumn;
    @javafx.fxml.FXML
    private TableColumn<Appointments, String> USRTitleColumn;
    @javafx.fxml.FXML
    private TableColumn<Appointments, String> USRTypeColumn;
    @javafx.fxml.FXML
    private TableColumn<Appointments, String> USRDescriptionColumn;
    @javafx.fxml.FXML
    private TableColumn<Appointments, Date> USRStartDTColumn;
    @javafx.fxml.FXML
    private TableColumn<Appointments, Date> USREndDTColumn;
    @javafx.fxml.FXML
    private TableColumn<Customers, Integer> USRCustomerIDColumn;
    @javafx.fxml.FXML
    private ComboBox<Users> SelectUser;
    @javafx.fxml.FXML
    private Button USRResults;
    @javafx.fxml.FXML
    private Button USRBackBtn;
    @javafx.fxml.FXML
    private TableView<Appointments> userRT;

    Parent scene;
    Stage stage;

    @javafx.fxml.FXML
    public void SelectUserMenu(ActionEvent actionEvent) {
    }

    /**
     * Activate Results Button to show selected User
     *
     * @param actionEvent
     */
    @javafx.fxml.FXML
    public void USRResultsBtn(ActionEvent actionEvent) {
        int userSelect = SelectUser.getSelectionModel().getSelectedItem().getUserID();
        userRT.setItems(DataBaseUsers.getAppointmentsByUser(userSelect));
        System.out.println("Results Button Activated");
    }

    /**
     * Returns to Main Report Screen
     *
     * @param actionEvent
     * @throws IOException
     */
    @javafx.fxml.FXML
    public void ReturnReportScreen(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning!");
        alert.setContentText("You sure you want to return?");
        alert.showAndWait();

        stage = (Stage)((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/src/code/view/MainReportScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userRT.setItems(DataBaseAppts.getAllAppointments());
        SelectUser.setItems(DataBaseUsers.getAllUsers());
        SelectUser.getSelectionModel().selectFirst();

        USRApptColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        USRTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        USRDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        USRTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        USRStartDTColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        USREndDTColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        USRCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
    }
}
