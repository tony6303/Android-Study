package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapplication2.note.NoteAdapter;
import com.example.myapplication2.note.NoteRecyclerActivity;
import com.example.myapplication2.user.UserRecyclerActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import info.androidhive.fontawesome.FontDrawable;
import info.androidhive.fontawesome.FontTextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private FloatingActionButton fab ,fab2;
    private FontTextView ftv;
    private ImageView iv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ftv = findViewById(R.id.ftv);
        iv1 = findViewById(R.id.iv_1);
        fab = findViewById(R.id.fab);
        fab2 = findViewById(R.id.fab2);

//        FontDrawable drawable = new FontDrawable(this, R.string.fa_heart, false, false );
//        drawable.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark));
        FontDrawable drawable = new FontDrawable(this, R.string.fa_paper_plane_solid, true, false);
        // white color to icon
        drawable.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        fab.setImageDrawable(drawable);

        Intent intent = new Intent(this, UserRecyclerActivity.class);

        fab.setOnClickListener(v -> {
            this.startActivity(intent);
        });


        Intent intent2 = new Intent(this, NoteRecyclerActivity.class);

        fab2.setOnClickListener(v -> {
            this.startActivity(intent2);
        });

        Glide
                .with(MainActivity.this)
                .load("https://picsum.photos/200/300")
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(iv1);


    }
}