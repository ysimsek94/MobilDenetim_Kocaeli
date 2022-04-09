package com.dembs.android.mobildenetim.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.models.DuzeltmeTalebi;
import com.dembs.android.mobildenetim.models.Ruhsat;
import com.dembs.android.mobildenetim.models.VarakaBekleyen;
import com.dembs.android.mobildenetim.ui.IslemMenuActivity;
import com.dembs.android.mobildenetim.utils.ProgressDialogManager;
import com.google.android.material.button.MaterialButton;

import java.io.Serializable;
import java.util.ArrayList;

public class RuhsatBilgisiAdapter extends RecyclerView.Adapter<RuhsatBilgisiAdapter.CardviewParse> {
    String ybsNo, adi, tip;
    int ruhsatTipId;
    int varakaBekleyenSize = 0;
    private Context mcontext;
    private ProgressDialogManager progressDialogManager;
    private ArrayList<Ruhsat> ruhsatList=new ArrayList<>();
    private int currentitem;
    private Intent intent;
    private Ruhsat ruhsat;
    String ruhsatNo;
    int duzeltmeTalebiListSize=0;
    ArrayList<VarakaBekleyen> varakaBekleyenList = new ArrayList<>();
    ArrayList<DuzeltmeTalebi> duzeltmeTalebiBekleyenArrayList = new ArrayList<>();

    public RuhsatBilgisiAdapter(Context context, ArrayList<Ruhsat> ruhsatList, ArrayList<VarakaBekleyen>varakaBekleyenArrayList,
                                int varakaBekleyenSize, ArrayList<DuzeltmeTalebi>duzeltmeTalebiBekleyenArrayList,
                                int duzeltmeTalebiListSize) {
        this.mcontext = context;
        this.ruhsatList = ruhsatList;
        this.varakaBekleyenSize = varakaBekleyenSize;
        this.varakaBekleyenList=varakaBekleyenArrayList;
        this.duzeltmeTalebiBekleyenArrayList=duzeltmeTalebiBekleyenArrayList;
        this.duzeltmeTalebiListSize=duzeltmeTalebiListSize;

    }

