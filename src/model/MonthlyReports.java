package model;

/**Monthly Reports Model Class**/
public class MonthlyReports {
    private int count;
    private String month;
    private String type;

    /**
     * Monthly Reports Data
     *
     * @param count Count
     * @param month Month
     * @param type Type
     */
    public MonthlyReports(int count, String month, String type){
        this.count = count;
        this.month = month;
        this.type = type;
    }

    /**Getter for Count
     * @return Count**/
    public int getCount() {
        return count;
    }

    /**Getter for Month
     * @return Month**/
    public String getMonth() {
        return month;
    }

    /**Getter for Type
     * @return Type**/
    public String getType() {
        return type;
    }
}

