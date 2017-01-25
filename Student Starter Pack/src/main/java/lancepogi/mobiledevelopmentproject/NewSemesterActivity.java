package lancepogi.mobiledevelopmentproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewSemesterActivity extends AppCompatActivity {

    android.app.FragmentManager fm = getFragmentManager();

    private DBHelper dbhelper;
    private int etColor;

    EditText etName, etYear1, etYear2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sem);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New Semester");

        this.dbhelper = new DBHelper(this);

        etName = (EditText) findViewById(R.id.etName);
        etYear1 = (EditText) findViewById(R.id.etYear1);
        etYear2 = (EditText) findViewById(R.id.etYear2);
        etColor = etName.getCurrentTextColor();

        etName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(etName.getText().toString().equals("Enter your Name")) {
                    etName.setTextColor(Color.BLACK);
                    etName.setText("");
                }
                else {
                    etName.setText(etName.getText());
                }
                return false;
            }
        });

        etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus == false) {
                    if(etName.getText().toString().equals("")) {
                        etName.setTextColor(etColor);
                        etName.setText("Enter your Name");
                    } else {
                    }
                }
            }
        });

        etYear1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (etYear1.getText().toString().equals("20xx")) {
                    etYear1.setTextColor(Color.BLACK);
                    etYear1.setText("");
                } else {
                    etYear1.setText(etYear1.getText());
                }
                return false;
            }
        });

        etYear1.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus == false) {
                    if(etYear1.getText().toString().equals("")) {
                        etYear1.setTextColor(etColor);
                        etYear1.setText("20xx");
                    } else {

                    }

                }
            }
        });

        etYear2.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (etYear2.getText().toString().equals("20xx")) {
                    etYear2.setTextColor(Color.BLACK);
                    etYear2.setText("");
                } else {
                    etYear2.setText(etYear2.getText());
                }
                return false;
            }
        });

        etYear2.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus == false) {
                    if(etYear2.getText().toString().equals("")) {
                        etYear2.setTextColor(etColor);
                        etYear2.setText("20xx");
                    } else {

                    }

                }
            }
        });
    }

    public  void submitSem(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra("Semester", setSemester());
        dbhelper.newSemester(setSemester());

        startActivity(intent);
    }

    public void newSubject(View view) {
        //FragmentNewSubject newSubject = new FragmentNewSubject();
        //newSubject.show(fm, "New Subject");
        dbhelper.newSemester(setSemester());
    }

    public Semester setSemester() {
        Semester sem = new Semester();
        sem.setStudName(etName.getText().toString());
        sem.setYear(etYear1.getText().toString() + " - " + etYear2.getText().toString());
        return sem;
    }




}
