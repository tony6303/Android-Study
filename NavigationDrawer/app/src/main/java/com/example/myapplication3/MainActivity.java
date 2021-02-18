package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private DrawerLayout drawer;
    private NavigationView nv;
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.drawer);
        btn1 = findViewById(R.id.btn1);
        nv = findViewById(R.id.navi_view);

        btn1.setOnClickListener(v -> {
            drawer.openDrawer(GravityCompat.START);
        });
        nv = findViewById(R.id.navi_view);

        Log.d(TAG, "onCreate: 나실행됨");
        NavigationViewHelper.enable(MainActivity.this, nv);
    }

}