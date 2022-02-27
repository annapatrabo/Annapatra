package com.example.annapatra;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class receiverlogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiverlogin);
        getSupportActionBar().hide();

        EditText etReceiverMail=findViewById(R.id.etReceiverMail);
        EditText etReceiverpassword=findViewById(R.id.etReceiverpassword);
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();

        Button ReceiverLogin=findViewById(R.id.btnReceiverLogin);
        ReceiverLogin.setOnClickListener(new View.OnClickListener() {

            String REmail=etReceiverMail.getText().toString();
            String RPassword=etReceiverpassword.getText().toString();
            @Override
            public void onClick(View view) {

//                if(!REmail.contains("@") || !REmail.contains(".") )
//                {
//                    etReceiverMail.setError("Invalid Email");
//                    return;
//                }
//                if (RPassword.isEmpty())
//                {
//                    etReceiverpassword.setError("Password can't be empty");
//                    return;
//                }
//                if (REmail.length()<6)
//                {
//                    etReceiverpassword.setError("Password should be atleast 8 char long");
//                    return;
//                }

                firebaseAuth.signInWithEmailAndPassword(REmail,RPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
//                            SharedPreferences sharedPreferences =getSharedPreferences(splash.PREFS_NAME,0);
//                            SharedPreferences.Editor editor=sharedPreferences.edit();
//                            editor.putBoolean("hasLoggedIn",true);
//                            editor.commit();
                            Toast.makeText(receiverlogin.this,"Login Successfully",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(receiverlogin.this, receiverdash.class));
                            finish();
                        }
                        else {
                            Toast.makeText(receiverlogin.this,"Wrong Email and Password",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });

            }
        });
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