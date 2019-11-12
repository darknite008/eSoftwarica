package com.om.esoftwarica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.om.esoftwarica.fragments.SignIn;

public class Welcome extends AppCompatActivity {
    TextView textView;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        textView=findViewById(R.id.userid);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        textView.setText(firebaseUser.getEmail());
    }

    public void logout(View view) {
        firebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), SignIn.class));
        finish();
    }
}
