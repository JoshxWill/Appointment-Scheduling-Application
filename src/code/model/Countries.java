package code.model;

import code.dao.DataBaseCountries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**Countries Model Class**/
public class Countries {
    private static final ObservableList<Countries> allCountries = FXCollections.observableArrayList();
    private int ID;
    private String Name;

    /**Countries Constructors*/
    public Countries(int ID, String Name){
        this.ID = ID;
        this.Name = Name;
    }

    /**Return all Countries from database**/
    public static ObservableList<Countries> getAllCountries(){
        return DataBaseCountries.getAllCountries();
    }

    /**Getter for Countries ID**/
    public int getID(){
        return ID;
    }

    /**Getter for Countries Name**/
    public String getName(){
        return Name;
    }

    /**Overrides method for Menu box**/
    @Override
    public String toString(){
        return (Name);
    }
}
