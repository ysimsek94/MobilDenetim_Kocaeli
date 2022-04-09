package com.dembs.android.mobildenetim.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.ui.fragments.ReklamSorgulamaFragment;
import com.dembs.android.mobildenetim.models.SoforBilgisi;
import com.dembs.android.mobildenetim.utils.ProgressDialogManager;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class SoforEgitimListAdapter extends RecyclerView.Adapter<SoforEgitimListAdapter.ViewHolderCardView> {
    private Context mcontext;
    private ProgressDialogManager progressDialogManager;
    private ArrayList<SoforBilgisi> soforBilgisiList;
    private int currentitem;
    private Intent intent;
    private SoforBilgisi soforBilgisi;
    private ReklamSorgulamaFragment reklamSorgulamaFragment;


    public SoforEgitimListAdapter(Context context, ArrayList<SoforBilgisi> soforBilgisiArrayListt) {
        this.mcontext = context;
        this.soforBilgisiList = soforBilgisiArrayListt;
    }


    @Override
    public ViewHolderCardView onCreateViewHolder(ViewGroup parent, int viewType) {
      View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_sofor_egitim_row, parent, false);
        return new ViewHolderCardView(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolderCardView holder, final int position) {

        soforBilgisi = soforBilgisiList.get(position);
//
//        String plaka = (soforBilgisiList.get(position).getPlaka());
//        String marka = (soforBilgisiList.get(position).getMarka());
//        String model = (String.valueOf(soforBilgisiList.get(position).getModel()));
//        String tip = (soforBilgisiList.get(position).getAracCinsi());


        holder.tvAd.setText(soforBilgisi.getAdi());
        holder.tvSoyad.setText(soforBilgisi.getSoyadi());
        holder.tvTcNo.setText(soforBilgisi.getTcKimlikNo());
        holder.tvEgitimProgrami.setText(soforBilgisi.getEgitimProgrami());
        holder.tvEgitimDonemi.setText(soforBilgisi.getDonemi());
        holder.tvGecerlilikTarihi.setText(soforBilgisi.getGecerlilikTarihi());

//        holder.btnReklamEkle.setOnClickListener(v -> {
//            progressDialogManager=new ProgressDialogManager(mcontext,"");
//            progressDialogManager.createProgressBar();
//            currentitem = holder.getAdapterPosition();
//            intent = new Intent(mcontext, ReklamGirisActivity.class);
//            intent.putExtra("plaka",plaka);
//            progressDialogManager.dismissDialog();
//            mcontext.startActivity(intent);
//
//        });
    }

    @Override
    public int getItemCount() {
        return soforBilgisiList.size();
    }

    public class ViewHolderCardView extends RecyclerView.ViewHolder {
        CardView cvSoforBilgisi;
        TextView tvAd,tvSoyad,tvTcNo,tvEgitimProgrami,tvEgitimDonemi,tvGecerlilikTarihi;

        public ViewHolderCardView(View itemView) {

            super(itemView);

            cvSoforBilgisi=itemView.findViewById(R.id.cvSoforBilgisi);
            tvAd = itemView.findViewById(R.id.tvAd);
            tvSoyad =  itemView.findViewById(R.id.tvSoyad);
            tvTcNo =  itemView.findViewById(R.id.tvTcNo);
            tvEgitimProgrami =  itemView.findViewById(R.id.tvEgitimProgrami);
            tvEgitimDonemi = itemView.findViewById(R.id.tvEgitimDonemi);
            tvGecerlilikTarihi = itemView.findViewById(R.id.tvGecerlilikTarihi);

        }
    }

}
