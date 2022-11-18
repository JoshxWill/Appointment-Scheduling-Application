package model;

/**Monthly Reports Model Class**/
public class MonthlyReports {
    private int count;
    private String month;
    private String type;

    public MonthlyReports(int count, String month, String type){
        this.count = count;
        this.month = month;
        this.type = type;
    }

    /**Getter for Count**/
    public int getCount() {
        return count;
    }

    /**Getter for Month**/
    public String getMonth() {
        return month;
    }

    /**Getter for Type**/
    public String getType() {
        return type;
    }
}

