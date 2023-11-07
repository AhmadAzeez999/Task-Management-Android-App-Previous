package com.example.finalmobileappproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.CheckBox;

import androidx.annotation.Nullable;

public class DBManager extends SQLiteOpenHelper {
    private static String dbname = "reminder";         //Table  name to store reminders in sqllite

    public DBManager(@Nullable Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "create table tbl_reminder(id integer primary key autoincrement,title text,date text,time text)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String query = "DROP TABLE IF EXISTS tbl_reminder";
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);

    }

    public String addReminder(String title, String date, String time)
    {
        SQLiteDatabase database = this.getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("date", date);
        contentValues.put("time", time);

        float result = database.insert("tbl_reminder", null, contentValues);
        database.close();
        if (result == -1) {
            return "Failed";
        } else {
            return "Successfully added";
        }
    }

    public String deleteReminder(String title)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        float result = db.delete("tbl_reminder", "title=?", new String[]{title});
        db.close();
        if (result == -1) {
            return "Failed";
        } else {
            return "Successfully removed";
        }
    }

    public Cursor readAllReminders() {
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "select * from tbl_reminder order by id desc";
        Cursor cursor = database.rawQuery(query, null);
        return cursor;
    }
}
