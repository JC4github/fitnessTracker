package com.example.fitness;

public class workoutmodel {
    private String muscle;
    private String weekday;
    private String date;

    public workoutmodel(String muscle, String weekday, String date) {
        this.muscle = muscle;
        this.weekday = weekday;
        this.date = date;
    }

    public String getMuscle() {
        return muscle;
    }

    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
