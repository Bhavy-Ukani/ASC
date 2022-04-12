package com.asc.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asc.R;
import com.asc.adapters.HomeAdapter;
import com.asc.adapters.StudentAdapter;
import com.asc.model.HomeModel;
import com.asc.model.StudentModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class Home extends Fragment {
    private final FirebaseDatabase myDb = FirebaseDatabase.getInstance();
    private final DatabaseReference myRef = myDb.getReference("user/profile");
    private ArrayList<StudentModel> list;

    public Home() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        RecyclerView recview = (RecyclerView) view.findViewById(R.id.home_recycle);
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
        return view;
    }

}