package lancepogi.mobiledevelopmentproject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Lance on 1/11/2017.
 */
@SuppressWarnings("serial")

public class Semester implements Serializable {

    private int id;
    private String studName;
    private String startDate;
    private String endDate;
    private String defaultAlarm;
    private String defaultNotif;
    private int isSet;



    public Semester() {

    }


    public void setStudName(String studName) {
        this.studName = studName;
    }

    public void setStartDate(String date) {
        this.startDate = date;
    }

    public void setEndDate(String date) {
        this.endDate = date;
    }

    public void setDefaultAlarm(String alarm) {
        this.defaultAlarm = alarm;
    }

    public void setDefaultNotif(String notif) {
        this.defaultNotif = notif;
    }



    public int getID() {
        return this.id;
    }

    public String getStudName() {
        return this.studName;
    }

    public void setIsSet(String isSet) {
        this.isSet = Integer.parseInt(isSet);
    }

    public Date getStartDate() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.parse(this.startDate);
    }

    public String getStartDateString() {
        return this.startDate;
    }

    public Date getEndDate() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.parse(this.endDate);
    }

    public String getEndDateString() {
        return this.endDate;
    }


    public String getDefaultAlarmString() {
        return this.defaultAlarm;
    }

    public Date getDefaultNotif() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
        return dateFormat.parse(this.defaultNotif);
    }

    public String getDefaultNotifString() {
        return this.defaultNotif;
    }

    public boolean getIsSet() {
        if (isSet == 1) {
            return true;
        } else {
            return false;
        }
    }

    public String getIsSetString() {
        return String.valueOf(this.isSet);
    }

}
