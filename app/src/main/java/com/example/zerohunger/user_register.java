package com.example.zerohunger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class user_register extends AppCompatActivity {
    EditText name,email,phone,password,confirm;
    String sname,semail,sphone,spassword,smobile;
    Button register;
    DatabaseReference reference;
    User user;
    Toast backtoast;
    long backpress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        phone=(EditText)findViewById(R.id.phone);
        password=(EditText)findViewById(R.id.pass);
        confirm=(EditText)findViewById(R.id.confirm);
        register=(Button) findViewById(R.id.register);
        user=new User();
        reference=FirebaseDatabase.getInstance().getReference().child("User");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                sname=name.getText().toString().trim();
                semail=email.getText().toString();
                spassword=password.getText().toString();
                smobile=phone.getText().toString();
                if (sname.isEmpty())
                {
                    name.setError("name required");
                    name.requestFocus();
                }
                else if (smobile.isEmpty())
                {
                    phone.setError("numer required");
                    phone.requestFocus();

                }
                else if (semail.isEmpty())
                {
                    email.setError("email required");
                    email.requestFocus();

                }
                else if (spassword.isEmpty())
                {
                    password.setError("password required");
                    password.requestFocus();
                }

                else
                {
                    String code="91";
                    sphone="+" + code + smobile;


                  Query query=reference.orderByChild("user_mobile").equalTo(sphone);
                  query.addListenerForSingleValueEvent(new ValueEventListener() {
                      @Override
                      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                          if (dataSnapshot.exists())
                          {
                              phone.setError("number already exists");
                          }
                          else
                          {
                              user.setUser_email(semail);
                              user.setUser_mobile(sphone);
                              user.setUser_name(sname);
                              user.setUser_password(spassword);
                              reference.child(sphone).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                  @Override
                                  public void onSuccess(Void aVoid) {

                                      Toast.makeText(getApplicationContext(),"succefully registered",Toast.LENGTH_SHORT).show();

                                  }
                              });

                          }
                      }

                      @Override
                      public void onCancelled(@NonNull DatabaseError databaseError) {
                          Toast.makeText(getApplicationContext(),"failed to register",Toast.LENGTH_SHORT).show();


                      }
                  });


                }
            }


        });

    }


    public void onBackPressed()
{
    if (backpress+2000>System.currentTimeMillis())
    {
        backtoast.cancel();
        Intent intent=new Intent(getApplicationContext(),user_login.class);
        startActivity(intent);
    }
    else
    {
        backtoast=Toast.makeText(getApplicationContext(),"press again to exit",Toast.LENGTH_SHORT);
        backtoast.show();
    }
    backpress=System.currentTimeMillis();
}



}

