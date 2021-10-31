package com.example.ocrapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Activity3 extends AppCompatActivity {
    EditText edit;
    Button btn,btn_back;
    ListView list;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        btn_back=findViewById(R.id.button_back);
        btn = findViewById(R.id.button2);
        edit = findViewById(R.id.editTextTextPersonName);
        list = findViewById(R.id.listview);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("abc");
        String city = getIntent().getStringExtra("b");
        String state = getIntent().getStringExtra("c");
        String country= getIntent().getStringExtra("d");
        edit.setText(String.valueOf(name));
        edit.append("\n");
        edit.append(city);
        edit.append("\n");
        edit.append(state);
        edit.append("\n");
        edit.append(country);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("data");
        getValue();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sValue = edit.getText().toString().trim();
                String sKey= databaseReference.push().getKey();
                if(sKey!=null){
                    databaseReference.child(sKey).child("value").setValue(sValue);
                    edit.setText("");
                }
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity3.this,activity2.class);
                startActivity(intent);
            }
        });

    }

    private void getValue() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String sValue = dataSnapshot.child("value").getValue(String.class);
                    arrayList.add(sValue);
                }
                list.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
}