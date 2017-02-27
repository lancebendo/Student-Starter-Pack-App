package lancepogi.mobiledevelopmentproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.io.File;
import java.io.InterruptedIOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lance on 1/11/2017.
 */

public class DBHelper extends SQLiteOpenHelper implements Serializable {

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
    private static final String TABLE_ALARM = "alarm";
    private static final String TABLE_NO_CLASS = "no_class";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_SUBJECT_NAME = "subject_name";
    private static final String KEY_DESC = "desc";
    private static final String KEY_IS_DONE = "isDone";
    private static final String KEY_DEADLINE = "deadline";
    private static final String KEY_DAY = "day";

    // SEMESTER Table - column names
    //id
    private static final String KEY_STUDENT_NAME = "student_name";
    private static final String KEY_START_DATE = "start_date";
    private static final String KEY_END_DATE = "end_date";
    private static final String KEY_DEFAULT_ALARM = "default_alarm";
    private static final String KEY_DEFAULT_NOTIF = "default_notif";
    private static final String KEY_ISSET = "is_set";

    // SUBJECT Table - column names
    //id
    //subj_name
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
    //id
    private static final String KEY_ACTIVITY_NAME = "act_name";
    //desc
    //isDone
    //deadline

    //ALARM Table - column names
    //id
    private static final String KEY_ALARM_TIME = "alarm_time";
    private static final String KEY_ALARM_TYPE = "alarm_type";

    //NO_CLASS Table - column names
    //id
    //day
    //desc

    public DBHelper(Context context) {


        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        createTable();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    public void createTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        String CREATE_SEMESTER_TABLE = " CREATE TABLE " + TABLE_SEMESTER + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_STUDENT_NAME + " TEXT, "
                + KEY_START_DATE + " TEXT, "
                + KEY_END_DATE + " TEXT, "
                + KEY_DEFAULT_ALARM + " TEXT, "
                + KEY_DEFAULT_NOTIF + " TEXT, "
                + KEY_ISSET + " INT DEFAULT 0)";

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

        String CREATE_ALARM_TABLE = " CREATE TABLE " + TABLE_ALARM + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_ALARM_TIME + " TEXT , "
                + KEY_ALARM_TYPE + " TEXT, "
                + KEY_DAY + " TEXT)";

        String CREATE_NOCLASS_TABLE = " CREATE TABLE " + TABLE_NO_CLASS + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_DAY + " TEXT, "
                + KEY_DESC + " TEXT)";

        db.execSQL(CREATE_SEMESTER_TABLE);
        db.execSQL(CREATE_SUBJECT_TABLE);
        db.execSQL(CREATE_ASSIGNMENT_TABLE);
        db.execSQL(CREATE_ACTIVITY_TABLE);
        db.execSQL(CREATE_ALARM_TABLE);
        db.execSQL(CREATE_NOCLASS_TABLE);

    }

