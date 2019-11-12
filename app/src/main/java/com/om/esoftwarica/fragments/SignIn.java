package com.om.esoftwarica.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.om.esoftwarica.Dashboard;
import com.om.esoftwarica.R;
import com.om.esoftwarica.Welcome;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignIn extends Fragment implements View.OnClickListener {
    EditText etN,etP;
    Button btnS;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;

    public SignIn() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sign_in, container, false);
        etN=view.findViewById(R.id.etfis);
        etP=view.findViewById(R.id.etsec);
        btnS=view.findViewById(R.id.btnc);
        progressBar=view.findViewById(R.id.progresss);
        firebaseAuth=FirebaseAuth.getInstance();
        btnS.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        String email=etN.getText().toString().trim();
        String password=etP.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            etN.setError("Email is Required!");
            return;
        }
        if (TextUtils.isEmpty(password)){
            etP.setError("Email is Required!");
            return;
        }
        if (password.length()<6){
            etP.setError("Password must be > 6.");
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        //authenticate the user
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getActivity(), "Login Successful.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), Dashboard.class));
                    progressBar.setVisibility(View.GONE);
                    clear();
                }else{
                    Toast.makeText(getActivity(), "Login Failed!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);

                }
            }
        });

    }

    private void clear() {
        etN.setText("");
        etP.setText("");
    }
}
