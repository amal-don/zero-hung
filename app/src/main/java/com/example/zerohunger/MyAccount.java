package com.example.zerohunger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyAccount extends AppCompatActivity {
Button logOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        this.setTitle("My Account");
        logOut = (Button) findViewById(R.id.logout);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("userLogin", MODE_PRIVATE).edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(getApplicationContext(),user_login.class);
                startActivity(intent);
            }
        });
    }
}
