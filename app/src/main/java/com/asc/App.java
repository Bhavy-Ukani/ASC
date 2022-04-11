package com.asc;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

import com.google.firebase.database.FirebaseDatabase;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
