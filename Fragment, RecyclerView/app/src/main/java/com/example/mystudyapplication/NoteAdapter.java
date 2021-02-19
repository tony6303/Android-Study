package com.example.mystudyapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder>{

    private static final String TAG = "NoteAdapter";

    // 4번 컬렉션 만들기
    private final List<Note> notes;

    public NoteAdapter(List<Note> notes) {
        this.notes = notes;
    }

    // 5번 addItem , removeItem 만들기
    public void addItem(Note note){
        notes.add(note);
        notifyDataSetChanged();
    }

    public void removeItem(int position){
        notes.remove(position);
        notifyDataSetChanged();
    }


    // 7번 getView의 역할을 하는녀석
    // 차이점 : ListView 에서는  최초 로딩시에 화면에 나오는 개수만큼만 로딩함. (최초화면에 6개가 필요하다면 getView가 6번 호출됨)
    // 그 다음 스크롤해서 추가로 필요하면 개수 만큼 다시 getView 를 호출함
    // ViewHolder 의 특징
    // onCreateViewHolder 는 해당 액티비티 실행 시에만 호출됨
    // 그 다음 스크롤해서 추가로 필요하면 onBindViewHolder 를 호출함
    @NonNull
    @Override
    public NoteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");

        // mainActivity에 연결됨 ( RecyclerActivity )
        LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.note_item, parent,false);
        return new MyViewHolder(view); // ViewHolder에 view를 꼽아서 반환함 ~
        // 하단의 MyViewHolder 생성자로 가보자
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.MyViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        holder.setItem(notes.get(position));
    }

    // 6번 컬렉션 사이즈
    @Override
    public int getItemCount() {
        return notes.size();
    }

    // 1번 ViewHolder 만들기 . 내부클래스로 만들어야함!!!
    // 다른데서 안쓰고 나만 쓸거라서 내부클래스임
    // ViewHolder란 : 하나의 View를 갖고있음.  // 위험한점 : 데이터는 없고 View만 있음
    public class MyViewHolder extends RecyclerView.ViewHolder{

        // 2번 user_item이 가지고 있는 위젯들을 선언
        private TextView tvId;
        private TextView tvTitle;
        private TextView tvSubTitle;
        private TextView tvMin;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //tvId = itemView.findViewById(R.id.tv_id);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvSubTitle = itemView.findViewById(R.id.tv_subtitle);
            tvMin = itemView.findViewById(R.id.tv_min);

        }

        public void setItem(Note note) {
            //tvId.setText(String.valueOf(note.getId()));
            tvTitle.setText(note.getTitle());
            tvSubTitle.setText(note.getSubTitle());
            tvMin.setText(String.valueOf(note.getMin()) +"min");
        }
    }
}
