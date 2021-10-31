package com.example.ocrapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity5 extends AppCompatActivity {
    EditText etplace;
    Button btsubmit;
    TextView tvaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);
        etplace=findViewById(R.id.et_place);
        btsubmit=findViewById(R.id.bt_submit);
        tvaddress=findViewById(R.id.tv_address);
        btsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bn = getIntent().getExtras();
                String name = bn.getString("abc");
                etplace.setText(String.valueOf(name));
                GeoLocation geoLocation = new GeoLocation();
                geoLocation.getAddress(name,getApplicationContext(),new GeoHandler());
            }
        });
    }

    private class GeoHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            String address;
            switch (msg.what){
                case 1:
                    Bundle bundle= msg.getData();
                    address=bundle.getString("address");
                    break;
                default:
                    address=null;
            }
            tvaddress.setText(address);
        }
    }
}