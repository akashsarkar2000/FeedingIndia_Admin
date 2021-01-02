package com.example.feedingindia_admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddUserSelection extends AppCompatActivity {

    Button addCharity, addDonor;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_selection);

        addCharity = findViewById(R.id.add_charity);
        addDonor = findViewById(R.id.add_donor);

        mToolbar = findViewById(R.id.add_user_selection_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Select User");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addCharity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), AddCharity.class);
                startActivity(intent);
            }
        });

        addDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), AddDonor.class);
                startActivity(intent);
            }
        });
    }
}
