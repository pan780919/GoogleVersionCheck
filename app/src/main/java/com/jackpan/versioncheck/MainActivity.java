package com.jackpan.versioncheck;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.jackpan.googleversioncheck.DoneListener;
import com.jackpan.googleversioncheck.GoogleVersionCheck;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GoogleVersionCheck googleVersionCheck = new GoogleVersionCheck(this);
        Log.d(TAG, "onCreate: "+GoogleVersionCheck.getUrl());
//        GoogleVersionCheck.checkOnce(this, new DoneListener() {
//
//            @Override
//            public void onError() {
//
//            }
//
//            @Override
//            public void onRequesting() {
//
//            }
//
//            @Override
//            public void onHasNewVersion() {
//
//            }
//
//            @Override
//            public void onLatestVersion() {
//
//            }
//
//            @Override
//            public void onConnectionFailed() {
//
//            }
//        });
//        

        }

}
