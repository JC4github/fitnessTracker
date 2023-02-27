package com.example.fitness;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;

public class StatsFragment extends Fragment {

    //TODO: add stats

    private DBHelper DB;

    ArrayList<workoutmodel> list; //list of muscle class has name, weekday, date

    private PieChart pieChart;
    private BarChart barChart;

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

        //initialise list
        list = new ArrayList<>();

        //get the existing database
        DB = Database.getDatabase();
        //query the database add it to an array list
        Cursor result = DB.getDataAll();
        while (result.moveToNext()) {
            list.add(0, new workoutmodel(result.getString(0), result.getString(1), result.getString(2)));
        }

        //initialise charts
        pieChart = view.findViewById(R.id.statsPie);
        barChart = view.findViewById(R.id.statsBar);

        setupPieChart();
        loadPieChartData();
        loadBarChartData();

    }

    private void setupPieChart(){
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(18);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Muscle Groups");
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

        int total = list.size();
        float percent = (float) total / 100;

        //pie chart categories in percentages
        float Arms = 0;
        float Shoulders = 0;
        float Legs = 0;
        float ChestAndAbs = 0;
        float Back = 0;

        //count each group as percentages of the pie
        for (workoutmodel entry: list){
            switch (entry.getMuscle()){
                case "Forearms":
                case "Biceps":
                case "Triceps":
                    Arms += (1 * percent);
                    break;
                case "Upper Traps":
                case "Shoulders":
                    Shoulders += (1 * percent);
                    break;
                case "Calves":
                case "Quads":
                case "Hamstrings":
                case "Glutes":
                    Legs += (1 * percent);
                    break;
                case "Chest":
                case "Abs":
                    ChestAndAbs += (1 * percent);
                    break;
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
        entries.add(new PieEntry(ChestAndAbs, "Chest&Abs"));
        entries.add(new PieEntry(Back, "Back"));

        //load some colors for the categories
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(252, 124, 98));
        colors.add(Color.rgb(245, 108, 81));
        colors.add(Color.rgb(255, 91, 59));
        colors.add(Color.rgb(250, 79, 45));
        colors.add(Color.rgb(235, 60, 26));
        colors.add(Color.rgb(224, 52, 18));
        colors.add(Color.rgb(252, 42, 0));

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

    private void loadBarChartData(){
        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        //Bar chart values
        float Mon = 0;
        float Tue = 0;
        float Wed = 0;
        float Thu = 0;
        float Fri = 0;
        float Sat = 0;
        float Sun = 0;

        //count how many entry for each weekday
        for (workoutmodel entry: list){
            switch (entry.getWeekday()){
                case "Monday":
                    Mon += 1;
                    break;
                case "Tuesday":
                    Tue += 1;
                    break;
                case "Wednesday":
                    Wed += 1;
                    break;
                case "Thursday":
                    Thu += 1;
                    break;
                case "Friday":
                    Fri += 1;
                    break;
                case "Saturday":
                    Sat += 1;
                    break;
                case "Sunday":
                    Sun += 1;;
                    break;
            }
        }

        //add the categories to the pie chart
        entries.add(new BarEntry(0, Mon));
        entries.add(new BarEntry(1, Tue));
        entries.add(new BarEntry(2, Wed));
        entries.add(new BarEntry(3, Thu));
        entries.add(new BarEntry(4, Fri));
        entries.add(new BarEntry(5, Sat));
        entries.add(new BarEntry(6, Sun));

        BarDataSet dataSet = new BarDataSet(entries, "Activity");
        dataSet.setColor(Color.rgb(255, 79, 44));

        BarData data = new BarData(dataSet);
        data.setDrawValues(true);
        data.setValueTextSize(20f);
        data.setValueTextColor(Color.BLACK);
        ValueFormatter valueFormats = new DefaultValueFormatter(0);
        data.setValueFormatter(valueFormats);


        //animation
        barChart.animateY(2000);

        ArrayList<String> xLabel = new ArrayList<>();
        xLabel.add("MON");
        xLabel.add("TUE");
        xLabel.add("WED");
        xLabel.add("THU");
        xLabel.add("FRI");
        xLabel.add("SAT");
        xLabel.add("SUN");

        String[] x = new String[]{"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        IndexAxisValueFormatter formatter = new IndexAxisValueFormatter();
        formatter.setValues(x);
        xAxis.setValueFormatter(formatter);

        barChart.setDrawValueAboveBar(false);
        barChart.getXAxis().setTextSize(11f);
        barChart.getLegend().setEnabled(false);
        barChart.getDescription().setEnabled(false);
        barChart.setFitBars(true);
        barChart.setData(data);
        barChart.invalidate();
    }

}