package com.example.mystudyapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {

    private RecyclerView rvNoteList;
    private NoteAdapter noteAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat,container, false);

        List<Note> notes = new ArrayList<>();
        for(int i=0; i<20; i++){
            // id, Title, subTitle, min
            notes.add(new Note(i,"Title"+i, "SubTitle"+i, 10+i));
        }

        LinearLayoutManager manager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        rvNoteList = view.findViewById(R.id.rv_note_list);
        rvNoteList.setLayoutManager(manager); // RecyclerView 에 매니저(manager) 세팅

        noteAdapter = new NoteAdapter(notes); // userAdapter 에 users 세팅
        rvNoteList.setAdapter(noteAdapter); // RecyclerVide 에 userAdapter 세팅팅

        return view;

    }
}
