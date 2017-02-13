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
        List<Subject> subjectList = dbHelper.getSubjectOn("monday");
        List<Alarm> alarmList = dbHelper.getAlarm();



        TableLayout sample = (TableLayout) rootView.findViewById(R.id.sampleTable);
        TableLayout.LayoutParams sampleParams = new TableLayout.LayoutParams();
        //sampleParams.setMargins(2,2,2,2);
        //sampleParams.weight = 1;

        TableRow.LayoutParams tableParams = new TableRow.LayoutParams();
        tableParams.setMargins(2,2,2,2);
        tableParams.weight = 1;

        for (Alarm alarm:alarmList) {
            TableRow sampleRow = new TableRow(getActivity());
            sampleRow.addView(newTextView(alarm.getDay()), tableParams);
            sampleRow.addView(newTextView(alarm.getTime()), tableParams);
            sampleRow.addView(newTextView(alarm.getType()), tableParams);
            sample.addView(sampleRow, sampleParams);
        }

        for (Subject subj:subjectList) {
            TableRow sampleRow = new TableRow(getActivity());
            sampleRow.addView(newTextView(subj.getSubjName()), tableParams);
            sampleRow.addView(newTextView(subj.getUnits()), tableParams);
            sampleRow.addView(newTextView(subj.getStartTime()), tableParams);
            sampleRow.addView(newTextView(subj.getEndTime()), tableParams);
            sampleRow.addView(newTextView(String.valueOf(subj.getDay("monday"))), tableParams);
            sampleRow.addView(newTextView(String.valueOf(subj.getDay("tuesday"))), tableParams);
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
