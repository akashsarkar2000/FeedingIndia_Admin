package com.example.feedingindia_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditCharityAllDetails extends AppCompatActivity {

    private Toolbar mToolbar;
    TextView mRegNum, mCharityEmail, mPassword;
    EditText mName, mAddress, mPhoneNo, mRequirements, mDescription, mPostDetails;
    Button mSaveButton;
    private FirebaseAuth mAuth;
    private DatabaseReference mUsersDatabase;
    private FirebaseUser mCurrentUser;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_charity_all_details);

        mToolbar = findViewById(R.id.charity_list_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Edit Charity Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        mAuth = FirebaseAuth.getInstance();
//        final String user_id = getIntent().getStringExtra("user_id");
//        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Charity").child(user_id);
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



    }
}