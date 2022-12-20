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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    Context thiscontext;

    private RecyclerView workoutRecView;
    ArrayList<workoutmodel> list;
    workoutRecViewAdapter adapter;

    private TextView chestdate;
    private TextView backdate;
    private TextView bicepdate;
    private TextView tricepdate;
    private TextView forearmdate;
    private TextView shoulderdate;
    private TextView coredate;
    private TextView legdate;

    private DBHelper DB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        thiscontext = container.getContext();
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        chestdate = (TextView) view.findViewById(R.id.chestdate);
        backdate = (TextView) view.findViewById(R.id.backdate);
        bicepdate = (TextView) view.findViewById(R.id.bicepdate);
        tricepdate = (TextView) view.findViewById(R.id.tricepdate);
        forearmdate = (TextView) view.findViewById(R.id.forearmdate);
        shoulderdate = (TextView) view.findViewById(R.id.shoulderdate);
        coredate = (TextView) view.findViewById(R.id.coredate);
        legdate = (TextView) view.findViewById(R.id.legdate);

        return view;
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

        Cursor result1 = DB.findLast("Triceps");
        while(result1.moveToNext()){
            String lastDate = result1.getString(0);
            String lastYear = result1.getString(1);

            DateDiff df = new DateDiff();
            long output = df.DateDiff(lastDate, lastYear);

            tricepdate.setText(String.valueOf(output)+"d");
        }

        Cursor result2 = DB.findLast("Biceps");
        while(result2.moveToNext()){
            String lastDate = result2.getString(0);
            String lastYear = result2.getString(1);

            DateDiff df = new DateDiff();
            long output = df.DateDiff(lastDate, lastYear);

            bicepdate.setText(String.valueOf(output)+"d");
        }

        Cursor result3 = DB.findLast("Forearms");
        while(result3.moveToNext()){
            String lastDate = result3.getString(0);
            String lastYear = result3.getString(1);

            DateDiff df = new DateDiff();
            long output = df.DateDiff(lastDate, lastYear);

            forearmdate.setText(String.valueOf(output)+"d");
        }

        Cursor result4 = DB.findLast("Shoulders");
        while(result4.moveToNext()){
            String lastDate = result4.getString(0);
            String lastYear = result4.getString(1);

            DateDiff df = new DateDiff();
            long output = df.DateDiff(lastDate, lastYear);

            shoulderdate.setText(String.valueOf(output)+"d");
        }

        Cursor result5 = DB.findLast("Abs");
        while(result5.moveToNext()){
            String lastDate = result5.getString(0);
            String lastYear = result5.getString(1);

            DateDiff df = new DateDiff();
            long output = df.DateDiff(lastDate, lastYear);

            coredate.setText(String.valueOf(output)+"d");
        }

        Cursor result6 = DB.findLast("Quads");
        while(result6.moveToNext()){
            String lastDate = result6.getString(0);
            String lastYear = result6.getString(1);

            DateDiff df = new DateDiff();
            long output = df.DateDiff(lastDate, lastYear);

            legdate.setText(String.valueOf(output)+"d");
        }

        Cursor result7 = DB.findLast("Chest");
        while(result7.moveToNext()){
            String lastDate = result7.getString(0);
            String lastYear = result7.getString(1);

            DateDiff df = new DateDiff();
            long output = df.DateDiff(lastDate, lastYear);

            chestdate.setText(String.valueOf(output)+"d");
        }

        Cursor result8 = DB.findLast("Lats");
        while(result8.moveToNext()){
            String lastDate = result8.getString(0);
            String lastYear = result8.getString(1);

            DateDiff df = new DateDiff();
            long output = df.DateDiff(lastDate, lastYear);

            backdate.setText(String.valueOf(output)+"d");
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