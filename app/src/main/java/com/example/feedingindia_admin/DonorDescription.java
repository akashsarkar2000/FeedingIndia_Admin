package com.example.feedingindia_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
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
    private Button mPostButton, mContactButton, mFoodButton, mMoneyButton;
    private DatabaseReference mUsersDatabase;
    private DatabaseReference mFriendReqDatabase;
    private ProgressDialog mProgressDialog;
    private FirebaseUser mCurrentUser;
    private DatabaseReference mFriendDatabase;
    private DatabaseReference mRootRef;
    private String mCurrent_state;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_description);

        mAuth = FirebaseAuth.getInstance();

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
        mDonorPass = findViewById(R.id.donor_description_password);
        mDonorStatus = findViewById(R.id.donor_description_status);



//        if (mAuth.getCurrentUser() != null) {
//            mUserRef = FirebaseDatabase.getInstance().getReference().child("Users").child("Donor").child(mAuth.getCurrentUser().getUid());
//        }

        mProgressDialog = new ProgressDialog(DonorDescription.this);
        mProgressDialog.setTitle("Loading Donor data");
        mProgressDialog.setMessage("Please wait while we load the donor  data.");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();



        mUsersDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String donor_name = Objects.requireNonNull(dataSnapshot.child("donor_name").getValue()).toString();
                String email = Objects.requireNonNull(dataSnapshot.child("email").getValue()).toString();
                String phone = Objects.requireNonNull(dataSnapshot.child("phone").getValue()).toString();
                String profession = Objects.requireNonNull(dataSnapshot.child("profession").getValue()).toString();
                String pass = Objects.requireNonNull(dataSnapshot.child("password").getValue()).toString();
                String image = Objects.requireNonNull(dataSnapshot.child("image").getValue()).toString();
                String status = Objects.requireNonNull(dataSnapshot.child("status").getValue()).toString();

                mDonorName.setText(donor_name);
                mDonorProfession.setText(profession);
                mDonorEmail.setText(email);
                mDonorPhone.setText(phone);
                mDonorPass.setText(pass);
                mDonorStatus.setText(status);

                Picasso.get().load(image).placeholder(R.drawable.default_image).into(mDonorImages);
                mProgressDialog.dismiss();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}
