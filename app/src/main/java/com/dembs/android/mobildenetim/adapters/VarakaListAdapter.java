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
import com.dembs.android.mobildenetim.models.VarakaResult;
import com.dembs.android.mobildenetim.ui.VarakaActivity;
import com.dembs.android.mobildenetim.utils.ProgressDialogManager;

import java.util.List;

public class VarakaListAdapter extends RecyclerView.Adapter<VarakaListAdapter.CardviewParse> {
    private Context mContext;
    private List<VarakaResult> varakaBekleyenlerDetayList;
    private int currentitem;
    private ProgressDialogManager progressDialogManager ;
    private Intent intent;
    private VarakaResult varakaBekleyenlerDetay;



    public VarakaListAdapter(Context context, List<VarakaResult> varakaBekleyenlerDetayList1List) {
        this.mContext = context;
        this.varakaBekleyenlerDetayList = varakaBekleyenlerDetayList1List;
    }

    @NonNull
    @Override
    public CardviewParse onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_varaka_list_row, parent, false);
        return new CardviewParse(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardviewParse holder, final int position) {

        varakaBekleyenlerDetay = varakaBekleyenlerDetayList.get(position);

        String varakaNo = (varakaBekleyenlerDetayList.get(position).getVaraka().getVarakaNo());
        String ihlalMaddesi = (varakaBekleyenlerDetayList.get(position).getIhlalMaddeAdi());
        String ihlalBendi = (varakaBekleyenlerDetayList.get(position).getIhlalBendAdi());
        String aciklama = String.valueOf((varakaBekleyenlerDetayList.get(position).getVaraka().getAciklama()));

        holder.tvVarakaNo.setText(varakaNo);
        holder.tvIhlalMaddesi.setText(ihlalMaddesi);
        holder.tvIhlalBendi.setText(ihlalBendi);
        holder.tvAciklama.setText(aciklama);

        holder.cvVarakaList.setOnClickListener(v -> {

            progressDialogManager= new ProgressDialogManager(mContext,"");
            currentitem = holder.getAdapterPosition();
            intent = new Intent(mContext, VarakaActivity.class);
            //intent.putExtra("List", (Parcelable) varakaBekleyenlerDetayList.get(currentitem));
            intent.putExtra("denetimId",varakaBekleyenlerDetayList.get(currentitem).getVaraka().getDenetimId());
            intent.putExtra("itemId",varakaBekleyenlerDetayList.get(currentitem).getVaraka().getFormItemId());
            progressDialogManager.createProgressBar();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
            progressDialogManager.dismissDialog();

        });
    }

    @Override
    public int getItemCount() {
        return varakaBekleyenlerDetayList.size();
    }



    class CardviewParse extends RecyclerView.ViewHolder {
        private CardView cvVarakaList;
        private TextView tvVarakaNo;
        private TextView tvIhlalMaddesi;
        private TextView tvIhlalBendi;
        private TextView tvAciklama;
        private ImageView im_ileri;

        CardviewParse(View view) {

            super(view);
            cvVarakaList=view.findViewById(R.id.cvVarakaList);
            im_ileri = view.findViewById(R.id.btnIleri);
            tvAciklama = (TextView) view.findViewById(R.id.tvAciklama);
            tvVarakaNo = (TextView) view.findViewById(R.id.tvVarakaNo);
            tvIhlalMaddesi = (TextView) view.findViewById(R.id.tvIhlalMaddesi);
            tvIhlalBendi = (TextView) view.findViewById(R.id.tvIhlalBendi);
        }
    }   
}
