package lancepogi.mobiledevelopmentproject;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Lance on 2/6/2017.
 */

public class AlarmScheduler {

    private static final String[] dayArray = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday"};
    private Context context;

    AlarmManager alarmManager;


    public AlarmScheduler(Context context) {
        this.context = context;
    }

    public void setAlarmSchedule() throws ParseException {
        DBHelper dbHelper = new DBHelper(this.context);
        for (String day:dayArray) {

            List<Subject> subjectLoop = dbHelper.getSubjectOn(day);
            long earliest = 0;

            for (int i = 0; i < subjectLoop.size(); i++) {

                Subject tempSubject = subjectLoop.get(i);

                if (i == 0) {
                    earliest = tempSubject.getStartMilliTime();
                } else {
                    earliest = getLong(earliest, tempSubject.getStartMilliTime());
                }

            }
            if (subjectLoop.size() != 0) {
                dbHelper.newAlarm(getAlarm(earliest, day, "alarm"));
            }

        }

    }


    public void setIntent(String day) {
        Intent sampleIntent = new Intent(this.context, AlarmReceiver.class);
        PendingIntent newPendingIntent = PendingIntent.getBroadcast(this.context, 0, sampleIntent, 0);

        Calendar now = Calendar.getInstance();
        //long time = now.getTimeInMillis() + 60000;
        now.setTimeInMillis(now.getTimeInMillis() + 5000);
        //now.set(Calendar.SECOND, 0);

        this.alarmManager = (AlarmManager) this.context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, now.getTimeInMillis(), newPendingIntent);
        //this.alarmIsSet = true;
        //this.sample.setText(now.getTime().toString());
        Toast.makeText(this.context, "Alarm is set", Toast.LENGTH_LONG).show();
    }



    private long getLong(long earliest, long loopObject) {
        if (earliest > loopObject) {
            return loopObject;
        } else {
            return earliest;
        }

    }

    private Alarm getAlarm(long earliest, String day, String type) {

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(earliest);
        int hour, minute;
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        Alarm alarm = new Alarm();
        alarm.setDay(day);
        alarm.setType(type);
        alarm.setTime(getTime(hour, minute));

        return alarm;
    }


    private String getTime(int hour, int minute) {
        String period, hourString;
        int minuteLength = (int) Math.log10(minute) + 1;
        int hourLength = (int) Math.log10(hour) + 1;


        if(hourLength == 1) {
            hourString = "0" + String.valueOf(hour);
        } else {
            hourString = String.valueOf(hour);
        }

        if (minuteLength == 1) {
            return hourString + ":0" + minute;
        } else {
            if (minute == 0) {
                return hourString + ":0" + minute; //dito yung para :00 yung lumalabas sa minutes pag zero .
            } else {
                return hourString + ":" + minute;
            }
        }

    }


    private int getIndex(String item) {
        int index = 1;	//index begins in 1 because 0 is considered as sunday
        for (String str : dayArray) {
            if (str == item) {
                break;
            }
            index++;
        }

        return index;
    }




    //SCHEDULER MAIN ENGINE. HEHE. MADE BY: LANCE PATRICK BENDO POGI            2/9/2017
    public Calendar getAlarmSchedule(Alarm alarm) throws ParseException {
        SimpleDateFormat dt = new SimpleDateFormat("HH:mm");
        Date tempDate = dt.parse(alarm.getTime());
        Calendar c = Calendar.getInstance();

        int[] sched = {0,0,0};	//{day, hour, minute, second}   the second index should always be zero!

        String day = alarm.getDay();
        int targetMinute = tempDate.getMinutes();
        int targetHour = tempDate.getHours();
        int todayMinute = c.get(Calendar.MINUTE);
        int todayHour = c.get(Calendar.HOUR_OF_DAY);
        int dayTodayIndex = c.get(Calendar.DAY_OF_WEEK) - 1;
        int targetDayIndex = getIndex(day);

        boolean minusHour = false;
        boolean minusDay = false;

        //FOR MINUTE LOGIC
        if (todayMinute < targetMinute) {
            sched[2] = targetMinute - todayMinute;
        } else if (todayMinute > targetMinute) {
            //IF THIS IS THE CASE, THE HOUR DURATION SHOULD BE SUBTRACTED TO 1
            minusHour = true;
            sched[2] = (60 - todayMinute) + targetMinute;
        }


        //FOR HOUR LOGIC
        if (todayHour < targetHour) {
            sched[1] = targetHour - todayHour;
        } else if (todayHour > targetHour) {
            //if this is the case, the day duration should be subtracted to 1
            minusDay = true;
            sched[1] = (24 - todayHour) + targetHour;
        } else {
            sched[1] = todayHour;
        }


        //FOR DAY LOGIC
        if (dayTodayIndex == targetDayIndex) {
            sched[0] = 7;
        } else if (dayTodayIndex > targetDayIndex) {
            sched[0] = 7 - (dayTodayIndex - targetDayIndex);
        } else if (dayTodayIndex < targetDayIndex) {
            sched[0] = targetDayIndex - dayTodayIndex;
        }

        if (minusHour == true) {
            sched[1] = sched[1] - 1;
        }

        if (minusDay == true) {
            sched[0] = sched[0] - 1;
        }

        c.add(Calendar.DAY_OF_WEEK, sched[0]);
        c.add(Calendar.HOUR, sched[1]);
        c.add(Calendar.MINUTE, sched[2]);
        c.set(Calendar.SECOND, 0);
        return c;
    }



}
