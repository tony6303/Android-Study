package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
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

        //어댑터 => 내장 어댑터 쓸거임,  특별한 모습의 어댑터를 쓸려면 커스텀 리스트를 만들어야함
        List<String> mid = new ArrayList<>();
        mid.add("가십걸");
        mid.add("덱스터");
        mid.add("왕좌의게임");
        mid.add("브레이킹배드");
        mid.add("배터콜사울");
        mid.add("가십걸2");
        mid.add("덱스터2");
        mid.add("왕좌의게임2");
        mid.add("브레이킹배드2");
        mid.add("배터콜사울2");

        //어댑터는 화면 사이즈와 리스트의 하나의 아이템의 사이즈를 알아야하고 그 다음에 데이터 컬렉션을 알아야 한다.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, mid);
        lvMovie.setAdapter(adapter);


    }

}