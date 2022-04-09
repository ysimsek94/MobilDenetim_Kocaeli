package com.dembs.android.mobildenetim.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.adapters.ReklamBilgisiAdapter;
import com.dembs.android.mobildenetim.models.AracReklamResult;
import com.dembs.android.mobildenetim.ui.SorgulaActivity;
import com.dembs.android.mobildenetim.utils.AlertDialogManager;

import java.util.ArrayList;
import java.util.Objects;

public class ReklamBilgisiFragment2  extends Fragment {
String plaka;
    private ArrayList<AracReklamResult> reklamList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SorgulaActivity sorgulaActivity = new SorgulaActivity();

        View view = inflater.inflate(R.layout.fragment_reklam_bilgisi2, container, false);
        View header;

        this.reklamList=getArguments().getParcelableArrayList("reklamList");
        this.plaka=getArguments().getString("plaka");

        RecyclerView rvReklamBilgisiList = view.findViewById(R.id.rvReklamBilgisiList);
        rvReklamBilgisiList.setHasFixedSize(true);
        rvReklamBilgisiList.setLayoutManager(new LinearLayoutManager(sorgulaActivity));

        ReklamBilgisiAdapter adapter = new ReklamBilgisiAdapter(Objects.requireNonNull(getActivity()),reklamList,plaka);
        rvReklamBilgisiList.setAdapter(adapter);

        if (reklamList.size()<1){
            AlertDialogManager alertDialogManager = new AlertDialogManager(getActivity(), "UYARI", "Bu plakaya ait reklam bilgisi bulunamadı..");
            alertDialogManager.createAlertDialog();
        }


        return view;
    }
}
