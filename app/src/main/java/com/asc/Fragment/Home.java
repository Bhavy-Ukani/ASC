package com.asc.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asc.R;
import com.asc.activity.new_discuss;
import com.asc.adapters.HomeAdapter;
import com.asc.adapters.StudentAdapter;
import com.asc.model.HomeModel;
import com.asc.model.StudentModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.File;
import java.util.ArrayList;

public class Home extends Fragment {
    private final FirebaseDatabase myDb = FirebaseDatabase.getInstance();
    private final DatabaseReference myRef = myDb.getReference("user/profile");
    private ArrayList<StudentModel> list;
    private BottomSheetDialog btmD;

    private RecyclerView recview;
    private FloatingActionButton fab;

    public Home() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recview = (RecyclerView) view.findViewById(R.id.home_recycle);
        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(view1 -> {
            if (allFilesAccessCheck()) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), new_discuss.class);
                startActivity(intent);
            } else {
                Toast.makeText(getActivity(),"Allow Acces to manage all files",Toast.LENGTH_SHORT).show();
                com.asc.utility.File.requestAllFilesAccessPermission(getActivity(),getActivity());
            }
        });
        LoadData();
        return view;
    }

    public void LoadData() {
        recview.setLayoutManager(new LinearLayoutManager(getActivity()));
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("user/profile")
                .limitToLast(50);
        FirebaseRecyclerOptions<HomeModel> options =
                new FirebaseRecyclerOptions.Builder<HomeModel>()
                        .setQuery(query, HomeModel.class)
                        .build();
        HomeAdapter homeAdapter = new HomeAdapter(options);
        recview.setAdapter(homeAdapter);
        homeAdapter.startListening();
    }

    private boolean allFilesAccessCheck() {
        boolean granted = false;
        if (Build.VERSION.SDK_INT > 29) {
            File optOutFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), ".skip_all_files_access_notice");
            granted = Environment.isExternalStorageManager();
            if (!optOutFile.exists() && !granted) {
                granted = false;
            } else {
                granted = true;
            }
        }
        return granted;
    }
}