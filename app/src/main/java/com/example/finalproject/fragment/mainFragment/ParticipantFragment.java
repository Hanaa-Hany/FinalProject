package com.example.finalproject.fragment.mainFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.R;
import com.example.finalproject.activity.MainActivity;
import com.example.finalproject.adapter.ParticipantAdapter;
import com.example.finalproject.dataModel.ParticipantDataItem;

import java.util.ArrayList;


public class ParticipantFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<ParticipantDataItem> participantDataItemArrayList=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_participant, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView=view.findViewById(R.id.recycler);
        setParticipantDataItemArrayList();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        ParticipantAdapter participantAdapter=new ParticipantAdapter(getContext(),participantDataItemArrayList);
        recyclerView.setAdapter(participantAdapter);

    }
    public void setParticipantDataItemArrayList(){

        ParticipantDataItem participantDataItem=new ParticipantDataItem("Hanaa Hany");
        participantDataItemArrayList.add(participantDataItem);


        ParticipantDataItem participantDataItem1=new ParticipantDataItem("Aya Abed");
        participantDataItemArrayList.add(participantDataItem1);

        ParticipantDataItem participantDataItem2=new ParticipantDataItem("Hager Gom3a");
        participantDataItemArrayList.add(participantDataItem2);

        ParticipantDataItem participantDataItem3=new ParticipantDataItem("Bassant Hamouda");
        participantDataItemArrayList.add(participantDataItem3);

        ParticipantDataItem participantDataItem4=new ParticipantDataItem("Hanaa Hany");
        participantDataItemArrayList.add(participantDataItem4);

        ParticipantDataItem participantDataItem5=new ParticipantDataItem("Hanaa Hany");
        participantDataItemArrayList.add(participantDataItem5);

        ParticipantDataItem participantDataItem6=new ParticipantDataItem("Hanaa Hany");
        participantDataItemArrayList.add(participantDataItem6);

        ParticipantDataItem participantDataItem7=new ParticipantDataItem("Hanaa Hany");
        participantDataItemArrayList.add(participantDataItem7);

        ParticipantDataItem participantDataItem8=new ParticipantDataItem("Hanaa Hany");
        participantDataItemArrayList.add(participantDataItem8);

        ParticipantDataItem participantDataItem9=new ParticipantDataItem("Hanaa Hany");
        participantDataItemArrayList.add(participantDataItem9);
        ParticipantDataItem participantDataItem10=new ParticipantDataItem("Hanaa Hany");
        participantDataItemArrayList.add(participantDataItem10);
        ParticipantDataItem participantDataItem11=new ParticipantDataItem("Hanaa Hany");
        participantDataItemArrayList.add(participantDataItem11);
        ParticipantDataItem participantDataItem12=new ParticipantDataItem("Hanaa Hany");
        participantDataItemArrayList.add(participantDataItem12);
        ParticipantDataItem participantDataItem13=new ParticipantDataItem("Hanaa Hany");
        participantDataItemArrayList.add(participantDataItem13);
        ParticipantDataItem participantDataItem14=new ParticipantDataItem("Hanaa Hany");
        participantDataItemArrayList.add(participantDataItem14);
        ParticipantDataItem participantDataItem15=new ParticipantDataItem("Hanaa Hany");
        participantDataItemArrayList.add(participantDataItem15);
        ParticipantDataItem participantDataItem16=new ParticipantDataItem("Hanaa Hany");
        participantDataItemArrayList.add(participantDataItem16);
    }
}