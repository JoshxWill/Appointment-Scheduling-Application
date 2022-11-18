package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Main Screen Class
 *
 * @author Joshua Williams
 */
public class MainFormScreenController implements Initializable {
    @javafx.fxml.FXML
    private Button MainScreenApptBtn;
    @javafx.fxml.FXML
    private Button MainScreenCustomerBtn;
    @javafx.fxml.FXML
    private Button MainScreenReportBtn;
    @javafx.fxml.FXML
    private Button SignOut;

    Parent scene;
    Stage stage;

    /**
     * Appointment Button initiates Appointment screen
     *
     * @param actionEvent Event
     * @throws IOException Exception
     */
    @javafx.fxml.FXML
    public void MainScreenAppointmentBtn(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AppointmentForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Customer Button initiates Customer Screen
     *
     * @param actionEvent Event
     * @throws IOException Exception
     */
    @javafx.fxml.FXML
    public void MainScreenCustomerBtn(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/CustomerForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Report Button initiates Main Screen Report
     *
     * @param actionEvent Event
     * @throws IOException FXML Exception
     */
    @javafx.fxml.FXML
    public void MainScreenReportBtn(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainReportScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Returns to Login Screen
     *
     * @param actionEvent Event
     * @throws IOException FXML Exception
     */
    @javafx.fxml.FXML
    public void ReturnLoginForm(ActionEvent actionEvent) throws  IOException{
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
