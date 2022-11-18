package controller;

import dao.DataBaseAppts;
import model.Appointments;
import model.MonthlyReports;
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
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Overall Appointment Report
 *
 * @author Joshua Williams
 */
public class OverallReportController implements Initializable {
    @FXML
    private TableView<MonthlyReports> OARTable;
    @javafx.fxml.FXML
    private TableColumn<Appointments, String> OverallReportMonthColumn;
    @javafx.fxml.FXML
    private TableColumn<Appointments, String> OverallReportTypeColumn;
    @javafx.fxml.FXML
    private TableColumn<Appointments, Integer> OverallReportCountColumn;
    @javafx.fxml.FXML
    private ComboBox<String> OARMonth;
    @javafx.fxml.FXML
    private Button Results;
    @javafx.fxml.FXML
    private Button BackBtn;

    Parent scene;
    Stage stage;


    /**
     * Activate Results Button to show selected month report
     *
     * @param actionEvent Event
     * @throws SQLException SQLLoader
     */
    @FXML
    public void SeeMonthBtn(ActionEvent actionEvent) throws SQLException {
        String monthSelect = OARMonth.getSelectionModel().getSelectedItem().toUpperCase();
        OARTable.setItems(DataBaseAppts.getApptByMonthAndType(monthSelect));
        System.out.println("Results Button Activated");
    }

    /**
     * Returns To Main Report Screen
     *
     * @param actionEvent Event
     * @throws IOException FXML Exception
     */
    @javafx.fxml.FXML
    public void ReturnReportScreen(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning!");
        alert.setContentText("You sure you want to return?");
        alert.showAndWait();

        stage = (Stage)((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainReportScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        OverallReportMonthColumn.setCellValueFactory(new PropertyValueFactory<>("month"));
        OverallReportTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        OverallReportCountColumn.setCellValueFactory(new PropertyValueFactory<>("count"));

        ObservableList<String> months = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
        OARMonth.setItems(months);
        OARMonth.getSelectionModel().selectFirst();
    }
}
