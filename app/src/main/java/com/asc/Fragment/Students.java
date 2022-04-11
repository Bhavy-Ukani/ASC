package com.asc.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asc.R;
import com.asc.adapters.StudentAdapter;
import com.asc.model.StudentModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class Students extends Fragment {
    private final FirebaseDatabase myDb = FirebaseDatabase.getInstance();
    private final DatabaseReference myRef = myDb.getReference("user/profile");
    private ArrayList<StudentModel> list;

    public Students() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_students,container,false);
        RecyclerView recview = (RecyclerView) view.findViewById(R.id.studentsList);
        recview.setLayoutManager(new LinearLayoutManager(getActivity()));
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("user/profile")
                .limitToLast(50);
        FirebaseRecyclerOptions<StudentModel> options =
                new FirebaseRecyclerOptions.Builder<StudentModel>()
                        .setQuery(query, StudentModel.class)
                        .build();
        StudentAdapter studentAdapter = new StudentAdapter(options);
        recview.setAdapter(studentAdapter);
        studentAdapter.startListening();
        return view;
    }

}