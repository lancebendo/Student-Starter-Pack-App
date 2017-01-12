package lancepogi.mobiledevelopmentproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.NetworkInfo;
import android.support.v4.view.ViewPropertyAnimatorListener;

/**
 * Created by Lance on 1/11/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ContentDatabase";

    // Table Names
    private static final String TABLE_SEMESTER = "semester";
    private static final String TABLE_SUBJECT = "subject";
    private static final String TABLE_ASSIGNMENT = "assignment";
    private static final String TABLE_SCHOOL_ACTIVITY = "school_activity";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_SUBJECT_NAME = "subject_name";
    private static final String KEY_DESC = "desc";
    private static final String KEY_IS_DONE = "isDone";
    private static final String KEY_DEADLINE = "deadline";

    // SEMESTER Table - column nmaes
    private static final String KEY_STUDENT_NAME = "student_name";
    private static final String KEY_SCHOOL_YEAR = "school_year";

    // SUBJECT Table - column names
    private static final String KEY_UNITS = "units";
    private static final String KEY_START_TIME = "start_time";
    private static final String KEY_END_TIME = "end_time";
    private static final String KEY_MONDAY = "monday";
    private static final String KEY_TUESDAY = "tuesday";
    private static final String KEY_WEDNESDAY = "wednesday";
    private static final String KEY_THURSDAY = "thursday";
    private static final String KEY_FRIDAY = "friday";
    private static final String KEY_SATURDAY = "saturday";

    // SCHOOL_ACTIVITY Table - column names
    private static final String KEY_ACTIVITY_NAME = "act_name";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SEMESTER_TABLE = " CREATE TABLE " + TABLE_SEMESTER + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_STUDENT_NAME + " TEXT, "
                + KEY_SCHOOL_YEAR + " TEXT)";

        String CREATE_SUBJECT_TABLE = " CREATE TABLE " + TABLE_SUBJECT + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_SUBJECT_NAME + " TEXT, "
                + KEY_UNITS + " INTEGER, "
                + KEY_START_TIME + " TEXT, "
                + KEY_END_TIME + " TEXT, "
                + KEY_MONDAY + " INTEGER DEFAULT 0, "
                + KEY_TUESDAY + " INTEGER DEFAULT 0, "
                + KEY_WEDNESDAY + " INTEGER DEFAULT 0, "
                + KEY_THURSDAY + " INTEGER DEFAULT 0, "
                + KEY_FRIDAY + " INTEGER DEFAULT 0, "
                + KEY_SATURDAY + " INTEGER DEFAULT 0)";

        String CREATE_ASSIGNMENT_TABLE = " CREATE TABLE " + TABLE_ASSIGNMENT + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_SUBJECT_NAME + " TEXT, "
                + KEY_DESC + " TEXT, "
                + KEY_IS_DONE + " INTEGER DEFAULT 0, "
                + KEY_DEADLINE + " TEXT)";

        String CREATE_ACTIVITY_TABLE = " CREATE TABLE " + TABLE_SCHOOL_ACTIVITY + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_ACTIVITY_NAME + " TEXT, "
                + KEY_DESC + " TEXT, "
                + KEY_IS_DONE + " INTEGER DEFAULT 0, "
                + KEY_DEADLINE + " TEXT)";

        db.execSQL(CREATE_SEMESTER_TABLE);
        db.execSQL(CREATE_SUBJECT_TABLE);
        db.execSQL(CREATE_ASSIGNMENT_TABLE);
        db.execSQL(CREATE_ACTIVITY_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void newSemester(Semester sem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_STUDENT_NAME, sem.getStudName());
        values.put(KEY_SCHOOL_YEAR, sem.getYear());
        db.insert(TABLE_SEMESTER, null, values);
        db.close();
    }

    public void newSubject(Subject subject) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SUBJECT_NAME, subject.getSubjName());
        values.put(KEY_UNITS, subject.getUnits());
        values.put(KEY_START_TIME, subject.getStartTime());
        values.put(KEY_END_TIME, subject.getEndTime());
        values.put(KEY_MONDAY, subject.getDay("monday"));
        values.put(KEY_TUESDAY, subject.getDay("tuesday"));
        values.put(KEY_WEDNESDAY, subject.getDay("wednesday"));
        values.put(KEY_THURSDAY, subject.getDay("thursday"));
        values.put(KEY_FRIDAY, subject.getDay("friday"));
        values.put(KEY_SATURDAY, subject.getDay("saturday"));
        db.insert(TABLE_SUBJECT, null, values);
        db.close();
    }

    public void newAssignment(Assignment assign) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SUBJECT_NAME, assign.getSubj_name());
        values.put(KEY_DESC, assign.getDesc());
        values.put(KEY_IS_DONE, 0);
        values.put(KEY_DEADLINE, assign.getDeadline());
        db.insert(TABLE_ASSIGNMENT, null, values);
        db.close();
    }

    public void newActivity(SchoolActivity sa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ACTIVITY_NAME, sa.getAct_name());
        values.put(KEY_DESC, sa.getDesc());
        values.put(KEY_IS_DONE, 0);
        values.put(KEY_DEADLINE, sa.getDeadline());
    }

}
