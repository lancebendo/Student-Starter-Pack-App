package lancepogi.mobiledevelopmentproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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


        return rootView;
    }
}
