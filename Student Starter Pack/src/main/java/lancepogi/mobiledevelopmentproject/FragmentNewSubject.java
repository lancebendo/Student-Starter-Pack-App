package lancepogi.mobiledevelopmentproject;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Lance on 12/27/2016.
 */

public class FragmentNewSubject extends DialogFragment implements OnClickListener {

    Button btnStart, btnEnd;
    EditText etSubjName, etStart, etEnd;
    CheckBox cbMonday, cbTuesday, cbWednesday, cbThursday, cbFriday, cbSaturday;

    SubjectListAdapter newAdapter;
    List<Subject> subjectList;

    private int mHour, mMinute;



    public static final String[] dayArray = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday"};
    int[] startTime = {99,99};
    int[] endTime = {99,99};

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

        this.etSubjName = (EditText) rootView.findViewById(R.id.etSubjectName);

        this.etStart = (EditText) rootView.findViewById(R.id.etDesc);
        this.etEnd = (EditText) rootView.findViewById(R.id.etEnd);

        etSubjName.setTextColor(Color.GRAY);


        Button submit = (Button) rootView.findViewById(R.id.btnSubmitAdd);
        submit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (isComplete() == true) {
                    try {
                        if (isValidData() == true) {
                            submitSubject();
                            dismiss();
                        } else {
                            Toast.makeText(getActivity(), "Conflict on sched or invalid time!", Toast.LENGTH_LONG).show();
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getActivity(), "Please fill up all the required fields!!", Toast.LENGTH_LONG).show();
                }
            }
        });

        this.newAdapter = (SubjectListAdapter) getArguments().getSerializable("adapter");
        this.subjectList = (List<Subject>) getArguments().getSerializable("subject");

        return rootView;
    }

    private boolean isValidData() throws ParseException {

        DateFormat startTime = new SimpleDateFormat("hh:mm a");
        DateFormat endTime = new SimpleDateFormat("hh:mm a");

        startTime.parse(etStart.getText().toString());
        endTime.parse(etEnd.getText().toString());

        long start = startTime.getCalendar().getTimeInMillis();
        long end = endTime.getCalendar().getTimeInMillis();

        if(start >= end) {
            return false;
        }

        for (Subject subj:subjectList) {
            if (etSubjName.getText().toString().matches(subj.getSubjName())) {
                return false;
            }

            DateFormat loopStartTime = new SimpleDateFormat("HH:mm");
            DateFormat loopEndTime = new SimpleDateFormat("HH:mm");
            Toast.makeText(getActivity(), String.valueOf(subj.getStartTime()), Toast.LENGTH_LONG).show();
            loopStartTime.parse(subj.getStartTime());
            loopEndTime.parse(subj.getEndTime());

            long loopStart = loopStartTime.getCalendar().getTimeInMillis();
            long loopEnd = loopEndTime.getCalendar().getTimeInMillis();


            for (String dayString:dayArray) {
                if (getDay(dayString) == 1) {
                    for (String loopDay:subj.totalDay()) {
                        if (dayString == loopDay) {
                            if (start >= loopStart) {
                                if (start >= loopEnd) {
                                } else {
                                    return false;
                                }
                            } else if (end <= loopStart) {
                                if (end >= loopEnd) {

                                } else {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean isComplete() {
        String subjname, start, end;
        subjname = etSubjName.getText().toString();

        start = etStart.getText().toString();
        end = etEnd.getText().toString();
        if (subjname.matches("")) {
            return false;
        } else if (start.matches("start time")) {
            return false;
        } else if (end.matches("end time")) {
            return false;
        } else if (isNoDay() == true) {
            return false;
        }


        return true;
    }

    private boolean isNoDay() {
        if (cbMonday.isChecked() == false && cbTuesday.isChecked() == false && cbWednesday.isChecked() == false && cbThursday.isChecked() == false && cbFriday.isChecked() == false && cbSaturday.isChecked() == false) {
            return true;
        }
        return false;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onClick(View v) {

        if (v == btnStart) {
            String defaultTime = "7:00 am";
            SimpleDateFormat simpleTime = new SimpleDateFormat("hh:mm");

            if(startTime[0] == 99 && startTime[0] == 99) {
                startTime[0] = 7;
                startTime[1] = 0;
            }

            TimePickerDialog tpd = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    startTime[0] = hourOfDay;
                    startTime[1] = minute;
                    etStart.setText(getTimePeriod(hourOfDay, minute));
                }
            }, startTime[0], startTime[1], false);
            tpd.show();
        }

        if (v == btnEnd) {

            if (endTime[0] == 99 && endTime[1] == 99) {
                endTime[0] = 8;
                endTime[1] = 0;
            }

            TimePickerDialog tpd = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    endTime[0] = hourOfDay;
                    endTime[1] = minute;
                    etEnd.setText(getTimePeriod(hourOfDay, minute));
                }
            }, endTime[0], endTime[1], false);
            tpd.show();
        }


    }


    private String getTime(int hour, int minute) {
        String period, hourString;
        int minuteLength = (int) Math.log10(minute) + 1;
        int hourLength = (int) Math.log10(hour) + 1;


        if(hourLength == 1) {
            hourString = "0" + String.valueOf(hour);
        } else {
            hourString = String.valueOf(hour);
        }

        if (minuteLength == 1) {
            return hourString + ":0" + minute;
        } else {
            if (minute == 0) {
                return hourString + ":0" + minute; //dito yung para :00 yung lumalabas sa minutes pag zero .
            } else {
                return hourString + ":" + minute;
            }
        }

    }

    private String getTimePeriod(int hour, int minute) {
        String period, hourString;
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

        int hourLength = (int) Math.log10(hour) + 1;

        if(hourLength == 1) {
            hourString = "0" + String.valueOf(hour);
        } else {
            hourString = String.valueOf(hour);
        }

        if (minuteLength == 1) {
            return hourString + ":0" + minute + " " + period;
        } else {
            if (minute == 0) {
                return hourString + ":0" + minute + " " + period; //dito yung para :00 yung lumalabas sa minutes pag zero .
            } else {
                return hourString + ":" + minute + " " + period;
            }
        }

    }

    private int getDay(String day) {
        switch (day) {
            case "monday" :
                if (cbMonday.isChecked()){
                    return 1;
                } else {
                    return 0;
                }

            case "tuesday" :
                if(cbTuesday.isChecked()) {
                    return 1;
                } else {
                    return 0;
                }

            case "wednesday" :
                if(cbWednesday.isChecked()) {
                    return 1;
                } else {
                    return 0;
                }

            case "thursday" :
                if(cbThursday.isChecked()) {
                    return 1;
                } else {
                    return 0;
                }

            case "friday" :
                if(cbFriday.isChecked()) {
                    return 1;
                } else {
                    return 0;
                }

            case "saturday" :
                if (cbSaturday.isChecked()) {
                    return 1;
                } else {
                    return 0;
                }
                default:
                    return 0;
        }
    }

    private void submitSubject() {

        Subject newSubject = new Subject();
        newSubject.setSubjName(etSubjName.getText().toString());
        newSubject.setStartTime(getTime(startTime[0], startTime[1]));
        newSubject.setEndTime(getTime(endTime[0], endTime[1]));

        for (String dayString : dayArray) {
            newSubject.setDay(dayString, getDay(dayString));
        }

        this.subjectList.add(newSubject);
        this.newAdapter.notifyDataSetChanged();
    }

}

