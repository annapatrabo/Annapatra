package com.example.annapatra;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class receiverregister extends AppCompatActivity {

    String url="https://annapatrabo-4e59d-default-rtdb.firebaseio.com/";
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl(url);
    String userID;

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
            }
        });

        Button btnReceiverSignUp=findViewById(R.id.btnReceiverSignUp);
        final EditText etReceiverFN=findViewById(R.id.etReceiverFN);
        final EditText etReceiverLN=findViewById(R.id.etReceiverLN);
        final EditText etReceiverEmail=findViewById(R.id.etReceiverEmail);
        final EditText etReceiverPassword=findViewById(R.id.etReceiverPassword);
        final EditText etReceiverCPassword=findViewById(R.id.etReceiverCPassword);
        final EditText etReceiverMob=findViewById(R.id.etReceiverMob);
        final EditText etReceiverUniqueNo=findViewById(R.id.etReceiverUniqueNo);
        final EditText etReceiverOrganizationName=findViewById(R.id.etReceiverOrganizatioName);
        firebaseAuth=FirebaseAuth.getInstance();

        btnReceiverSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String ReceiverFN= etReceiverFN.getText().toString();
                final String ReceiverLN= etReceiverLN.getText().toString();
                final String ReceiverEmail= etReceiverEmail.getText().toString();
                final String ReceiverPassword= etReceiverPassword.getText().toString();
                final String ReceiverCPassword= etReceiverCPassword.getText().toString();
                final String ReceiverMob= etReceiverMob.getText().toString();
                final String ReceiverUniqueNo= etReceiverUniqueNo.getText().toString();
                final String ReceiverOrganizationName= etReceiverOrganizationName.getText().toString();

                if(ReceiverFN.isEmpty())
                {
                    etReceiverFN.setError("Field cannot be left blank");
                    return;
                }

                if(ReceiverLN.isEmpty())
                {
                    etReceiverLN.setError("Field cannot be left blank");
                    return;
                }

                if(ReceiverEmail.isEmpty())
                {
                    etReceiverEmail.setError("Field cannot be left blank");
                    return;
                }

                if(!ReceiverEmail.contains("@") || !ReceiverEmail.contains("."))
                {
                    etReceiverEmail.setError("Invalid Email");
                }

                if(ReceiverPassword.isEmpty())
                {
                    etReceiverPassword.setError("Field cannot be left blank");
                    return;
                }

                if(ReceiverPassword.length()<6)
                {
                    etReceiverPassword.setError("Password should be at least 6 character long");
                }

                if(ReceiverCPassword.isEmpty())
                {
                    etReceiverCPassword.setError("Field cannot be left blank");
                    return;
                }
                if(!ReceiverPassword.equals(ReceiverCPassword))
                {
                    Toast.makeText(receiverregister.this, "Above password Doesn't match!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(ReceiverMob.isEmpty())
                {
                    etReceiverMob.setError("Field cannot be left blank");
                    return;
                }
                if(ReceiverMob.length()<10)
                {
                    etReceiverMob.setError("Mobile Number should be at least 10 character long");
                }

                if(ReceiverUniqueNo.isEmpty())
                {
                    etReceiverUniqueNo.setError("Field cannot be left blank");
                    return;
                }

                if(ReceiverOrganizationName.isEmpty())
                {
                    etReceiverOrganizationName.setError("Field cannot be left blank");
                    return;
                }

                if (Patterns.EMAIL_ADDRESS.matcher(ReceiverEmail).matches()) {
                    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("ReceiverUsers");
                    databaseReference.orderByChild("MobileNumber").equalTo(ReceiverMob).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                Toast.makeText(receiverregister.this,"User already registered",Toast.LENGTH_SHORT).show();
                            }
                            else {

                                firebaseAuth.createUserWithEmailAndPassword(ReceiverEmail, ReceiverPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        userID= firebaseAuth.getUid();
                                        databaseReference.child(userID).child("Email").setValue(ReceiverEmail);
                                        databaseReference.child(userID).child("FirstName").setValue(ReceiverFN);
                                        databaseReference.child(userID).child("LastName").setValue(ReceiverLN);
                                        databaseReference.child(userID).child("Password").setValue(ReceiverPassword);
                                        databaseReference.child(userID).child("MobileNumber").setValue(ReceiverMob);
                                        databaseReference.child(userID).child("UniqueNumber").setValue(ReceiverUniqueNo);
                                        databaseReference.child(userID).child("OrganizationName").setValue(ReceiverOrganizationName);
                                        startActivity(new Intent(receiverregister.this,receiverdash.class));
                                        finish();
                                        Toast.makeText(receiverregister.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(receiverregister.this,"Database Error",Toast.LENGTH_SHORT).show();
                        }
                    });
                }else etReceiverEmail.setError("Enter Correct Email");

            }
        });
    }
}
