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
import com.dembs.android.mobildenetim.models.ReklamResim;
import com.dembs.android.mobildenetim.ui.ReklamResimDetayActivity;
import com.dembs.android.mobildenetim.utils.ProgressDialogManager;

import java.util.ArrayList;

public class ReklamResimListAdapter  extends RecyclerView.Adapter<ReklamResimListAdapter.CardviewParse> {
    private Context mContext;
    private ArrayList<ReklamResim> reklamResimArrayList;
    private ProgressDialogManager progressDialogManager ;
    private Intent intent;

    public ReklamResimListAdapter(Context context, ArrayList<ReklamResim> reklamResimArrayList1) {
        this.mContext = context;
        this.reklamResimArrayList = reklamResimArrayList1;
    }


    @NonNull
    @Override
    public CardviewParse onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_reklamresim_list_satir, parent, false);
        return new CardviewParse(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardviewParse holder, final int position) {

        ReklamResim reklamResim = reklamResimArrayList.get(position);

        holder.tvDosyaAdi.setText(reklamResimArrayList.get(position).getDocName());


        holder.cvReklamResim.setOnClickListener(v -> {
            progressDialogManager= new ProgressDialogManager(mContext,"");
            //progressDialogManager.createProgressBar();
            if (reklamResim.getId()>0){

                intent=new Intent(mContext,ReklamResimDetayActivity.class);
                intent.putExtra("resimId",reklamResim.getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);}

        });
    }

    @Override
    public int getItemCount() {
        return reklamResimArrayList.size();
    }


    class CardviewParse extends RecyclerView.ViewHolder {
        private CardView cvReklamResim;
        private TextView tvDosyaAdi;

        CardviewParse(View view) {

            super(view);
            cvReklamResim=view.findViewById(R.id.cvReklamResimList);
            tvDosyaAdi = (TextView) view.findViewById(R.id.tvDosyaAdi);

        }


    }
}