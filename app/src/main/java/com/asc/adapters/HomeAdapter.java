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
import com.asc.model.HomeModel;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class HomeAdapter extends FirebaseRecyclerAdapter<HomeModel,HomeAdapter.myviewholder>
{

    public HomeAdapter(@NonNull FirebaseRecyclerOptions<HomeModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull HomeModel model) {
        holder.firstname.setText(model.getName());
        Glide.with(holder.avatar)
                .load(Uri.parse(model.getAvtar()))
                .circleCrop()
                .into(holder.avatar);
    }


    @NonNull
    @Override
    public HomeAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home,parent,false);

        return new myviewholder(view);
    }

    public static class myviewholder extends RecyclerView.ViewHolder {
        TextView firstname, username;
        ImageView avatar;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            firstname = itemView.findViewById(R.id.user_flname);
            username = itemView.findViewById(R.id.username);
            avatar = itemView.findViewById(R.id.avatar);
        }
    }
}
