package lancepogi.mobiledevelopmentproject;


import java.text.DateFormat;
import java.text.SimpleDateFormat;

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

    public  String getTimeComplete() {
        String hour = this.time.substring(0, this.time.indexOf(":"));
        String minute = this.time.substring(this.time.indexOf(":") + 1, this.time.length());



        return getTimePeriod(Integer.parseInt(hour), Integer.parseInt(minute));
    }

    public String getType() {
        return this.type;
    }

    public String getDay() {
        return this.day;
    }


    private String getTimePeriod(int hour, int minute) {
        String period, hourString;
        int minuteLength = (int) Math.log10(minute) + 1;

        if (hour > 12) {
            hour = hour - 12;
            period = "PM";
        } else if (hour == 0){
            hour = 12;
            period = "AM";
        } else if (hour == 12) {
            period = "PM";
        } else {
            period = "AM";
        }

        int hourLength = (int) Math.log10(hour) + 1;

        if(hourLength == 1) {
            hourString = "0" + String.valueOf(hour);
        } else {
            hourString = String.valueOf(hour);
        }

        if (minuteLength == 1) {
            return hourString + ":0" + minute + " " + period;
        } else {
            if (minute == 0) {
                return hourString + ":0" + minute + " " + period; //dito yung para :00 yung lumalabas sa minutes pag zero .
            } else {
                return hourString + ":" + minute + " " + period;
            }
        }

    }

}
