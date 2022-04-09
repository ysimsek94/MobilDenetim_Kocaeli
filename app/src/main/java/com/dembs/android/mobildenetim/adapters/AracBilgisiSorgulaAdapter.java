package com.dembs.android.mobildenetim.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.models.AracLine;
import com.dembs.android.mobildenetim.utils.ProgressDialogManager;

import java.util.ArrayList;

import static com.dembs.android.mobildenetim.utils.DateTimeInit.getFormattedTimeWithoutSaat;

public class AracBilgisiSorgulaAdapter extends RecyclerView.Adapter<AracBilgisiSorgulaAdapter.ViewHolderCardView> {
    private Context mcontext;
    private ProgressDialogManager progressDialogManager;
    private ArrayList<AracLine> aracList;
    private int currentitem;
    private Intent intent;
    private AracLine arac;


    public AracBilgisiSorgulaAdapter(Context context, ArrayList<AracLine> aracList) {
        this.mcontext = context;
        this.aracList = aracList;
    }


    @Override
    public ViewHolderCardView onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_arac_bilgileri, parent, false);
        progressDialogManager = new ProgressDialogManager(mcontext, "");
        return new ViewHolderCardView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCardView holder, final int position) {


        //Todo getAdi methodu yeni modelde yok nasıl yapılacak
        // holder.tvRuhsatSahibi.setText(aracList.get(0).getAdi());
        holder.tvMarka.setText(aracList.get(position).getMarkasi());
        holder.tvPlaka.setText(aracList.get(position).getArac().getPlaka());
        holder.tvModel.setText(String.valueOf(aracList.get(position).getArac().getModel()));
        //Todo getTelNo methodu yeni modelde yok nasıl yapılacak ve tescil tarihi yok
        // holder.tvTelno.setText(aracList.get(0).getTelNo());
        holder.tvTescilTarihi.setText(getFormattedTimeWithoutSaat(aracList.get(position).getArac().getTescilTarihi()));
        holder.tvAyaktaYolcuA.setText(String.valueOf(aracList.get(position).getArac().getYolcuAdetArti()));
        holder.tvMotorNo.setText(aracList.get(position).getArac().getMotorNo());
        holder.tvIslemTarihi.setText(getFormattedTimeWithoutSaat(aracList.get(position).getArac().getIslemTarihi()));
        holder.tvAracCinsi.setText(aracList.get(position).getAracCinsi());
        holder.tvSaseNo.setText(aracList.get(position).getArac().getSaseNo());
    //todo engellidurum yok
        //holder.tvEngelliDurumu.setText(aracList.get(position).getArac() ? "Engelli":"-");


    }

    @Override
    public int getItemCount() {
        return aracList.size();
    }

    public class ViewHolderCardView extends RecyclerView.ViewHolder {

        CardView cvAracBilgisi;
        TextView tvRuhsatSahibi;
        TextView tvPlaka;
        TextView tvMarka;
        TextView tvModel;
        TextView tvTelno;
        TextView tvTescilTarihi;
        TextView tvAyaktaYolcuA;
        TextView tvSaseNo;
        TextView tvMotorNo;
        TextView tvIslemTarihi;
        TextView tvAracCinsi;
        TextView tvEngelliDurumu;

        public ViewHolderCardView(View itemView) {

            super(itemView);

            cvAracBilgisi = itemView.findViewById(R.id.cvAracBilgileri);
            tvRuhsatSahibi = itemView.findViewById(R.id.tvRuhsatSahibi);
            tvMarka = itemView.findViewById(R.id.tvAracMarka);
            tvPlaka = itemView.findViewById(R.id.tvPlaka);
            tvModel = itemView.findViewById(R.id.tvModel);
            tvTelno = itemView.findViewById(R.id.tvTelNo);
            tvTescilTarihi = itemView.findViewById(R.id.tvTescilTarihi);
            tvAyaktaYolcuA = itemView.findViewById(R.id.tvAyaktaYolcuA);
            tvSaseNo = itemView.findViewById(R.id.tvSaseNo);
            tvMotorNo = itemView.findViewById(R.id.tvMotorNo);
            tvIslemTarihi = itemView.findViewById(R.id.tvIslemTarihi);
            tvAracCinsi = itemView.findViewById(R.id.tvAracCinsi);
            tvEngelliDurumu = itemView.findViewById(R.id.tvEngelliDurumu);


        }
    }

}
