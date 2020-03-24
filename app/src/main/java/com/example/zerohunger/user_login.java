package com.example.zerohunger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zerohunger.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class user_login extends AppCompatActivity {
    TextView register;
    EditText userName,password;
    String suserName,spasswod;
    Button login;
    DatabaseReference reference;
    User user;

    TextView gotoRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        login=(Button)findViewById(R.id.loginSignInButton);
        userName=(EditText)findViewById(R.id.loginUserName);
        password=(EditText)findViewById(R.id.loginUserPassword);
        gotoRegister=(TextView)findViewById(R.id.loginRegisterTextView);
        user=new User();
        reference= FirebaseDatabase.getInstance().getReference().child("User");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suserName = userName.getText().toString();
                spasswod = password.getText().toString();
                if (suserName.isEmpty() || suserName.length() < 10) {
                    userName.setError("enter correct number");
                    userName.requestFocus();

                } else if (spasswod.isEmpty()) {
                    password.setError("password required");
                    password.requestFocus();

                }else if (suserName.equals("1111111111")||spasswod.equals("admin"))
                {
                    Intent intent=new Intent(getApplicationContext(),Admin.class);
                    startActivity(intent);
                }
                else {
                    String tusername=suserName;
                    String code="91";
                    suserName="+" + code + tusername;
                Query query = reference.orderByChild("user_mobile").equalTo(suserName);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                user = snapshot.getValue(User.class);
                                if (spasswod.equals(user.user_password)) {
                                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);

                                    Toast.makeText(getApplicationContext(), "logged successfully", Toast.LENGTH_SHORT).show();
                                    userName.setText("");
                                    password.setText("");

                                } else {
                                    Toast.makeText(getApplicationContext(), "wrong password", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "no account in this number", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }
        });
        gotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),user_register.class);
                startActivity(intent);
            }
        });
    }
}
