package OOP_CW_commandLine;

import java.io.Serializable;

public class Date implements Serializable {

    private String day;
    private String month;
    private String year;

    public Date(String day, String month, String year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Date(String[] dateValues){
        this.day = dateValues[0];
        this.month=dateValues[1];
        this.year= dateValues[2];
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return this.year + "-" + this.month + "-" + this.day;
    }
}
