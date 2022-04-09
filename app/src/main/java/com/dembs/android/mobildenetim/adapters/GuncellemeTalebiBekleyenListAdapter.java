package com.dembs.android.mobildenetim.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.models.DuzeltmeTalebi;
import com.dembs.android.mobildenetim.utils.ProgressDialogManager;

import java.util.List;

import static com.dembs.android.mobildenetim.utils.DateTimeInit.getFormattedTime;

public class GuncellemeTalebiBekleyenListAdapter extends RecyclerView.Adapter<GuncellemeTalebiBekleyenListAdapter.CardviewParse> {
    private Context mContext;
    private List<DuzeltmeTalebi> duzeltmeTalebiBekleyenList;
    private int currentitem;
    private ProgressDialogManager progressDialogManager;
    private Intent intent;
    private DuzeltmeTalebi duzeltmeTalebiBekleyen;
    public GuncellemeTalebiBekleyenListAdapter(Context context, List<DuzeltmeTalebi> duzeltmeTalebiBekleyenList1) {
        this.mContext = context;
        this.duzeltmeTalebiBekleyenList = duzeltmeTalebiBekleyenList1;
    }
    @NonNull
    @Override
    public GuncellemeTalebiBekleyenListAdapter.CardviewParse onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_guncelleme_talebi_satir, parent, false);
        return new GuncellemeTalebiBekleyenListAdapter.CardviewParse(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GuncellemeTalebiBekleyenListAdapter.CardviewParse holder, int position) {
         duzeltmeTalebiBekleyen= duzeltmeTalebiBekleyenList.get(position);
        //Denetim özet için
        String talepTuru = (duzeltmeTalebiBekleyen.getTalepTurAdi()==null?" - ":duzeltmeTalebiBekleyen.getTalepTurAdi());
        String talepTarihi = (duzeltmeTalebiBekleyen.getDuzeltmeTalep().getIslemTarihi()==null?" - ":getFormattedTime(duzeltmeTalebiBekleyen.getDuzeltmeTalep().getIslemTarihi()));
        String aciklama = (duzeltmeTalebiBekleyen.getDuzeltmeTalep().getAciklama()==null||duzeltmeTalebiBekleyen.getDuzeltmeTalep().getAciklama().equals("")?" - ":duzeltmeTalebiBekleyen.getDuzeltmeTalep().getAciklama());


        holder.tvTalepTuru.setText(talepTuru);
        holder.tvTalepTarihi.setText(talepTarihi);
        holder.tvAciklama.setText(aciklama);


    }

    @Override
    public int getItemCount() {

        return duzeltmeTalebiBekleyenList.size();
    }
    class CardviewParse extends RecyclerView.ViewHolder {
        private TextView tvTalepTuru;
        private TextView tvTalepTarihi;
        private TextView tvAciklama;


        CardviewParse(View view) {

            super(view);

            tvTalepTuru = view.findViewById(R.id.tvTalepTuru);
            tvTalepTarihi = (TextView) view.findViewById(R.id.tvTalepTarihi);
            tvAciklama = (TextView) view.findViewById(R.id.tvAciklama);

        }
    }
}
