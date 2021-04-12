package com.example.feedingindia_admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TrustedDonorAdapter extends RecyclerView.Adapter<TrustedDonorAdapter.ChatViewHolder> {

    private List<TrustedDonorData> trustedDonorDataList;
    Context context;

    public TrustedDonorAdapter(List<TrustedDonorData> trustedDonorDataList, Context context) {
        this.trustedDonorDataList = trustedDonorDataList;
        this.context = context;
    }


    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChatViewHolder(LayoutInflater.from(context).inflate(R.layout.trusted_card,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        TrustedDonorData commentData = trustedDonorDataList.get(position);
        try {
            holder.date.setText(commentData.getDatetime());
            holder.email.setText(commentData.getEmail());
            holder.message.setText(commentData.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return trustedDonorDataList != null ? trustedDonorDataList.size() : 0;
    }
    public class ChatViewHolder extends RecyclerView.ViewHolder{
        public TextView email,message,date;
        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.trusted_email);
            message = itemView.findViewById(R.id.trusted_donor_details);
            date = itemView.findViewById(R.id.trusted_donor_details_date);
        }
    }
}