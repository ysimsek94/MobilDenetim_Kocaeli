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
import com.dembs.android.mobildenetim.models.Denetim;
import com.dembs.android.mobildenetim.models.Guzergah;
import com.dembs.android.mobildenetim.utils.ProgressDialogManager;

import java.util.List;

public class GuzergahListAdapter extends RecyclerView.Adapter<GuzergahListAdapter.CardviewParse> {
    String sendDateTime;
    private Context mContext;
    private List<Guzergah> guzergahList;
    private int currentitem;
    private ProgressDialogManager progressDialogManager;
    private Intent intent;
    private Denetim denetim;

    public GuzergahListAdapter(Context context, List<Guzergah> dList) {
        this.mContext = context;
        this.guzergahList = dList;
    }

    @NonNull
    @Override
    public CardviewParse onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_belge_text_row, parent, false);
        return new CardviewParse(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardviewParse holder, final int position) {


        String guzergahAdi = (guzergahList.get(position).getAdi());
        holder.tvValue.setText(guzergahAdi);
    }

    @Override
    public int getItemCount() {
        return guzergahList.size();
    }

    private String getFormattedTime(String getDateNow) {

        return getDateNow.substring(8, 10) + "-" + getDateNow.substring(5, 7) + "-" + getDateNow.substring(0, 4) + " " + getDateNow.substring(11, 16);
    }

    class CardviewParse extends RecyclerView.ViewHolder {
        private CardView cvDenetimList;
        private TextView tvTitle;
        private TextView tvValue;




        CardviewParse(View view) {

            super(view);
            cvDenetimList = view.findViewById(R.id.cvDenetimList);
            tvTitle=(TextView) view.findViewById(R.id.tvTitle);
            tvTitle.setText("Güzergah Adı");
            tvValue=(TextView) view.findViewById(R.id.tvValue);


        }
    }
}

