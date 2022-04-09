package com.dembs.android.mobildenetim.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.adapters.AracBilgisiSorgulaAdapter;
import com.dembs.android.mobildenetim.models.AracLine;
import com.dembs.android.mobildenetim.utils.AlertDialogManager;

import java.util.ArrayList;
import java.util.Objects;

public class AracBilgisiSorgulaFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.arac_bilgisi_listview2_fragment, container, false);
        View header;
        ArrayList<AracLine> aracList = new ArrayList<>();
        RecyclerView recyclerView = view.findViewById(R.id.rvAracBilgisiList);
       String[] aracBilgisiTitle = Objects.requireNonNull(getActivity()).getResources().getStringArray(R.array.AracBilgisiTitle);

        aracList = getArguments().getParcelableArrayList("aracBilgisi");
        AracBilgisiSorgulaAdapter adapter = new AracBilgisiSorgulaAdapter(Objects.requireNonNull(getActivity()),aracList);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
       recyclerView.setAdapter( adapter );



       // recyclerView.setAdapter(adapter);

        if (aracList != null && aracList.size() < 1) {
            AlertDialogManager alertDialogManager = new AlertDialogManager(getActivity(), "UYARI", "Bu plakaya ait araç bulunamadı..");
            alertDialogManager.createAlertDialog();
        }
        return view;
    }

}
