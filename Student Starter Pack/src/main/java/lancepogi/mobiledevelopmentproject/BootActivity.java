package lancepogi.mobiledevelopmentproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.ParseException;

public class BootActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Intent intent = new Intent(getApplicationContext(), AlarmService.class);
        //startService(intent);
        AlarmScheduler alarmScheduler = new AlarmScheduler(this);
        try {
            alarmScheduler.setAlarmSchedule();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        finish();
    }
}
