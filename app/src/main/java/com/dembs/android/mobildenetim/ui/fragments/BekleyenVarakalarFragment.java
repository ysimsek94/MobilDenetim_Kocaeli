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
import com.dembs.android.mobildenetim.adapters.VarakaListAdapter;
import com.dembs.android.mobildenetim.models.VarakaResult;
import com.dembs.android.mobildenetim.network.Api;
import com.dembs.android.mobildenetim.ui.RuhsatDetayActivity;

import java.util.ArrayList;

public class BekleyenVarakalarFragment extends Fragment{

        private ArrayList<VarakaResult> varakaBekleyenlerDetayList = new ArrayList<>();
        private RecyclerView rvBekleyenVarakalar;
        TextView tvEmptyList;
        Api api;
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_bekleyen_varakalar, container, false);
            RuhsatDetayActivity ruhsatDetayActivity = new RuhsatDetayActivity();
            Bundle bundle = this.getArguments();
            varakaBekleyenlerDetayList = bundle.getParcelableArrayList("varakaBekleyenDetayList");
            tvEmptyList=viewGroup.findViewById(R.id.tvEmptyList);
            rvBekleyenVarakalar = viewGroup.findViewById(R.id.rvBekleyenVaraka);
             rvBekleyenVarakalar.setHasFixedSize(true);
            rvBekleyenVarakalar.setLayoutManager(new LinearLayoutManager(ruhsatDetayActivity));

            if (varakaBekleyenlerDetayList.size() > 0) {
                rvBekleyenVarakalar.setHasFixedSize(true);
                rvBekleyenVarakalar.setVisibility(View.VISIBLE);
                VarakaListAdapter varakaListAdapter = new VarakaListAdapter(getActivity(), varakaBekleyenlerDetayList);
                rvBekleyenVarakalar.setAdapter(varakaListAdapter);

            }else{
                tvEmptyList.setText("Liste Boş !");
                tvEmptyList.setVisibility(View.VISIBLE);}

            return viewGroup;
        }

    @Override
    public void onResume() {
        super.onResume();
    }
}
