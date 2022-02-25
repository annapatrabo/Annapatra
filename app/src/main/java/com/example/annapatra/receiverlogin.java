package com.example.annapatra;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class receiverlogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiverlogin);
        getSupportActionBar().hide();
        Button donorpage,btncreatereceiver;
        donorpage=findViewById(R.id.btndonorloginpage);
        donorpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(receiverlogin.this, userselection.class);
                startActivity(intent);
                finish();
            }
        });

        btncreatereceiver=findViewById(R.id.btnreceivercreateaccount);
        btncreatereceiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(receiverlogin.this, receiverregister.class);
                startActivity(intent);
            }
        });
    }
}