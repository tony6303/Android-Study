package com.example.myapplication2.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication2.R;

import java.util.ArrayList;
import java.util.List;

public class UserRecyclerActivity extends AppCompatActivity {

    private RecyclerView rvUserList;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        List<User> users = new ArrayList<>();
        for(int i=0; i<50; i++){
            users.add(new User(i,"user"+i));
        }

        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvUserList = findViewById(R.id.rv_user_list);
        rvUserList.setLayoutManager(manager); // RecyclerView 에 매니저(manager) 세팅

        userAdapter = new UserAdapter(users); // userAdapter 에 users 세팅
        rvUserList.setAdapter(userAdapter); // RecyclerVide 에 userAdapter 세팅팅



    }
}