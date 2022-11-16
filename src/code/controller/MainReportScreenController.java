package code.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Main Report Screen
 *
 * @author Joshua Williams
 */
public class MainReportScreenController implements Initializable {
    @javafx.fxml.FXML
    private Button BackBtn;
    @javafx.fxml.FXML
    private Button OverallAppt;
    @javafx.fxml.FXML
    private Button ContactScheduleReport;
    @javafx.fxml.FXML
    private Button UserScheduleReport;

    Parent scene;
    Stage stage;

    /**
     * Returns to Main Screen
     *
     * @param actionEvent
     * @throws IOException
     */
    @javafx.fxml.FXML
    public void ReturnMainScreen(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning!");
        alert.setContentText("You sure you want to return?");
        alert.showAndWait();

        stage = (Stage)((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainFormScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * All Appointment Report
     *
     * @param actionEvent
     * @throws IOException
     */
    @javafx.fxml.FXML
    public void OverallCustomerApptBtn(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/OverallAppointmentReport.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Contact Report Screen
     *
     * @param actionEvent
     * @throws IOException
     */
    @javafx.fxml.FXML
    public void ContactScheduleReportBtn(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ContactReport.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * User Schedule Screen
     *
     * @param actionEvent
     * @throws IOException
     */
    @javafx.fxml.FXML
    public void UserScheduleBtn(ActionEvent actionEvent) throws IOException{
        stage = (Stage)((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/UserScheduleReport.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
