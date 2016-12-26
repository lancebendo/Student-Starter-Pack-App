package lancepogi.mobiledevelopmentproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Lance on 12/23/2016.
 */

public class FragmentSummary extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.summary_fragment,container,false);
        TextView tv = (TextView) rootView.findViewById(R.id.textView);

        return rootView;
    }
}
