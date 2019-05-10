package com.ridlr.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.example.library.PlusOneFragment;
import com.ridlr.R;


public class MainActivity extends AppCompatActivity {
    private static final int CONTENT_VIEW_ID = 10101010;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FrameLayout frameLayout = findViewById(R.id.container);
        frameLayout.setId(CONTENT_VIEW_ID);
        Fragment newFragment = new PlusOneFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(CONTENT_VIEW_ID, newFragment).commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, requestCode, data);
        if (requestCode == 10000) {
            switch (resultCode) {
                case RESULT_OK:
                    Snackbar.make(findViewById(android.R.id.content), "RESULT OK", Snackbar.LENGTH_LONG).show();
                    break;
                case RESULT_CANCELED:
                    Snackbar.make(findViewById(android.R.id.content), "Cancelled by user", Snackbar.LENGTH_LONG).show();
                    break;
                case RESULT_FIRST_USER:
                    Snackbar.make(findViewById(android.R.id.content), "ResultFirst User", Snackbar.LENGTH_LONG).show();
                    break;
            }
        }
    }

}
