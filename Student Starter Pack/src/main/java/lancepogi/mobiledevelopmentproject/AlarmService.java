package lancepogi.mobiledevelopmentproject;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Calendar;

/**
 * Created by Lance on 1/31/2017.
 */

public class AlarmService extends Service {

    public static final String[] dayArray = {"sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday"};

    static Ringtone ringtone;

    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        DBHelper dbHelper = new DBHelper(getApplicationContext());
        Calendar c = Calendar.getInstance();
        String dayToday = this.dayArray[c.get(Calendar.DAY_OF_WEEK) - 1];

        if (dbHelper.isNoClassToday() == true) {
            AlarmScheduler alarmScheduler = new AlarmScheduler(getApplicationContext());
            try {
                alarmScheduler.setNextWeekAlarm(dayToday);
            } catch (ParseException e) {
                Toast.makeText(this.getApplicationContext(), "fail", Toast.LENGTH_LONG).show();

            }
            return START_NOT_STICKY;
        }

        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if(alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        ringtone = RingtoneManager.getRingtone(getApplicationContext(), alarmUri);
        ringtone.play();


        Intent newIntent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent notifIntent = PendingIntent.getActivity(this, 0, newIntent, 0);

        NotificationManager notif = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder nbuilder = new NotificationCompat.Builder(getApplicationContext())
                .setContentTitle("Wake up!")
                .setContentText("Touch to stop the alarm.")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(notifIntent)
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_MAX)
                .setDefaults(Notification.DEFAULT_ALL)
                .setVisibility(android.support.v7.app.NotificationCompat.VISIBILITY_PUBLIC)
                ;

        notif.notify(1, nbuilder.build());



        AlarmScheduler alarmScheduler = new AlarmScheduler(getApplicationContext());


        try {
            alarmScheduler.setNextWeekAlarm(dayToday);
        } catch (ParseException e) {
            Toast.makeText(this.getApplicationContext(), "fail", Toast.LENGTH_LONG).show();

        }



        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
