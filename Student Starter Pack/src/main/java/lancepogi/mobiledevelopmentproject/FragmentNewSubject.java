package lancepogi.mobiledevelopmentproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;
import android.view.View.OnClickListener;

import java.util.Calendar;

/**
 * Created by Lance on 12/27/2016.
 */

public class FragmentNewSubject extends DialogFragment implements OnClickListener {

    Button btnStart, btnEnd;
    EditText etSubjName, etUnits, etStart, etEnd;
    CheckBox cbMonday, cbTuesday, cbWednesday, cbThursday, cbFriday, cbSaturday;

    private int mHour, mMinute, etColor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.subject_fragment, container, false);

        this.btnStart = (Button) rootView.findViewById(R.id.btnStartTime);
        this.btnEnd = (Button) rootView.findViewById(R.id.btnEndTime);
        btnStart.setOnClickListener(this);
        btnEnd.setOnClickListener(this);

        this.cbMonday = (CheckBox) rootView.findViewById(R.id.cbMonday);
        this.cbTuesday = (CheckBox) rootView.findViewById(R.id.cbTuesday);
        this.cbWednesday = (CheckBox) rootView.findViewById(R.id.cbWednesday);
        this.cbThursday = (CheckBox) rootView.findViewById(R.id.cbThursday);
        this.cbFriday = (CheckBox) rootView.findViewById(R.id.cbFriday);
        this.cbSaturday = (CheckBox) rootView.findViewById(R.id.cbSaturday);

        this.etSubjName = (EditText) rootView.findViewById(R.id.etSubjName);
        this.etUnits = (EditText) rootView.findViewById(R.id.etSubjUnits);
        this.etStart = (EditText) rootView.findViewById(R.id.etStart);
        this.etEnd = (EditText) rootView.findViewById(R.id.etEnd);

        etSubjName.setTextColor(Color.GRAY);
        etUnits.setTextColor(Color.GRAY);

        Button submit = (Button) rootView.findViewById(R.id.btnSubmitAdd);
        submit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        etSubjName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(etSubjName.getText().toString().equals("Subject Name")) {
                    etSubjName.setTextColor(Color.BLACK);
                    etSubjName.setText("");
                }
                else {
                    etSubjName.setText(etSubjName.getText());
                }
                return false;
            }
        });
        etSubjName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus == false) {
                    if(etSubjName.getText().toString().equals("")) {
                        etSubjName.setTextColor(Color.GRAY);
                        etSubjName.setText("Subject Name");
                    } else {
                    }
                }
            }
        });

        etUnits.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(etUnits.getText().toString().equals("No. of Units")) {
                    etUnits.setTextColor(Color.BLACK);
                    etUnits.setText("");
                }
                else {
                    etUnits.setText(etUnits.getText());
                }
                return false;
            }
        });
        etUnits.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus == false) {
                    if(etUnits.getText().toString().equals("")) {
                        etUnits.setTextColor(Color.GRAY);
                        etUnits.setText("No. of Units");
                    } else {
                    }
                }
            }
        });

        return rootView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onClick(View v) {

        if (v == btnStart) {
            final Calendar c = Calendar.getInstance();

            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            TimePickerDialog tpd = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                    etStart.setText(getTime(hourOfDay, minute));
                }
            }, mHour, mMinute, false);
            tpd.show();
        }

        if (v == btnEnd) {
            final Calendar c = Calendar.getInstance();

            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            TimePickerDialog tpd = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    etEnd.setText(getTime(hourOfDay, minute));
                }
            }, mHour, mMinute, false);
            tpd.show();
        }


    }

    private String getTime(int hour, int minute) {
        String period;
        int minuteLength = (int) Math.log10(minute) + 1;

        if (hour > 12) {
            hour = hour - 12;
            period = "PM";
        } else if (hour == 0){
            hour = 12;
            period = "AM";
        } else if (hour == 12) {
            period = "PM";
        } else {
            period = "AM";
        }

        if (minuteLength == 1) {
            return hour + ":0" + minute + " " + period;
        } else {
            return hour + ":" + minute + " " + period;
        }

    }
}

