package com.example.feedingindia_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TrustedDonor extends AppCompatActivity {

    private List<TrustedDonorData> trustedDonorDataList;
    private Toolbar mToolbar;
    private EditText editText;
    private ImageButton send;
    private String charityKey;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mUserDatabase;
    private View view;
    private RecyclerView recyclerView;
    String key;
    private RecyclerView mUsersList;
    private DatabaseReference mUsersDatabase;
    private ProgressDialog mProgressDialog;
    private AlertDialog.Builder builder;
    private String deleteUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trusted_donor);

        mToolbar = findViewById(R.id.charity_side_trusted_donor);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Trusted Donor");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.id_all_charity_trusted_donor_list);

        view = this.getCurrentFocus();
        trustedDonorDataList = new ArrayList<TrustedDonorData>();
        charityKey = getIntent().getStringExtra("key");
        firebaseAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        key = intent.getStringExtra("user_id");

        Log.i("mykey",firebaseAuth.getCurrentUser().getEmail());

        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Charity").child(key);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        getAllComments();

    }



    private void getAllComments(){
        mUserDatabase.child("Trusted_Donor").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                trustedDonorDataList.clear();
                for (DataSnapshot data : snapshot.getChildren()){
                    TrustedDonorData commentData = data.getValue(TrustedDonorData.class);
                    trustedDonorDataList.add(commentData);
                    TrustedDonorAdapter commentAdapter = new TrustedDonorAdapter(trustedDonorDataList,TrustedDonor.this);
                    recyclerView.setAdapter(commentAdapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

}