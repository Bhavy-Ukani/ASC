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
        holder.firstname.setText(model.getName());
        Glide.with(holder.avatar)
                .load(Uri.parse(model.getAvtar()))
                .circleCrop()
                .into(holder.avatar);
        try {
            holder.username.setText(model.getUsername());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public StudentAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_students,parent,false);
        return new myviewholder(view);
    }

    public static class myviewholder extends RecyclerView.ViewHolder {
        TextView firstname, username;
        ImageView avatar;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            firstname = itemView.findViewById(R.id.user_flname);
            username = itemView.findViewById(R.id.username);
            avatar = itemView.findViewById(R.id.avtar);
        }
    }
}
