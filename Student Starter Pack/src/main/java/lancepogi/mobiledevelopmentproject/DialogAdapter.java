package lancepogi.mobiledevelopmentproject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Lance on 2/25/2017.
 */

public class DialogAdapter extends DialogFragment implements OnClickListener {

    //common attributes
    EditText etSubjName, etActivityName, etDesc, etDeadline;
    Button btnSubmit, btnDeadline;
    //end common attributes

    //subject attributes
    EditText etNoUnits, etStartTime, etEndTime;
    Button btnStartTime, btnEndTime;
    CheckBox cbMonday, cbTuesday, cbWednesday, cbThursday, cbFriday, cbSaturday;
    //end subject attributes

    //assignment attributes
            //etSubjName, etDesc, btnDeadline, etDeadline, btnSubmit
    //end assignment attributes

    //activity attributes
            //etActivityName, etDesc, btnDeadline, etDeadline, btnSubmit
    //end activity attributes

    //no class attributes
        Button btnDate;
        EditText etDate;
        //etDesc, btnSubmit
    //end no class attributes

    //delete attributes
        Spinner spChoose;
        TextView tvDeleteTitle;
        //btnSubmit
    //end delete attributes


    private static final String[] days = {"sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday"};
    public static final String[] dayArray = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday"};
    private int toWhatIndex = 0;
    private int toDoIndex = 0;
    int[] startTime = {99,99};
    int[] endTime = {99,99};
    private int[] startDateInt = {0,0,0}; //first index is year, second is month, third is day
    private int[] endDateInt = {0,0,0}; //first index is year, second is month, third is day
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //index of toDo: 1 for add, 2 for edit, 3 for delete
        //index of toWhat: 1 for subject, 2 for assignment, 3 for activity, 4 for no class
        int[] spinnerIndex = getArguments().getIntArray("index");
        toWhatIndex = spinnerIndex[1];
        toDoIndex = spinnerIndex[0];
        View rootView;

        if (spinnerIndex[0] == 1) {
            //add
            switch (spinnerIndex[1]) {
                case 1:
                    rootView = inflater.inflate(R.layout.subject_fragment, container, false);
                    initializeSubject(rootView);
                    break;
                case 2:
                    rootView = inflater.inflate(R.layout.assignment_fragment, container, false);
                    initializeAssignment(rootView);
                    break;
                case 3:
                    rootView = inflater.inflate(R.layout.activity_fragment, container, false);
                    initializeActivity(rootView);
                    break;
                case 4:
                    rootView = inflater.inflate(R.layout.no_class_fragment, container, false);
                    initializeNoClass(rootView);
                    break;
                default:
                        rootView = inflater.inflate(R.layout.subject_fragment, container, false);
                        break;
            }
        } else if (spinnerIndex[0] == 2) {
            //delete
            rootView = inflater.inflate(R.layout.delete_fragment, container, false);
            initializeDelete(rootView, spinnerIndex[1]);
        } else {
            rootView = inflater.inflate(R.layout.subject_fragment, container, false);
        }

