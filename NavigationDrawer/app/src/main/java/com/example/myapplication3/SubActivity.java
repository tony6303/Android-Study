package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

public class SubActivity extends AppCompatActivity {

    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        nv = findViewById(R.id.navi_view);
        NavigationViewHelper.enable(SubActivity.this, nv);
    }
}