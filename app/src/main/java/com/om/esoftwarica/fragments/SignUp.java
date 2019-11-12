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
public class SignUp extends Fragment implements View.OnClickListener{
    private EditText editText,editText1,editText2,editText3;
    private Button button;
    private FirebaseAuth firebaseAuth;
    ProgressBar progressBar;

    public SignUp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sign_up, container, false);
        editText    =   view.findViewById(R.id.etname);
        editText1   =   view.findViewById(R.id.etpassword);
        editText2   =   view.findViewById(R.id.etnewpass);
        editText3   =   view.findViewById(R.id.etemaill);
        progressBar =   view.findViewById(R.id.progressBarr);
        button      =   view.findViewById(R.id.btnsignup);
        button.setOnClickListener(this);

        firebaseAuth=FirebaseAuth.getInstance();

        return view;
    }

    @Override
    public void onClick(View view) {
        String name=editText.getText().toString().trim();
        String password=editText1.getText().toString().trim();
        String newpassword=editText2.getText().toString().trim();
        String email=editText3.getText().toString().trim();

        if (TextUtils.isEmpty(name)){
            editText.setError("Username is required");
            return;
        }
        if (TextUtils.isEmpty(password)){
            editText1.setError("Password is required.");
            return;
        }
        if(password.length()<6){
            editText1.setError("Password must be more than 6.");
            return;
        }
        if (TextUtils.isEmpty(newpassword)) {
            editText2.setError("Field is empty");
            return;
        }
        if (!newpassword.equals(password)){
            editText2.setError("Password does not match!");
            return;
        }
        if (TextUtils.isEmpty(email)){
            editText3.setError("Email is required");
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        //connect to firebase
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getActivity(), "User Created.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(), Dashboard.class));
                    progressBar.setVisibility(View.GONE);
                    clear();
                }else{
                    Toast.makeText(getActivity(), "Sign Up Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
    public void clear(){
        editText.setText("");
        editText2.setText("");
        editText3.setText("");
        editText1.setText("");
    }
}
