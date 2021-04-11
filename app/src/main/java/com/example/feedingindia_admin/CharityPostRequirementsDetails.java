package com.example.feedingindia_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class CharityPostRequirementsDetails extends AppCompatActivity {

    private Button mEditPostRequirements;
    private FirebaseAuth mAuth;
    private DatabaseReference mUserRef;
    private Toolbar mToolbar;
    private DatabaseReference mUsersDatabase;
    private ProgressDialog mProgressDialog;
    private FirebaseUser mCurrentUser;
    TextView mRequirements, mPostDescription, mCharityName;
    ImageView mPostImage;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_post_requirements_details);

        mToolbar = findViewById(R.id.charity_post_requirements_details_admin);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Post & Requirements Charity Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        key = intent.getStringExtra("user_id");

        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Charity").child(key);
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();

        mProgressDialog = new ProgressDialog(CharityPostRequirementsDetails.this);
        mProgressDialog.setTitle("Loading All Details");
        mProgressDialog.setMessage("Please wait while we load all the details...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        mCharityName = findViewById(R.id.charity_name_post_side);
        mRequirements = findViewById(R.id.charity_requirements);
        mPostImage = findViewById(R.id.charity_post_image);
        mPostDescription = findViewById(R.id.charity_post_description);
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
        try {
            getRequirement();
        }catch (Exception e){
            Toast.makeText(this,"404 not found",Toast.LENGTH_SHORT).show();
        }
    }

    private void getRequirement() throws Exception{
        if(this.key == null){
            return;
        }
        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Charity");
        mUsersDatabase.child(this.key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UsersCharity usersCharityData = snapshot.getValue(UsersCharity.class);
                if(usersCharityData != null){
                    Log.i("Data",usersCharityData.getRequirements());
                    mRequirements.setText(usersCharityData.getRequirements());
                    Log.i("Data",usersCharityData.getCharity_name());
                    mCharityName.setText(usersCharityData.getCharity_name());
                    Log.i("Data",usersCharityData.getPost_description());
                    mPostDescription.setText(usersCharityData.getPost_description());
                    Log.i("Data",usersCharityData.getPost_image());
                    Picasso.get().load(usersCharityData.getPost_image()).placeholder(R.drawable.default_image).into(mPostImage);
                    mProgressDialog.dismiss();
                }else {
                    Log.e("error","null data");
                    mProgressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}