    @NonNull
    @Override
    public CardviewParse onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_ruhsat_bilgi, parent, false);
        progressDialogManager = new ProgressDialogManager(mcontext, "");
        return new CardviewParse(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardviewParse holder, final int position) {

        ruhsat = ruhsatList.get(position);
        ruhsatNo=ruhsat.getRuhsatNo();
        holder.isAktifLine.setId(position);
        if (!ruhsatList.get(position).isAktif()) {
            holder.isAktifLine.setBackgroundColor(Color.RED);
            ruhsatList.get(position).setAktif(false);
        }else {
            holder.isAktifLine.setBackgroundColor(Color.GREEN);
            ruhsatList.get(position).setAktif(true);
        }
        SharedPreferences sharedPref = this.mcontext.getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        //Denetim özet için
        ruhsatNo= (ruhsatList.get(position).getRuhsatNo());
        String plaka = (ruhsatList.get(position).getPlaka());
        if (plaka != null) {
            editor.remove("plaka");
            editor.apply();
        }
        editor.putString("plaka", plaka); //string değer ekleniyor
        editor.apply();

        ybsNo = ruhsat.getYbsNo()==null||ruhsat.getYbsNo().equals("") ? " - ":ruhsat.getYbsNo();
        adi = ruhsat.getAdi()==null||ruhsat.getAdi().equals("") ? " - ":ruhsat.getAdi();


        ruhsatTipId =ruhsat.getRuhsatTipId();
        switch (ruhsatTipId) {
            case 1:
                tip="SERVİS ARACI";
                break;
            case 2:
              tip="TİCARİ TAKSİ";
                break;
            case 3:
               tip="TOPLU TAŞIMA";
                break;
            default:
                break;
        }
//        holder.tvRuhsatNo.setText(ruhsatNo);
        holder.tvPlaka.setText(plaka);
        holder.tvAdi.setText(adi);
        holder.tvYbsNo.setText(ybsNo);
        if (tip != null) {

            holder.tvTipi.setText(editTip(tip));
        }

        holder.tvToplamBorc.setText("--");
        holder.cvRuhsatOzet.setOnClickListener(v -> {
            progressDialogManager = new ProgressDialogManager(mcontext, "");
            progressDialogManager.createProgressBar();

           //intent = new Intent(mcontext, DenetimListActivity.class);
            intent = new Intent(mcontext, IslemMenuActivity.class);
            intent.putExtra("Ruhsat", (Serializable) ruhsatList.get(position));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            progressDialogManager.dismissDialog();
            mcontext.startActivity(intent);

        });
//        holder.btnYeniDenetim.setOnClickListener(v -> {
//
//            Intent i = new Intent(mcontext, DenetimGirisActivity.class);
//            i.putExtra("detayId", ruhsatList.get(currentitem).getDetayId());
//
//         i.putExtra("denetimTurId", ruhsatList.get(currentitem).getRuhsatTipId());
//
//            i.putExtra("tip",tip);
//            i.putExtra("ruhsatNo", ruhsatList.get(currentitem).getRuhsatNo());
//            i.putExtra("Plaka", ruhsatList.get(currentitem).getPlaka());
//            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            mcontext.startActivity(i);
//        });
//        holder.cvRuhsatDetay.setOnClickListener(v -> {
//            Intent i = new Intent(mcontext, RuhsatDetayActivity.class);
//            i.putExtra("ruhsatNo", ruhsatList.get(position).getRuhsatNo());
//            i.putExtra("plaka", ruhsatList.get(position).getPlaka());
//            i.putExtra("denetimTurId", ruhsatList.get(position).getRuhsatTipId());
//            i.putExtra("varakaBekleyenList",varakaBekleyenList);
//            i.putExtra("duzeltmeTalebiBekleyenList",duzeltmeTalebiBekleyenArrayList);
//            i.putExtra("varakaBekleyenListSize",varakaBekleyenSize);
//            i.putExtra("duzeltmeTalebiBekleyenSize",duzeltmeTalebiListSize);
//            mcontext.startActivity(i);
//        });

    }

    private String editTip(String tip) {
        switch (tip) {
            case "S":
                tip = "SERVİS ARACI";
                break;

            case "T":
                tip = "Taksi";
                break;

            case "U":
                tip = "Toplu Taşıma";
                break;

            default:

                break;
        }
        return tip;
    }

    @Override
    public int getItemCount() {
        return ruhsatList.size();
    }

    public Context getMcontext() {

        return mcontext;
    }

    public class CardviewParse extends RecyclerView.ViewHolder {
        LottieAnimationView lottieAnimationView;
        private CardView cvRuhsatOzet, cvRuhsatDetay;
        private TextView tvPlaka;
       // private TextView tvRuhsatNo;
        private TextView tvYbsNo;
        private TextView tvAdi;
        private TextView tvTipi;
        private TextView tvToplamBorc;
        private TextView tvRuhsatDetay;
        private ImageView ivGoToDenetimler;
        private MaterialButton btnYeniDenetim;
        View isAktifLine;

        @SuppressLint("SetTextI18n")
        public CardviewParse(View view) {

            super(view);
            cvRuhsatOzet = view.findViewById(R.id.cvRuhsatOzet);
//            btnYeniDenetim = view.findViewById(R.id.btnYeniDenetim);
//            cvRuhsatDetay = view.findViewById(R.id.cvRuhsatDetay);
            ivGoToDenetimler = view.findViewById(R.id.ivGoToDenetimler);
          //  tvRuhsatNo = view.findViewById(R.id.tvRuhsatNo);
            tvPlaka = view.findViewById(R.id.tvPlakaNo);
            tvYbsNo = view.findViewById(R.id.tvYbsNo);
            tvAdi = view.findViewById(R.id.tvAdi);
            tvTipi = view.findViewById(R.id.tvTip);
            tvToplamBorc = view.findViewById(R.id.tvToplamBorc);
            isAktifLine = view.findViewById(R.id.isAktifLine);
            //tvRuhsatDetay=view.findViewById(R.id.tvRuhsatDetay);
//            lottieAnimationView = view.findViewById(R.id.lottieNotification);
//            if (varakaBekleyenSize > 0||duzeltmeTalebiListSize>0) {
//                tvRuhsatDetay.setText(ruhsatNo+" 'Nolu  Ruhsat İçin Bildirimleriniz Var. Detay İçin Tıklayınız !");
//                lottieAnimationView.setVisibility(View.VISIBLE);
//            }else {
//                lottieAnimationView.setVisibility(View.GONE);
//                tvRuhsatDetay.setText("Ruhsat Detay Bilgileri İçin Tıklayınız!");
//            }

        }

    }


}
