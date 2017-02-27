package lancepogi.mobiledevelopmentproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.widget.Toast;


/**
 * Created by Lance on 1/31/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {


        Toast.makeText(context, "eto startup", Toast.LENGTH_LONG).show();
            Intent serviceIntent = new Intent(context, AlarmService.class);
            context.startService(serviceIntent);


    }

}
