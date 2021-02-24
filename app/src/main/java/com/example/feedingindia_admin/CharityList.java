package com.example.feedingindia_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CharityList extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mUsersList;
    private DatabaseReference mUsersDatabase;
    private ProgressDialog mProgressDialog;
    private AlertDialog.Builder builder;
    private String deleteUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_list);

        mToolbar = findViewById(R.id.charity_list_toolbar);
        builder = new AlertDialog.Builder(this);
        builder.setMessage("Delete") .setTitle("Delete Charity");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("All Registered Charity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Charity");

        mUsersList = findViewById(R.id.charity_all_list);
        mUsersList.setHasFixedSize(true);
        mUsersList.setLayoutManager(new LinearLayoutManager(this));

        mProgressDialog = new ProgressDialog(CharityList.this);
        mProgressDialog.setTitle("Loading All Charities");
        mProgressDialog.setMessage("Please wait while we load all the charities...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
        generateAlertBuilder();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<UsersCharity> options=
                new FirebaseRecyclerOptions.Builder<UsersCharity>()
                        .setQuery(mUsersDatabase,UsersCharity.class)
                        .setLifecycleOwner(this)
                        .build();

        FirebaseRecyclerAdapter<UsersCharity, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<UsersCharity, UsersViewHolder>(options) {


            @NonNull
            @Override
            public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new UsersViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card_charity, parent, false));
            }

            @Override
            protected void onBindViewHolder(@NonNull UsersViewHolder usersViewHolder, int position, @NonNull UsersCharity users_charity) {

                usersViewHolder.setName(users_charity.getName());
                usersViewHolder.setReg(users_charity.getReg());
                usersViewHolder.setEmail(users_charity.getEmail());
//                usersViewHolder.setEmail(users_charity.getEmail());
//                usersViewHolder.setUserImage(users_charity.getThumb_image(),getApplicationContext());
                final String user_id = getRef(position).getKey();
                mProgressDialog.dismiss();
                usersViewHolder.mView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        setDeleteUid(getRef(position).getKey());
                        AlertDialog alert = builder.create();
                        //Setting the title manually
                        alert.setTitle("Delete Charity");
                        alert.show();
                        return true;
                    }
                });
                usersViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent profileIntent = new Intent(CharityList.this, CharityDescription.class);
                        profileIntent.putExtra("user_id",user_id);
                        startActivity(profileIntent);
                    }
                });
            }
        };
        mUsersList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class UsersViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public UsersViewHolder(View itemView){
            super(itemView);
            mView = itemView;
        }

        public void setName(String name){

            TextView userNameView = mView.findViewById(R.id.charity_name);
            userNameView.setText(name);

        }

        public void setReg(String reg){

            TextView userStatusView = mView.findViewById(R.id.charity_register_number);
            userStatusView.setText(reg);

        }

        public void setEmail(String email){

            TextView userStatusView = mView.findViewById(R.id.charity_email);
            userStatusView.setText(email);

        }

//        public void setUserImage(String thumb_image, Context context){
//
//            CircleImageView userImageView = mView.findViewById(R.id.user_single_image);
//            Picasso.get().load(thumb_image).placeholder(R.drawable.default_image).into(userImageView);
//        }

    }
    private void deleteCharity(String uid){
        mUsersDatabase.child(uid).removeValue();
    }

    private void generateAlertBuilder(){

        builder.setMessage("Do you really want to delete this Donor ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        deleteCharity(getDeleteUid());
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();
                    }
                });
    }

    public void setDeleteUid(String deleteUid) {
        this.deleteUid = deleteUid;
    }

    public String getDeleteUid() {
        return deleteUid;
    }
}







