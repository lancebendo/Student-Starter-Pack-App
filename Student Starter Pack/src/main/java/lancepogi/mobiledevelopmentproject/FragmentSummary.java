package lancepogi.mobiledevelopmentproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lance on 12/23/2016.
 */

public class FragmentSummary extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.summary_fragment,container,false);
        final TextView tv = (TextView) rootView.findViewById(R.id.tvSample);
        Button btn = (Button) rootView.findViewById(R.id.btnSample);
        final DBHelper dbHelper = new DBHelper(getActivity());
        Semester sem = dbHelper.getSemester();
        tv.setText( dbHelper.countSemester());

        /*
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(dbHelper.countSemester()) == 1) {
                    tv.setText("it is 1!");
                } else {
                    tv.setText("h" + dbHelper.countSemester() + "hts this");
                }
            }
        }); */

        GridView gvSample = (GridView) rootView.findViewById(R.id.gvSample);


        List<String> sampleList = new ArrayList<String>();
        ArrayAdapter<String> aa = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, sampleList);


        sampleList.add(String.valueOf(sem.getID()));
        sampleList.add(sem.getStudName());
        sampleList.add(sem.getYear());

        sampleList.add("23");
        sampleList.add("pogi");
        sampleList.add("ako");


        gvSample.setAdapter(aa);


        return rootView;
    }
}
