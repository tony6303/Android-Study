package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button mBtnAdd, mBtnMinus;
    private TextView mTvNum;
    private Integer num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // xml에 있는 그림이 메모리에 올라오는과정 : inflate

        init();
        initSetting();
        initListener();

    }

    private void initSetting() {
        num = 1;
        mTvNum.setText(num.toString());
    }

    private void init() {
        mBtnAdd = findViewById(R.id.btn_add);
        mBtnMinus = findViewById(R.id.btn_minus);
        mTvNum = findViewById(R.id.tv_num);
    }

    private void initListener() {// 람다표현식 : 어짜피 들어올수 있는 함수는 OnClickListener
        // 안드로이드 OS가 이 함수를 실행함
        mBtnAdd.setOnClickListener(v -> {
            num = num + 1 ;
            mTvNum.setText(num.toString());
        });

        mBtnMinus.setOnClickListener(v -> {
            num = num - 1 ;
            mTvNum.setText(num.toString());
        });
    }

}