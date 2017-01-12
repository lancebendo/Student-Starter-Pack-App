package lancepogi.mobiledevelopmentproject;

/**
 * Created by Lance on 1/11/2017.
 */

public class Semester {

    private int id;
    private String studName;
    private String year;

    public  Semester(int id, String studName, String year) {
        this.id = id;
        this.studName = studName;
        this.year = year;
    }

    public void setStudName(String studName) {
        this.studName = studName;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getID() {
        return this.id;
    }

    public String getStudName() {
        return this.studName;
    }

    public String getYear() {
        return this.year;
    }

}
