package com.asc.activity;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.asc.R;
import com.asc.utility.Helper;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.util.HashMap;

public class new_discuss extends AppCompatActivity {
    private TextInputEditText query, mention,mediaText;
    private TextInputLayout mediaPick;
    private SwitchCompat isPrivate;
    private MaterialButton postBtn;

    private HashMap<String, Object> map = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_discuss);
        query = findViewById(R.id.newQuery);
        mention = findViewById(R.id.mentionUser);
//        mediaText = findViewById(R.id.mediaText);
        mediaPick = findViewById(R.id.mediPick);
        isPrivate = findViewById(R.id.isPrivate);
        postBtn = findViewById(R.id.postBtn);
        init();
    }
    private void init(){
        postBtn.setOnClickListener(view -> {
            String queryTxt, mentionTxt;
            queryTxt = query.getText().toString();
            if (queryTxt.equals("")|| queryTxt == null){
                query.setError("Enter Your query");
            }
            else{
                map.put("query",queryTxt);
            }
        });
        mediaPick.addOnEndIconChangedListener(new TextInputLayout.OnEndIconChangedListener() {
            @Override
            public void onEndIconChanged(@NonNull TextInputLayout textInputLayout, int previousIcon) {
                Helper.message(getApplicationContext(),"Hii There");
            }
        });
    }
}