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
import com.dembs.android.mobildenetim.models.VarakaResim;
import com.dembs.android.mobildenetim.ui.VarakaResimDetayActivity;
import com.dembs.android.mobildenetim.utils.ProgressDialogManager;

import java.util.ArrayList;

public class VarakaResimListAdapter  extends RecyclerView.Adapter<VarakaResimListAdapter.CardviewParse> {
    private Context mContext;
    private ArrayList<VarakaResim> varakaResimArrayList;
    private ProgressDialogManager progressDialogManager ;
    private Intent intent;

    public VarakaResimListAdapter(Context context, ArrayList<VarakaResim> varakaResimArrayList1) {
        this.mContext = context;
        this.varakaResimArrayList = varakaResimArrayList1;
    }


    @NonNull
    @Override
    public VarakaResimListAdapter.CardviewParse onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_reklamresim_list_satir, parent, false);
        return new VarakaResimListAdapter.CardviewParse(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final VarakaResimListAdapter.CardviewParse holder, final int position) {

        VarakaResim varakaResim = varakaResimArrayList.get(position);

        holder.tvDosyaAdi.setText(varakaResimArrayList.get(position).getDocName());


        holder.cvReklamResim.setOnClickListener(v -> {
            progressDialogManager= new ProgressDialogManager(mContext,"");
            //progressDialogManager.createProgressBar();
            if (varakaResim.getId()>0){

                intent=new Intent(mContext, VarakaResimDetayActivity.class);
                intent.putExtra("resimId",varakaResim.getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);}

        });
    }

    @Override
    public int getItemCount() {
        return varakaResimArrayList.size();
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