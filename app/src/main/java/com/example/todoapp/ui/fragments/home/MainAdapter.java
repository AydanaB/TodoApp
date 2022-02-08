package com.example.todoapp.ui.fragments.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.App;
import com.example.todoapp.R;
import com.example.todoapp.models.Task;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Task> list = new ArrayList<>() ;
    private Activity activity;

    public MainAdapter(Activity activity) {
        this.activity = activity;
    }

    public MainAdapter() {
    }

    public void setList(List<Task> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_main,
                parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
        holder.tvTitle.setOnLongClickListener(v -> {
            holder.delete(list.get(position));
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }

        public void onBind(Task task){
            tvTitle.setText(task.getTitle());
        }

        public void delete(Task task) {
            new AlertDialog.Builder(activity).setTitle("Do you want to delete it?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        App.dataBase.taskDao().deleteTask(task);
                    })
                    .setNegativeButton("No", (dialog, which) -> {
                        Toast.makeText(activity, "Отмена", Toast.LENGTH_SHORT).show();
                    }).show();
        }
    }
}
