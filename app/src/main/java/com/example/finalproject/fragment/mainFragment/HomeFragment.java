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
import android.widget.Adapter;

import com.example.finalproject.R;
import com.example.finalproject.adapter.HomeAdapter;
import com.example.finalproject.dataModel.HomeDataItem;

import java.util.ArrayList;

import ru.tinkoff.scrollingpagerindicator.ScrollingPagerIndicator;


public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    HomeAdapter adapter;
    ArrayList<HomeDataItem>homeDataItemArrayList=new ArrayList<>();
    ScrollingPagerIndicator recyclerIndicator;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();
        setHomeDataItems();
        //set layoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        //object from adapter
        adapter=new HomeAdapter(getContext(),homeDataItemArrayList);
        recyclerView.setAdapter(adapter);

        //attach indicators
        recyclerIndicator.attachToRecyclerView(recyclerView);
    }

    //define views
    private void initViews() {

        recyclerView=getView().findViewById(R.id.recycler_home);
         recyclerIndicator = getView().findViewById(R.id.indicator);

    }

    public void setHomeDataItems(){

        HomeDataItem homeDataItem=new HomeDataItem(R.drawable.ic_bill_gates,"Bill Gates");
        HomeDataItem homeDataItem1=new HomeDataItem(R.drawable.ic_bill_gates,"Bill Gates");
        HomeDataItem homeDataItem2=new HomeDataItem(R.drawable.ic_bill_gates,"Bill Gates");
        HomeDataItem homeDataItem3=new HomeDataItem(R.drawable.ic_bill_gates,"Bill Gates");
        HomeDataItem homeDataItem4=new HomeDataItem(R.drawable.ic_bill_gates,"Bill Gates");

        homeDataItemArrayList.add(homeDataItem);
        homeDataItemArrayList.add(homeDataItem1);
        homeDataItemArrayList.add(homeDataItem2);
        homeDataItemArrayList.add(homeDataItem3);
        homeDataItemArrayList.add(homeDataItem4);
    }
}