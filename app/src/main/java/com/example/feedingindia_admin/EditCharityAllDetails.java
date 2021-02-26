package com.example.feedingindia_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditCharityAllDetails extends AppCompatActivity {

    private Toolbar mToolbar;
    TextView mRegNum, mCharityEmail, mPassword;
    EditText mName, mAddress, mPhoneNo, mRequirements, mDescription, mPostDetails;
    Button mSaveButton;
    private FirebaseAuth mAuth;
    private DatabaseReference mUsersDatabase;
    private FirebaseUser mCurrentUser;
    private ProgressDialog mProgressDialog;
    private String key;
    private Map<String,Object> map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_charity_all_details);
        Intent intent = getIntent();
        map = new HashMap<String,Object>();
        key = intent.getStringExtra("user_id");
        mSaveButton = findViewById(R.id.donor_info_edit_save_btn);
        mToolbar = findViewById(R.id.charity_list_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Edit Charity Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        mAuth = FirebaseAuth.getInstance();
//        final String user_id = getIntent().getStringExtra("user_id");
        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Charity").child(key);
//        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
//

        //PROGRESS
        mProgressDialog = new ProgressDialog(this);

//        String charityReg = getIntent().getStringExtra("charityReg");
//        String charity_address = getIntent().getStringExtra("charity_address");
//        String charity_name = getIntent().getStringExtra("charity_name");
//        String description = getIntent().getStringExtra("description");
//        String email = getIntent().getStringExtra("email");
//        String password = getIntent().getStringExtra("password");
//        String phone = getIntent().getStringExtra("phone");
//        String post_description = getIntent().getStringExtra("post_description");
//        String requirements = getIntent().getStringExtra("requirements");

        mRegNum = findViewById(R.id.edit_regNum);
        mCharityEmail = findViewById(R.id.edit_charity_email);
        mPassword = findViewById(R.id.edit_charity_password);
        mName = findViewById(R.id.edit_charity_name);
        mAddress = findViewById(R.id.edit_charity_address);
        mPhoneNo = findViewById(R.id.edit_charity_phone);
        mRequirements = findViewById(R.id.edit_charity_requirements);
        mDescription = findViewById(R.id.edit_charity_description);
        mPostDetails = findViewById(R.id.edit_charity_post_details);
//
//
//        mRegNum.setText(charityReg);
//        mCharityEmail.setText(email);
//        mPassword.setText(password);
//        mName.setText(charity_name);
//        mAddress.setText(charity_address);
//        mPhoneNo.setText(phone);
//        mRequirements.setText(requirements);
//        mDescription.setText(description);
//        mPostDetails.setText(post_description);

        listeners();
        getData();
    }

    private void update(){
        mUsersDatabase.updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(EditCharityAllDetails.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditCharityAllDetails.this,CharityDescription.class));
            }
        });
    }

    private void getData(){
        mUsersDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UsersCharity usersCharity = snapshot.getValue(UsersCharity.class);
                try{
                    mName.setText(usersCharity.getCharity_name());
                    mAddress.setText(usersCharity.getCharity_address());
                    mPhoneNo.setText(usersCharity.getPhone());
                    mRequirements.setText(usersCharity.getRequirements());
                    mDescription.setText(usersCharity.getDescription());
                    mPostDetails.setText(usersCharity.getPost_description());
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
        mName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                map.put("charity_name",s.toString());
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
                map.put("charity_address",s.toString());
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
        mDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                map.put("description",s.toString());
            }
        });
        mPostDetails.addTextChangedListener(new TextWatcher() {
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
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
    }
}