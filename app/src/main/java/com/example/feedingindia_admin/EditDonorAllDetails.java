package com.example.feedingindia_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditDonorAllDetails extends AppCompatActivity {

    private Toolbar mToolbar;
    TextView mEmail;
    EditText mName, mDonorImageUrl, mAddress, mPhoneNo, mProfession, mStatusDescription;
    Button mSaveButton;
    private DatabaseReference mUsersDatabase;
    private String key;
    private Map<String,Object> map;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private ProgressDialog mProgressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_donor_all_details);

        Intent intent = getIntent();
        map = new HashMap<String,Object>();
        key = intent.getStringExtra("user_id");

        mSaveButton = findViewById(R.id.donor_info_edit_save_btn);
        mToolbar = findViewById(R.id.donor_account_setting_appBar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Edit Donor Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Donor").child(key);

        //PROGRESS
        mProgressDialog = new ProgressDialog(this);


        mEmail = findViewById(R.id.donor_edit_page_email);
        mName = findViewById(R.id.edit_donor_name);
        mProfession = findViewById(R.id.edit_donor_profession);
        mPhoneNo = findViewById(R.id.edit_donor_phone_number);
        mStatusDescription = findViewById(R.id.edit_donor_status);
        mAddress = findViewById(R.id.edit_donor_address);
        mDonorImageUrl = findViewById(R.id.edit_donor_image);
        mSaveButton = findViewById(R.id.donor_info_edit_save_btn);


        listeners();
        getData();
    }


    private void update(){
        mUsersDatabase.updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(EditDonorAllDetails.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditDonorAllDetails.this, DonorDescription.class));
            }
        });
    }


    private void getData(){
        mUsersDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users usersDonors = snapshot.getValue(Users.class);
                try{
                    mEmail.setText(usersDonors.getEmail());
                    mName.setText(usersDonors.getName());
                    mPhoneNo.setText(usersDonors.getPhone());
                    mProfession.setText(usersDonors.getProfession());
                    mDonorImageUrl.setText(usersDonors.getImage());
                    mAddress.setText(usersDonors.getAddress());
                    mStatusDescription.setText(usersDonors.getStatus());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void listeners(){
        mEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                map.put("email",s.toString());
            }
        });
        mName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                map.put("donor_name",s.toString());
            }
        });
        mPhoneNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                map.put("phone",s.toString());
            }
        });
        mDonorImageUrl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                map.put("image",s.toString());
            }
        });
        mAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                map.put("address",s.toString());
            }
        });
        mProfession.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                map.put("profession",s.toString());
            }
        });
        mStatusDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                map.put("status",s.toString());
            }
        });
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
    }
}