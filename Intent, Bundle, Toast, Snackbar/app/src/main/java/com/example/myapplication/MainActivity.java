package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Context mContext = MainActivity.this; // Context = 내 자신의 정보를 들고있는 녀석
    private FloatingActionButton fabRoute;
    private ConstraintLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // xml에 있는 그림이 메모리에 올라오는과정 : inflate

        mainLayout = findViewById(R.id.main_layout);
        fabRoute = findViewById(R.id.fab_route);
        User user = new User();
        user.setId(1);
        user.setUsername("ssar");
        user.setPassword("1234");



        //함수를 하나만 가지고있을 때 만 람다사용가능, 그 함수의 리턴값을 꼭 확인해서 주의해서 사용!
        fabRoute.setOnClickListener(v -> {
            //1. Intent 사용법 1번 (현재 내 화면, 이동할 화면)
            //2. (현재 내 화면, 내부앱의 이동할 화면)   내부앱 이란 -> 전화, 문자, 달력 등등 '실제 앱'

            //(2번) 다른 앱으로 이동 = (상대방 앱의 키, 데이터) ,,,,,, 계속 뒤로가기를 누르면 결국 Main화면이 나와서 내 위치는 필요없다.
//            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:01023231212"));  // 생성자

            //(1번)
            Intent intent = new Intent(mContext, SubActivity.class);
            intent.putExtra("user", user); // 객체 바로넘기기

            Bundle bundle = new Bundle();
            bundle.putSerializable("user", user); //번들에 담아서 넘기기
            intent.putExtra("userBundle", bundle);

//            startActivity(intent);
            startActivityForResult(intent,300);
        });

    }

    // finish() 될때 콜백 되는 함수
    // setResult() 함수의 파라메터가 전달됨
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "onActivityResult: 실행됨");
        Log.d(TAG, "requestCode:: "+ requestCode);
        Log.d(TAG, "resultCode: "+ resultCode);

        if(requestCode == 300){ // 서브 액티비티에서 돌아왔다
            if(resultCode == 1){ // 로직이 성공했다
                Toast.makeText(mContext, "인증 성공" + data.getStringExtra("auth"), Toast.LENGTH_SHORT).show();
                //Snackbar.make(mainLayout, "인증 성공", BaseTransientBottomBar.LENGTH_LONG);
            }else{ //로직 실패
                Toast.makeText(mContext, "인증 실패", Toast.LENGTH_SHORT).show();
            }
        }
    }
}