package com.jeanboy.app.dynamictest.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jeanboy.app.dynamictest.R;
import com.jeanboy.app.dynamictest.utils.BugUtil;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {

    private TextView tv_display;

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

        String apkPath = getExternalCacheDir().getAbsolutePath() + "/bundle.apk";
        Log.e("====apkPath===", apkPath);
        loadApk(apkPath);
    }

    private void loadApk(String apkPath) {
        File optDir = getDir("opt", MODE_PRIVATE);
        DexClassLoader classLoader = new DexClassLoader(apkPath, optDir.getAbsolutePath(), null, this.getClassLoader());
        try {
            Class<?> clazz = classLoader.loadClass("com.jeanboy.app.bundle.BundleUtil");
            if (clazz != null) {
                Object instance = clazz.newInstance();
                Method method = clazz.getMethod("printLog");
                method.invoke(instance);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void toHotFix(View view) {
    }

    public void toUpdate(View view) {
        tv_display.setText(BugUtil.getDisplayString());
    }
}
