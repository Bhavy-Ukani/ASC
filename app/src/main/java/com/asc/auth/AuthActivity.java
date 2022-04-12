package com.asc.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.asc.R;
import com.asc.activity.MainActivity;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class AuthActivity extends AppCompatActivity {

    List<AuthUI.IdpConfig> provider = Collections.emptyList();

    FirebaseDatabase userInfo = FirebaseDatabase.getInstance();
    DatabaseReference useRef;
    private MaterialButton emailbtn = null;
    private MaterialButton ggl = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_auth);
        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            loadui();
            logic();
        } else {
            Intent i = new Intent();
            i.setClass(getApplicationContext(), com.asc.activity.Base.class);
            startActivity(i);
            finish();
        }
    }

    private void loadui() {
        emailbtn = findViewById(R.id.emilbtn);
        ggl = findViewById(R.id.ggl);
    }


    private void logic() {
        //TODO: Email login init
        emailbtn.setOnClickListener(view -> {
            provider = Collections.singletonList(
                    new AuthUI.IdpConfig.EmailBuilder().build()
            );
            handleLoginregister();
        });
        // TODO: Google Login init
        ggl.setOnClickListener(view -> {
            provider = Collections.singletonList(
                    new AuthUI.IdpConfig.GoogleBuilder().build()
            );
            handleLoginregister();
        });
    }

    private void handleLoginregister() {
        Intent intent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setIsSmartLockEnabled(true,true)
                .setAvailableProviders(provider)
                .setAlwaysShowSignInMethodScreen(false)
                .setTosAndPrivacyPolicyUrls("https:google.com", "https://Google.com")
                .setLogo(R.drawable.ic_launcher_background)
                .setTheme(R.style.Theme_MyApplication)
                .build();
        signInLauncher.launch(intent);
    }

    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            this::onSignInResult
    );

    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        if (result.getResultCode() == RESULT_OK) {
            // TODO: when successfully signed in
            Log.d("A", result.toString());
            Intent i = new Intent();
            i.setClass(getApplicationContext(),MainActivity.class);
            startActivity(i);
            if(result.getIdpResponse().isNewUser()) {
                userData();
            } else {
                finish();
            }
        }
        else {
            Toast.makeText(getApplicationContext(),"Something went wrong..",Toast.LENGTH_LONG).show();
        }

    }

    private void userData(){
//        Todo: uploading user data to firebase
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        useRef = userInfo.getReference("user/profile");
        HashMap<String,Object> map = new HashMap<>();
        map.put("name", firebaseUser.getDisplayName());
        if(firebaseUser.getPhotoUrl() != null) {
            map.put("avtar", firebaseUser.getPhotoUrl().toString());
        } else {
            map.put("avtar","false");
        }
        map.put("mail", firebaseUser.getEmail());
        map.put("type", "1");
        map.put("mobile",firebaseUser.getPhoneNumber());
        useRef.child(firebaseUser.getUid()).updateChildren(map);
    }

}