package code.controller;

import code.dao.DataBaseAppts;
import code.dao.DataBaseUsers;
import code.model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.TimeZone;

/**
 * Login Screen Class
 * @author Joshua Williams
 */
public class LoginFormController implements Initializable {
    Parent scene;
    Stage stage;

    private ZoneId zoneId = ZoneId.systemDefault();
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");

    @FXML
    private Label schedulenamelabel;
    @FXML
    private Label loginlabel;
    @FXML
    private Label displayzone;
    @javafx.fxml.FXML
    private TextField LoginUserName;
    @javafx.fxml.FXML
    private TextField LoginPassword;
    @javafx.fxml.FXML
    private Button LoginSubmitBtn;
    @javafx.fxml.FXML
    private Button LoginExit;
    @javafx.fxml.FXML
    private Text TimeZoneID;

    Locale locale = Locale.getDefault();

    @javafx.fxml.FXML
    public void txtUsername(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void txtPassword(ActionEvent actionEvent) {
    }

    /**
     * Submit Button Functionality
     * @param actionEvent
     * @return
     */
    @javafx.fxml.FXML
    public boolean SubmitBtn(ActionEvent actionEvent) throws IOException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("main/lang", Locale.getDefault());

        if (LoginUserName.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Username Blank");
            alert.setContentText("Must enter Username!");
            alert.showAndWait();
        }

        if (LoginPassword.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Password Blank");
            alert.setContentText("Must enter Password!");
            alert.showAndWait();
        }
        String username = LoginUserName.getText();
        String password = LoginPassword.getText();
        boolean loginValid = DataBaseUsers.getUserLogIn(username, password);

        if (loginValid) {
            if (upcomingAppt15().size() >= 1) {
                for (Appointments appointments : upcomingAppt15()) {
                    if (Locale.getDefault().toString().equals("en_US")) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Welcome!");
                        alert.setContentText("Reminder: You have an upcoming appointment in 15 min! " + "Appointment ID: " + appointments.getAppointmentID() + " | Start: " + appointments.getStartDT());
                        alert.showAndWait();
                    }

                    if (Locale.getDefault().toString().equals("fr_FR")) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Bienvenu!");
                        alert.setContentText("Rappel : Vous avez un rendez-vous à venir dans 15 min ! " + "ID de rendez-vous : " + appointments.getAppointmentID() + " | Commencer: " + appointments.getStartDT());
                        alert.showAndWait();
                    }
                }
            } else {
                if (Locale.getDefault().toString().equals("en_US")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Welcome!");
                    alert.setContentText("There are no upcoming appointments!");
                    alert.showAndWait();
                }

                if (Locale.getDefault().toString().equals("fr_FR")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Bienvenu!");
                    alert.setContentText("Il n'y a pas de rendez-vous à venir !");
                    alert.showAndWait();

                }
            }

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainFormScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

            fileSuccessLog(username);
        } else {
            if (Locale.getDefault().toString().equals("en_US")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Warning!");
                alert.setContentText("Username or Password Incorrect! Try again.");
                alert.showAndWait();

                fileInvalidLog(username);
            }

            if (Locale.getDefault().toString().equals("fr_FR")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Avertissement!");
                alert.setContentText("Nom d'utilisateur ou mot de passe incorrect! Réessayer.");
                alert.showAndWait();

                fileInvalidLog(username);

            }
        }

        return false;
    }

    /**Log for Successful Login**/
    public static void fileSuccessLog(String username){
        try {
            String loginLog = "login_activity.txt";
            File file = new File(loginLog);
            FileWriter fileWriter = new FileWriter(loginLog, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            LocalDateTime localDateTime = LocalDateTime.now();
            printWriter.println("User: " + username + " Successful Login: " + Timestamp.valueOf(localDateTime));
            printWriter.close();

            //Lambda Code #1
            new Thread(() -> System.out.println(username + "Successful Login"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**Log for Unsuccessful Login**/
    public static void fileInvalidLog(String username){
        try {
            String loginLog = "login_activity.txt";
            File file = new File(loginLog);
            FileWriter fileWriter = new FileWriter(loginLog, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            LocalDateTime localDateTime = LocalDateTime.now();
            printWriter.println("User: " + username + " Unsuccessful Login: " + Timestamp.valueOf(localDateTime));
            printWriter.close();

            //Lambda Code #2
            new Thread(() -> System.out.println(username + "Invalid Login"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DataBaseUsers userdata = new DataBaseUsers();

    /**Upcoming Appointments (15 min)**/
    public ObservableList<Appointments> upcomingAppt15(){
        ObservableList<Appointments> allAppt = DataBaseAppts.getAllAppointments();
        ObservableList<Appointments> upcomingAppt = FXCollections.observableArrayList();
        if (allAppt != null){
            for (Appointments appointments : allAppt){
                LocalDateTime startDT = appointments.getStartDT().toLocalDateTime();
                LocalDateTime nowDT = Timestamp.from(Instant.now()).toLocalDateTime();

                if(startDT.isBefore(nowDT.plusMinutes(30))){
                    if(startDT.isAfter(nowDT)){
                        upcomingAppt.add(appointments);
                    }
                }
            }
        }

        return  upcomingAppt;
    }



    /**
     * Exit Button Functionality
     * @param actionEvent
     */
    @javafx.fxml.FXML
    public void ExitBtn(ActionEvent actionEvent) {
        System.out.println("Exit Button Activated");
        System.exit(0);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ResourceBundle rbundle = ResourceBundle.getBundle("main/lang", Locale.getDefault());

            schedulenamelabel.setText(rbundle.getString("schedulenamelabel"));
            loginlabel.setText(rbundle.getString("loginlabel"));
            TimeZoneID.setText(rbundle.getString("TimeZoneID"));
            LoginSubmitBtn.setText(rbundle.getString("LoginSubmitBtn"));
            LoginExit.setText(rbundle.getString("LoginExit"));


        } catch (MissingResourceException MRE) {
            MRE.printStackTrace();
        }

        LocalDate dateinParis = LocalDate.of(2022, 11, 16);
        LocalTime timeinParis = LocalTime.of(3, 50);
        ZoneId zoneIdParis = ZoneId.of("Europe/Paris");
        ZonedDateTime zoneDateParis = ZonedDateTime.of(dateinParis, timeinParis, zoneIdParis);
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());

        Instant instantParis = zoneDateParis.toInstant();
        ZonedDateTime parisLocal = zoneDateParis.withZoneSameInstant(localZoneId);
        ZonedDateTime GMTtoLocalZDT = instantParis.atZone(localZoneId);

        System.out.println("Local: " + ZonedDateTime.now());
        System.out.println("Paris: " + zoneDateParis);
        System.out.println("Paris to GMT: " + instantParis);
        System.out.println("GMT to Local " + GMTtoLocalZDT);
        System.out.println("GMT to LocalDate: " + GMTtoLocalZDT.toLocalDate());
        System.out.println("GMT to LocalTime: " + GMTtoLocalZDT.toLocalTime());

        String date = String.valueOf(GMTtoLocalZDT.toLocalDate());
        String time = String.valueOf(GMTtoLocalZDT.toLocalTime());
        String datetime = date + " " + time;

        System.out.println(datetime);
        displayzone.setText(String.valueOf(localZoneId));
    }
}
