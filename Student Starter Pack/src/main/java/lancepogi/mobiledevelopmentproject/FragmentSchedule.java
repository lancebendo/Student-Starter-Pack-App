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

import org.w3c.dom.Text;

/**
 * Created by Lance on 12/23/2016.
 */

public class FragmentSchedule extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.schedule_fragment,container,false);

        TableLayout sample = (TableLayout) rootView.findViewById(R.id.sampleTable);
        TableLayout.LayoutParams sampleParams = new TableLayout.LayoutParams();
        //sampleParams.setMargins(2,2,2,2);
        //sampleParams.weight = 1;

        TableRow.LayoutParams tableParams = new TableRow.LayoutParams();
        tableParams.setMargins(2,2,2,2);
        tableParams.weight = 1;

        for (int i = 0; i < 80; i++) {
        TableRow sampleRow = new TableRow(getActivity());
            sampleRow.addView(newTextView(i), tableParams);
            sampleRow.addView(newTextView(i), tableParams);
            sample.addView(sampleRow, sampleParams);
        }
        return rootView;
    }

    public TextView newTextView(int txt) {
        TextView view = new TextView(getActivity());
        view.setText("this is " + txt);
        view.setTextColor(Color.BLACK);
        view.setBackgroundColor(Color.parseColor("#ffcccccc"));
        view.setGravity(Gravity.CENTER_HORIZONTAL);

        return view;
    }
}
