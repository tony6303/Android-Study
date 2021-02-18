package com.example.myapplication2.note;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.myapplication2.R;

import java.util.ArrayList;
import java.util.List;

public class NoteRecyclerActivity extends AppCompatActivity {

    private static final String TAG = "NoteRecyclerActivity";
    private RecyclerView rvNoteList;
    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_recycler);

        List<Note> notes = new ArrayList<>();
        for(int i=0; i<20; i++){
            // id, Title, subTitle, min
            notes.add(new Note(i,"Title"+i, "SubTitle"+i, 10+i));
        }

        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvNoteList = findViewById(R.id.rv_note_list);
        rvNoteList.setLayoutManager(manager); // RecyclerView 에 매니저(manager) 세팅

        noteAdapter = new NoteAdapter(notes); // userAdapter 에 users 세팅
        rvNoteList.setAdapter(noteAdapter); // RecyclerVide 에 userAdapter 세팅팅

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {


            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Log.d(TAG, "onSwiped: 스와이프");
                Log.d(TAG, "onSwiped: 번호 : "+viewHolder.getAdapterPosition());
                noteAdapter.removeItem(viewHolder.getAdapterPosition());
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rvNoteList);


    }
}