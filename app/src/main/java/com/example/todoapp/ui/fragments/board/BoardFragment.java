package com.example.todoapp.ui.fragments.board;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MediatorLiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.todoapp.R;
import com.example.todoapp.databinding.FragmentBoardBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class BoardFragment extends Fragment {

    private FragmentBoardBinding binding;
    private BoardAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBoardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewPager();
    }

    private void initViewPager() {
        adapter = new BoardAdapter();
        binding.vpBoard.setAdapter(adapter);
        initTabListener();
        initTextViewsListeners();
    }

    private void initTextViewsListeners() {
        binding.btnSkip.setOnClickListener(v -> {
            binding.vpBoard.setCurrentItem(3);
        });
        binding.vpBoard.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 2) {
                    binding.btnFinish.setVisibility(View.VISIBLE);
                }else {
                    binding.btnFinish.setVisibility(View.GONE);
                }
            }
        });
        binding.btnFinish.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(),
                    R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.navigation_home);
        });
    }

    private void initTabListener() {
        new TabLayoutMediator(binding.tabLayout, binding.vpBoard, (tab, position) -> {
            if (position == 0) {
                tab.setIcon(R.drawable.black_dot);
            } else {
                tab.setIcon(R.drawable.default_dot);
            }
        }).attach();
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.setIcon(R.drawable.black_dot);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setIcon(R.drawable.default_dot);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}