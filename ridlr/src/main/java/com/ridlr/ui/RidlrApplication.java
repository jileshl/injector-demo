package com.ridlr.ui;

import android.support.multidex.MultiDexApplication;

public class RidlrApplication extends MultiDexApplication /*, HasDispatchingActivityInjector */ {

    public static final String TAG = "RidlrApplication";

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
