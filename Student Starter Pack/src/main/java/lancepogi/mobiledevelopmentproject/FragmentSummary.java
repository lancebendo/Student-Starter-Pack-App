package lancepogi.mobiledevelopmentproject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Lance on 12/23/2016.
 */

public class FragmentSummary extends android.support.v4.app.Fragment {

    private String[] summaryDayArray = {"", "sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.summary_fragment,container,false);
        final TextView tvGreeting = (TextView) rootView.findViewById(R.id.tvGreet);
        final TextView tvSem = (TextView) rootView.findViewById(R.id.tvSem);
        final TextView tvHaveClass = (TextView) rootView.findViewById(R.id.tvHaveClass);

        final ListView lvSummary = (ListView) rootView.findViewById(R.id.lvSummary);

        final DBHelper dbHelper = new DBHelper(getActivity());
        Semester sem = dbHelper.getSemester();
        tvGreeting.setText("Hello " + sem.getStudName() + "!");
        tvSem.setText(sem.getSemesterComplete() + " : " + sem.getStartDateString() + " to " + sem.getEndDateString());

        Calendar c = Calendar.getInstance();
        String dayToday = summaryDayArray[c.get(Calendar.DAY_OF_WEEK)];
        int haveClass = dbHelper.getSubjectOn(dayToday).size();
        if (haveClass != 0) {
            tvHaveClass.setText("You have a class today! Please be advised!");
        } else {

            tvHaveClass.setText("You don't have a class today.");
        }


        List<String> summaryList = new ArrayList<>();
        summaryList.add("UPCOMING ASSIGNMENTS");

        int tempInt = summaryList.size();

        for (Assignment assign:dbHelper.getUpcomingAssignment()) {
            summaryList.add(" - " + assign.getSubj_name() + "\n Description: " + assign.getDesc() + " \nDeadline: " + assign.getDeadline());
        }

        if (tempInt == summaryList.size()) {
            summaryList.add("- no upcoming assignment");
        }

        //summaryList.add("   ");
        summaryList.add("UPCOMING ACTIVITIES");

        tempInt = summaryList.size();

        for(SchoolActivity sc:dbHelper.getUpcomingActivity()) {
            summaryList.add(" - " + sc.getAct_name() + " \n Description: " + sc.getDesc() + " \nDate : " + sc.getDeadline());
        }

        if (tempInt == summaryList.size()) {
            summaryList.add("- no upcoming activity");
        }
        //summaryList.add("   ");
        summaryList.add("UPCOMING NO CLASS");

        tempInt = summaryList.size();

        for (NoClass nc:dbHelper.getUpcomingNoClass()) {
            summaryList.add(" - " + nc.getDay() + " \n Description: " + nc.getDesc());
        }

        if (tempInt == summaryList.size()) {
            summaryList.add("- no upcoming No Class");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, summaryList);
        lvSummary.setAdapter(adapter);






        return rootView;
    }
}
