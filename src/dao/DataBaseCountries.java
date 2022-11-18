package dao;

import model.Countries;
import utilities.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**Countries Database Class**/
public class DataBaseCountries {
    /**Returns all data from Countries database
     * @return All Countries**/
    public static ObservableList<Countries> getAllCountries(){

        ObservableList<Countries> countriesObservableList = FXCollections.observableArrayList();

    try{
        String sql = "Select * from countries";
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            int countryID = resultSet.getInt("Country_ID");
            String countryName = resultSet.getString("Country");
            Countries countries = new Countries(countryID, countryName);
            countriesObservableList.add(countries);

        }
    } catch (SQLException throwables){
        throwables.printStackTrace();
    }
    return countriesObservableList;
    }
}
