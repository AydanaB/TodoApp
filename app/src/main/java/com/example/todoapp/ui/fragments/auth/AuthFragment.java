package com.example.todoapp.ui.fragments.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.todoapp.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;


public class AuthFragment extends Fragment {

    private GoogleSignInClient mGoogleSignInCLient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auth, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder
                (GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(
                        "467270903057-bqe2obi8sfe1rg93m5p605ndiclisub8.apps.googleusercontent.com")
                .requestEmail()
                .build();
        mGoogleSignInCLient = GoogleSignIn.getClient(requireContext(), gso);
        view.findViewById(R.id.tv_google).setOnClickListener(v->{
            signInWithGoogle();
        });
    }

    private void signInWithGoogle(){
        Intent signInIntent = mGoogleSignInCLient.getSignInIntent();
        resultLauncher.launch(signInIntent);
    }

    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK){
                    try {
                        Task<GoogleSignInAccount> task = GoogleSignIn
                                .getSignedInAccountFromIntent(result.getData());
                        GoogleSignInAccount account = task.getResult(ApiException.class);
                        signIn(account);
                    } catch (ApiException e) {
                        e.printStackTrace();
                    }
                }
            });

    private void signIn(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),
                null);
        FirebaseAuth.getInstance()
                .signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Navigation.findNavController(requireActivity(),
                                    R.id.nav_host_fragment_activity_main).navigateUp();
                        }else {
                            Toast.makeText(requireContext(), "Unsuccessful",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}