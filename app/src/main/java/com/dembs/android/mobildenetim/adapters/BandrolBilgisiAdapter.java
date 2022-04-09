package com.dembs.android.mobildenetim.adapters;

import static com.dembs.android.mobildenetim.utils.DateTimeInit.getFormattedTimeWithoutSaat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.models.BandrolLine;
import com.dembs.android.mobildenetim.models.RuhsatBandrol;

import java.util.ArrayList;

public class BandrolBilgisiAdapter extends RecyclerView.Adapter<BandrolBilgisiAdapter.ViewHolderCardView> {
    private Context mcontext;
    private ArrayList<BandrolLine> ruhsatBandrolList;
    private RuhsatBandrol ruhsatBandrolBilgisi;

    public BandrolBilgisiAdapter(Context context, ArrayList<BandrolLine> ruhsatBandrolArrayList) {
        this.mcontext = context;
        this.ruhsatBandrolList = ruhsatBandrolArrayList;
    }


    @Override
    public ViewHolderCardView onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_bandrol_list_row, parent, false);
        return new ViewHolderCardView(v);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderCardView holder, final int position) {

        ruhsatBandrolBilgisi = ruhsatBandrolList.get(position).getRuhsatBandrol();

        holder.tvIslemTarihi.setText(getFormattedTimeWithoutSaat(ruhsatBandrolBilgisi.getIslemTarihi()));
        holder.tvAciklama.setText(ruhsatBandrolBilgisi.getAciklama());
        holder.tvTutar.setText(ruhsatBandrolBilgisi.getTutar()+" TL");
    }

    @Override
    public int getItemCount() {
        return ruhsatBandrolList.size();
    }

    public class ViewHolderCardView extends RecyclerView.ViewHolder {
        CardView cvBandrolBilgisi;
        TextView tvIslemTarihi,tvAciklama,tvTutar;



        public ViewHolderCardView(View itemView) {

            super(itemView);

            cvBandrolBilgisi=itemView.findViewById(R.id.cvBandrolBilgisi);
            tvIslemTarihi = itemView.findViewById(R.id.tvIslemTarihi);
            tvAciklama =  itemView.findViewById(R.id.tvAciklama);
            tvTutar =  itemView.findViewById(R.id.tvTutar);


        }
    }

}
