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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import kotlin.jvm.internal.PropertyReference0Impl;

public class EditCharityPostRequirementsDetails extends AppCompatActivity {

    private String key;
    private Map<String,Object> map;
    private Toolbar mToolbar;
    TextView mRegNum, mCharityEmail;
    EditText mImageUrl, mRequirements, mPostDescription;
    Button mSaveButton;
    private DatabaseReference mUsersDatabase;
    private ProgressDialog mProgressDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_charity_post_requirements_details);

        Intent intent = getIntent();
        map = new HashMap<String,Object>();
        key = intent.getStringExtra("user_id");
        mSaveButton = findViewById(R.id.charity_edit_post_requirements_save_button);


        mToolbar = findViewById(R.id.charity_edit_post_requirements_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Edit Charity Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Charity").child(key);


        //PROGRESS
        mProgressDialog = new ProgressDialog(this);

        mRegNum = findViewById(R.id.edit_regNum);
        mCharityEmail = findViewById(R.id.edit_charity_email);
        mImageUrl = findViewById(R.id.edit_charity_images_url);
        mPostDescription = findViewById(R.id.edit_charity_post_description);
        mRequirements = findViewById(R.id.edit_charity_requirements);

        listeners();
        getData();
    }



    private void update(){
        mUsersDatabase.updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(EditCharityPostRequirementsDetails.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditCharityPostRequirementsDetails.this,CharityDescription.class));
            }
        });
    }

    private void getData(){
        mUsersDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UsersCharity usersCharity = snapshot.getValue(UsersCharity.class);
                try{
                    mRegNum.setText(usersCharity.getCharityReg());
                    mCharityEmail.setText(usersCharity.getEmail());
                    mImageUrl.setText(usersCharity.getPost_image());
                    mPostDescription.setText(usersCharity.getPost_description());
                    mRequirements.setText(usersCharity.getRequirements());
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
        mRegNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                map.put("charityReg",s.toString());
            }
        });
        mCharityEmail.addTextChangedListener(new TextWatcher() {
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
        mImageUrl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                map.put("post_image",s.toString());
            }
        });
        mPostDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                map.put("post_description",s.toString());
            }
        });
        mRequirements.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                map.put("requirements",s.toString());
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