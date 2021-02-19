package com.example.mystudyapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

// 메인액티비티에 모든 코드를 작성하면 어지러웠을듯 .
public class SettingsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // xml에 있는 그림이 메모리에 올라오는과정 : inflate
        View view = inflater.inflate(R.layout.fragment_settings,container, false);

        return view;
    }
}
