package com.gmail.carbit3333333.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.gmail.carbit3333333.model.ModelTask;

/**
 * Created by Руслан on 04.02.2017.
 */

public class DbUpdateManager {
private SQLiteDatabase database;

    public DbUpdateManager(SQLiteDatabase database) {
        this.database = database;
    }
    public void title(long timeStamp, String title){
        update(DbHelper.TASK_TITLE_COLUMN, timeStamp, title);
    }
    public void date(long timeStamp, long date){
        update(DbHelper.TASK_DATE_COLUMN, timeStamp, date);
    }
    public void priority(long timeStamp, int proirity){
        update(DbHelper.TASK_PRIORITY_COLUMN, timeStamp, proirity);
    }
    public void status(long timeStamp, int status){
        update(DbHelper.TASK_STATUS_COLUMN,timeStamp, status);
    }
    public void task(ModelTask task){
        title(task.getTimeStamp(), task.getTitle());
        date(task.getTimeStamp(), task.getDate());
        priority(task.getTimeStamp(), task.getPriority());
        status(task.getTimeStamp(), task.getStatus());
    }


    private void update(String column, long key, String value) {
        ContentValues cv = new ContentValues();
        cv.put(column, value);
        database.update(DbHelper.TASKS_TABLE, cv, DbHelper.TASK_TIME_STAMP_COLUMN + " = " + key, null);
    }
    private void update (String column, long key, long value) {
        ContentValues cv = new ContentValues();
        cv.put(column, value);
        database.update(DbHelper.TASKS_TABLE, cv, DbHelper.TASK_TIME_STAMP_COLUMN + " = " + key, null);
    }
}
