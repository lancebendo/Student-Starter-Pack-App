package lancepogi.mobiledevelopmentproject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lance on 12/23/2016.
 */

public class FragmentSchedule extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.schedule_fragment,container,false);

        DBHelper dbHelper = new DBHelper(getActivity().getApplicationContext());
        //List<Subject> subjectList = dbHelper.getAllSubject();
        List<Subject> subjectList = dbHelper.getAllSubject();
        List<Alarm> alarmList = dbHelper.getAlarm();



        TableLayout sample = (TableLayout) rootView.findViewById(R.id.sampleTable);
        TableLayout.LayoutParams sampleParams = new TableLayout.LayoutParams();

        TableRow.LayoutParams spaceParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT,1.0f);
        spaceParams.height = 150;

        //sampleParams.setMargins(2,2,2,2);
        //sampleParams.weight = 1;

        //baguhin mo to
        TableRow.LayoutParams subjHeaderParams = new TableRow.LayoutParams();
        subjHeaderParams.setMargins(2,2,2,2);
        subjHeaderParams.weight = 1;
        subjHeaderParams.height = 100;


        TableRow.LayoutParams tableParams = new TableRow.LayoutParams();
        tableParams.setMargins(2,2,2,2);
        tableParams.weight = 1;
        tableParams.height = 50;



        TableRow headerSubject = new TableRow(getActivity());
        headerSubject.addView(newTextView("Subject Schedule"), subjHeaderParams);
        sample.addView(headerSubject, sampleParams);

        TableRow headerRowSubject = new TableRow(getActivity());
        headerRowSubject.addView(newTextView("Subject"), subjHeaderParams);
        headerRowSubject.addView(newTextView("Units"), subjHeaderParams);
        headerRowSubject.addView(newTextView("Time"), subjHeaderParams);
        headerRowSubject.addView(newTextView("Day"), subjHeaderParams);
        sample.addView(headerRowSubject, sampleParams);


        for (Subject subj:subjectList) {
            TableRow sampleRow = new TableRow(getActivity());
            sampleRow.addView(newTextView(subj.getSubjName()), tableParams);
            sampleRow.addView(newTextView(subj.getUnits()), tableParams);
            sampleRow.addView(newTextView(subj.getStartTime() + " - " + subj.getEndTime()), tableParams);
            sampleRow.addView(newTextView(subj.getTotalDay()), tableParams);
            sample.addView(sampleRow, sampleParams);
        }

        TableRow spaceRow = new TableRow(getActivity());
        spaceRow.addView(newTextView(""), spaceParams);
        sample.addView(spaceRow, sampleParams);

        TableRow headerAlarm = new TableRow(getActivity());
        headerAlarm.addView(newTextView("Alarms"), subjHeaderParams);
        sample.addView(headerAlarm, sampleParams);

        TableRow headerRowAlarm = new TableRow(getActivity());
        headerRowAlarm.addView(newTextView("Day"), subjHeaderParams);
        headerRowAlarm.addView(newTextView("Time"), subjHeaderParams);
        sample.addView(headerRowAlarm, sampleParams);

        for (Alarm alarm:alarmList) {
            TableRow sampleRow = new TableRow(getActivity());
            sampleRow.addView(newTextView(alarm.getDay()), tableParams);
            sampleRow.addView(newTextView(alarm.getTime()), tableParams);
            sample.addView(sampleRow, sampleParams);
        }


        return rootView;
    }

    public TextView newTextView(String txt) {
        TextView view = new TextView(getActivity());
        view.setText(txt);
        view.setTextColor(Color.BLACK);
        view.setBackgroundColor(Color.parseColor("#ffcccccc"));
        view.setGravity(Gravity.CENTER_HORIZONTAL);

        return view;
    }
}
