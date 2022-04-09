package com.asc.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.asc.R;
import com.asc.auth.AuthActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);
        Intent i = new Intent();
        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
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
