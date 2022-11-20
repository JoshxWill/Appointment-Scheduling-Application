package model;

import dao.DataBaseDivisions;

/**First Level Divisions Model Class**/
public class FirstLevelDivisions extends DataBaseDivisions {
    private int countryID;
    private int divisionID;
    private String divisionName;

    /**
     * First-Level Divisions Constructor
     * @param countryID Country ID
     * @param divisionID Division ID
     * @param divisionName Division Name
     */
    public FirstLevelDivisions(int divisionID, String divisionName, int countryID){
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.countryID = countryID;
    }


    /**Getter for countryID
     * @return Country ID**/
    public int getCountryID() {
        return countryID;
    }

    /**Getter for First Level Division ID
     * @return Division ID**/
    public int getDivisionID() {
        return divisionID;
    }

    /**Getter for First Level Division Name
     * @return Division Name**/
    public String getDivisionName() {
        return divisionName;
    }

    /**Overrides toString method for Menu box
     * @return Division Name**/
    @Override
    public String toString(){
        return (divisionName);
    }
}
