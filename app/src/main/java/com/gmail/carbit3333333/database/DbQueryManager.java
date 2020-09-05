package com.gmail.carbit3333333.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gmail.carbit3333333.model.ModelTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Руслан on 04.02.2017.
 */

public class DbQueryManager {
    private SQLiteDatabase database;

    public DbQueryManager(SQLiteDatabase database) {
        this.database = database;
    }

    public List<ModelTask> getTask(String selection, String[] selectionArgs, String orderBy) {
        List<ModelTask> tasks = new ArrayList<>();

        Cursor c = database.query(DbHelper.TASKS_TABLE, null, selection, selectionArgs, null, null, orderBy);
        if (c.moveToFirst()) {
            do {
                String title = c.getString(c.getColumnIndex(DbHelper.TASK_TITLE_COLUMN));
                long date = c.getLong(c.getColumnIndex(DbHelper.TASK_DATE_COLUMN));
                int priority = c.getInt(c.getColumnIndex(DbHelper.TASK_PRIORITY_COLUMN));
                int status = c.getInt(c.getColumnIndex(DbHelper.TASK_STATUS_COLUMN));
                long timeStamp = c.getLong(c.getColumnIndex(DbHelper.TASK_TIME_STAMP_COLUMN));

                ModelTask modelTask = new ModelTask(title, date, priority, status, timeStamp);
                tasks.add(modelTask);
            } while (c.moveToNext());
        }
        c.close();
        return tasks;
    }
    public ModelTask getTask(long timeStamp) {
        ModelTask modelTask = null;
        Cursor cursor = database.query(DbHelper.TASKS_TABLE, null, DbHelper.SELECTION_TIME_STAMP,
                new String[]{Long.toString(timeStamp)}, null, null, null);

        if (cursor.moveToFirst()) {
            String title = cursor.getString(cursor.getColumnIndex(DbHelper.TASK_TITLE_COLUMN));
            long date = cursor.getLong(cursor.getColumnIndex(DbHelper.TASK_DATE_COLUMN));
            int priority = cursor.getInt(cursor.getColumnIndex(DbHelper.TASK_PRIORITY_COLUMN));
            int status = cursor.getInt(cursor.getColumnIndex(DbHelper.TASK_STATUS_COLUMN));

            modelTask = new ModelTask(title, date, priority, status, timeStamp);
        }
        cursor.close();

        return modelTask;

    }
}
