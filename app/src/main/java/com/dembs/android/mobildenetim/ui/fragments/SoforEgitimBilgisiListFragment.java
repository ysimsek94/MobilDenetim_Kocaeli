package com.dembs.android.mobildenetim.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.adapters.SoforEgitimListAdapter;
import com.dembs.android.mobildenetim.models.SoforBilgisi;
import com.dembs.android.mobildenetim.models.VarakaBekleyen;
import com.dembs.android.mobildenetim.ui.RuhsatDetayActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SoforEgitimBilgisiListFragment extends Fragment {
    ArrayList<VarakaBekleyen> varakaBekleyenList = new ArrayList<>();
    private RecyclerView rvSofoEgitimList;
    private ArrayList<SoforBilgisi> soforBilgisiList = new ArrayList<>();
    TextView tvEmptyList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_sofor_egitim_bilgisi, container, false);
        RuhsatDetayActivity ruhsatDetayActivity = new RuhsatDetayActivity();
        Bundle bundle = this.getArguments();
        soforBilgisiList = bundle.getParcelableArrayList("soforBilgisiList");
        tvEmptyList=viewGroup.findViewById(R.id.tvEmptyList);
        rvSofoEgitimList = viewGroup.findViewById(R.id.rvSoforEgitimListView);
        rvSofoEgitimList.setHasFixedSize(true);
        rvSofoEgitimList.setLayoutManager(new LinearLayoutManager(ruhsatDetayActivity));
        if (soforBilgisiList.size() > 0) {
            rvSofoEgitimList.setVisibility(View.VISIBLE);
            SoforEgitimListAdapter myAdapter = new SoforEgitimListAdapter(getActivity(), soforBilgisiList);
            rvSofoEgitimList.setAdapter(myAdapter);
        }else{
            tvEmptyList.setText("Liste Boş !");
            tvEmptyList.setVisibility(View.VISIBLE);}
        return viewGroup;
    }

}
