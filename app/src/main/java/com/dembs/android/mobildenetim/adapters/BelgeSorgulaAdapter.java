package com.dembs.android.mobildenetim.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.models.BelgeLine;
import com.dembs.android.mobildenetim.models.BelgeLineResult;
import com.dembs.android.mobildenetim.ui.BelgeDetayActivity;
import com.dembs.android.mobildenetim.utils.ProgressDialogManager;

import java.util.ArrayList;

import static com.dembs.android.mobildenetim.utils.DateTimeInit.getFormattedTime;

public class BelgeSorgulaAdapter extends RecyclerView.Adapter<BelgeSorgulaAdapter.ViewHolder> {
    private LayoutInflater userInflater;
    private  ArrayList<BelgeLineResult> belgeLineResultArrayList;
    private  Context mcontext;
    private ProgressDialogManager progressDialogManager;

    public BelgeSorgulaAdapter(Context context, ArrayList<BelgeLineResult> belgeLineResultArrayList) {
        this.mcontext = context;
        this.belgeLineResultArrayList = belgeLineResultArrayList;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_belge_row, parent, false);
        progressDialogManager = new ProgressDialogManager(mcontext, "");
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        BelgeLineResult belgeLineResult = belgeLineResultArrayList.get(position);
        BelgeLine belgeLine = belgeLineResult.getBelgeLine();
        String surucu = belgeLineResultArrayList.get(position).getKisi().getAdi() + " " + belgeLineResultArrayList.get(position).getKisi().getSoyadi();
        //Todo belge line dan isletmeci bilgisi dönmüyor
        //holder.tvIsletmeci.setText((belgeLine.getIsletmeci() == null) || (belgeLine.getIsletmeci().equals("")) ? "-" : belgeLine.getIsletmeci());
        holder.tvGecerlilikTarihi.setText((belgeLine.getBelge().getGecerlilikTarihi() == null) || (belgeLine.getBelge().getGecerlilikTarihi().equals("")) ? "-" : getFormattedTime(belgeLine.getBelge().getGecerlilikTarihi()));
        holder.tvPlaka.setText((belgeLineResult.getArac().getPlaka() == null) || (belgeLineResult.getArac().getPlaka().equals("")) ? "-" : belgeLineResult.getArac().getPlaka());
        holder.tvSurucu.setText((surucu == null) || (surucu.equals("")) ? "-" : surucu);
        holder.tvBelgeAdi.setText((belgeLine.getBelgeTuru() == null) || (belgeLine.getBelgeTuru().equals("")) ? "-" : belgeLine.getBelgeTuru());
        holder.cvBelgeRow.setOnClickListener(v -> {
            Intent intent = new Intent(mcontext, BelgeDetayActivity.class);
            intent.putExtra("belgeLineResult", (Parcelable) belgeLineResult);
            mcontext.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return belgeLineResultArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cvBelgeRow;
        TextView tvIsletmeci, tvPlaka, tvSurucu, tvGecerlilikTarihi, tvBelgeAdi;

        public ViewHolder(View view) {
            super(view);
            cvBelgeRow = view.findViewById(R.id.cvBelgeRow);
            tvIsletmeci = view.findViewById(R.id.tvIsletmeci);
            tvPlaka = view.findViewById(R.id.tvPlaka);
            tvSurucu = view.findViewById(R.id.tvSurucu);
            tvGecerlilikTarihi = view.findViewById(R.id.tvGecerlilikTarihi);
            tvBelgeAdi = view.findViewById(R.id.tvBelgeAdi);


        }

    }
}