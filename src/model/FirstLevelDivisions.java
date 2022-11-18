package model;

import dao.DataBaseDivisions;

/**First Level Divisions Model Class**/
public class FirstLevelDivisions extends DataBaseDivisions {
    private int countryID;
    private int divisionID;
    private String divisionName;

    /**First-Level Divisions Constructor**/
    public FirstLevelDivisions(int countryID, int divisionID, String divisionName){
        this.countryID = countryID;
        this.divisionID = divisionID;
        this.divisionName = divisionName;
    }

    public FirstLevelDivisions(int divisionID, String divisionName, int countryID) {
        super();
    }

    /**Getter for countryID**/
    public int getCountryID() {
        return countryID;
    }

    /**Getter for First Level Division ID**/
    public int getDivisionID() {
        return divisionID;
    }

    /**Getter for First Level Division Name**/
    public String getDivisionName() {
        return divisionName;
    }

    /**Overrides toString method for Menu box**/
    @Override
    public String toString(){
        return (divisionName);
    }
}
