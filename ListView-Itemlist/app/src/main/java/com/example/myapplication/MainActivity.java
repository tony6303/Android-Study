package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Context mContext = MainActivity.this; // Context = 내 자신의 정보를 들고있는 녀석

    private ListView lvMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // xml에 있는 그림이 메모리에 올라오는과정 : inflate

        lvMovie = findViewById(R.id.lv_movie);

    List<Movie> movies = new ArrayList<>();

    for (int i=0; i<20; i++){
        movies.add(new Movie(i, "제목" +i , "서브제목"+ i));
    }

    ItemAdapter adapter = new ItemAdapter(movies);

    lvMovie.setAdapter(adapter);
    }

}