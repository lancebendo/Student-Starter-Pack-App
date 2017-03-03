package lancepogi.mobiledevelopmentproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;


/**
 * Created by Lance on 1/9/2017.
 */

public class FragmentSettings extends DialogFragment {

    Button btnApply, btnCancel, btnReset;

    Spinner spHours, spSem;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.settings_fragment, container, false);

        spHours = (Spinner) rootView.findViewById(R.id.spHours);
        spSem = (Spinner) rootView.findViewById(R.id.spSem);

        btnApply = (Button) rootView.findViewById(R.id.btnApply);
        btnCancel = (Button) rootView.findViewById(R.id.btnCancel);
        btnReset = (Button) rootView.findViewById(R.id.btnReset);

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spHours.getSelectedItemPosition() != 0 && spSem.getSelectedItemPosition() != 0) {
                    final int subtractHours = spHours.getSelectedItemPosition();
                    DBHelper dbHelper = new DBHelper(getActivity());
                    Semester newSemester = dbHelper.getSemester();
                    newSemester.setDefaultAlarm(String.valueOf(subtractHours));
                    newSemester.setSemester(spSem.getSelectedItem().toString());
                    try {
                        dbHelper.updateSemesterAlarm(newSemester);
                        AlarmScheduler newAlarmScheduler = new AlarmScheduler(getActivity());
                        newAlarmScheduler.cancelAllAlarm();
                        newAlarmScheduler.updateAlarmSchedule();
                        newAlarmScheduler.setAllAlarm(dbHelper.getAlarm());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    startActivity(new Intent(getActivity(), StartupActivity.class));
                    getActivity().finish();
                } else {
                    Toast.makeText(getActivity(), "Please choose on the spinner before applying changes!", Toast.LENGTH_LONG).show();
                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                DBHelper dbHelper = new DBHelper(getActivity());
                                AlarmScheduler newAlarmScheduler = new AlarmScheduler(getActivity());
                                newAlarmScheduler.cancelAllAlarm();
                                dbHelper.resetSemester();
                                startActivity(new Intent(getActivity(), StartupActivity.class));
                                getActivity().finish();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:

                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Are you sure?")
                        .setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });



        return rootView;
    }


}


