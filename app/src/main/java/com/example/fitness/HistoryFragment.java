package com.example.fitness;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    Context thiscontext;

    private RecyclerView workoutRecView;
    ArrayList<workoutmodel> list;
    workoutRecViewAdapter adapter;

    private DBHelper DB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        thiscontext = container.getContext();
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //gets database from Database class
        DB = Database.getInstance(thiscontext);

        workoutRecView = view.findViewById(R.id.workoutRecView);

        list = new ArrayList<>();
        loadcard();

        adapter = new workoutRecViewAdapter();
        adapter.setList(list);

        workoutRecView.setAdapter(adapter);
        workoutRecView.setLayoutManager(new LinearLayoutManager(thiscontext));

        Cursor result = DB.findLast("Chest");
        while(result.moveToNext()){
            String text = result.getString(0);
            Toast.makeText(getActivity(), text,
                    Toast.LENGTH_LONG).show();
        }


    }

    //loops over database results and adds them to a list as workoutmodels
    public void loadcard() {
        list.clear();
        Cursor result = DB.getdata();
        while (result.moveToNext()) {
            list.add(0, new workoutmodel(result.getString(0), result.getString(1), result.getString(2)));
        }
    }
}