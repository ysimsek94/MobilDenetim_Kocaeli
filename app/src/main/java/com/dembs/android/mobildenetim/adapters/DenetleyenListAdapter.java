package com.dembs.android.mobildenetim.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.models.DenetimDenetleyen;
import com.dembs.android.mobildenetim.network.Api;
import com.dembs.android.mobildenetim.network.ClientConfigs;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DenetleyenListAdapter extends RecyclerView.Adapter<DenetleyenListAdapter.CardviewParse> {
    private Context mContext;
    private List<DenetimDenetleyen> denetleyenList;
    int denetimId;
    Api api;
    int kullaniciId;



    public DenetleyenListAdapter(Context context, List<DenetimDenetleyen> dList,int denetimId) {
        this.denetimId=denetimId;
        this.mContext = context;
        this.denetleyenList = dList;
    }
    @NonNull
    @Override
    public CardviewParse onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_denetleyen_list_row, parent, false);
        CardviewParse cardviewParse = new CardviewParse(v);
        api = ClientConfigs.createRetrofit(mContext).create(Api.class);
        return cardviewParse;
    }

    @Override
    public void onBindViewHolder(@NonNull  CardviewParse holder, int position) {

        String denetleyen = (denetleyenList.get(position).getAdi() + " " +denetleyenList.get(position).getSoyadi());
        holder.tvDenetleyenAd.setText(denetleyen);
        holder.btnDel.setOnClickListener(v -> {
            kullaniciId =denetleyenList.get(position).getKullaniciId();
            denetleyenList.remove(position);
            deleteDenetimDenetleyen();
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, denetleyenList.size());

        });
    }

    private void deleteDenetimDenetleyen() {

        Call<Void> call = api.deleteDenetleyen(denetimId, kullaniciId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful())
                {
                    Toasty.success(mContext,"Denetleyen Silinmiştir..",Toasty.LENGTH_SHORT,true).show();
                }else {
                    Toasty.error(mContext, "Denetleyen silinirken hata oluştu..", Toasty.LENGTH_LONG, true).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toasty.error(mContext, "Denetleyen silinirken hata oluştu..", Toasty.LENGTH_LONG, true).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return denetleyenList.size();
    }

    class CardviewParse extends RecyclerView.ViewHolder {
        private CardView cvDenetimList;
        private TextView tvDenetleyenAd;
        private ImageView btnDel;
        CardviewParse(View view) {
            super(view);
            cvDenetimList=view.findViewById(R.id.cvDenetleyenList);
            btnDel = view.findViewById(R.id.btnDel);
            tvDenetleyenAd=view.findViewById(R.id.tvDenetleyenAd);
        }
    }
}

