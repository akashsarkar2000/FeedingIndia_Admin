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
    TextView mRegNum, mCharityEmail;
    EditText mName, mCharityImageUrl, mAddress, mPhoneNo, mDescription;
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
        setContentView(R.layout.activity_edit_charity_all_details);
        Intent intent = getIntent();
        map = new HashMap<String,Object>();
        key = intent.getStringExtra("user_id");
        mSaveButton = findViewById(R.id.donor_info_edit_save_btn);
        mToolbar = findViewById(R.id.charity_all_details_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Edit Charity Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Charity").child(key);

//        mAuth = FirebaseAuth.getInstance();
//        final String user_id = getIntent().getStringExtra("user_id");
//        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();

        //PROGRESS
        mProgressDialog = new ProgressDialog(this);

        mRegNum = findViewById(R.id.edit_regNum);
        mCharityEmail = findViewById(R.id.edit_charity_email);
        mName = findViewById(R.id.edit_charity_name);
        mCharityImageUrl = findViewById(R.id.edit_charity_image_url);
        mAddress = findViewById(R.id.edit_charity_address);
        mPhoneNo = findViewById(R.id.edit_charity_phone);
        mDescription = findViewById(R.id.edit_charity_description);

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
                    mRegNum.setText(usersCharity.getCharityReg());
                    mCharityEmail.setText(usersCharity.getEmail());
                    mName.setText(usersCharity.getCharity_name());
                    mPhoneNo.setText(usersCharity.getPhone());
                    mCharityImageUrl.setText(usersCharity.getImage());
                    mAddress.setText(usersCharity.getCharity_address());
                    mDescription.setText(usersCharity.getDescription());
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
        mCharityImageUrl.addTextChangedListener(new TextWatcher() {
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
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
    }
}