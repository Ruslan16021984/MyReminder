package com.gmail.carbit3333333.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.gmail.carbit3333333.MainActivity;
import com.gmail.carbit3333333.R;
import com.gmail.carbit3333333.adapters.TaskAdapter;
import com.gmail.carbit3333333.alarm.AlarmHelper;
import com.gmail.carbit3333333.model.Item;
import com.gmail.carbit3333333.model.ModelTask;
import com.google.android.material.snackbar.Snackbar;


/**
 * Created by Руслан on 29.01.2017.
 */

public abstract class TaskFragment extends Fragment{
    protected RecyclerView recyclerView;
    protected RecyclerView.LayoutManager layoutManager;
    protected TaskAdapter adapter;
    protected AlarmHelper alarmHelper;
    public MainActivity activity;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity()!=null){
            activity = (MainActivity) getActivity();
        }
        alarmHelper = AlarmHelper.getInstance();
        addTaskFromDb();
    }

    public void addTask(ModelTask newTask, boolean saveToDb){
        int position = -1;
        for (int i =0; i < adapter.getItemCount(); i++){
            if (adapter.getItem(i).isTask()){
                ModelTask task = (ModelTask) adapter.getItem(i);
                if (newTask.getDate()< task.getDate()){
                    position = i;
                    break;
                }
            }

        }
        if (position !=-1){
            adapter.addItem(position, newTask);
        }else {
            adapter.addItem(newTask);
        }
        if (saveToDb){
            activity.dbHelper.saveTask(newTask);
        }
    }
    public void removeTaskDialog(final int location) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());

        dialogBuilder.setMessage(R.string.dialog_removing_message);

        Item item = adapter.getItem(location);

        if (item.isTask()) {

            ModelTask removingTask = (ModelTask) item;

            final long timeStamp = removingTask.getTimeStamp();
            final boolean[] isRemoved = {false};

            dialogBuilder.setPositiveButton(R.string.removed, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    adapter.removeItem(location);
                    isRemoved[0] = true;
                    Snackbar snackbar = Snackbar.make(getActivity().findViewById(R.id.coordinator),
                            R.string.removed, Snackbar.LENGTH_LONG);
                    snackbar.setAction(R.string.dialog_cancel, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            addTask(activity.dbHelper.query().getTask(timeStamp), false);
                            isRemoved[0] = false;
                        }
                    });
                    snackbar.getView().addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                        @Override
                        public void onViewAttachedToWindow(View v) {

                        }

                        @Override
                        public void onViewDetachedFromWindow(View v) {
                            if (isRemoved[0]) {
                                alarmHelper.removeAlarm(timeStamp);
                                activity.dbHelper.removeTask(timeStamp);
                            }
                        }
                    });

                    snackbar.show();


                    dialog.dismiss();

                }
            });

            dialogBuilder.setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

        }

        dialogBuilder.show();
    }
    public abstract void addTaskFromDb();
    public abstract void move(ModelTask modelTask);
    public abstract void findTasks(String title);
}
