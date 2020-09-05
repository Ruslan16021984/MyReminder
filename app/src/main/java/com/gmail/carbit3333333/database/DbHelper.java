package com.gmail.carbit3333333.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.gmail.carbit3333333.model.ModelTask;

/**
 * Created by Руслан on 04.02.2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "reminder_database1";

    public static final String TASKS_TABLE = "tasks_table";

    public static final String TASK_TITLE_COLUMN = "task_title";
    public static final String TASK_DATE_COLUMN = "task_date";
    public static final String TASK_PRIORITY_COLUMN = "task_priority";
    public static final String TASK_STATUS_COLUMN = "task_status";
    public static final String TASK_TIME_STAMP_COLUMN = "task_time_stamp";

    public static final String SELECTION_STATUS = DbHelper.TASK_STATUS_COLUMN + " = ?";
    public static final String SELECTION_TIME_STAMP = TASK_TIME_STAMP_COLUMN + " = ?";
    public static final String SELECTION_LIKE_TITLE = TASK_TITLE_COLUMN + " LIKE ?";
    private DbQueryManager queryManager;
    private DbUpdateManager updateManager;
    private static final String TASKS_TABLE_CREATE_SCRIPT = "CREATE TABLE "
            + TASKS_TABLE + " (" + BaseColumns._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TASK_TITLE_COLUMN + " TEXT NOT NULL, "
            + TASK_DATE_COLUMN + " LONG, " + TASK_PRIORITY_COLUMN + " INTEGER, "
            + TASK_STATUS_COLUMN + " INTEGER, " + TASK_TIME_STAMP_COLUMN + " LONG);";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        queryManager = new DbQueryManager(getReadableDatabase());
        updateManager = new DbUpdateManager(getWritableDatabase());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TASKS_TABLE_CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE " + TASKS_TABLE);
        onCreate(db);
    }

    public void saveTask(ModelTask task) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASK_TITLE_COLUMN, task.getTitle());
        contentValues.put(TASK_DATE_COLUMN, task.getDate());
        contentValues.put(TASK_STATUS_COLUMN, task.getStatus());
        contentValues.put(TASK_PRIORITY_COLUMN, task.getPriority());
        contentValues.put(TASK_TIME_STAMP_COLUMN, task.getTimeStamp());
        getWritableDatabase().insert(TASKS_TABLE, null, contentValues);
    }
    public void removeTask(long timeStamp) {
        getWritableDatabase().delete(TASKS_TABLE, SELECTION_TIME_STAMP, new String[]{Long.toString(timeStamp)});
    }
    public DbQueryManager query(){
        return queryManager;
    }
    public DbUpdateManager update(){
        return updateManager;
    }
}
