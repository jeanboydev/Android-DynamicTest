package com.jeanboy.app.dynamictest.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jeanboy.app.dynamictest.R;
import com.jeanboy.app.dynamictest.patch.TinkerManager;
import com.jeanboy.app.dynamictest.utils.BugUtil;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private TextView tv_display;

    private static final String FILE_STUFFIX = ".apk";
    // /storage/emulated/0/Android/data/com.jeanboy.app.dynamictest/cache/patch/
    private String patchDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_display = findViewById(R.id.tv_display);


//        ClassLoader classLoader = getClassLoader();
//
//        if (classLoader != null) {
//            Log.e("======", "classloader:" + classLoader.toString());
//            while (classLoader.getParent() != null) {
//                classLoader = classLoader.getParent();
//                Log.e("======", "classloader:" + classLoader.toString());
//            }
//        }

        patchDir = getExternalCacheDir().getAbsolutePath() + "/patch/";
        Log.e("=====",patchDir);
        File file = new File(patchDir);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public void toHotFix(View view) {

        String path = patchDir.concat("test").concat(FILE_STUFFIX);
        Log.e("===path==",path);
        TinkerManager.loadPatch(path);
    }

    public void toUpdate(View view) {
        tv_display.setText(BugUtil.getDisplayString());
    }
}
