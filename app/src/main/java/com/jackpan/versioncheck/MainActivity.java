package com.jackpan.versioncheck;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jackpan.googleversioncheck.DoneListener;
import com.jackpan.googleversioncheck.GoogleVersionCheck;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GoogleVersionCheck googleVersionCheck = new GoogleVersionCheck(this);
        GoogleVersionCheck.checkOnce(this, new DoneListener() {

            @Override
            public void onError() {

            }

            @Override
            public void onRequesting() {

            }

            @Override
            public void onHasNewVersion() {

            }

            @Override
            public void onLatestVersion() {

            }

            @Override
            public void onConnectionFailed() {

            }
        });


    }

}
