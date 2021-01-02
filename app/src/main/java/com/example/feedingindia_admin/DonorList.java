package com.example.feedingindia_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
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
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class DonorList extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mUsersList;
    private DatabaseReference mUsersDatabase;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_list);

        mToolbar = findViewById(R.id.donor_list_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("All Registered Donor");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Donor");

        mUsersList = findViewById(R.id.donor_all_list);
        mUsersList.setHasFixedSize(true);
        mUsersList.setLayoutManager(new LinearLayoutManager(this));


        mProgressDialog = new ProgressDialog(DonorList.this);
        mProgressDialog.setTitle("Loading All Donors");
        mProgressDialog.setMessage("Please wait while we load all the donors...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

    }

    @Override
    protected void onStart() {
        super.onStart();


        FirebaseRecyclerOptions<Users> options=
                new FirebaseRecyclerOptions.Builder<Users>()
                        .setQuery(mUsersDatabase,Users.class)
                        .setLifecycleOwner(this)
                        .build();

        FirebaseRecyclerAdapter<Users, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Users, UsersViewHolder>(options) {


            @NonNull
            @Override
            public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new UsersViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card_donor, parent, false));
            }

            @Override
            protected void onBindViewHolder(@NonNull UsersViewHolder usersViewHolder, int position, @NonNull Users users) {


                usersViewHolder.setName(users.getName());
                usersViewHolder.setEmail(users.getEmail());
                usersViewHolder.setProfession(users.getProfession());
//                usersViewHolder.setUserImage(users.getThumb_image(),getApplicationContext());

                final String user_id = getRef(position).getKey();

                mProgressDialog.dismiss();

                usersViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent profileIntent = new Intent(DonorList.this, DonorDescription.class);
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

            TextView userNameView = mView.findViewById(R.id.donor_name);
            userNameView.setText(name);

        }

        public void setEmail(String email){

            TextView userNameView = mView.findViewById(R.id.donor_email);
            userNameView.setText(email);

        }

        public void setProfession(String profession){

            TextView userNameView = mView.findViewById(R.id.donor_profession);
            userNameView.setText(profession);

        }

//        public void setUserStatus(String status){
//
//            TextView userStatusView = mView.findViewById(R.id.donor_address);
//            userStatusView.setText(status);
//
//        }



//        public void setUserImage(String thumb_image, Context context){
//
//            CircleImageView userImageView = mView.findViewById(R.id.user_single_image);
//            Picasso.get().load(thumb_image).placeholder(R.drawable.default_image).into(userImageView);
//        }

    }
}
