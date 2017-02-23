package lancepogi.mobiledevelopmentproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.text.ParseException;

public class BootActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DBHelper dbHelper = new DBHelper(this);

        if (dbHelper.isSemesterExisting() == true) {
            AlarmScheduler alarmScheduler = new AlarmScheduler(this);
            try {
                alarmScheduler.setAllAlarm(dbHelper.getAlarm());        //set pending intents on bootup
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        finish();
    }
}
