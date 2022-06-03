package com.example.fitness;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashSet;

public class NotesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        EditText editText = view.findViewById(R.id.editText);

        //load data
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("com.example.note", Context.MODE_PRIVATE);
        String text = sharedPreferences.getString("notes", "");

        //update view
        editText.setText(text);

        //saving data on text change
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("com.example.note", Context.MODE_PRIVATE);
                sharedPreferences.edit().putString("notes", String.valueOf(charSequence)).apply();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}