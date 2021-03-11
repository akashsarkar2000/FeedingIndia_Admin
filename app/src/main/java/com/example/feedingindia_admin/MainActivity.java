package com.example.feedingindia_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mUserRef;
    private Toolbar mToolbar;
    Button charityDetails, donorDetails, addUser, queries;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        charityDetails = findViewById(R.id.charity_list);
        donorDetails = findViewById(R.id.donor_list);
        addUser = findViewById(R.id.add_user);
        queries = findViewById(R.id.queries);

        mAuth = FirebaseAuth.getInstance();

        mToolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Feeding India : Admin");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  /// FOR BACK BUTTON

        charityDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CharityList.class);
                startActivity(intent);
            }
        });

        donorDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DonorList.class);
                startActivity(intent);
            }
        });

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddUserSelection.class);
                startActivity(intent);
            }
        });

        queries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.gmail.com/");
            }
        });

        if(mAuth.getCurrentUser() != null){
            mUserRef = FirebaseDatabase.getInstance().getReference().child("Users").child("Charity").child(mAuth.getCurrentUser().getUid());
        }
    }

    private void gotoUrl(String s) {

        Uri url = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,url));
    }

    // FUNCTION FOR LOGOUT AND LOGIN
    private void sendToStart() {
        Intent startIntent = new Intent (MainActivity.this, LoginPage.class);
        startActivity(startIntent);
        finish();
    }

    // LOGIN AND REDIRECT TO NEXT PAGE //
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null){
            sendToStart();
        }
        else {
//            mUserRef.child("online").setValue("true");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
//            mUserRef.child("online").setValue(ServerValue.TIMESTAMP);
        }
    }

    // DISPLAY OF MAIN MENU //
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    // SIGN OUT //
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.id_main_logout){
            FirebaseAuth.getInstance().signOut();
            sendToStart();  // If Logout Successful then redirect to First page
        }
        return true;
    }




}
