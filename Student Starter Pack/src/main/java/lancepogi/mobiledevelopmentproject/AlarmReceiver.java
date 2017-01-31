package lancepogi.mobiledevelopmentproject;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.renderscript.RenderScript;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.widget.Toast;

import static android.R.id.message;

/**
 * Created by Lance on 1/31/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {

    static Ringtone ringtone;
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "AYTO NA!", Toast.LENGTH_LONG).show();
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if(alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();


        Intent newIntent = new Intent(context, MainActivity.class);
        PendingIntent notifIntent = PendingIntent.getActivity(context, 0, newIntent, 0);



        NotificationManager notif = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder nbuilder = new NotificationCompat.Builder(context)
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


    }

}
