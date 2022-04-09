package com.dembs.android.mobildenetim.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.models.Arac;

import java.util.ArrayList;

public class SoforBilgisiAdapter extends ArrayAdapter<Arac> {

    private Context mContext;
    private int mResource;

    public SoforBilgisiAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Arac> objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;

    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//todo marka dönmeli
       // String marka=getItem(position).getMarkaId();

        LayoutInflater inflater=LayoutInflater.from(mContext);
        convertView=inflater.inflate(mResource,parent,false);
        TextView tvAd =(TextView)convertView.findViewById(R.id.tvAd);
        TextView tvSoyad =(TextView)convertView.findViewById(R.id.tvSoyad);
        TextView tvTcNo =(TextView)convertView.findViewById(R.id.tvTcNo);
        TextView tvEgitimProgrami =(TextView)convertView.findViewById(R.id.tvEgitimProgrami);
        TextView tvEgitimDonemi =(TextView)convertView.findViewById(R.id.tvEgitimDonemi);
        TextView tvGecerlilikTarihi =(TextView)convertView.findViewById(R.id.tvGecerlilikTarihi);
//todo problem var set edilenlerin hepsinde
//        tvAd.setText(marka);
//        tvSoyad.setText(marka);
//        tvTcNo.setText(marka);
//        tvEgitimDonemi.setText(marka);
//        tvEgitimProgrami.setText(marka);
//        tvGecerlilikTarihi.setText(marka);


        return convertView;


    }
}
