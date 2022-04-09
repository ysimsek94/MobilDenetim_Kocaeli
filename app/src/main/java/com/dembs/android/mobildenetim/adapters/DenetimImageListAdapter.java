package com.dembs.android.mobildenetim.adapters;

import android.app.Activity;
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
import com.dembs.android.mobildenetim.models.DenetimImageModel;
import com.dembs.android.mobildenetim.ui.DenetimImageDetayActivity;


import java.util.List;

public class DenetimImageListAdapter extends RecyclerView.Adapter<DenetimImageListAdapter.CardviewParse> {

    private Context mContext;
    private List<DenetimImageModel> denetimImageModelList;
    private  int index;
    private Intent intent;

    public DenetimImageListAdapter(Context context, List<DenetimImageModel> imageModelList1st) {
        this.mContext = context;
        this.denetimImageModelList = imageModelList1st;
    }

    @NonNull
    @Override
    public CardviewParse onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_denetim_image_list_row, parent, false);
        return new CardviewParse(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardviewParse holder, final int position) {

        DenetimImageModel denetimImageModel = denetimImageModelList.get(position);

        String docName = denetimImageModel.getDocName()==null?"-": denetimImageModel.getDocName();


        holder.tvDocName.setText(docName);

        holder.cvDenetimImageList.setOnClickListener(v -> {

            index = holder.getBindingAdapterPosition();
            intent = new Intent(mContext, DenetimImageDetayActivity.class);
            intent.putExtra("imageId", denetimImageModelList.get(index).getId());
            ((Activity) mContext).startActivityForResult(intent,5);


        });
    }

    @Override
    public int getItemCount() {
        return denetimImageModelList.size();
    }


    class CardviewParse extends RecyclerView.ViewHolder {
        private CardView cvDenetimImageList;
        private TextView tvDocName;
        private ImageView ivIleri;


        CardviewParse(View view) {
            super(view);
           initRow(view);

        }

        private void initRow(View view) {
            cvDenetimImageList = view.findViewById(R.id.cvDenetimImageList);
            tvDocName = (TextView) view.findViewById(R.id.tvDocName);
            ivIleri= view.findViewById(R.id.ivIleri);
        }
    }
}
