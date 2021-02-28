package com.example.feedingindia_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CharityPostRequirementsDetails extends AppCompatActivity {

    private Button mEditPostRequirements;
    private FirebaseAuth mAuth;
    private DatabaseReference mUserRef;
    private Toolbar mToolbar;
    private DatabaseReference mUsersDatabase;
    private ProgressDialog mProgressDialog;
    private FirebaseUser mCurrentUser;
    String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_post_requirements_details);

        mToolbar = findViewById(R.id.charity_post_requirements_details_admin);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Post & Requirements Charity Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mEditPostRequirements = findViewById(R.id.edit_post_requirements_button);

        mEditPostRequirements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), EditCharityPostRequirementsDetails.class);
                intent.putExtra("user_id",key);
                startActivity(intent);
            }
        });

    }
}