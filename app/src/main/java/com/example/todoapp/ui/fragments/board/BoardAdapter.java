package com.example.todoapp.ui.fragments.board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.databinding.ItemBoardBinding;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private final int[] images = {R.raw.first_anim,
            R.raw.second_anim,
            R.raw.third_anom};
    private final String[] title = new String[3];
    private final String[] subTitle = new String[3];
    private ItemBoardBinding binding;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemBoardBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        initTitles();
        holder.onBind(images[position], title[position], subTitle[position]);

    }

    private void initTitles() {
        title[0] = "Welcome to Surf";
        title[1] = "Design Template uploads Every Tuesday!";
        title[2] = "Download now!";
        subTitle[0] = "I provide essential stuff for your ui designers every tuesday!";
        subTitle[1] = "Make sure to take a look my upload profile every tuesday";
        subTitle[2] = "You can follow me if you wanted comment on any to get some freebies";
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull ItemBoardBinding binding) {
            super(binding.getRoot());
        }

        public void onBind(int image, String title, String subTitle) {
            binding.ivBoard.setAnimation(image);
            binding.tvBoardTitle.setText(title);
            binding.tvBoardSubtitle.setText(subTitle);
        }
    }
}
