package com.dembs.android.mobildenetim.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.models.DenetimDenetleyenRapor;

import java.util.List;

import static com.dembs.android.mobildenetim.utils.DateTimeInit.getFormattedTime;

public class DenetleyenRaporListAdapter extends RecyclerView.Adapter<DenetleyenRaporListAdapter.CardviewParse> {
    private Context mContext;
    private List<DenetimDenetleyenRapor> denetimDenetleyenRaporList;
    private int currentitem;
    // private ProgressDialogManager progressDialogManager ;
    private Intent intent;
    //private Denetim denetim;
    String sendDateTime;



    public DenetleyenRaporListAdapter(Context context, List<DenetimDenetleyenRapor> dList) {
        this.mContext = context;
        this.denetimDenetleyenRaporList = dList;
    }

    @NonNull
    @Override
    public CardviewParse onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_denetim_denetleyen_rapor_list_row, parent, false);
        CardviewParse cardviewParse = new CardviewParse(v);
        return cardviewParse;
    }

    @Override
    public void onBindViewHolder(@NonNull final CardviewParse holder, final int position) {

        holder.tvDenetimTarihi.setText(getFormattedTime(denetimDenetleyenRaporList.get(position).getDenetimTarihi()));
       // holder.tvRuhsatNo.setText(denetimDenetleyenRaporList.get(position).getRuhsatNo());
        holder.tvPlakaNo.setText(denetimDenetleyenRaporList.get(position).getPlaka());
        holder.tvVarakaAdedi.setText(String.valueOf(denetimDenetleyenRaporList.get(position).getVarakaAdet()));
        holder.tvIlce.setText(denetimDenetleyenRaporList.get(position).getIlceAdi());
        holder.tvEkip.setText(denetimDenetleyenRaporList.get(position).getEkipAdi());

    }

    @Override
    public int getItemCount() {
        return denetimDenetleyenRaporList.size();
    }



    class CardviewParse extends RecyclerView.ViewHolder {
        private CardView cvDenetimList;
        private TextView tvDenetimTarihi,tvPlakaNo,tvVarakaAdedi,tvIlce,tvEkip;
        private ImageView btnDel;

        CardviewParse(View view) {

            super(view);
            tvDenetimTarihi=view.findViewById(R.id.tvDenetimTarihi);
           // tvRuhsatNo=view.findViewById(R.id.tvRuhsatNo);
            tvPlakaNo=view.findViewById(R.id.tvPlakaNo);
            tvVarakaAdedi=view.findViewById(R.id.tvVarakaAdet);
            tvIlce=view.findViewById(R.id.tvIlce);
            tvEkip=view.findViewById(R.id.tvEkip);




        }
    }
}

