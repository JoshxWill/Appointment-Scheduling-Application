package main;

import utilities.DatabaseQuery;
import utilities.JDBC;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static javafx.fxml.FXMLLoader.load;


/**
 * Main Class: Opens Login Screen
 *
 * Software II - Advanced Java Concepts
 * @author Joshua Williams
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = load(getClass().getResource("/view/LoginForm.fxml"));
        primaryStage.setTitle("Appointment Scheduler");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException {
        JDBC.makeConnection();
        Connection connection = JDBC.getConnection();
        DatabaseQuery.setApptStatement(connection);
        Statement statement = DatabaseQuery.getApptStatement();

        launch(args);
        JDBC.closeConnection();
    }
}