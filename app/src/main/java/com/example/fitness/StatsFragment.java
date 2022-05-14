package com.example.fitness;

import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class StatsFragment extends Fragment {

    //TODO: add stats

    private DBHelper DB;

    ArrayList<workoutmodel> list; //list of muscle class has name, weekday, date

    private PieChart pieChart;

    Context context;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = container.getContext();
        return inflater.inflate(R.layout.fragment_stats, container, false);
    }

    //this method is like onCreate but for fragments
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //get the existing database
        DB = Database.getDatabase();

        //initialise pieChart
        pieChart = view.findViewById(R.id.statsPie);

        //initialise list
        list = new ArrayList<>();

        setupPieChart();
        loadPieChartData();

    }

    private void setupPieChart(){
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(18);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Training Frequency By Muscle Group");
        pieChart.setCenterTextSize(20);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(false);
    }

    private void loadPieChartData(){
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        Cursor result = DB.getdata();
        while (result.moveToNext()) {
            list.add(0, new workoutmodel(result.getString(0), result.getString(1), result.getString(2)));

        }

        int total = list.size();
        float percent = (float) total / 100;

        //pie chart categories in percentages
        float Arms = 0;
        float Shoulders = 0;
        float Legs = 0;
        float Chest = 0;
        float Abs = 0;
        float Back = 0;

        //count each group as percentages of the pie
        for (workoutmodel entry: list){
            switch (entry.getMuscle()){
                case "Forearms":
                case "Biceps":
                case "Triceps":
                    Arms += (1 * percent);
                    break;
                case "Shoulders":
                    Shoulders += (1 * percent);
                    break;
                case "Calfs":
                case "Quads":
                case "Hamstrings":
                case "Glutes":
                    Legs += (1 * percent);
                    break;
                case "Chest":
                    Chest += (1 * percent);
                    break;
                case "Abs":
                    Abs += (1 * percent);
                    break;
                case "Upper Traps":
                case "Mid Traps":
                case "Lats":
                case "Lower Back":
                    Back += (1 * percent);
                    break;
            }
        }

        //add the categories to the pie chart
        entries.add(new PieEntry(Arms, "Arms"));
        entries.add(new PieEntry(Shoulders, "Shoulders"));
        entries.add(new PieEntry(Legs, "Legs"));
        entries.add(new PieEntry(Chest, "Chest"));
        entries.add(new PieEntry(Abs, "Abs"));
        entries.add(new PieEntry(Back, "Back"));

        //load some colors for the categories
        ArrayList<Integer> colors = new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }
        for (int color: ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "Muscle Groups");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(20f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();

        //animation
        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }
}