package com.example.gymscape.ui.exerciselist;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymscape.Model.Exercise;
import com.example.gymscape.R;

import java.util.ArrayList;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {

    public ArrayList<Exercise> exercisesList;
    final private OnListItemClickListener mOnListItemClickListener;

    ExerciseAdapter(ArrayList<Exercise> exercises, OnListItemClickListener listener){
        exercisesList = exercises;
        mOnListItemClickListener = listener;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.exercise_list_item, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.name.setText(exercisesList.get(position).getName());
        switch (exercisesList.get(position).getCategory())
        {
            case 1:
                viewHolder.iconMuscle.setImageResource(R.drawable.icon_core);
                break;
            case 2:
                viewHolder.iconMuscle.setImageResource(R.drawable.icon_chest);
                break;
            case 3:
                viewHolder.iconMuscle.setImageResource(R.drawable.icon_back);
                break;
            case 4:
                viewHolder.iconMuscle.setImageResource(R.drawable.icon_biceps);
                break;
            case 5:
                viewHolder.iconMuscle.setImageResource(R.drawable.icon_triceps);
                break;
            case 6:
                viewHolder.iconMuscle.setImageResource(R.drawable.icon_shoulder);
                break;
            case 7:
                viewHolder.iconMuscle.setImageResource(R.drawable.icon_legs);
                break;
            case 8:
                viewHolder.iconMuscle.setImageResource(R.drawable.icon_glutes);
                break;
        }
    }

    public int getItemCount() {
        return exercisesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        ImageView iconMuscle;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.exerciseName);
            iconMuscle = itemView.findViewById(R.id.iconMuscle);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }

    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
}