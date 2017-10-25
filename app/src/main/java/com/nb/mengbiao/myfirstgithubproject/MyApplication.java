package com.nb.mengbiao.myfirstgithubproject;

import android.app.Application;
import android.content.Context;

/**
 * Created by mengbiao on 2017/10/24.
 */

public class MyApplication extends Application {
    private static Context mcontext;

    @Override
    public void onCreate() {
        super.onCreate();
        mcontext = getApplicationContext();
    }

    public static Context getInstance() {
        return mcontext;
    }
}
