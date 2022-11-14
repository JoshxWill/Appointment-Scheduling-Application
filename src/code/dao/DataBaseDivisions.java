package code.dao;

import code.model.FirstLevelDivisions;
import code.utilities.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**First Level Divisions Class**/
public class DataBaseDivisions {
    /**Returns all data from First Level Divisions**/
    private static final ObservableList<DataBaseDivisions> allFirstDivisions = FXCollections.observableArrayList();

    public static ObservableList<FirstLevelDivisions> getAllFirstDivisions (){
        ObservableList<FirstLevelDivisions> firstLevelDivisionsObservableList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * from first_level_divisions";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int divisionID = resultSet.getInt("Division_ID");
                String divisionName = resultSet.getString("Division");
                int countryID = resultSet.getInt("Country_ID");
                FirstLevelDivisions firstLevelDivisions = new FirstLevelDivisions(divisionID, divisionName, countryID);
                firstLevelDivisionsObservableList.add(firstLevelDivisions);
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return firstLevelDivisionsObservableList;
    }

    /**Returns all First Level Divisions in UK**/
    private static final ObservableList<FirstLevelDivisions> divisionsUK = FXCollections.observableArrayList();

    public static ObservableList<FirstLevelDivisions> getAllUKDivisions(){
        try {
            String sql = "SELECT * FROM client_schedule.first_level_divisions\n" +
                    "Where Country_ID = 2;";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int divisionID = resultSet.getInt("Division_ID");
                String divisionName = resultSet.getString("Division");
                int countryID = resultSet.getInt("Country_ID");
                FirstLevelDivisions ukDivisions = new FirstLevelDivisions(divisionID, divisionName, countryID);
                divisionsUK.add(ukDivisions);
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return divisionsUK;
    }

    /**Return all data from first leve divisions in Canada**/
    private static final ObservableList<FirstLevelDivisions> canadaDivision = FXCollections.observableArrayList();

    public static ObservableList<FirstLevelDivisions> getAllCanadaDivisions (){
        try {
            String sql = "SELECT * FROM client_schedule.first_level_divisions\n" +
                    "Where Country_ID = 3;";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int divisionID = resultSet.getInt("Division_ID");
                String divisionName = resultSet.getString("Division");
                int countryID = resultSet.getInt("Country_ID");
                FirstLevelDivisions canDivisions = new FirstLevelDivisions(divisionID, divisionName, countryID);
                canadaDivision.add(canDivisions);
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return canadaDivision;
    }

    /**Return all data from first leve divisions in United States**/
    private static final ObservableList<FirstLevelDivisions> USDivision = FXCollections.observableArrayList();

    public static ObservableList<FirstLevelDivisions> getAllUSDivisions (){
        try {
            String sql = "SELECT * FROM client_schedule.first_level_divisions\n" +
                    "Where Country_ID = 1;";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int divisionID = resultSet.getInt("Division_ID");
                String divisionName = resultSet.getString("Division");
                int countryID = resultSet.getInt("Country_ID");
                FirstLevelDivisions unitedDivisions = new FirstLevelDivisions(divisionID, divisionName, countryID);
                USDivision.add(unitedDivisions);
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return USDivision;
    }
}
