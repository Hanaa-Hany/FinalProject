package com.example.finalproject.fragment.mainFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.adapter.ParticipantAdapter;
import com.example.finalproject.dataModel.ParticipantDataItem;
import com.example.finalproject.fragment.signFragment.SignInFragment;

import java.util.ArrayList;


public class ParticipantFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<ParticipantDataItem> participantDataItemArrayList = new ArrayList<>();
    String Nickname;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_participant, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Nickname = (String) getActivity().getIntent().getExtras().getString(SignInFragment.NICKNAME);

        recyclerView = view.findViewById(R.id.recycler);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        ParticipantAdapter participantAdapter = new ParticipantAdapter(getContext(), participantDataItemArrayList);
        ParticipantDataItem participantDataItem=new ParticipantDataItem(Nickname);
        participantDataItemArrayList.add(participantDataItem);
        participantAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(participantAdapter);

    }


}