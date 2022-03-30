package com.asc.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.asc.R;

public class Home extends Fragment {

    private RecyclerView recycle_home;
    private ProgressBar progress_home;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        initui(savedInstanceState,view);
        initLogic();
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    private void initLogic() {

    }

    private void initui(Bundle savedInstanceState, View view) {
        recycle_home = view.findViewById(R.id.home_recycle);
        progress_home = view.findViewById(R.id.home_load);
    }
}