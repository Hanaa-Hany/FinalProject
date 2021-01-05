package com.example.finalproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.dataModel.ParticipantDataItem;

import java.util.ArrayList;

public class ParticipantAdapter extends RecyclerView.Adapter<ParticipantAdapter.ParticipantViewHolder> {

    Context context;
    ArrayList<ParticipantDataItem>arrayListParticipant;

    public ParticipantAdapter(Context context, ArrayList arrayListParticipant) {
        this.context = context;
        this.arrayListParticipant = arrayListParticipant;
    }

    @NonNull
    @Override
    public ParticipantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_participant_layout,parent,false);
        ParticipantViewHolder participantViewHolder=new ParticipantViewHolder(view);
        return participantViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ParticipantViewHolder holder, int position) {
        ParticipantDataItem participantDataItem=arrayListParticipant.get(position);
        holder.name.setText(participantDataItem.getName());

    }

    @Override
    public int getItemCount() {
        return arrayListParticipant.size();
    }

    class ParticipantViewHolder extends RecyclerView.ViewHolder {

        TextView name;

        public ParticipantViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.tv_participant_name);
        }
    }
}
