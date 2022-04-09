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
import com.dembs.android.mobildenetim.ui.ReklamGirisActivity;
import com.dembs.android.mobildenetim.ui.fragments.ReklamSorgulamaFragment;
import com.dembs.android.mobildenetim.utils.ProgressDialogManager;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public  class ReklamSorgulamaRecyclerViewAdapter extends RecyclerView.Adapter<ReklamSorgulamaRecyclerViewAdapter.ViewHolderCardView> {
    private Context mcontext;
    private ProgressDialogManager progressDialogManager;
    private ArrayList<AracLine> aracList;
    private int currentitem;
    private Intent intent;
    private AracLine aracLine;
    private ReklamSorgulamaFragment reklamSorgulamaFragment;


    public ReklamSorgulamaRecyclerViewAdapter(Context context, ArrayList<AracLine> aracList) {
        this.mcontext = context;
        this.aracList = aracList;
    }


    @Override
    public ViewHolderCardView onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.reklam_sorgula_arac_bilgisi_row, parent, false);
        progressDialogManager=new ProgressDialogManager(mcontext,"");
        return new ViewHolderCardView(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolderCardView  holder, final int position) {

        aracLine = aracList.get(position);

        String plaka = (aracList.get(position).getArac().getPlaka());
        String marka = (aracList.get(position).getMarkasi());
        String model = (String.valueOf(aracList.get(position).getArac().getModel()));
        String aracCinsi = (aracList.get(position).getAracCinsi());


        holder.tvMarka.setText(marka);
        holder.tvPlaka.setText(plaka);
        holder.tvModel.setText(model);
       holder.tvTipi.setText(aracCinsi);

        holder.btnReklamEkle.setOnClickListener(v -> {
            progressDialogManager=new ProgressDialogManager(mcontext,"");
            progressDialogManager.createProgressBar();
            currentitem = holder.getAdapterPosition();
            intent = new Intent(mcontext, ReklamGirisActivity.class);
            intent.putExtra("plaka",plaka);
            intent.putExtra("aracId",aracList.get(position).getArac().getId());
            progressDialogManager.dismissDialog();
            mcontext.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return aracList.size();
    }

    public class ViewHolderCardView extends RecyclerView.ViewHolder {
         CardView cvAracBilgisi;
         TextView tvPlaka;
         TextView tvMarka;
         TextView tvModel;
         TextView tvTipi;
         MaterialButton btnReklamEkle;


        public ViewHolderCardView(View itemView) {

            super(itemView);

            cvAracBilgisi=itemView.findViewById(R.id.cvAracBilgisi);
            btnReklamEkle = itemView.findViewById(R.id.btnReklamEkle);
            tvMarka =  itemView.findViewById(R.id.tvMarka);
            tvPlaka =  itemView.findViewById(R.id.tvPlaka);
            tvModel =  itemView.findViewById(R.id.tvModel);
            tvTipi = itemView.findViewById(R.id.tvTip);

        }
    }

}
