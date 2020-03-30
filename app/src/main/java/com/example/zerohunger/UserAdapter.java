package com.example.zerohunger;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>
{
    private ValueEventListener mCtx;
    private ArrayList<User> users;
    Context context;


    UserAdapter(Context context, ArrayList<User> itemList)
    {
        this.context = context;
        users = itemList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater=LayoutInflater.from((Context) context);
        View view=layoutInflater.inflate(R.layout.accept_cardview,null);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, final int position)
    {
        holder.t1.setText(users.get(position).getUser_name());
        holder.t2.setText(users.get(position).getUser_email());
        holder.t3.setText(users.get(position).getUser_mobile());
        holder.t4.setText(users.get(position).getUser_password());






    }

    @Override
    public int getItemCount()

    {
        return users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder
    {
        TextView t1,t2,t3,t4;


        public UserViewHolder(@NonNull View ownerView) {
            super(ownerView);
            t1=(TextView) ownerView.findViewById(R.id.userName);
            t2=(TextView)ownerView.findViewById(R.id.emailcard);
            t3=(TextView)ownerView.findViewById(R.id.number);
            t4=(TextView)ownerView.findViewById(R.id.password);


        }
    }
}

