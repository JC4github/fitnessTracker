package com.example.fitness;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class workoutRecViewAdapter extends RecyclerView.Adapter<workoutRecViewAdapter.ViewHolder>{

    private ArrayList<workoutmodel> list = new ArrayList<>();

    public workoutRecViewAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.muscle.setText(list.get(position).getMuscle());
        holder.weekday.setText(list.get(position).getWeekday());
        holder.date.setText(list.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(ArrayList<workoutmodel> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView muscle, weekday, date;
        private CardView parent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            muscle = itemView.findViewById(R.id.muscleTxt);
            weekday = itemView.findViewById(R.id.weekdayTxt);
            date = itemView.findViewById(R.id.dateTxt);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}
