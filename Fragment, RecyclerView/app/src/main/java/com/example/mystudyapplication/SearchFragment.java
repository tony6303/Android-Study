package com.example.mystudyapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

// 메인액티비티에 모든 코드를 작성하면 어지러웠을듯 .
public class SearchFragment extends Fragment {
    private static final String TAG = "SearchFragment";
    private TextView tvTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // xml에 있는 그림이 메모리에 올라오는과정 : inflate
        View view = inflater.inflate(R.layout.fragment_search,container, false);

        return view;
    }
}
