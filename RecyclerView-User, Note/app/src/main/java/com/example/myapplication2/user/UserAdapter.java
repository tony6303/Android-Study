package com.example.myapplication2.user;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.R;

import java.util.List;

// 3번 상속받기 // ViewHolder 를 먼저 안만들면 상속을 못함!!!!! 순서 지키셈
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private static final String TAG = "UserAdapter";

    // 4번 컬렉션 만들기
    private final List<User> users;

    public UserAdapter(List<User> users) {
        this.users = users;
    }

    // 5번 addItem , removeItem 만들기
    public void addItem(User user) {
        users.add(user);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        users.remove(position);
    }


    // 7번 getView의 역할을 하는녀석
    // 차이점 : ListView 에서는  최초 로딩시에 화면에 나오는 개수만큼만 로딩함. (최초화면에 6개가 필요하다면 getView가 6번 호출됨)
    // 그 다음 스크롤해서 추가로 필요하면 개수 만큼 다시 getView 를 호출함
    // ViewHolder 의 특징
    // onCreateViewHolder 는 해당 액티비티 실행 시에만 호출됨
    // 그 다음 스크롤해서 추가로 필요하면 onBindViewHolder 를 호출함
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");

        // mainActivity에 연결됨 ( RecyclerActivity )
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // xml에 있는 그림이 메모리에 올라오는과정 : inflate
        View view = inflater.inflate(R.layout.user_item, parent, false);
        return new MyViewHolder(view); // ViewHolder에 view를 꼽아서 반환함 ~
        // 하단의 MyViewHolder 생성자로 가보자
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        holder.setItem(users.get(position)); // 데이터를 여기서 넣어줌 (바인딩)
    }

    // 6번 컬렉션 사이즈
    @Override
    public int getItemCount() {
        return users.size();
    }

    // 1번 ViewHolder 만들기 . 내부클래스로 만들어야함!!!
    // 다른데서 안쓰고 나만 쓸거라서 내부클래스임
    // ViewHolder란 : 하나의 View를 갖고있음.  // 위험한점 : 데이터는 없고 View만 있음
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        // 2번 user_item이 가지고 있는 위젯들을 선언
        private TextView tvId;
        private TextView tvUsername;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvUsername = itemView.findViewById(R.id.tv_username);

        }

        public void setItem(User user) {
            tvId.setText(String.valueOf(user.getId()));
            tvUsername.setText(user.getUsername());
        }
    }
}
