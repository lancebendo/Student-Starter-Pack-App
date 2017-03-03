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
        final TextView tvGreeting = (TextView) rootView.findViewById(R.id.tvGreet);
        final TextView tvSem = (TextView) rootView.findViewById(R.id.tvSem);
        final DBHelper dbHelper = new DBHelper(getActivity());
        Semester sem = dbHelper.getSemester();
        tvGreeting.setText("Hello " + sem.getStudName() + "!");
        tvSem.setText("This is for " + sem.getSemesterComplete() + ".");









        return rootView;
    }
}
