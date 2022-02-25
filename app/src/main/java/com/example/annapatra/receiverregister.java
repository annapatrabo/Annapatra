package com.example.annapatra;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class receiverregister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiverregister);
        getSupportActionBar().hide();

        Button receiverlog=findViewById(R.id.receiverlog);

        receiverlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(receiverregister.this,receiverlogin.class));
                finish();
            }
        });
    }
}
