package com.example.zerohunger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;
import java.util.List;

public class Donate_food extends AppCompatActivity {
EditText name_donor,number_donor,address_donor;
Button submit_donor;
Spinner quantity_food;
String[] quandity={"20","30","40","50","100","Above 100"};
DatabaseReference reference;
Food food;
RadioGroup radioGroup;
RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_food);
        name_donor=(EditText)findViewById(R.id.donor_name);
        number_donor=(EditText)findViewById(R.id.donor_number);
        address_donor=(EditText)findViewById(R.id.donor_address);
        quantity_food=(Spinner)findViewById(R.id.food_quandity);
        submit_donor=(Button)findViewById(R.id.donor_submit);
        radioGroup=(RadioGroup)findViewById(R.id.food_rad_group);
        food=new Food();

        reference= FirebaseDatabase.getInstance().getReference().child("food");
        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,quandity);
        myAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        quantity_food.setAdapter(myAdapter);
        submit_donor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food.setDaddress(address_donor.getText().toString());
                food.setDname(name_donor.getText().toString());
                food.setDnumber(number_donor.getText().toString());
                food.setDquandity(quantity_food.getSelectedItem().toString());
                radioButton=(RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
                food.setDtype(radioButton.getText().toString());
                Query query=reference.orderByChild("dnumber").equalTo(name_donor.getText().toString());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        reference.push().setValue(food);
                        Toast.makeText(getApplicationContext(),"successfully registered",Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });


    }
}
