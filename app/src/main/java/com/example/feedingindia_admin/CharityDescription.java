    package com.example.feedingindia_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class CharityDescription extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mUserRef;
    private Toolbar mToolbar;
    ImageView mCharityImages, mCharityProofImages;
    private TextView mCharityAddress;
    private TextView mCharityPhone;
    private TextView mCharityName;
    private TextView mCharityEmail;
    private TextView mCharityReg;
    private TextView mCharityDescription;
    private DatabaseReference mUsersDatabase;
    private ProgressDialog mProgressDialog;
    private FirebaseUser mCurrentUser;
    Button mEditInfo, mCharityComments, mPostRequirements;
    String key;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_description);


        mAuth = FirebaseAuth.getInstance();


        Intent intent = getIntent();
        key = intent.getStringExtra("user_id");

        mToolbar = findViewById(R.id.charity_description_admin);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Charity Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final String user_id = getIntent().getStringExtra("user_id");
        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Charity").child(user_id);
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();

        mCharityImages = findViewById(R.id.charity_image);
        mCharityProofImages = findViewById(R.id.charity_proof);
        mCharityName = findViewById(R.id.charity_name);
        mCharityAddress = findViewById(R.id.charity_address);
        mCharityEmail = findViewById(R.id.charity_email);
        mCharityPhone = findViewById(R.id.charity_phone);
        mCharityReg = findViewById(R.id.charity_reg);
        mCharityDescription = findViewById(R.id.charity_description);
        mEditInfo = findViewById(R.id.edit_charity_detail_button);
        mPostRequirements = findViewById(R.id.edit_charity_post_requirements__button);
        mCharityComments = findViewById(R.id.edit_charity_comments__button);


        if (mAuth.getCurrentUser() != null) {
            mUserRef = FirebaseDatabase.getInstance().getReference().child("Users").child("Charity").child(mAuth.getCurrentUser().getUid());
        }


        mCharityComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), CharityPostRequirementsDetails.class);
                intent.putExtra("user_id",key);
                startActivity(intent);
            }
        });


        mPostRequirements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), CharityPostRequirementsDetails.class);
                intent.putExtra("user_id",key);
                startActivity(intent);
            }
        });


        mEditInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), EditCharityAllDetails.class);
                intent.putExtra("user_id",key);
                startActivity(intent);
            }
        });



        mProgressDialog = new ProgressDialog(CharityDescription.this);
        mProgressDialog.setTitle("Loading Charity data");
        mProgressDialog.setMessage("Please wait while we load the charity data.");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();



        mUsersDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String image = Objects.requireNonNull(dataSnapshot.child("image").getValue()).toString();
                String proof_url = Objects.requireNonNull(dataSnapshot.child("proof_url").getValue()).toString();
                String charity_name = Objects.requireNonNull(dataSnapshot.child("charity_name").getValue()).toString();
                String charity_address = Objects.requireNonNull(dataSnapshot.child("charity_address").getValue()).toString();
                String email = Objects.requireNonNull(dataSnapshot.child("email").getValue()).toString();
                String phone = Objects.requireNonNull(dataSnapshot.child("phone").getValue()).toString();
                String charity_reg = Objects.requireNonNull(dataSnapshot.child("charityReg").getValue()).toString();
                String description = Objects.requireNonNull(dataSnapshot.child("description").getValue()).toString();

                Picasso.get().load(image).placeholder(R.drawable.default_image).into(mCharityImages);
                Picasso.get().load(proof_url).placeholder(R.drawable.default_image).into(mCharityProofImages);
                mCharityName.setText(charity_name);
                mCharityAddress.setText(charity_address);
                mCharityEmail.setText(email);
                mCharityPhone.setText(phone);
                mCharityReg.setText(charity_reg);
                mCharityDescription.setText(description);

                mProgressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}
