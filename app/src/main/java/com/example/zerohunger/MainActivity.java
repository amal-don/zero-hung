package com.example.zerohunger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
LinearLayout food,accound;
long backpress;
Toast backtoast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Home");
        SharedPreferences sharedPreferences=getSharedPreferences("userLogin",MODE_PRIVATE);

        food=(LinearLayout)findViewById(R.id.donate_food);
        accound=(LinearLayout)findViewById(R.id.profile);
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Donate_food.class);
                startActivity(intent);
            }
        });

        accound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MyAccount.class);
                startActivity(intent);
            }
        });
    }
    public void onBackPressed()
    {
        if (backpress+2000>System.currentTimeMillis())
        {
            backtoast.cancel();
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
        else
        {
            backtoast=Toast.makeText(getApplicationContext(),"press again to exit",Toast.LENGTH_SHORT);
            backtoast.show();
        }
        backpress=System.currentTimeMillis();
    }
}
