package com.example.feedingindia_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

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

public class DonorDescription extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mUserRef;
    private Toolbar mToolbar;
    private RecyclerView mUsersList;
    private ImageView mDonorImages;
    private TextView mDonorName, mDonorAddress, mDonorEmail, mDonorPhone, mDonorProfession, mDonorPass, mDonorStatus;
    private Button mEditInfo;
    private DatabaseReference mUsersDatabase;
    private DatabaseReference mFriendReqDatabase;
    private ProgressDialog mProgressDialog;
    private FirebaseUser mCurrentUser;
    private DatabaseReference mFriendDatabase;
    private DatabaseReference mRootRef;
    private String mCurrent_state;
    String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_description);

        mAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        key = intent.getStringExtra("user_id");

        mToolbar = findViewById(R.id.donor_description_admin);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Donor Description");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final String user_id = getIntent().getStringExtra("user_id");
        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Donor").child(user_id);
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();

        mDonorImages = findViewById(R.id.donor_description_image);
        mDonorName = findViewById(R.id.donor_description_name);
        mDonorAddress = findViewById(R.id.donor_description_address);
        mDonorEmail = findViewById(R.id.donor_description_email);
        mDonorPhone = findViewById(R.id.donor_description_phone);
        mDonorProfession = findViewById(R.id.donor_description_profession);
        mDonorStatus = findViewById(R.id.donor_description_status);
        mEditInfo = findViewById(R.id.edit_donor_detail_button);


        if (mAuth.getCurrentUser() != null) {
            mUserRef = FirebaseDatabase.getInstance().getReference().child("Users").child("Donor").child(mAuth.getCurrentUser().getUid());
        }

        mEditInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), EditDonorAllDetails.class);
                intent.putExtra("user_id",key);
                startActivity(intent);
            }
        });

        mProgressDialog = new ProgressDialog(DonorDescription.this);
        mProgressDialog.setTitle("Loading Donor");
        mProgressDialog.setMessage("Please wait while we load the donor data...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();



        mUsersDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String donor_name = Objects.requireNonNull(dataSnapshot.child("donor_name").getValue()).toString();
                String email = Objects.requireNonNull(dataSnapshot.child("email").getValue()).toString();
                String phone = Objects.requireNonNull(dataSnapshot.child("phone").getValue()).toString();
                String profession = Objects.requireNonNull(dataSnapshot.child("profession").getValue()).toString();
                String image = Objects.requireNonNull(dataSnapshot.child("image").getValue()).toString();
                String status = Objects.requireNonNull(dataSnapshot.child("status").getValue()).toString();
                String address = Objects.requireNonNull(dataSnapshot.child("address").getValue()).toString();


                Picasso.get().load(image).placeholder(R.drawable.default_image).into(mDonorImages);
                mDonorName.setText(donor_name);
                mDonorProfession.setText(profession);
                mDonorEmail.setText(email);
                mDonorPhone.setText(phone);
                mDonorStatus.setText(status);
                mDonorAddress.setText(address);

                mProgressDialog.dismiss();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}
