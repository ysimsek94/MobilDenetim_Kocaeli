package com.dembs.android.mobildenetim.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.adapters.ReklamSorgulamaRecyclerViewAdapter;
import com.dembs.android.mobildenetim.models.AracLine;
import com.dembs.android.mobildenetim.ui.SorgulaActivity;
import com.dembs.android.mobildenetim.utils.AlertDialogManager;

import java.util.ArrayList;

public class ReklamSorgulamaFragment extends Fragment {
    private ArrayList<AracLine> aracList = new ArrayList<>();

    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        SorgulaActivity sorgulaActivity = new SorgulaActivity();
        View view = inflater.inflate(R.layout.fragment_reklam_sorgulama, container, false);
        TextView textView=view.findViewById(R.id.tvAracBilgisi);
        RecyclerView myrv =view.findViewById(R.id.rvAracList);
        myrv.setHasFixedSize(true);
        myrv.setLayoutManager(new LinearLayoutManager(sorgulaActivity));

        aracList=getArguments().getParcelableArrayList("aracList");
        ReklamSorgulamaRecyclerViewAdapter myAdapter = new ReklamSorgulamaRecyclerViewAdapter(getContext(),aracList);
        myrv.setAdapter(myAdapter);
        if (aracList.size()<1){
            AlertDialogManager alertDialogManager = new AlertDialogManager(getActivity(), "UYARI", "Bu plakaya ait araç bulunamadı..");
            alertDialogManager.createAlertDialog();
        }
        return  view;
    }
}
