package lancepogi.mobiledevelopmentproject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NewSemesterActivity extends Activity implements View.OnClickListener{

    android.app.FragmentManager fm = getFragmentManager();


    private SubjectListAdapter subjAdapter;
    DBHelper dbHelper;

    Button btnStartDate, btnEndDate;
    TextView tvStartDate, tvEndDate;

    EditText etName;

    Spinner spSemester;

    private int[] startDateInt = {0,0,0}; //first index is year, second is month, third is day
    private int[] endDateInt = {0,0,0}; //first index is year, second is month, third is day

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sem);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("New Semester");
        toolbar.setTitleTextColor(Color.WHITE);
        List<Subject> subjectList = new ArrayList<Subject>();
        this.dbHelper = new DBHelper(this);
        this.subjAdapter = new SubjectListAdapter(this, R.layout.list_view_layout, subjectList);
        ListView lv = (ListView) findViewById(R.id.lvSubject);
        lv.invalidateViews();

        TextView headerView = new TextView(this);
        headerView.setText("Subject Name                   Day");
        lv.addHeaderView(headerView);
        lv.setAdapter(this.subjAdapter);


        etName = (EditText) findViewById(R.id.etName);

        this.btnStartDate = (Button) findViewById(R.id.btnStartDate);
        this.btnEndDate = (Button) findViewById(R.id.btnEndDate);
        this.tvStartDate = (TextView) findViewById(R.id.tvStartDate);
        this.tvEndDate = (TextView) findViewById(R.id.tvEndDate);

        this.spSemester = (Spinner) findViewById(R.id.spSemester);

        btnStartDate.setOnClickListener(this);
        btnEndDate.setOnClickListener(this);

    }


    public  void submitSem(View view) throws ParseException {
        if (isComplete() == true && spSemester.getSelectedItemPosition() != 0) {
            if (isValidDate() == true) {

            Intent intent = new Intent(this, MainActivity.class);
            List<Subject> newList = subjAdapter.subjectList;
            for (Subject subject:newList) {
                dbHelper.newSubject(subject);
            }

            dbHelper.newSemester(setSemester());
            AlarmScheduler alarmScheduler = new AlarmScheduler(this);
            alarmScheduler.setAlarmSchedule();
            alarmScheduler.setAllAlarm(dbHelper.getAlarm());
            startActivity(intent);

            } else {
                Toast.makeText(this, "Invalid start and end date!!", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Please fill up all the required fields!!", Toast.LENGTH_LONG).show();
        }

    }

    private boolean isValidDate() throws ParseException {
        DateFormat startFormat = new SimpleDateFormat("MMMM dd, yyyy");
        DateFormat endFormat = new SimpleDateFormat("MMMM dd, yyyy");

        startFormat.parse(tvStartDate.getText().toString());
        endFormat.parse(tvEndDate.getText().toString());

        long start = startFormat.getCalendar().getTimeInMillis();
        long end = endFormat.getCalendar().getTimeInMillis();

        if (start >= end) {
            return false;
        }

        Calendar c = Calendar.getInstance();

        if (end <= c.getTimeInMillis()) {
            return false;
        }


        return true;
    }

    public void newSubject(View view) {
        FragmentNewSubject newSubject = new FragmentNewSubject();
        Bundle args = new Bundle();
        args.putSerializable("subject", (Serializable) subjAdapter.subjectList);
        args.putSerializable("adapter", subjAdapter);
        newSubject.setArguments(args);
        newSubject.show(fm, "New Subject");

    }

    private boolean isComplete() {
        String name, start, end;
        name = etName.getText().toString();
        start = tvStartDate.getText().toString();
        end = tvEndDate.getText().toString();
        if (name.matches("")) {
            return false;
        } else if (start.matches("MM/dd/yyyy")) {
            return false;
        } else if (end.matches("MM/dd/yyyy")) {
            return false;
        } else if (subjAdapter.getCount() == 0) {
            return false;
        }
        return true;
    }



    public Semester setSemester() {

        String startDateString = startDateInt[1] + "/" + startDateInt[2] + "/" + startDateInt[0];
        String endDateString = endDateInt[1] + "/" + endDateInt[2] + "/" + endDateInt[0];
        String semesterString = spSemester.getSelectedItem().toString();

        Semester sem = new Semester();

        sem.setSemester(semesterString);
        sem.setStudName(etName.getText().toString());
        sem.setStartDate(startDateString);
        sem.setEndDate(endDateString);
        return sem;
    }


    @Override
    public void onClick(View v) {
        if(v == this.btnStartDate) {
            Calendar c = Calendar.getInstance();

            if (startDateInt[0] == 0) {
                startDateInt[0] = c.get(Calendar.YEAR);
                startDateInt[1] = c.get(Calendar.MONTH);
                startDateInt[2] = c.get(Calendar.DAY_OF_MONTH);
            }
            DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    startDateInt[0] = year;
                    startDateInt[1] = month;
                    startDateInt[2] = dayOfMonth;
                    tvStartDate.setText(getMonth(startDateInt[1]) + " " + startDateInt[2] + ", " + startDateInt[0]);

                }
            }, startDateInt[0], startDateInt[1], startDateInt[2]);
            dpd.show();
        }

        if(v == this.btnEndDate) {
            Calendar c = Calendar.getInstance();

            if (endDateInt[0] == 0) {
                endDateInt[0] = c.get(Calendar.YEAR);
                endDateInt[1] = c.get(Calendar.MONTH);
                endDateInt[2] = c.get(Calendar.DAY_OF_MONTH);
            }
            DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    endDateInt[0] = year;
                    endDateInt[1] = month;
                    endDateInt[2] = dayOfMonth;
                    tvEndDate.setText(getMonth(endDateInt[1]) + " " + endDateInt[2] + ", " + endDateInt[0]);
                }
            }, endDateInt[0], endDateInt[1], endDateInt[2]);
            dpd.show();
        }
    }

    private String getMonth(int index) {
        String[] month = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return month[index];
    }

}
