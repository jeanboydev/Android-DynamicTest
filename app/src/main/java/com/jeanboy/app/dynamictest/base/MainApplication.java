package com.jeanboy.app.dynamictest.base;

import android.app.Application;
import android.content.Context;

import com.jeanboy.app.dynamictest.patch.AndFixPatchManager;


public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndFixPatchManager.getInstance().initPatch(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
