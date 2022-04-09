package com.dembs.android.mobildenetim.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.adapters.GuncellemeTalebiBekleyenListAdapter;
import com.dembs.android.mobildenetim.models.DuzeltmeTalebi;
import com.dembs.android.mobildenetim.models.DuzeltmeTalebiBekleyen;
import com.dembs.android.mobildenetim.ui.RuhsatDetayActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GuncellemeTalebiListFragment extends Fragment {
    private ArrayList<DuzeltmeTalebi> duzeltmeTalebiBekleyenList = new ArrayList<>();
    private RecyclerView rvDuzeltmeTalebi;
    TextView tvEmptyList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_duzeltme_talebi_list, container, false);
        RuhsatDetayActivity ruhsatDetayActivity = new RuhsatDetayActivity();
        Bundle bundle = this.getArguments();
        duzeltmeTalebiBekleyenList = bundle.getParcelableArrayList("duzeltmeTalebiBekleyenList");
        tvEmptyList=viewGroup.findViewById(R.id.tvEmptyList);
        rvDuzeltmeTalebi = viewGroup.findViewById(R.id.rvBekleyenDuzeltmeTalebi);
        rvDuzeltmeTalebi.setHasFixedSize(true);
        rvDuzeltmeTalebi.setLayoutManager(new LinearLayoutManager(ruhsatDetayActivity));
        if (duzeltmeTalebiBekleyenList.size() > 0) {
            rvDuzeltmeTalebi.setHasFixedSize(true);
            rvDuzeltmeTalebi.setVisibility(View.VISIBLE);
            GuncellemeTalebiBekleyenListAdapter guncellemeTalebiBekleyenListAdapter = new GuncellemeTalebiBekleyenListAdapter(getActivity(), duzeltmeTalebiBekleyenList);
            rvDuzeltmeTalebi.setAdapter(guncellemeTalebiBekleyenListAdapter);

        }else{
            tvEmptyList.setText("Liste Boş !");
            tvEmptyList.setVisibility(View.VISIBLE);}

        return viewGroup;//burada fragmentin görüntsünü döndürüyoruz ve create edildiği yer.
    }


}
