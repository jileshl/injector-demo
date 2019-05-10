package com.ridlr;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * It is a single listener that will be called back whenever an activity lifecycle method
 * is called on any activity in our application.
 *
 * Created by riteshg on 27/7/16.
 */
public class Foreground implements Application.ActivityLifecycleCallbacks {

    // To differentiate the transitions between
    // 1. Activity 1 onPause() to Activity2 onResume()
    // 2. From foreground to background - our interest.
    public static final long CHECK_DELAY = 1 * 1000;
    public static final String TAG = Foreground.class.getName();

    /**
     * Listener Interface to get notified of foreground/background state transitions.
     */
    public interface Listener {

        void onBecameForeground();
        void onBecameBackground();
    }

    private static Foreground instance;

    private boolean foreground = false;
    private boolean paused = true;//to track whether we're paused or not.
    private Handler handler = new Handler();
    private List<Listener> listeners = new CopyOnWriteArrayList<Listener>();
    private Runnable check;//a reference to the Runnable that is posted to the main thread, so that it can be canceled when necessary.

    /**
     * Hook the instance into the activity lifecycle methods by providing an
     * instance of Application class for our application.
     *
     * @param application
     * @return
     */
    public static Foreground init(Application application){
        if (instance == null) {
            instance = new Foreground();
            // Application.registerActivityLifecycleCallbacks is only available from API-level 14 onwards.
            application.registerActivityLifecycleCallbacks(instance);
        }
        return instance;
    }

    public static Foreground get(Application application){
        if (instance == null) {
            init(application);
        }
        return instance;
    }

    public static Foreground get(Context ctx){
        if (instance == null) {
            Context appCtx = ctx.getApplicationContext();
            if (appCtx instanceof Application) {
                init((Application)appCtx);
            }
            throw new IllegalStateException(
                    "Foreground is not initialised and " +
                            "cannot obtain the Application object");
        }
        return instance;
    }

    public static Foreground get(){
        if (instance == null) {
            throw new IllegalStateException(
                    "Foreground is not initialised - invoke " +
                            "at least once with parameterised init/get");
        }
        return instance;
    }

    public boolean isForeground(){
        return foreground;
    }

    public boolean isBackground(){
        return !foreground;
    }

    public void addListener(Listener listener){
        listeners.add(listener);
    }

    public void removeListener(Listener listener){
        listeners.remove(listener);
    }

    @Override
    public void onActivityResumed(final Activity activity) {
        paused = false;
        final boolean wasBackground = !foreground;
        foreground = true;

        if (check != null)
            handler.removeCallbacks(check);

        /**
         * The callback is invoked from the lifecycle callbacks and therefore on the main thread.
         */
        handler.postDelayed(check = new Runnable() {
            @Override
            public void run() {

                if (wasBackground) {
                    Log.i(TAG, "went foreground");

                    for (Listener l : listeners) {
                        try {
                            l.onBecameForeground();
                        } catch (Exception exc) {
                            Log.e(TAG, "Listener threw exception!", exc);
                        }
                    }
                } else {
                    Log.i(TAG, "still foreground");
                }

            }
        }, CHECK_DELAY);
    }

    @Override
    public void onActivityPaused(final Activity activity) {

        paused = true;

        if (check != null)
            handler.removeCallbacks(check);

        /**
         * The callback is invoked from the lifecycle callbacks and therefore on the main thread.
         */
        handler.postDelayed(check = new Runnable(){
            @Override
            public void run() {
                if (foreground && paused) {
                    foreground = false;
                    Log.i(TAG, "went background");

                    // Start sync service when app goes in the background.

                    for (Listener l : listeners) {
                        try {
                            l.onBecameBackground();
                        } catch (Exception exc) {
                            Log.e(TAG, "Listener threw exception!", exc);
                        }
                    }
                } else {
                    Log.i(TAG, "still foreground");
                }
            }
        }, CHECK_DELAY);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {}

    @Override
    public void onActivityStarted(Activity activity) {}

    @Override
    public void onActivityStopped(Activity activity) {}

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}

    @Override
    public void onActivityDestroyed(Activity activity) {}

}
