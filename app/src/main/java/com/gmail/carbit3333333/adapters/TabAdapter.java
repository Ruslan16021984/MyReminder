package com.gmail.carbit3333333.adapters;

import android.app.Fragment;
import android.app.FragmentManager;


import androidx.legacy.app.FragmentStatePagerAdapter;

import com.gmail.carbit3333333.fragments.CurrentTaskFragment;
import com.gmail.carbit3333333.fragments.DoneTaskFragment;


/**
 * Created by Руслан on 28.01.2017.
 */

public class TabAdapter extends FragmentStatePagerAdapter {
    private int numberOfTabs;
    public static final int CURRENT_TASK_FRAGMENT_POSITION = 0;
    public static final int DONE_TASK_FRAGMENT_POSITION = 1;

    private CurrentTaskFragment currentTaskFragment;
    private DoneTaskFragment doneTaskFragment;

    public TabAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
        currentTaskFragment = new CurrentTaskFragment();
        doneTaskFragment = new DoneTaskFragment();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return currentTaskFragment;
            case 1:
                return doneTaskFragment;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
