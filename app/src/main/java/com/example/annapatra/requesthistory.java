package com.example.annapatra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class requesthistory extends AppCompatActivity {
    Button sendrqt,getloc;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    TextView dlg, dlt;
    FusedLocationProviderClient fusedLocationProviderClient;
    Location currentLocation;
    LocationManager locationManager;
    String latitude, longitude;
    TextView showLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requesthistory);
        getSupportActionBar().hide();
        final TextView rdfoodname=findViewById(R.id.rdfoodname);
        final TextView rdfoodqty=findViewById(R.id.rdfoodqty);
        final TextView rdnopeople=findViewById(R.id.rdnopeople);
        final TextView rdaddress=findViewById(R.id.rdaddress);
        final TextView rrstatus=findViewById(R.id.rstatus);


        databaseReference= firebaseDatabase.getInstance().getReference().child("Requests").child("f");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    String val1 = snapshot.child("Food Name").getValue().toString();
                    String val2 = snapshot.child("Quantity").getValue().toString();
                    String val3 = snapshot.child("no of people").getValue().toString();
                    String val4 = snapshot.child("address").getValue().toString();
                    String addlt = snapshot.child("Latitude").getValue().toString();
                    String addlg = snapshot.child("Longitude").getValue().toString();;
                    rdfoodname.setText(val1);
                    rdfoodqty.setText(val2);
                    rdnopeople.setText(val3);
                    rdaddress.setText(val4);
                    String rstatus="In Process";
                    rrstatus.setText(rstatus);
                }
                else{
                    Toast.makeText(requesthistory.this,"No Donate Request",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Button declined=findViewById(R.id.rdeclined);
        declined.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference= firebaseDatabase.getInstance().getReference().child("Requests");
                databaseReference.child("f").removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(requesthistory.this,"Request Deleted",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(requesthistory.this,"No Request to delete",Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }
}
