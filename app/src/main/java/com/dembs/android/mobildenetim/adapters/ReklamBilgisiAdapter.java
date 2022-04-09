package com.dembs.android.mobildenetim.adapters;

import android.annotation.SuppressLint;
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

import com.dembs.android.mobildenetim.models.AracReklamResult;
import com.dembs.android.mobildenetim.ui.ReklamGirisActivity;
import com.dembs.android.mobildenetim.ui.fragments.ReklamSorgulamaFragment;
import com.dembs.android.mobildenetim.utils.ProgressDialogManager;

import java.util.ArrayList;

public class ReklamBilgisiAdapter extends RecyclerView.Adapter<ReklamBilgisiAdapter.ViewHolderCardView> {
    String plaka;
    private Context mcontext;
    private ProgressDialogManager progressDialogManager;
    private ArrayList<AracReklamResult> reklamList;
    private int currentitem;
    private Intent intent;
    private AracReklamResult reklamArac;
    private ReklamSorgulamaFragment reklamSorgulamaFragment;

    public ReklamBilgisiAdapter(Context context, ArrayList<AracReklamResult> reklamList, String plaka) {
        this.mcontext = context;
        this.reklamList = reklamList;
        this.plaka = plaka;
    }


    @Override
    public ReklamBilgisiAdapter.ViewHolderCardView onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_reklam_bilgisi_list_row, parent, false);
        progressDialogManager = new ProgressDialogManager(mcontext, "");
        return new ReklamBilgisiAdapter.ViewHolderCardView(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ReklamBilgisiAdapter.ViewHolderCardView holder, final int position) {

        reklamArac = reklamList.get(position);

        holder.tvBasTarih.setText(getFormattedTime(reklamArac.getAracReklam().getBaslangicTarihi()));
        holder.tvBitTarih.setText(getFormattedTime(reklamArac.getAracReklam().getBitisTarihi()));
        holder.tvYbsNo.setText(reklamArac.getArac().getSaseNo());
        holder.tvKaydeden.setText(String.valueOf(reklamArac.getArac().getModel()));

        if (reklamArac.getAracReklam().isAktif()) {
            holder.tvDurumu.setText("Aktif");
        }else{
        holder.tvDurumu.setText("Pasif");}


        holder.cvReklamBilgisi.setOnClickListener(v -> {
            progressDialogManager = new ProgressDialogManager(mcontext, "");
            progressDialogManager.createProgressBar();
            reklamArac = reklamList.get(position);
            intent = new Intent(mcontext, ReklamGirisActivity.class);
            intent.putExtra("reklamList", (Parcelable) reklamArac);
            intent.putExtra("plaka", plaka);
            progressDialogManager.dismissDialog();
            mcontext.startActivity(intent);
           // ((Activity) mcontext).finish();
        });
    }

    @Override
    public int getItemCount() {
        return reklamList.size();
    }

    public class ViewHolderCardView extends RecyclerView.ViewHolder {

        CardView cvReklamBilgisi;
        ImageView btnIleri;
        TextView tvBasTarih, tvBitTarih, tvYbsNo, tvKaydeden, tvDurumu;

        public ViewHolderCardView(View view) {

            super(view);
            cvReklamBilgisi = view.findViewById(R.id.cvReklamBilgisi);
            btnIleri = view.findViewById(R.id.btnIleri);
            tvBasTarih = view.findViewById(R.id.tvBaslangicTarihi);
            tvBitTarih = view.findViewById(R.id.tvBitisTarihi);
            tvYbsNo = view.findViewById(R.id.tvSasiNo);
            tvKaydeden = view.findViewById(R.id.tvModel);
            tvDurumu = view.findViewById(R.id.tvDurumu);

        }
    }
    private String getFormattedTime(String getDateNow) {

        String s = getDateNow.substring(8, 10) + "-" + getDateNow.substring(5, 7) + "-" + getDateNow.substring(0, 4) + " " + getDateNow.substring(11, 16);
        return s;
    }
}
