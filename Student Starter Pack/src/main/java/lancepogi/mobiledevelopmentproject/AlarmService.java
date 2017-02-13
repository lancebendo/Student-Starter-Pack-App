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

import java.util.Calendar;

/**
 * Created by Lance on 1/31/2017.
 */

public class AlarmService extends Service {

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

        Toast.makeText(this.getApplicationContext(), "AYTO NA!", Toast.LENGTH_LONG).show();
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
/*
        Intent sampleIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
        PendingIntent newPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, sampleIntent, 0);

        Calendar now = Calendar.getInstance();
        //long time = now.getTimeInMillis() + 60000;
        now.setTimeInMillis(now.getTimeInMillis() + 5000);
        //now.set(Calendar.SECOND, 0);

        this.alarmManager = (AlarmManager) getApplication().getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, now.getTimeInMillis(), newPendingIntent);
        //this.alarmIsSet = true;
        //this.sample.setText(now.getTime().toString());
        Toast.makeText(getApplicationContext(), "Alarm is set", Toast.LENGTH_LONG).show();
*/
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
