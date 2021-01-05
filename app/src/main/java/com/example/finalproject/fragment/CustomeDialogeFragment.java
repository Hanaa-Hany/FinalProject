package com.example.finalproject.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.example.finalproject.R;
import com.example.finalproject.activity.MainActivity;


public class CustomeDialogeFragment extends DialogFragment {

    Button buttonYes,buttonCancel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_custome_dialoge, container, false);


    }

    @Override
    public void onResume() {

        //to resize custome Dialoge
            int width = getResources().getDimensionPixelSize(R.dimen._300sdp);
            int height = getResources().getDimensionPixelSize(R.dimen._200sdp);
            getDialog().getWindow().setLayout(width, height);
            // Call super onResume after sizing

        super.onResume();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonYes=view.findViewById(R.id.btn_yes);
        buttonCancel=view.findViewById(R.id.btn_cancel);


        //to handel buttomDialog Action
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

    }




}