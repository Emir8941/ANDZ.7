package com.example.homework7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    List<TaskModel> list = new ArrayList<>();
    Context context;

    public RecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false);
        return new RecyclerViewHolder(view);
    }

    public void addTask(TaskModel taskModel) {
        this.list.add(taskModel);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.RecyclerViewHolder holder, int position) {
        holder.txtTitle.setText(list.get(position).getTitle());
        holder.txtDescription.setText(list.get(position).getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, list.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtDescription;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.item_title_txt);
            txtDescription = itemView.findViewById(R.id.item_description_txt);
        }
    }
}
