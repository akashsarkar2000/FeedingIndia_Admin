package com.example.feedingindia_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddCharity extends AppCompatActivity {

    private Toolbar mToolbar;
    EditText charityName, charityEmail, charityPhone, charityPassword, charityRegistration;
    Button charityRegister;
    TextView login;
    boolean isNameValid, isEmailValid, isPhoneValid, isPasswordValid, isRegistrationValid;
    TextInputLayout nameError, emailError, phoneError, passError, registrationError;
    private FirebaseAuth mAuth;
    private ProgressDialog mRegProgress;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_charity);
        mRegProgress = new ProgressDialog(this);

        mToolbar = findViewById(R.id.add_charity_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Add Charity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        charityName = findViewById(R.id.add_charity_name);
        charityEmail = findViewById(R.id.add_charity_email);
        charityPhone = findViewById(R.id.add_charity_phone);
        charityRegistration =  findViewById(R.id.add_charity_registration);
        charityPassword = findViewById(R.id.add_charity_password);

        charityRegister = findViewById(R.id.add_charity_button);

        nameError = findViewById(R.id.nameError);
        emailError = findViewById(R.id.emailError);
        phoneError = findViewById(R.id.phoneError);
        passError = findViewById(R.id.passError);
        registrationError =  findViewById(R.id.registrationError);


        // FIREBASE AUTH
        mAuth = FirebaseAuth.getInstance();

        charityRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String charity_name = charityName.getText().toString();
                String email = charityEmail.getText().toString();
                String phone = charityPhone.getText().toString();
                String charityReg = charityRegistration.getText().toString();
                String password = charityPassword.getText().toString();
                SetValidation();
                if (!TextUtils.isEmpty(charity_name) || !TextUtils.isEmpty(email) || !TextUtils.isEmpty(phone) || !TextUtils.isEmpty(charityReg) || !TextUtils.isEmpty(password)){
                    // PROGRESS BAR //
                    mRegProgress.setTitle("Registering User");
                    mRegProgress.setMessage("Please wait while we create your account !");
                    mRegProgress.setCanceledOnTouchOutside(false);
                    mRegProgress.show();
                    register_user(charity_name, email, phone, charityReg, password);
                }

            }
        });

    }



    private void register_user(final String charity_name, final String email, final String phone, final String charityReg, final String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                    assert current_user != null;
                    String uid = current_user.getUid();
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Charity").child(uid);
                    HashMap<String,String> userMap = new HashMap<>();
                    userMap.put("charity_name",charity_name);
                    userMap.put("email",email);
                    userMap.put("phone",phone);
                    userMap.put("charityReg",charityReg);
                    userMap.put("password", password);
                    userMap.put("charity_address","Hi, This is our charity address");
                    userMap.put("status","Hi there, I'm using Feeding India Application");
                    userMap.put("description","Hi, This is our charity description");
                    userMap.put("requirements","Currently no requirements are available from charity, check after uploading details");
                    userMap.put("post_description","Write post/requirements description");
                    userMap.put("post_image","default");
                    userMap.put("image","default");
                    userMap.put("thumb_image","default");
                    mDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                mRegProgress.dismiss();
                                Intent mainIntent = new Intent(AddCharity.this, MainActivity.class);
                                Toast.makeText(AddCharity.this,"Charity Account created successfully, check database",Toast.LENGTH_LONG).show();
                                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(mainIntent);
                                finish();
                            }
                        }
                    });
                }
                else{
                    mRegProgress.hide();
                    Toast.makeText(AddCharity.this,"Cannot Sign in. Please check the form and try again",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void SetValidation() {
        // Check for a valid name.
        if (charityName.getText().toString().isEmpty()) {
            nameError.setError(getResources().getString(R.string.name_error));
            isNameValid = false;
        } else  {
            isNameValid = true;
            nameError.setErrorEnabled(false);
        }

        // Check for a valid email address.
        if (charityEmail.getText().toString().isEmpty()) {
            emailError.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(charityEmail.getText().toString()).matches()) {
            emailError.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else  {
            isEmailValid = true;
            emailError.setErrorEnabled(false);
        }

        // Check for a valid phone number.
        if (charityPhone.getText().toString().isEmpty()) {
            phoneError.setError(getResources().getString(R.string.phone_error));
            isPhoneValid = false;
        } else  {
            isPhoneValid = true;
            phoneError.setErrorEnabled(false);
        }

        // Check for a registration name.
        if (charityRegistration.getText().toString().isEmpty()) {
            registrationError.setError(getResources().getString(R.string.registration_error));
            isRegistrationValid = false;
        } else  {
            isRegistrationValid = true;
            registrationError.setErrorEnabled(false);
        }

        // Check for a valid password.
        if (charityPassword.getText().toString().isEmpty()) {
            passError.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (charityPassword.getText().length() < 6) {
            passError.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;
            passError.setErrorEnabled(false);
        }

        if (isNameValid && isEmailValid && isPhoneValid && isPasswordValid && isRegistrationValid) {
            Toast.makeText(getApplicationContext(), "Please Wait", Toast.LENGTH_SHORT).show();
        }

    }

}


