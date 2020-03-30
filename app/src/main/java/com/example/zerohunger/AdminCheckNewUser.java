package com.example.zerohunger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminCheckNewUser extends AppCompatActivity
{
    DatabaseReference refee;
    RecyclerView recyclerView;
    UserAdapter adapter;
    ArrayList<User> list;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_check_new_user);


        recyclerView=(RecyclerView)findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<User>();

        refee= FirebaseDatabase.getInstance().getReference().child("User");
        refee.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for (DataSnapshot studentDatasnapshot : dataSnapshot.getChildren())
                {
                    User user = studentDatasnapshot.getValue(User.class);
                    if (user.status.equals(false))
                    {
                        list.add(user);
                    }
                }
                adapter = new UserAdapter(AdminCheckNewUser.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                Toast.makeText(getApplicationContext(),"something wnt wrong",Toast.LENGTH_LONG).show();
            }
        });

    }

}

