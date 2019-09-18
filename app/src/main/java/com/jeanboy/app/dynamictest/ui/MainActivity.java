package com.jeanboy.app.dynamictest.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jeanboy.app.dynamictest.R;

public class MainActivity extends AppCompatActivity {

    private TextView tv_display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_display = findViewById(R.id.tv_display);


        ClassLoader classLoader = getClassLoader();

        if (classLoader != null) {
            Log.e("======", "classloader:" + classLoader.toString());
            while (classLoader.getParent() != null) {
                classLoader = classLoader.getParent();
                Log.e("======", "classloader:" + classLoader.toString());
            }
        }
    }

    public void toHotFix(View view) {
    }

    public void toUpdate(View view) {
        tv_display.setText("这是更新的内容！！");
    }
}
