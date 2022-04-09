package com.dembs.android.mobildenetim.adapters;

import android.content.Context;
import android.content.Intent;
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
import com.dembs.android.mobildenetim.models.Denetim;
import com.dembs.android.mobildenetim.models.DenetimLine;
import com.dembs.android.mobildenetim.ui.DenetimGirisActivity;
import com.dembs.android.mobildenetim.utils.ProgressDialogManager;

import java.util.List;

public class DenetimListAdapter extends RecyclerView.Adapter<DenetimListAdapter.CardviewParse> {
    String sendDateTime;
    private Context mContext;
    private List<DenetimLine> denetimLineList;
    private int currentitem;
    private ProgressDialogManager progressDialogManager;
    private Intent intent;
    private Denetim denetim;
    private int aracId;

    public DenetimListAdapter(Context context, List<DenetimLine> dList,int aracId) {
        this.mContext = context;
        this.denetimLineList = dList;
        this.aracId=aracId;
    }

    @NonNull
    @Override
    public CardviewParse onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_denetim_list_satir, parent, false);
        return new CardviewParse(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardviewParse holder, final int position) {

        denetim = denetimLineList.get(position).getDenetim();
        //Denetim özet için
        //String ruhsat_no = (denetimLineList.get(position).getDenetim().getRuhsatNo());
        String plaka = (denetimLineList.get(position).getDenetim().getPlaka());
        String denetimTarihi = (denetimLineList.get(position).getDenetim().getDenetimTarihi());
        //String varakaAdedi = String.valueOf((denetimLineList.get(position).getVarakaAdet()));

        //holder.tvRuhsatno.setText(ruhsat_no);
        holder.tvPlaka.setText(plaka);
        // sendDateTime=denetimTarihi;

        holder.tvDenetimTarihi.setText(getFormattedTime(denetimTarihi));
      // holder.tvVarakaAdet.setText(varakaAdedi);

        holder.cvDenetimList.setOnClickListener(v -> {

            progressDialogManager = new ProgressDialogManager(mContext, "");
            currentitem = holder.getAbsoluteAdapterPosition();
            intent = new Intent(mContext, DenetimGirisActivity.class);
            DenetimLine denetimLine=new DenetimLine();
            denetimLine=denetimLineList.get(currentitem);
            intent.putExtra("denetimLine", (Parcelable) denetimLine);
            intent.putExtra("detayId", denetimLineList.get(currentitem).getDenetim().getDetayId());
            intent.putExtra("denetimId", denetimLineList.get(currentitem).getDenetim().getId());
            intent.putExtra("denetimTurId", denetimLineList.get(currentitem).getDenetim().getDenetimTurId());
            intent.putExtra("denetimTipId", denetimLineList.get(currentitem).getDenetim().getDenetimTipId());
            intent.putExtra("aracId", aracId);
            intent.putExtra("Plaka", denetimLineList.get(currentitem).getDenetim().getPlaka());
            intent.putExtra("ruhsatNo", denetimLineList.get(currentitem).getDenetim().getRuhsatNo());

            progressDialogManager.createProgressBar();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
            progressDialogManager.dismissDialog();

        });
    }

    @Override
    public int getItemCount() {
        return denetimLineList.size();
    }

    private String getFormattedTime(String getDateNow) {

        return getDateNow.substring(8, 10) + "-" + getDateNow.substring(5, 7) + "-" + getDateNow.substring(0, 4) + " " + getDateNow.substring(11, 16);
    }

    class CardviewParse extends RecyclerView.ViewHolder {
        private CardView cvDenetimList;
        private TextView tvPlaka;
       // private TextView tvRuhsatno;
        private TextView tvDenetimTarihi;
    //    private TextView tvVarakaAdet;
        private ImageView im_ileri;

        CardviewParse(View view) {

            super(view);
            cvDenetimList = view.findViewById(R.id.cvDenetimList);
            im_ileri = view.findViewById(R.id.btn_ileri);
           // tvVarakaAdet = (TextView) view.findViewById(R.id.tvVarakaAdetDL);
            tvPlaka = (TextView) view.findViewById(R.id.tvPlakaDL);
           // tvRuhsatno = (TextView) view.findViewById(R.id.tvRuhsatNoDL);
            tvDenetimTarihi = (TextView) view.findViewById(R.id.tvTarihDL);
        }
    }
}

