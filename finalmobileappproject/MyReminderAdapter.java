package com.example.finalmobileappproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyReminderAdapter extends RecyclerView.Adapter<MyReminderAdapter.MyViewHolder> {
    ArrayList<Model> dataHolder = new ArrayList<Model>();

    public MyReminderAdapter(ArrayList<Model> dataHolder) {
        this.dataHolder = dataHolder;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reminder_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(dataHolder.get(position).getTitle());
        holder.date.setText(dataHolder.get(position).getDate());
        holder.time.setText(dataHolder.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, date, time;
        Button button;
        MyReminderAdapter adapter;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.txtTitle);
            date = itemView.findViewById(R.id.txtDate);
            time = itemView.findViewById(R.id.txtTime);
            button = itemView.findViewById(R.id.remove_reminder_btn);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), "Remove btn works", Toast.LENGTH_SHORT).show();
                    dataHolder.remove(getAdapterPosition());
                    notifyDataSetChanged();
                    String result = new DBManager(itemView.getContext()).deleteReminder(title.getText().toString());
                    Toast.makeText(itemView.getContext(), result, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
