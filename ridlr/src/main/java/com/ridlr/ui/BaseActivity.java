package com.ridlr.ui;

import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;




public class BaseActivity extends Activity {
    //	Action Bar Crash
//	http://stackoverflow.com/questions/23586440/nullpointerexception-actionbarimplics-appcompat-longpress
    private static final String TAG = "BaseActivity";

    public static AtomicInteger notificationCounter = new AtomicInteger();
    public static List<Notification> notif = new ArrayList<Notification>();
    protected int[] reusable_position = new int[2];
    protected ActivityState state;
    //Location params
    protected String locationOfUser = null;



//    private UiLifecycleHelper uiHelper;
    private PendingAction pendingAction = PendingAction.NONE;
    private String hoponPostMessage;


   /* @Inject
    public BaseActivity(Provider<LoggerComponent.Builder> requestLoggerProvider) {
        super(requestLoggerProvider);
    }*/

    public String getHoponPostMessage() {
        return hoponPostMessage;
    }

    public void setHoponPostMessage(String hoponPostMessage) {
        this.hoponPostMessage = hoponPostMessage;
    }

    public String getLocationOfUser() {
        return locationOfUser;
    }

    public void setLocationOfUser(String locationOfUser) {
        this.locationOfUser = locationOfUser;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    protected void init(Bundle savedInstanceState) {

    }

    private void wizrocketEvent() {

    }

    protected void dinit() {
        //LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001) {

        }

    }


    @Override
    protected void onStop() {
        super.onStop();
        //Branch.getInstance(getApplicationContext()).closeSession();

    }

    @Override
    public void onStart() {
        super.onStart();
//        Branch branch = Branch.getInstance();
//
//        branch.initSession(new Branch.BranchReferralInitListener() {
//            @Override
//            public void onInitFinished(JSONObject referringParams, BranchError error) {
//                if (error == null) {
//                    // params are the deep linked params associated with the link that the user clicked -> was re-directed to this app
//                    // params will be empty if no data found
//                    // ... insert custom logic here ...
//                } else {
//                    Log.i("MyApp", error.getMessage());
//                }
//            }
//        }, this.getIntent().getData(), this);
    }

    @Override
    public void onNewIntent(Intent intent) {
        this.setIntent(intent);
    }

    //	protected String currentlyHoppedOnRouteId;
    protected enum ActivityState {
        SLIDING, OUT, IN
    }

    protected enum widget_active {
        ONLY_MAP, MAP, TOP, BOTTOM
    }

    private enum PendingAction {
        NONE, POST_PHOTO, POST_STATUS_UPDATE
    }



    /*************************************************************************/
//    public static boolean isAppWentToBg = false;
//    public static boolean isWindowFocused = false;
//    public static boolean isBackPressed = false;
//
//    @Override
//    public void onBackPressed() {
//        isBackPressed = true;
////        super.onBackPressed();
//        finish();
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        if (isAppWentToBg) {
//            isAppWentToBg = false;
//        }
//    }
//
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//
//        isWindowFocused = hasFocus;
//
//        if (isBackPressed && !hasFocus) {
//            isBackPressed = false;
//            isWindowFocused = true;
//        }
//
//        super.onWindowFocusChanged(hasFocus);
//    }
//
//
//
//    public void applicationdidenterbackground() {
//        if (!isWindowFocused) {
//            isAppWentToBg = true;
//            Toast.makeText(getApplicationContext(),
//                    "App is Going to Background", Toast.LENGTH_SHORT).show();
//        }
//    }
}
