package lancepogi.mobiledevelopmentproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Lance on 12/23/2016.
 */

public class FragmentAdd extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_fragment,container,false);
        TextView tv = (TextView) rootView.findViewById(R.id.textView);

        return rootView;
    }
}
