package lancepogi.mobiledevelopmentproject;


/**
 * Created by Lance on 2/2/2017.
 * this is for the object of an alarm
 */

public class Alarm {

    private int id;
    private String time, type, day;

    public void setId(int id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getId() {
        return this.id;
    }

    public String getTime() {
        return this.time;
    }

    public String getType() {
        return this.type;
    }

    public String getDay() {
        return this.day;
    }

}
