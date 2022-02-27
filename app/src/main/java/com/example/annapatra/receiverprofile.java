package com.example.annapatra;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class receiverprofile extends AppCompatActivity {
    DatabaseReference databaseReference;
    DatabaseError databaseError;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    private TextView REmail;
    private TextView ROrgName;
    private TextView RMob;
    String userID="";
    String val="trt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiverprofile);
        getSupportActionBar().hide();

        FirebaseUser duser=firebaseAuth.getInstance().getCurrentUser();
        userID=duser.getUid();
        REmail=findViewById(R.id.tvReceiverEmail);
        ROrgName=findViewById(R.id.tvOrgName);
        RMob=findViewById(R.id.tvReceiverMob);
        databaseReference= firebaseDatabase.getInstance().getReference().child("ReceiverUsers").child(userID);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    String val = snapshot.child("Email").getValue().toString();
                    String val2 = snapshot.child("OrganizationName").getValue().toString();
                    String val3 = snapshot.child("MobileNumber").getValue().toString();;
                    REmail.setText(val);
                    ROrgName.setText(val2);
                    RMob.setText(val3);
                }
                else{
                    Toast.makeText(receiverprofile.this,"Login ully",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        REmail=findViewById(R.id.tvReceiverEmail);


    }

}
