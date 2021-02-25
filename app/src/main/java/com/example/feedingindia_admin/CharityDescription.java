package com.example.feedingindia_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

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
    private RecyclerView mUsersList;
    private ImageView mCharityImages;
    private TextView mCharityAddress;
    private TextView mCharityPhone;
    private TextView mCharityName;
    private TextView mCharityEmail;
    private TextView mCharityReq;
    private TextView mCharityReg;
    private TextView mCharityPass;
    private TextView mCharityStatus;
    private Button mPostButton, mContactButton, mFoodButton, mMoneyButton;
    private DatabaseReference mUsersDatabase;
    private DatabaseReference mFriendReqDatabase;
    private ProgressDialog mProgressDialog;
    private FirebaseUser mCurrentUser;
    private DatabaseReference mFriendDatabase;
    private DatabaseReference mRootRef;
    private String mCurrent_state;
    Button mEditInfo, mEditPhoto;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_description);

        mAuth = FirebaseAuth.getInstance();

        mToolbar = findViewById(R.id.charity_description_admin);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Charity Description");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final String user_id = getIntent().getStringExtra("user_id");
        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Charity").child(user_id);
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();

        mCharityImages = findViewById(R.id.charity_image);
        mCharityName = findViewById(R.id.donor_name);
        mCharityAddress = findViewById(R.id.charity_address);
        mCharityEmail = findViewById(R.id.donor_email);
        mCharityPhone = findViewById(R.id.charity_phone);
        mCharityReg = findViewById(R.id.charity_registration);
        mCharityReq = findViewById(R.id.charity_requirements);
        mCharityPass = findViewById(R.id.charity_password);
        mCharityStatus = findViewById(R.id.charity_status);
        mEditInfo = findViewById(R.id.edit_charity_detail_button);
        mEditPhoto = findViewById(R.id.edit_charity_image_button);

        mEditInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), EditCharityAllDetails.class);
                startActivity(intent);
            }
        });

        if (mAuth.getCurrentUser() != null) {
            mUserRef = FirebaseDatabase.getInstance().getReference().child("Users").child("Donor").child(mAuth.getCurrentUser().getUid());
        }

        mProgressDialog = new ProgressDialog(CharityDescription.this);
        mProgressDialog.setTitle("Loading Charity data");
        mProgressDialog.setMessage("Please wait while we load the charity data.");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();


        mUsersDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String charity_name = Objects.requireNonNull(dataSnapshot.child("charity_name").getValue()).toString();
                String charity_address = Objects.requireNonNull(dataSnapshot.child("charity_address").getValue()).toString();
                String email = Objects.requireNonNull(dataSnapshot.child("email").getValue()).toString();
                String phone = Objects.requireNonNull(dataSnapshot.child("phone").getValue()).toString();
                String charity_reg = Objects.requireNonNull(dataSnapshot.child("charityReg").getValue()).toString();
                String requirements = Objects.requireNonNull(dataSnapshot.child("requirements").getValue()).toString();
                String pass = Objects.requireNonNull(dataSnapshot.child("password").getValue()).toString();
                String image = Objects.requireNonNull(dataSnapshot.child("image").getValue()).toString();
                String status = Objects.requireNonNull(dataSnapshot.child("description").getValue()).toString();

                mCharityName.setText(charity_name);
                mCharityReq.setText(requirements);
                mCharityEmail.setText(email);
                mCharityReg.setText(charity_reg);
                mCharityAddress.setText(charity_address);
                mCharityPhone.setText(phone);
                mCharityPass.setText(pass);
                mCharityStatus.setText(status);

                Picasso.get().load(image).placeholder(R.drawable.default_image).into(mCharityImages);
                mProgressDialog.dismiss();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}
