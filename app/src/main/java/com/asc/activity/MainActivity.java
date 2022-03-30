package com.asc.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.asc.R;
import com.asc.auth.AuthActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Intent i = new Intent();
        if(user != null) {
            i.setClass(getApplicationContext(),Base.class);
        } else {
            i.setClass(getApplicationContext(), AuthActivity.class);
        }
        startActivity(i);
        finish();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }
}