package com.example.annapatra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class receiverdash extends AppCompatActivity {

    public static String PREFS_NAME="MyPrefsFile";
    DatabaseReference databaseReference;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    private FirebaseAuth firebaseAuth;
    DataSnapshot dataSnapshot;
    Adapter adapter;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiverdash);


        navigationView=findViewById(R.id.ReceiverDashNav);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Rprofile:
                        startActivity(new Intent(receiverdash.this,receiverprofile.class));
                        break;
                    case R.id.Rlogout:
                        SignOutReceiver();
                        break;
                    case R.id.followus:
                        Intent browserintent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/___ashish_rana___/"));
                        startActivity(browserintent2);
                        break;
                    case R.id.aboutus:
                        Intent browserintent3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/ashish-rana-448713180"));
                        startActivity(browserintent3);
                        break;
                }
                return false;
            }
        });
    }

    private void SignOutReceiver() {
        SharedPreferences sharedPreferences =getSharedPreferences(splash.PREFS_NAME,0);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("hasLoggedIn",false);
        editor.commit();
        finish();
        firebaseAuth.signOut();
        Toast.makeText(this,"Logout Successfully",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,userselection.class));
    }

    private void setUpToolbar() {
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        actionBarDrawerToggle= new ActionBarDrawerToggle(this,drawerLayout, toolbar,R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
}