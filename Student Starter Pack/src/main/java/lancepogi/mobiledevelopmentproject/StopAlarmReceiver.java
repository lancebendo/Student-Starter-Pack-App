package lancepogi.mobiledevelopmentproject;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;

/**
 * Created by Lance on 1/31/2017.
 */

public class StopAlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent stopIntent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, stopIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.cancel(pendingIntent);
    }
}