    public void newSemester(Semester sem) throws ParseException {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_STUDENT_NAME, sem.getStudName());
        values.put(KEY_START_DATE, sem.getStartDateString());
        values.put(KEY_END_DATE, sem.getEndDateString());
        values.put(KEY_DEFAULT_ALARM, "2");
        values.put(KEY_DEFAULT_NOTIF, "20:00");
        values.put(KEY_ISSET, 0);
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
        db.insert(TABLE_SCHOOL_ACTIVITY, null, values);
        db.close();
    }

    public void newAlarm(Alarm alarm) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DAY, alarm.getDay());
        values.put(KEY_ALARM_TIME, alarm.getTime());
        values.put(KEY_ALARM_TYPE, alarm.getType());
        db.insert(TABLE_ALARM, null, values);
        db.close();
    }

    public  void newNoClass(NoClass noClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DAY, noClass.getDay());
        values.put(KEY_DESC, noClass.getDesc());
        db.insert(TABLE_NO_CLASS, null, values);
        db.close();
    }


    public Semester getSemester() {
        String selectQuery = "SELECT * FROM " + TABLE_SEMESTER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Semester sem = new Semester();
        if(cursor.moveToFirst()) {
            do {
                sem.setStudName(cursor.getString(1));
                sem.setStartDate(cursor.getString(2));
                sem.setEndDate(cursor.getString(3));
                sem.setDefaultAlarm(cursor.getString(4));
                sem.setDefaultNotif(cursor.getString(5));
                sem.setIsSet(cursor.getString(6));
            } while (cursor.moveToNext());
        }

        return sem;
    }

    public String countSemester() {
        String selectQuery = "SELECT COUNT(*) as bilang FROM " + TABLE_SEMESTER;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    public boolean isSemesterExisting() {
        String selectQuery = "SELECT COUNT(*) as bilang FROM " + TABLE_SEMESTER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if (Integer.parseInt(cursor.getString(0)) != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isAssignmentExisting() {
        String selectQuery = "SELECT COUNT(*) as bilang FROM " + TABLE_ASSIGNMENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if (Integer.parseInt(cursor.getString(0)) != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isActivityExisting() {
        String selectQuery = "SELECT COUNT(*) as bilang FROM " + TABLE_SCHOOL_ACTIVITY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if (Integer.parseInt(cursor.getString(0)) != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isNoClassExisting() {
        String selectQuery = "SELECT COUNT(*) as bilang FROM " + TABLE_NO_CLASS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if (Integer.parseInt(cursor.getString(0)) != 0) {
            return true;
        } else {
            return false;
        }
    }

    public void resetSemester() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE " + TABLE_SEMESTER);
        db.execSQL("DROP TABLE " + TABLE_SUBJECT);
        db.execSQL("DROP TABLE " + TABLE_ASSIGNMENT);
        db.execSQL("DROP TABLE " + TABLE_SCHOOL_ACTIVITY);
        db.execSQL("DROP TABLE " + TABLE_ALARM);
        db.execSQL("DROP TABLE " + TABLE_NO_CLASS);
        createTable();
    }

    public List<Subject> getAllSubject() {
        List<Subject> subjectList = new ArrayList<Subject>();
        String selectQuery = "SELECT * FROM " + TABLE_SUBJECT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Subject sub = new Subject();
                sub.setId(Integer.parseInt(cursor.getString(0)));
                sub.setSubjName(cursor.getString(1));
                sub.setUnits(cursor.getString(2));
                sub.setStartTime(cursor.getString(3));
                sub.setEndTime(cursor.getString(4));
                sub.setDay("monday", Integer.parseInt(cursor.getString(5)));
                sub.setDay("tuesday", Integer.parseInt(cursor.getString(6)));
                sub.setDay("wednesday", Integer.parseInt(cursor.getString(7)));
                sub.setDay("thursday", Integer.parseInt(cursor.getString(8)));
                sub.setDay("friday", Integer.parseInt(cursor.getString(9)));
                sub.setDay("saturday", Integer.parseInt(cursor.getString(10)));
                subjectList.add(sub);
            } while (cursor.moveToNext());
        }

        return subjectList;
    }

    public List<Subject> getSubjectOn(String day) {

        List<Subject> subjectList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        String selectQuery;

        switch (day) {
            case "monday" :
                selectQuery = "SELECT * FROM " + TABLE_SUBJECT + " WHERE " + KEY_MONDAY + " = 1";
                break;
            case "tuesday" :
                selectQuery = "SELECT * FROM " + TABLE_SUBJECT + " WHERE " + KEY_TUESDAY + " = 1";
                break;
            case "wednesday" :
                selectQuery = "SELECT * FROM " + TABLE_SUBJECT + " WHERE " + KEY_WEDNESDAY + " = 1";
                break;
            case "thursday" :
                selectQuery = "SELECT * FROM " + TABLE_SUBJECT + " WHERE " + KEY_THURSDAY + " = 1";
                break;
            case "friday" :
                selectQuery = "SELECT * FROM " + TABLE_SUBJECT + " WHERE " + KEY_FRIDAY + " = 1";
                break;
            case "saturday" :
                selectQuery = "SELECT * FROM " + TABLE_SUBJECT + " WHERE " + KEY_SATURDAY + " = 1";
                break;
            default:
                selectQuery = "SELECT * FROM " + TABLE_SUBJECT;
                break;
        }

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Subject sub = new Subject();
                sub.setId(Integer.parseInt(cursor.getString(0)));
                sub.setSubjName(cursor.getString(1));
                sub.setUnits(cursor.getString(2));
                sub.setStartTime(cursor.getString(3));
                sub.setEndTime(cursor.getString(4));
                sub.setDay("monday", Integer.parseInt(cursor.getString(5)));
                sub.setDay("tuesday", Integer.parseInt(cursor.getString(6)));
                sub.setDay("wednesday", Integer.parseInt(cursor.getString(7)));
                sub.setDay("thursday", Integer.parseInt(cursor.getString(8)));
                sub.setDay("friday", Integer.parseInt(cursor.getString(9)));
                sub.setDay("saturday", Integer.parseInt(cursor.getString(10)));
                subjectList.add(sub);
            } while (cursor.moveToNext());
        }

        return subjectList;

    }

    public List<Assignment> getAllAssignment() {
        List<Assignment> assignmentList = new ArrayList<Assignment>();
        String selectQuery = "SELECT * FROM " + TABLE_ASSIGNMENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Assignment assign = new Assignment();
                assign.setId(Integer.parseInt(cursor.getString(0)));
                assign.setSubj_name(cursor.getString(1));
                assign.setDesc(cursor.getString(2));
                assign.setIsDone(Integer.parseInt(cursor.getString(3)));
                assign.setDeadline(cursor.getString(4));
                assignmentList.add(assign);
            } while (cursor.moveToNext());
        }

        return assignmentList;
    }

    public List<SchoolActivity> getAllActivity() {
        List<SchoolActivity> schoolActivityList = new ArrayList<SchoolActivity>();
        String selectQuery = "SELECT * FROM " + TABLE_SCHOOL_ACTIVITY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                SchoolActivity sc = new SchoolActivity();
                sc.setId(Integer.parseInt(cursor.getString(0)));
                sc.setAct_name(cursor.getString(1));
                sc.setDesc(cursor.getString(2));
                sc.setIsDone(Integer.parseInt(cursor.getString(3)));
                sc.setDeadline(cursor.getString(4));
                schoolActivityList.add(sc);
            } while(cursor.moveToNext());
        }

        return schoolActivityList;
    }

    public List<NoClass> getNoClass() {
        List<NoClass> noClassList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NO_CLASS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                NoClass noClass = new NoClass();
                noClass.setId(Integer.parseInt(cursor.getString(0)));
                noClass.setDay(cursor.getString(1));
                noClass.setDesc(cursor.getString(2));
                noClassList.add(noClass);
            } while(cursor.moveToNext());
        }

        return noClassList;
    }

    public List<Alarm> getAlarm() {
        List<Alarm> alarmList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_ALARM;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Alarm alarm = new Alarm();
                alarm.setId(Integer.parseInt(cursor.getString(0)));
                alarm.setTime(cursor.getString(1));
                alarm.setType(cursor.getString(2));
                alarm.setDay(cursor.getString(3));
                alarmList.add(alarm);
            } while(cursor.moveToNext());
        }

        return alarmList;
    }

    public Alarm getAlarmOn(String day) {

        Alarm alarm = new Alarm();
//for testing muna        String selectQuery = "SELECT * FROM " + TABLE_ALARM + " WHERE " + KEY_DAY + " = '" + day + "' AND " + KEY_ALARM_TYPE + " = 'alarm'";
        String selectQuery = "SELECT * FROM " + TABLE_ALARM + " WHERE " + KEY_ALARM_TYPE + " = 'alarm'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        alarm.setId(Integer.parseInt(cursor.getString(0)));
        alarm.setTime(cursor.getString(1));
        alarm.setType("alarm");
        alarm.setDay(day);

        return alarm;
    }

    public List<String> getIdList(String table) {
        List<String> idList = new ArrayList<>();
        String selectQuery = "Select * from " + table;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()) {
            do {
                idList.add(cursor.getString(0) + " " + cursor.getString(1));
            } while (cursor.moveToNext());
        }

        return idList;
    }

    public List<String> getSubjectName() {
        List<String> subjectList = new ArrayList<>();
        String selectQuery = "Select * from " + TABLE_SUBJECT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()) {
            do {
                subjectList.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        return subjectList;
    }

    public  boolean isNoClass(String day) {

        String selectQuery = "SELECT COUNT(*) FROM " + TABLE_NO_CLASS + " WHERE " + KEY_DAY + " = '" + day + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if (Integer.parseInt(cursor.getString(0)) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public int updateSemesterAlarm(Semester semester) throws ParseException {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_STUDENT_NAME, semester.getStudName());
        values.put(KEY_START_DATE, semester.getStartDateString());
        values.put(KEY_END_DATE, semester.getEndDateString());
        values.put(KEY_DEFAULT_ALARM, semester.getDefaultAlarmString());
        values.put(KEY_DEFAULT_NOTIF, semester.getDefaultNotifString());
        values.put(KEY_ISSET, semester.getIsSetString());

        return db.update(TABLE_SEMESTER, values, KEY_ID + " = ", new String[] {String.valueOf(semester.getID()) });
    }

    public int updateSubject(Subject subject) {
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

        return db.update(TABLE_SUBJECT, values, KEY_ID + " = ", new String[] {String.valueOf(subject.getID()) });
    }

    public int updateAssignment(Assignment assign) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_SUBJECT_NAME, assign.getSubj_name());
        values.put(KEY_DESC, assign.getDesc());
        values.put(KEY_IS_DONE, assign.getIsDone());
        values.put(KEY_DEADLINE, assign.getDeadline());

        return db.update(TABLE_ASSIGNMENT, values, KEY_ID + "= ?", new String[] {String.valueOf(assign.getID()) });
    }

    public int updateActivity(SchoolActivity sc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_ACTIVITY_NAME, sc.getAct_name());
        values.put(KEY_DESC, sc.getDesc());
        values.put(KEY_IS_DONE, sc.getIsDone());
        values.put(KEY_DEADLINE, sc.getDeadline());

        return db.update(TABLE_SCHOOL_ACTIVITY, values, KEY_ID + " = ?", new String[] {String.valueOf(sc.getID()) });
    }

    public void removeSubject(String subjectName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_SUBJECT + " WHERE " + KEY_SUBJECT_NAME + " = '" + subjectName + "'");
    }

    public void removeId(String id,String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + tableName + " WHERE " + KEY_ID + " = " + id);
    }


}
