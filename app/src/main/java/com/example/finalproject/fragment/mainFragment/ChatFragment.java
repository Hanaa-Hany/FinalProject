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

import android.widget.EditText;
import android.widget.ImageView;


import com.example.finalproject.R;

import com.example.finalproject.adapter.ChatAdapter;
import com.example.finalproject.dataModel.ChatDataItem;
import com.example.finalproject.fragment.signFragment.SignInFragment;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;


import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {


    private EditText mInputMessageView;
    ChatAdapter adapter;
    RecyclerView recyclerView;
    List<ChatDataItem> arrayList=new ArrayList<>();
    ImageView imageViewSend;
    private Socket socket;
    String Nickname;



    private static final String TAG = "ChatFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            Nickname= (String)getActivity().getIntent().getExtras().getString(SignInFragment.NICKNAME);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      //  messagetxt = getView().findViewById(R.id.message) ;


        initViews();
        adapter = new ChatAdapter(arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
      //  recyclerView.setAdapter(adapter);

        try {
            socket = IO.socket("https://flannel-poutine-04705.herokuapp.com");
            socket.connect();
            socket.emit("join", Nickname);

        } catch (URISyntaxException e) {
            e.printStackTrace();

        }


        imageViewSend.setOnClickListener(v -> {
            if(mInputMessageView.getText().toString().trim().isEmpty()){
               return;
            }
                socket.emit("messagedetection",Nickname,mInputMessageView.getText().toString());

                mInputMessageView.setText(" ");



        });
        socket.on("message", args -> getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                JSONObject data = (JSONObject) args[0];
                try {
                    //extract data from fired event

                    String Nickname = data.getString("senderNickname");
                    String message = data.getString("message");

                    // make instance of message

                    ChatDataItem m = new ChatDataItem(Nickname,message);


                    //add the message to the messageList

                    arrayList.add(m);

                    // add the new updated list to the dapter
                    adapter = new ChatAdapter(arrayList);

                    // notify the adapter to update the recycler view

                    adapter.notifyDataSetChanged();

                    //set the adapter for the recycler view

                    recyclerView.setAdapter(adapter);

                    //to make adapter start from bottom
                    recyclerView.smoothScrollToPosition(recyclerView.getBottom());


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }));

    }

    private void initViews() {
    //    mInputMessageView=requireView().findViewById(R.id.new_message);
        recyclerView=requireView().findViewById(R.id.messagelist);
        imageViewSend=requireView().findViewById(R.id.send);
        mInputMessageView=requireView().findViewById(R.id.message);

    }





}