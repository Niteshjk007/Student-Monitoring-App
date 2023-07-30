package com.android.attendance.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBAdapter1 extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "Attendance";

    // Table name
    private static final String MARKS_TABLE = "marks_table";

    // Common column names
    private static final String KEY_ID = "id";

    // Marks table column names
    private static final String KEY_STUDENT_NAME = "student_name";
    private static final String KEY_MARKS = "marks";
    private static final String KEY_BRANCH = "branch";
    private static final String KEY_YEAR = "year";
    private static final String KEY_SUBJECT = "subject";

    public DBAdapter1(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creating marks table
        String createMarksTableQuery = "CREATE TABLE " + MARKS_TABLE + "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_STUDENT_NAME + " TEXT, " +
                KEY_MARKS + " TEXT, " +
                KEY_BRANCH + " TEXT, " +
                KEY_YEAR + " TEXT, " +
                KEY_SUBJECT + " TEXT" + ")";
        db.execSQL(createMarksTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + MARKS_TABLE);

        // Create table again
        onCreate(db);
    }

    public void addMarks(String studentName, String marks, String branch, String year, String subject) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_STUDENT_NAME, studentName);
        values.put(KEY_MARKS, marks);
        values.put(KEY_BRANCH, branch);
        values.put(KEY_YEAR, year);
        values.put(KEY_SUBJECT, subject);

        db.insert(MARKS_TABLE, null, values);
        db.close();
    }

    public ArrayList<String> getMarksByDepartmentYearSubject(String branch, String year, String subject) {
        ArrayList<String> marksList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + MARKS_TABLE +
                " WHERE " + KEY_BRANCH + "='" + branch + "' AND " +
                KEY_YEAR + "='" + year + "' AND " +
                KEY_SUBJECT + "='" + subject + "'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String studentName = cursor.getString(1);
                String marks = cursor.getString(2);

                String record = "Student: " + studentName + ", Marks: " + marks;
                marksList.add(record);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return marksList;
    }
}
