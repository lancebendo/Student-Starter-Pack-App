package lancepogi.mobiledevelopmentproject;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;


/**
 * Created by Lance on 1/9/2017.
 */

public class FragmentSettings extends DialogFragment {

    Button btnApply, btnCancel, btnReset;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.settings_fragment, container, false);

        btnApply = (Button) rootView.findViewById(R.id.btnApply);
        btnCancel = (Button) rootView.findViewById(R.id.btnCancel);
        btnReset = (Button) rootView.findViewById(R.id.btnReset);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(getActivity());
                dbHelper.resetSemester();
                startActivity(new Intent(getActivity(), StartupActivity.class));
                getActivity().finish();
            }
        });



        return rootView;
    }


}


