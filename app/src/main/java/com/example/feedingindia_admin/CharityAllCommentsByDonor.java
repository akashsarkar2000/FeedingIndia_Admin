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

public class CharityAllCommentsByDonor extends AppCompatActivity {

    private List<CommentData> commentDataList;
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
        setContentView(R.layout.activity_charity_all_comments_by_donor);

        mToolbar = findViewById(R.id.charity_side_comment_by_donor);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Charity Reviews");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.id_all_charity_comment_list);

        view = this.getCurrentFocus();
        commentDataList = new ArrayList<CommentData>();
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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(CharityAllCommentsByDonor.this,CharityDescription.class);
                intent.putExtra("user_id",charityKey);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Back button clicked", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    private void getAllComments(){
        mUserDatabase.child("Comments").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                commentDataList.clear();
                for (DataSnapshot data : snapshot.getChildren()){
                    CommentData commentData = data.getValue(CommentData.class);
                    commentDataList.add(commentData);
                    CommentAdapter commentAdapter = new CommentAdapter(commentDataList,CharityAllCommentsByDonor.this);
                    recyclerView.setAdapter(commentAdapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}

