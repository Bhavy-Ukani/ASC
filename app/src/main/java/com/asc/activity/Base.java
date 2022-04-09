package com.asc.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import com.asc.Fragment.Home;
import com.asc.Fragment.Inbox;
import com.asc.Fragment.Students;
import com.asc.Fragment.Teams;
import com.asc.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Base extends AppCompatActivity {

    private BottomNavigationView navigation;
    private FragmentContainerView fragmentContainerView;
    private ImageView img_profile;

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
        navigation = findViewById(R.id.bottom_navigation);
        fragmentContainerView = findViewById(R.id.fragmentContainerView);
        img_profile = findViewById(R.id.img_profile);
        initial();
    }

    @SuppressLint("NonConstantResourceId")
    private void initial() {
        openFragment(home,"home",0);
        //TODO: Logout
        img_profile.setOnClickListener(view -> {
            Intent i = new Intent();
            i.setClass(getApplicationContext(), Profile.class);
            i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(i);
        });

        navigation.setOnItemSelectedListener(item -> {
                switch(item.getItemId()){
                    case R.id.home:
                        openFragment(home,"home",0);
                        break;
                    case R.id.students:
                        openFragment(students,"Students",1);
                        break;
                    case R.id.teams:
                        openFragment(teams,"Teams",2);
                        break;
                    case R.id.inbox:
                        openFragment(inbox,"Inbox",3);
                        break;
                }
                return true;
            });
    }

    private void openFragment(Fragment fragment, String tag,int position) {
        if(fragment.isAdded()){
            fragmentManager.beginTransaction()
                    .hide(active)
                    .show(fragment).commit();
            Log.d("1", "openFragment: Added");
        } else {
            fragmentManager.beginTransaction()
                    .add(R.id.fragmentContainerView,fragment,tag)
                    .hide(active)
                    .show(fragment).commit();
            Log.d("1", "openFragment: Not Added");
        }
        navigation.getMenu().getItem(position).setChecked(true);
        active = fragment;
    }

    public void logout(){
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(task -> finish());
    }
    @Override
    protected void onRestoreInstanceState(Bundle bundle){
        super.onRestoreInstanceState(bundle);
    }

}