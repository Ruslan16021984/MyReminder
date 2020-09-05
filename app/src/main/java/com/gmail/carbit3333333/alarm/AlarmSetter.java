package com.gmail.carbit3333333.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.gmail.carbit3333333.database.DbHelper;
import com.gmail.carbit3333333.model.ModelTask;

import java.util.ArrayList;
import java.util.List;

public class AlarmSetter extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        DbHelper dbHelper = new DbHelper(context);
        AlarmHelper.getInstance().init(context);
        AlarmHelper alarmHelper = AlarmHelper.getInstance();
        List<ModelTask> tasks = new ArrayList<>();
        tasks.addAll(dbHelper.query().getTask(DbHelper.SELECTION_STATUS + " OR "
                + DbHelper.SELECTION_STATUS, new String[]{Integer.toString(ModelTask.STATUS_CURRENT),
                Integer.toString(ModelTask.STATUS_OVERDUE)}, DbHelper.TASK_DATE_COLUMN));
        for (ModelTask task:
             tasks) {
            if (task.getDate() !=0){
                alarmHelper.setAlarm(task);
            }
        }
    }
}
