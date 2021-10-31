package com.example.ocrapplication;


import  androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class
activity2 extends AppCompatActivity {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        EditText display = findViewById(R.id.editview);
        EditText city= findViewById(R.id.editTextTextPersonName3);
        EditText state= findViewById(R.id.editTextTextPersonName4);
        EditText country=findViewById(R.id.editTextTextPersonName5);
        btn=findViewById(R.id.button_add);
        Bundle bn = getIntent().getExtras();
        String name = bn.getString("abc");
        display.setText(String.valueOf(name));
        String getcity= city.getText().toString();
        String getstate=state.getText().toString();
        String getcountry=country.getText().toString();



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity2.this,Activity3.class);
                intent.putExtra("abc",name);
                intent.putExtra("b",getcity);
                intent.putExtra("c",getstate);
                intent.putExtra("d",getcountry);
                startActivity(intent);
            }
        });



    }
}