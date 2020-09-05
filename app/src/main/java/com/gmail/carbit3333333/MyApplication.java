package com.gmail.carbit3333333;

import android.app.Application;

public class MyApplication extends Application {
    private static boolean activityVisible;
    public static boolean isActivityVisible(){
        return activityVisible;
    }
    public static void activityResumed(){
        activityVisible = true;
    }
    public static void activityPaused(){
        activityVisible = false;
    }
}
