package lancepogi.mobiledevelopmentproject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Lance on 1/11/2017.
 */

public class Subject {

    private int id;
    private String subjName;
    private String units;
    private String startTime;
    private String endTime;
    private boolean isMonday, isTuesday, isWednesday, isThursday, isFriday, isSaturday = false;

    private static final String[][] totalDay = {
            {"monday", "M"}, {"tuesday", "T"}, {"wednesday", "W"}, {"thursday", "Th"}, {"friday", "F"}, {"saturday", "S"}
    };

    public Subject() {

    }

    public Subject(int id, String subjName, String units, String startTime, String endTime, String[] day) {
        this.id = id;
        this.subjName = subjName;
        this.units = units;
        this.startTime = startTime;
        this.endTime = endTime;

        for (String aDay: day) {

            switch (aDay) {
                case "monday" :
                    this.isMonday = true;
                    break;

                case "tuesday" :
                    this.isTuesday = true;
                    break;

                case "wednesday" :
                    this.isWednesday = true;
                    break;

                case "thursday" :
                    this.isThursday = true;
                    break;

                case "friday" :
                    this.isFriday = true;
                    break;

                case "saturday" :
                    this.isSaturday = true;
                    break;

                default:
            }   //end of switch statement

        }   //end of foreach loop

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSubjName(String subjName) {
        this.subjName = subjName;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setDay(String day, int intIsDay) {

        boolean isDay;

        if(intIsDay == 0) {
            isDay = false;
        }
        else {
            isDay = true;
        }

        switch (day) {
            case "monday" :
                this.isMonday = isDay;
                break;

            case "tuesday" :
                this.isTuesday = isDay;
                break;

            case "wednesday" :
                this.isWednesday = isDay;
                break;

            case "thursday" :
                this.isThursday = isDay;
                break;

            case "friday" :
                this.isFriday = isDay;
                break;

            case "saturday" :
                this.isSaturday = isDay;
                break;

            default:
        }

    }

    public String getID() {
        return String.valueOf(this.id);
    }

    public String getSubjName() {
        return this.subjName;
    }

    public String getUnits() {
        return this.units;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public int getDay(String day) {
        switch (day) {
            case "monday":
                return getBit(this.isMonday);
            case "tuesday":
                return getBit(this.isTuesday);
            case "wednesday":
                return getBit(this.isWednesday);
            case "thursday":
                return getBit(this.isThursday);
            case "friday":
                return getBit(this.isFriday);
            case "saturday":
                return getBit(this.isSaturday);
            default:
                return 0;
        }
    }

    public List<String> totalDay() {
        List<String> day = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            int bit = getDay(totalDay[i][0]);
            if(bit == 1) {
                day.add(totalDay[i][0]);
            }
        }
        return day;
    }

    public String getTotalDay() {

        String finalString = "";

        for (int i = 0; i < 6; i++) {   //6 because sunday isnt included in a week
            int bit = getDay(totalDay[i][0]);

            if(bit == 1 && i == 0) {
                finalString = finalString + totalDay[i][1];
            } else if(bit == 1 && i != 0) {
                if (finalString == "") {
                    finalString = totalDay[i][1];
                } else {
                    finalString = finalString + "-" + totalDay[i][1];
                }

            }
        }
        return finalString;
    }

    public int getBit(boolean bool) {
        if (bool == false) {
            return 0;
        } else {
            return 1;
        }
    }

    public long getStartMilliTime() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date time = dateFormat.parse(this.startTime);
        return time.getTime();
    }

    public long getEndtMilliTime() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date time = dateFormat.parse(this.endTime);
        return time.getTime();
    }

    public String getDayArray() {
        String[][] dayArray = {
                {"monday", "M"}, {"tuesday", "T"}, {"wednesday", "W"}, {"thursday", "Th"}, {"friday", "F"}, {"saturday", "S"}
        };

        String loopString = "";

        for (int i = 0; i < dayArray.length; i++) {
            if(getDay(dayArray[i][0]) == 1) {

                if(loopString == "") {
                    loopString = dayArray[i][1];
                } else {
                    loopString = loopString + " - " + dayArray[i][1];
                }
            }
        }

        return loopString;
    }

    private String getTime(int hour, int minute) {
        String period, hourString;
        int minuteLength = (int) Math.log10(minute) + 1;
        int hourLength = (int) Math.log10(hour) + 1;
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
