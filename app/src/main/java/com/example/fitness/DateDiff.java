package com.example.fitness;

import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateDiff {

    public long DateDiff(String lastDate, String lastYear){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd");

        Date endDate = Calendar.getInstance().getTime();

        try{
            Date startDate = sdf.parse(lastYear + " " + lastDate);

            long difference_In_Time = endDate.getTime() - startDate.getTime();

            long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;

            return difference_In_Days;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
