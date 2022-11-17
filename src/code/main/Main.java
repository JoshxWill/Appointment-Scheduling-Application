package code.main;

import code.utilities.DatabaseQuery;
import code.utilities.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Main Class: Opens Login Screen
 *
 * Software II - Advanced Java Concepts
 * @author Joshua Williams
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException {
        Connection connection = JDBC.getConnection();
        DatabaseQuery.setApptStatement(connection);
        Statement statement = DatabaseQuery.getApptStatement();
        JDBC.makeConnection();
        JDBC.closeConnection();
        launch(args);
    }
}
