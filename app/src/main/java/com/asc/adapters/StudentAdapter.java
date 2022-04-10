package com.asc.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asc.R;
import com.asc.model.StudentModel;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class StudentAdapter extends FirebaseRecyclerAdapter<StudentModel,StudentAdapter.myviewholder>
{

    public StudentAdapter(@NonNull FirebaseRecyclerOptions<StudentModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull StudentAdapter.myviewholder holder, int position, @NonNull StudentModel model) {
        holder.name.setText(model.getName());
        Glide.with(holder.avtar).load(Uri.parse(model.getAvtar())).into(holder.avtar);
    }

    @NonNull
    @Override
    public StudentAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_students,parent,false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView avtar;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.user_flname);
            avtar = itemView.findViewById(R.id.avtar);
        }
    }
}
