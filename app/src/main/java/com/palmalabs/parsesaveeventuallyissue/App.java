package com.palmalabs.parsesaveeventuallyissue;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "ipMq4c6JIWXtIPE8tRB6PJq1vTAisjUmgVeNYbF9",
                "HoN1G8MWV7VSxBXFgLswqw2xAz41dDctLlhUearj");
        Parse.setLogLevel(Parse.LOG_LEVEL_VERBOSE);
    }
}
