package com.asc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.asc.Fragment.Home;
import com.asc.Fragment.Inbox;
import com.asc.Fragment.Students;
import com.asc.Fragment.Teams;
import com.asc.R;
import com.asc.auth.AuthActivity;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Base extends AppCompatActivity {

    private BottomNavigationView navigation;
    private FrameLayout fragmentContainerView;
    private ImageView notification;

    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private final Fragment home = new Home();
    private final Fragment inbox = new Inbox();
    private final Fragment students = new Students();
    private final Fragment teams = new Teams();
    private Fragment active = home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        fragmentContainerView = findViewById(R.id.fragmentContainerView);
        notification = findViewById(R.id.notification);
        initial();
    }

    private void LoadBottomNav() {
        navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnItemSelectedListener(item -> {
            switch (item.getTitle().toString()) {
                case "Home":
                    openFragment(home, "home", 0);
                    break;
                case "Students":
                    openFragment(students, "Students", 1);
                    break;
                case "Teams":
                    openFragment(teams, "Teams", 2);
                    break;
                case "Inbox":
                    openFragment(inbox, "Inbox", 3);
                    break;
                case "Profile":
                    break;
            }
            return true;
        });
        openFragment(home, "home", 0);
    }

    private void initial() {
        notification.setOnClickListener(view -> {
            AuthUI.getInstance().signOut(getApplicationContext()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), AuthActivity.class);
                    finish();
                }
            });
        });
        LoadBottomNav();
    }

    private void openFragment(Fragment fragment, String tag, int position) {
        if (fragment.isAdded()) {
            fragmentManager.beginTransaction()
                    .hide(active)
                    .show(fragment).commit();
        } else {
            fragmentManager.beginTransaction()
                    .add(R.id.fragmentContainerView, fragment, tag)
                    .hide(active)
                    .show(fragment).commit();
        }
        navigation.getMenu().getItem(position).setChecked(true);
        active = fragment;
    }

//    @Override
//    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
//        Intent i = new Intent();
//        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        i.setClass(getApplicationContext(),Base.class);
//        finish();
//        startActivity(i);
//        super.onRestoreInstanceState(savedInstanceState);
//    }
}