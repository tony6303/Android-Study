package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SubActivity extends AppCompatActivity {

    private static final String TAG = "SubActivity";
    private FloatingActionButton fabPop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        // 여러 액티비티에서 putExtra를 실행 할 것이다.

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("userBundle");

        Log.d(TAG, "userBundle: "+ bundle.getSerializable("user")); // 번들로 받음
        Log.d(TAG, "user: "+ intent.getSerializableExtra("user")); // 인텐트로 받음

        fabPop = findViewById(R.id.fab_pop);
        fabPop.setOnClickListener(v -> {

            Intent newIntent = new Intent();
            newIntent.putExtra("auth", "ok");
            setResult(1, newIntent);

            finish();
        });
    }
}
