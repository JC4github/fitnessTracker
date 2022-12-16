package com.example.fitness;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {

    Context thiscontext;

    private EditText muscle;
    private EditText weekday;
    private EditText month;
    private EditText date;
    private EditText year;

    private String muscleData;
    private String weekdayData;
    private String monthData;
    private String dateData;
    private String yearData;

    private Button enterBtn;

    private DBHelper DB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        thiscontext = container.getContext();
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        muscle = (EditText) view.findViewById(R.id.muscleField);
        weekday = (EditText) view.findViewById(R.id.weekdayField);
        month = (EditText) view.findViewById(R.id.monthField);
        date = (EditText) view.findViewById(R.id.dateField);
        year = (EditText) view.findViewById(R.id.yearField);

        enterBtn = (Button) view.findViewById(R.id.addDataBtn);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        gets database from Database class
        DB = Database.getInstance(thiscontext);


        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                muscleData = muscle.getText().toString();
                weekdayData = weekday.getText().toString();
                monthData = month.getText().toString();
                dateData = date.getText().toString();
                yearData = year.getText().toString();

                String CombinedDate = monthData + " " + dateData;

                if ((!muscleData.isEmpty())&&(!weekdayData.isEmpty())&&(!monthData.isEmpty())&&(!dateData.isEmpty())&&(!yearData.isEmpty())){
                    DB.insertdata(muscleData, weekdayData, CombinedDate, yearData);
                } else{
                    Toast.makeText(getActivity(), "All fields must be filled",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}