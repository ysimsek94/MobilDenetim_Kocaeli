package com.dembs.android.mobildenetim.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.models.CezaLine;
import com.dembs.android.mobildenetim.ui.CezaDetayActivity;

import java.util.ArrayList;

import static com.dembs.android.mobildenetim.utils.DateTimeInit.getFormattedTime;

public class CezaListAdapter extends RecyclerView.Adapter<CezaListAdapter.CardviewParse> {

    public ArrayList<CezaLine> cezaList;
    public int currentitem;
    Intent intent;


    private Context context;

    public CezaListAdapter(Context mcontext, ArrayList<CezaLine> cList) {
        this.context = mcontext;
        this.cezaList = cList;
    }

    @NonNull
    @Override
    public CezaListAdapter.CardviewParse onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_ceza_list_satir, parent, false);
        return new CezaListAdapter.CardviewParse(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CezaListAdapter.CardviewParse holder,  int position) {
        CezaLine  cezaLine =  cezaList.get(position);
        String ruhsatNo = cezaLine.getCeza().getRuhsatNo()==null?" - ":cezaLine.getCeza().getRuhsatNo();
        String plaka =  cezaLine.getCeza().getPlaka()==null?" - ":cezaLine.getCeza().getPlaka();
        String ihlalTarihi = cezaLine.getCeza().getIhlalTarihi()==null?" - ":cezaLine.getCeza().getIhlalTarihi();

        //holder.tvRuhsatno.setText(ruhsatNo);
        holder.tvPlaka.setText(plaka);
        holder.tvDenetimTarihi.setText(getFormattedTime(ihlalTarihi));
        holder.cardViewDenetimList.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               currentitem = holder.getAbsoluteAdapterPosition();
               Intent i=new Intent(context,CezaDetayActivity.class);
               i.putExtra("cezaLine", (Parcelable) cezaList.get(currentitem));
               i.putExtras(i);
               context.startActivity(i);
           }
        });


    }

    @Override
    public int getItemCount() {
        return cezaList.size();
    }

    public class CardviewParse extends RecyclerView.ViewHolder {
        CardView cardViewDenetimList;
        private TextView tvPlaka;
        //private TextView tvRuhsatno;
        private TextView tvDenetimTarihi;

        ImageView im_ileri;

        public CardviewParse(View view) {

            super(view);
            cardViewDenetimList=view.findViewById(R.id.cvCezaRow);
            im_ileri = (ImageView) view.findViewById(R.id.btn_ileri);
            tvPlaka = (TextView) view.findViewById(R.id.tvPlakaDL);
           // tvRuhsatno = (TextView) view.findViewById(R.id.tvRuhsatNoDL);
            tvDenetimTarihi = (TextView) view.findViewById(R.id.tvTarihDL);
        }

    }
}

