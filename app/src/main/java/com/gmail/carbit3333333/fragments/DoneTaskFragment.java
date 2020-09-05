package com.gmail.carbit3333333.fragments;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.carbit3333333.R;
import com.gmail.carbit3333333.adapters.DoneTaskAdapter;
import com.gmail.carbit3333333.database.DbHelper;
import com.gmail.carbit3333333.model.ModelTask;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoneTaskFragment extends TaskFragment {


    public DoneTaskFragment() {
        // Required empty public constructor
    }
   OnTaskRestoreListener onTaskRestoreListener;
    public interface OnTaskRestoreListener{
        void onTaskTestore(ModelTask modelTask);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onTaskRestoreListener = (OnTaskRestoreListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + " must implement OnTaskRestoreListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_done_task, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.rvDoneTasks);

        layoutManager = new CustomLinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new DoneTaskAdapter(this);
        recyclerView.setAdapter(adapter);

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void addTaskFromDb() {
        adapter.removeAllItems();
        List<ModelTask> tasks = new ArrayList<>();
        tasks.addAll(activity.dbHelper.query().getTask(DbHelper.SELECTION_STATUS,
                new String[]{Integer.toString(ModelTask.STATUS_DONE)}, DbHelper.TASK_DATE_COLUMN));
        for (int i = 0; i < tasks.size(); i++) {
            addTask(tasks.get(i), false);
        }
    }

    @Override
    public void move(ModelTask modelTask) {
        if (modelTask.getDate() !=0){
            alarmHelper.setAlarm(modelTask);
        }
        onTaskRestoreListener.onTaskTestore(modelTask);
    }

    @Override
    public void findTasks(String title) {
        adapter.removeAllItems();
        List<ModelTask> tasks = new ArrayList<>();
        tasks.addAll(activity.dbHelper.query().getTask(DbHelper.SELECTION_LIKE_TITLE +" AND " + DbHelper.SELECTION_STATUS,
                new String[]{"%"+ title + "%", Integer.toString(ModelTask.STATUS_DONE)}, DbHelper.TASK_DATE_COLUMN));
        for (int i = 0; i < tasks.size(); i++) {
            addTask(tasks.get(i), false);
        }

    }
}
