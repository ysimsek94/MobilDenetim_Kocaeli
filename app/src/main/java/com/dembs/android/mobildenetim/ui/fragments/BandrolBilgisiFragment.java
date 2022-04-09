package com.dembs.android.mobildenetim.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.adapters.BandrolBilgisiAdapter;
import com.dembs.android.mobildenetim.models.BandrolLine;
import com.dembs.android.mobildenetim.models.RuhsatBandrol;
import com.dembs.android.mobildenetim.ui.RuhsatDetayActivity;

import java.util.ArrayList;

public class BandrolBilgisiFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<BandrolLine> ruhsatBandrolList =new ArrayList<>();
    TextView tvEmptyList;
    int bandrolListSize=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       ViewGroup viewGroup=(ViewGroup) inflater.inflate(R.layout.fragment_bandrol_bilgisi,container,false);
        Bundle bundle = this.getArguments();
        ruhsatBandrolList = bundle.getParcelableArrayList("bandrolList");
       if (ruhsatBandrolList !=null)
       {
           bandrolListSize= ruhsatBandrolList.size();
       }
       else
       {  bandrolListSize=0;
       }
        RuhsatDetayActivity ruhsatDetayActivity = new RuhsatDetayActivity();
        tvEmptyList=viewGroup.findViewById(R.id.tvEmptyList);
        recyclerView = viewGroup.findViewById(R.id.rvBandrolBilgisi);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(ruhsatDetayActivity));
        if (bandrolListSize> 0 ) {
            recyclerView.setVisibility(View.VISIBLE);
            BandrolBilgisiAdapter bandrolBilgisiAdapter = new BandrolBilgisiAdapter(getActivity(), ruhsatBandrolList);
            recyclerView.setAdapter(bandrolBilgisiAdapter);
        }else{
            tvEmptyList.setText("Liste Boş !");
            tvEmptyList.setVisibility(View.VISIBLE);}




        return viewGroup;
    }
}