        return rootView;
    }


    public void initializeSubject(View rootView) {
        this.btnStartTime = (Button) rootView.findViewById(R.id.btnStartTime);
        this.btnEndTime = (Button) rootView.findViewById(R.id.btnEndTime);
        btnStartTime.setOnClickListener(this);
        btnEndTime.setOnClickListener(this);

        this.cbMonday = (CheckBox) rootView.findViewById(R.id.cbMonday);
        this.cbTuesday = (CheckBox) rootView.findViewById(R.id.cbTuesday);
        this.cbWednesday = (CheckBox) rootView.findViewById(R.id.cbWednesday);
        this.cbThursday = (CheckBox) rootView.findViewById(R.id.cbThursday);
        this.cbFriday = (CheckBox) rootView.findViewById(R.id.cbFriday);
        this.cbSaturday = (CheckBox) rootView.findViewById(R.id.cbSaturday);

        this.etSubjName = (EditText) rootView.findViewById(R.id.etSubjectName);
        this.etStartTime = (EditText) rootView.findViewById(R.id.etDesc);
        this.etEndTime = (EditText) rootView.findViewById(R.id.etEnd);
        this.btnSubmit = (Button) rootView.findViewById(R.id.btnSubmitAdd);
        btnSubmit.setOnClickListener(this);
    }

    public void initializeAssignment(View rootView) {
        this.etSubjName = (EditText) rootView.findViewById(R.id.etSubjectName);
        this.etDesc = (EditText) rootView.findViewById(R.id.etDesc);
        this.btnDeadline = (Button) rootView.findViewById(R.id.btnDeadline);
        this.btnDeadline.setOnClickListener(this);
        this.etDeadline = (EditText) rootView.findViewById(R.id.etDeadline);
        this.btnSubmit = (Button) rootView.findViewById(R.id.btnSubmit);
        this.btnSubmit.setOnClickListener(this);
    }

    public void initializeActivity(View rootView) {
        this.etActivityName = (EditText) rootView.findViewById(R.id.etActivityName);
        this.etDesc = (EditText) rootView.findViewById(R.id.etDesc);
        this.btnDeadline = (Button) rootView.findViewById(R.id.btnDeadline);
        this.btnDeadline.setOnClickListener(this);
        this.etDeadline = (EditText) rootView.findViewById(R.id.etDeadline);
        this.btnSubmit = (Button) rootView.findViewById(R.id.btnSubmit);
        this.btnSubmit.setOnClickListener(this);
    }

    public void initializeNoClass(View rootView) {
        this.etDate = (EditText) rootView.findViewById(R.id.etDate);
        this.etDesc = (EditText) rootView.findViewById(R.id.etDesc);
        this.btnDate = (Button) rootView.findViewById(R.id.btnDate);
        this.btnDate.setOnClickListener(this);
        this.btnSubmit = (Button) rootView.findViewById(R.id.btnSubmit);
        this.btnSubmit.setOnClickListener(this);
    }

    public void initializeDelete(View rootView, int indexForwhat) {

        String tableName = "";

        this.btnSubmit = (Button) rootView.findViewById(R.id.btnSubmit);
        this.btnSubmit.setOnClickListener(this);

        DBHelper dbHelper = new DBHelper(getActivity().getApplicationContext());
        this.spChoose = (Spinner) rootView.findViewById(R.id.spChoose);
        this.tvDeleteTitle = (TextView) rootView.findViewById(R.id.tvDeleteTitle);
        if (indexForwhat == 1) {
            this.tvDeleteTitle.setText("Delete a Subject");
        } else if (indexForwhat == 2) {
            tableName = "assignment";
            this.tvDeleteTitle.setText("Delete an Assignment");
        } else if (indexForwhat == 3) {
            tableName = "school_activity";
            this.tvDeleteTitle.setText("Delete an Activity");
        } else if (indexForwhat == 4) {
            tableName = "no_class";
            this.tvDeleteTitle.setText("Delete a No Class");
        }

        if (indexForwhat != 1) {
            List<String> adapterList = new ArrayList<>();
            adapterList.add("Choose the # to delete:");
            for (String id:dbHelper.getIdList(tableName)) {
                adapterList.add(id);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    getActivity(), android.R.layout.simple_spinner_item, adapterList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            this.spChoose.setAdapter(adapter);
        } else {
            List<String> adapterList = new ArrayList<>();
            adapterList.add("Choose the name to delete:");
            for (String name:dbHelper.getSubjectName()) {
                adapterList.add(name);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    getActivity(), android.R.layout.simple_spinner_item, adapterList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            this.spChoose.setAdapter(adapter);
        }
    }


    @Override
    public void onClick(View v) {

        boolean isDone = true;

        if (v == this.btnStartTime) {
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
                    etStartTime.setText(getTimePeriod(hourOfDay, minute));
                }
            }, startTime[0], startTime[1], false);
            tpd.show();
        } else if (v == this.btnEndTime) {

            if (endTime[0] == 99 && endTime[1] == 99) {
                endTime[0] = 8;
                endTime[1] = 0;
            }

            TimePickerDialog tpd = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    endTime[0] = hourOfDay;
                    endTime[1] = minute;
                    etEndTime.setText(getTimePeriod(hourOfDay, minute));
                }
            }, endTime[0], endTime[1], false);
            tpd.show();
        } else if(v == this.btnDeadline) {

                Calendar c = Calendar.getInstance();

                if (startDateInt[0] == 0) {
                    startDateInt[0] = c.get(Calendar.YEAR);
                    startDateInt[1] = c.get(Calendar.MONTH);
                    startDateInt[2] = c.get(Calendar.DAY_OF_MONTH);
                }
                DatePickerDialog dpd = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        startDateInt[0] = year;
                        startDateInt[1] = month;
                        startDateInt[2] = dayOfMonth;
                        etDeadline.setText(getMonth(startDateInt[1]) + " " + startDateInt[2] + ", " + startDateInt[0]);

                    }
                }, startDateInt[0], startDateInt[1], startDateInt[2]);
                dpd.show();
        } else if (v == this.btnDate) {
            Calendar c = Calendar.getInstance();

            if (startDateInt[0] == 0) {
                startDateInt[0] = c.get(Calendar.YEAR);
                startDateInt[1] = c.get(Calendar.MONTH);
                startDateInt[2] = c.get(Calendar.DAY_OF_MONTH);
            }
            DatePickerDialog dpd = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    startDateInt[0] = year;
                    startDateInt[1] = month;
                    startDateInt[2] = dayOfMonth;
                    etDate.setText(getMonth(startDateInt[1]) + " " + startDateInt[2] + ", " + startDateInt[0]);

                }
            }, startDateInt[0], startDateInt[1], startDateInt[2]);
            dpd.show();
        } else if (v == this.btnSubmit) {
            int doSomething = 0;
            if (this.toDoIndex == 1) {      //add
                if (isComplete(this.toWhatIndex) == true) {

                    if (this.toWhatIndex == 1) {
                        try {
                            if(isValidData() == true) {
                                submitSubject();
                                isDone = true;
                            } else {
                                isDone = false;
                                Toast.makeText(getActivity(), "Invalid start and end date!!", Toast.LENGTH_LONG).show();
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    } else if (this.toWhatIndex == 2) {
                        submitAssignment();
                    } else if (this.toWhatIndex == 3) {
                        submitActivity();
                    } else if (this.toWhatIndex == 4) {
                        submitNoClass();
                    }
                    if (isDone == true) {
                        startActivity(new Intent(getActivity(), StartupActivity.class));
                        getActivity().finish();
                    }
                } else {
                    Toast.makeText(getActivity(), "Please complete all the required fields!", Toast.LENGTH_LONG).show();
                }
            } else if (this.toDoIndex == 2) {       //delete
                if (spChoose.getSelectedItemPosition() != 0) {

                    DBHelper dbHelper = new DBHelper(getActivity().getApplicationContext());
                    String tableName = "";
                    if (this.toWhatIndex == 1) {
                        if (dbHelper.isLastSubject() == false) {
                            String selectedItem = spChoose.getSelectedItem().toString();
                            dbHelper.removeSubject(selectedItem);

                        } else {
                            isDone = false;
                            Toast.makeText(getActivity(), "Can't delete the last subject. Please reset the semester instead.", Toast.LENGTH_LONG).show();
                        }
                    } else if (this.toWhatIndex == 2) {
                        String selectedItem = spChoose.getSelectedItem().toString().substring(0, spChoose.getSelectedItem().toString().indexOf(" "));
                        dbHelper.removeId(selectedItem, "assignment");
                    } else if (this.toWhatIndex == 3) {
                        String selectedItem = spChoose.getSelectedItem().toString().substring(0, spChoose.getSelectedItem().toString().indexOf(" "));
                        dbHelper.removeId(selectedItem, "school_activity");
                    } else if (this.toWhatIndex == 4) {
                        String selectedItem = spChoose.getSelectedItem().toString().substring(0, spChoose.getSelectedItem().toString().indexOf(" "));
                        dbHelper.removeId(selectedItem, "no_class");
                    }

                    if (isDone == true) {
                        startActivity(new Intent(getActivity(), StartupActivity.class));
                        getActivity().finish();
                    }

                } else {
                    doSomething = 1;
                    Toast.makeText(getActivity(), "Please choose an item you want to delete!!", Toast.LENGTH_LONG).show();
                }
            }

            if(doSomething != 1) {
                AlarmScheduler alarmScheduler = new AlarmScheduler(getActivity().getApplicationContext());
                alarmScheduler.cancelAllAlarm();
                try {
                    alarmScheduler.updateAlarmSchedule();
                    DBHelper dbHelper = new DBHelper(getActivity().getApplicationContext());
                    alarmScheduler.setAllAlarm(dbHelper.getAlarm());
                } catch (ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Please choose an item you want to delete!!", Toast.LENGTH_LONG).show();
                }
            }


        }


    }

    private boolean isValidData() throws ParseException {

        DBHelper dbHelper = new DBHelper(getActivity().getApplicationContext());
        List<Subject> subjectList = dbHelper.getAllSubject();
        DateFormat startTime = new SimpleDateFormat("hh:mm a");
        DateFormat endTime = new SimpleDateFormat("hh:mm a");

        startTime.parse(etStartTime.getText().toString());
        endTime.parse(etEndTime.getText().toString());

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

                                } else if (loopStart >= start) {
                                    if(loopStart >= end) {

                                    } else {
                                        return false;
                                    }
                                } else if (loopEnd <= end) {
                                    if (loopEnd >= end) {

                                    }   else {
                                        return false;
                                    }
                                }

                                else {
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

    private boolean isComplete(int toWhatIndex) {

        if (toWhatIndex == 1) {
            String subjname, units, start, end;
            subjname = etSubjName.getText().toString();
            units = etNoUnits.getText().toString().trim();
            start = etStartTime.getText().toString();
            end = etEndTime.getText().toString();
            if (subjname.matches("")) {
                return false;
            } else if (units.matches("")) {
                return false;
            } else if (start.matches("start time")) {
                return false;
            } else if (end.matches("end time")) {
                return false;
            } else if (isNoDay() == true) {
                return false;
            }
        } else if (toWhatIndex == 2) {
            String subjname, desc, deadline;
            subjname = etSubjName.getText().toString();
            desc = etDesc.getText().toString();
            deadline = etDeadline.getText().toString();
            if (subjname.matches("")) {
                return false;
            } else if (desc.matches("")) {
                return false;
            } else if (deadline.matches("Deadline Time")) {
                return false;
            }
        } else if (toWhatIndex == 3) {
            String actname, desc, deadline;
            actname = etActivityName.getText().toString();
            desc = etDesc.getText().toString();
            deadline = etDeadline.getText().toString();
            if (actname.matches("")) {
                return false;
            } else if (desc.matches("")) {
                return false;
            } else if (deadline.matches("Deadline Time")) {
                return false;
            }
        } else if (toWhatIndex == 4) {
            String desc, date;
            desc = etDesc.getText().toString();
            date = etDate.getText().toString();
            if (date.matches("No Class Date") || desc.matches("")) {
                return false;
            }
        }

        return true;
    }

    private boolean isNoDay() {
        if (cbMonday.isChecked() == false && cbTuesday.isChecked() == false && cbWednesday.isChecked() == false && cbThursday.isChecked() == false && cbFriday.isChecked() == false && cbSaturday.isChecked() == false) {
            return true;
        }
        return false;
    }


    private void submitSubject() {
        Subject newSubject = new Subject();
        newSubject.setSubjName(etSubjName.getText().toString());
        newSubject.setStartTime(getTime(startTime[0], startTime[1]));
        newSubject.setEndTime(getTime(endTime[0], endTime[1]));
        newSubject.setUnits(this.etNoUnits.getText().toString());

        for (String dayString : dayArray) {
            newSubject.setDay(dayString, getDay(dayString));
        }
        DBHelper dbHelper = new DBHelper(getActivity().getApplicationContext());
        dbHelper.newSubject(newSubject);
    }

    private void submitAssignment() {
        Assignment newAssignment = new Assignment();
        newAssignment.setSubj_name(etSubjName.getText().toString());
        newAssignment.setDesc(this.etDesc.getText().toString());
        newAssignment.setIsDone(0);
        newAssignment.setDeadline(this.etDeadline.getText().toString());


        DBHelper dbHelper = new DBHelper(getActivity().getApplicationContext());
        dbHelper.newAssignment(newAssignment);
    }

    private void submitActivity() {
        SchoolActivity newActivity = new SchoolActivity();
        newActivity.setAct_name(this.etActivityName.getText().toString());
        newActivity.setDesc(this.etDesc.getText().toString());
        newActivity.setIsDone(0);
        newActivity.setDeadline(this.etDeadline.getText().toString());

        DBHelper dbHelper = new DBHelper(getActivity().getApplicationContext());
        dbHelper.newActivity(newActivity);
    }

    private void submitNoClass() {
        NoClass newNoClass = new NoClass();
        newNoClass.setDay(this.etDate.getText().toString());
        newNoClass.setDesc(this.etDesc.getText().toString());

        DBHelper dbHelper = new DBHelper(getActivity().getApplicationContext());
        dbHelper.newNoClass(newNoClass);
    }

    private void submitDelete() {

    }

    private String getMonth(int index) {
        String[] month = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return month[index];
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

}
