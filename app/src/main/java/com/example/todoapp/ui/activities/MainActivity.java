package com.example.todoapp.ui.activities;

import android.os.Bundle;
import android.view.View;

import com.example.todoapp.App;
import com.example.todoapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.todoapp.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            ArrayList<Integer> listFragment = new ArrayList<>();
            listFragment.add(R.id.navigation_home);
            listFragment.add(R.id.navigation_dashboard);
            listFragment.add(R.id.navigation_notifications);
            if (listFragment.contains(destination.getId())){
                binding.navView.setVisibility(View.VISIBLE);
            }else {
                binding.navView.setVisibility(View.GONE);
            }
        });

        FirebaseAuth.AuthStateListener mAuthListener;
        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (FirebaseAuth.getInstance().getCurrentUser() == null){
                    navController.navigate(R.id.authFragment);
                }
            }
        };

        if (!App.prefs.isShow()){
            navController.navigate(R.id.boardFragment);
            App.prefs.setPrefs();
        }
    }
}