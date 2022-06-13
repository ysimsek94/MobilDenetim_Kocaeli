package com.dembs.android.mobildenetim.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.adapters.BelgeSorgulaAdapter;
import com.dembs.android.mobildenetim.models.BelgeLineResult;
import com.dembs.android.mobildenetim.models.BelgeOzetLine;
import com.dembs.android.mobildenetim.ui.SorgulaActivity;
import com.dembs.android.mobildenetim.utils.AlertDialogManager;

import java.util.ArrayList;

public class BelgeSorgulamaFragment extends Fragment {
    DividerItemDecoration dividerItemDecoration;
    ArrayList<BelgeOzetLine> belgeArrayList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

            SorgulaActivity sorgulaActivity = new SorgulaActivity();
            View view = inflater.inflate(R.layout.fragment_belge_sorgulama, container, false);

            RecyclerView myrv =view.findViewById(R.id.rvBelgeList);
            myrv.setHasFixedSize(true);
            myrv.setLayoutManager(new LinearLayoutManager(sorgulaActivity));

            belgeArrayList=getArguments().getParcelableArrayList("belgeList");
            BelgeSorgulaAdapter myAdapter = new BelgeSorgulaAdapter(getContext(),belgeArrayList);
            myrv.setAdapter(myAdapter);
            if (belgeArrayList.size()<1){
                AlertDialogManager alertDialogManager = new AlertDialogManager(getActivity(), "UYARI", "Bu plakaya ait Belge bulunamadı..");
                alertDialogManager.createAlertDialog();
            }
            return  view;
        }
}
