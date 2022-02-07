package com.example.todoapp.ui.fragments.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.todoapp.App;
import com.example.todoapp.R;
import com.example.todoapp.databinding.FragmentHomeBinding;
import com.example.todoapp.models.Task;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private MainAdapter adapter = new MainAdapter();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
        initFragmentListener();
    }

    private void initFragmentListener() {
        getParentFragmentManager().setFragmentResultListener("result",
                getViewLifecycleOwner(),
                (requestKey, result) -> {
                    Toast.makeText(requireContext(),
                            result.getString("key"),
                            Toast.LENGTH_SHORT).show();
                    App.dataBase.taskDao().addTask(new Task(result.getString("key")));
                    adapter.setList(App.dataBase.taskDao().getAllTasks());
                    binding.rvMain.setAdapter(adapter);
                });
    }


    private void deleteTask(Task task){
        new AlertDialog.Builder(requireActivity()).setTitle("Do you want to delete it?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    App.dataBase.taskDao().deleteTask(task);
                })
        .setNegativeButton("No", (dialog, which) -> {

        }).show();
    }

    private void initListener() {
        binding.btnAdd.setOnClickListener(v->{
            openDetailFragment();
        });

        binding.btnGallery.setOnClickListener(v->{
            openProfileFragment();
        });
    }

    private void openProfileFragment() {
        NavController navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.profileFragment);
    }

    private void openDetailFragment() {
        NavController navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.detailFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}