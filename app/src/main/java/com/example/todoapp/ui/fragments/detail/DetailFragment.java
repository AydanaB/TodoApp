package com.example.todoapp.ui.fragments.detail;

import android.os.Binder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todoapp.App;
import com.example.todoapp.R;
import com.example.todoapp.databinding.FragmentDetailBinding;
import com.example.todoapp.models.Task;

public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;
    private NavController navController;
    public static  boolean isFire = false ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setDefaultValues();
        initListeners();
        initListenerFire();
        startAnim();
    }

    private void setDefaultValues() {
        binding.btnSave.setAlpha(0);
        binding.edEnter.setScaleX(0);
    }

    private void startAnim() {
        binding.btnSave.animate().alpha(1).setDuration(2000).start();
        binding.edEnter.animate().scaleX(1).setDuration(2000).start();
    }

    private void initListenerFire() {
        if(isFire){
            binding.btnSave.setOnClickListener(v->{
            });
        }
    }

    private void initListeners() {
        if(!isFire) {
            binding.btnSave.setOnClickListener(v -> {
                String resultText = binding.edEnter.getText().toString().trim();
                App.dataBase.taskDao().addTask(new Task(resultText));
                closeFragment();
            });
        }
    }

    private void closeFragment() {
        navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }
}