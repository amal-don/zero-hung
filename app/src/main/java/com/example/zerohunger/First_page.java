package com.example.zerohunger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class First_page extends AppCompatActivity {
Button user_login,trust_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        SharedPreferences sharedPreferences=getSharedPreferences("userLogin",MODE_PRIVATE);
        String value=sharedPreferences.getString("userId",null);
        if(value!=null)
        {
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
        user_login=(Button)findViewById(R.id.login_user);
        trust_login=(Button)findViewById(R.id.login_trust);
        user_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),user_login.class);
                startActivity(intent);
            }
        });
    }
}
