package com.asc.activity;

import static com.asc.R.layout;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.asc.R;
import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends AppCompatActivity {

    private Toolbar toolbar;

    private TextView flname, email, userid, batch;
    private ImageView avtar;
    private MaterialButton signoutbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_profile);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loadui();
        initLogic();
    }

    private void initLogic() {
        loadData();
        signoutbtn.setOnClickListener(view -> {
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(task -> finish());
        });
    }
    // TODO: load all ui from here
    private void loadui() {
        avtar = findViewById(R.id.avtar);
        flname = findViewById(R.id.user_flname);
        email = findViewById(R.id.user_email);
        userid = findViewById(R.id.user_id);
        batch = findViewById(R.id.user_batch);
        signoutbtn = findViewById(R.id.signoutbtn);
    }

    // TODO: Load all user details here from firebase
    private void loadData(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        email.setText(user.getEmail().toString());
        flname.setText(user.getDisplayName());
        userid.setText(user.getUid());
        try {
            Glide.with(avtar).load(user.getPhotoUrl()).into(avtar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}