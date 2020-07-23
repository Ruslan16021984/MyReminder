package com.gmail.carbit3333333.fragments;

import android.app.Fragment;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.gmail.carbit3333333.MainActivity;
import com.gmail.carbit3333333.adapters.TaskAdapter;
import com.gmail.carbit3333333.model.ModelTask;


/**
 * Created by Руслан on 29.01.2017.
 */

public abstract class TaskFragment extends Fragment{
    protected RecyclerView recyclerView;
    protected RecyclerView.LayoutManager layoutManager;
    protected TaskAdapter adapter;
    public MainActivity activity;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity()!=null){
            activity = (MainActivity) getActivity();
        }
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
    public abstract void addTaskFromDb();
    public abstract void move(ModelTask modelTask);
}
