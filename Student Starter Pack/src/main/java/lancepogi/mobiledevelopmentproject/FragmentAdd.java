package lancepogi.mobiledevelopmentproject;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Calendar;

/**
 * Created by Lance on 12/23/2016.
 */

public class FragmentAdd extends Fragment {

    Button btnAlarm, btnCancel, btnNotif;

    TextView sample;

    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    static boolean alarmIsSet = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_fragment,container,false);
        this.sample = (TextView) rootView.findViewById(R.id.tvSampleAlarm);
        this.btnAlarm = (Button) rootView.findViewById(R.id.btnAlarm);
        this.btnCancel = (Button) rootView.findViewById(R.id.btnCancelAlarm);
        this.btnNotif = (Button) rootView.findViewById(R.id.btnNotifs);


        btnAlarm.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                try {
                    setAlarm(v);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    setAlarm(v);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        btnNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        return rootView;
    }

    private void setAlarm(View view) throws ParseException {

        if (view == btnAlarm && this.alarmIsSet == false) {


            Intent intent = new Intent(getActivity(), AlarmReceiver.class);

            this.pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, 0);

            Calendar now = Calendar.getInstance();
            //long time = now.getTimeInMillis() + 60000;
            now.setTimeInMillis(now.getTimeInMillis() + 5000);
            //now.set(Calendar.SECOND, 0);

            this.alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, now.getTimeInMillis(), pendingIntent);
            this.alarmIsSet = true;
            this.sample.setText(now.getTime().toString());
            Toast.makeText(getActivity(), "Alarm is set", Toast.LENGTH_LONG).show();



        }

        if (view == btnCancel) {
            this.alarmIsSet = false;

            //alarmManager.cancel(pendingIntent);

            Intent sampleIntent = new Intent(getActivity(), AlarmReceiver.class);
            PendingIntent newPendingIntent = PendingIntent.getBroadcast(getActivity(), 0, sampleIntent, 0);
            newPendingIntent.cancel();
            Toast.makeText(getContext(), "Canceled", Toast.LENGTH_LONG).show();
        }


    }




}
