package com.dembs.android.mobildenetim.adapters;
import static com.dembs.android.mobildenetim.utils.DateTimeInit.getCurrentUser;
import static com.dembs.android.mobildenetim.utils.DateTimeInit.getDateTimeNow;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.models.DenetimForm;
import com.dembs.android.mobildenetim.models.DenetimFormItem;
import com.dembs.android.mobildenetim.models.DenetimFormItemLine;
import com.dembs.android.mobildenetim.models.DenetimFormItemResult;
import com.dembs.android.mobildenetim.network.Api;
import com.dembs.android.mobildenetim.network.ClientConfigs;
import com.dembs.android.mobildenetim.ui.VarakaActivity;
import com.dembs.android.mobildenetim.utils.OnItemClick;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DenetimFormListAdapter extends RecyclerView.Adapter<DenetimFormListAdapter.ViewHolder> implements Filterable {


    private final ArrayList<DenetimFormItemLine> denetimFormItemLineArrayList = new ArrayList<>();
    private final ArrayList<DenetimForm> denetimFormList;
    private final ArrayList<DenetimForm> denetimFormListAll;

    private final Context context;
    private final int denetimId;
    private final OnItemClick mCallback;
    int denetimTipId;
    int formId;
    List<String> formItemAdiList = new ArrayList<>();
    String dateTimeNow;
    String currentUser;
    private final ArrayList<DenetimFormItem> denetimFormItemList;
    private DenetimFormItemResult denetimFormItemResult;
    private DenetimForm denetimForm;
    boolean isRuhsatsizDenetim;
    private int cnt = 0;

    private final Filter denetimFormItemFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<DenetimForm> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(denetimFormListAll);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (DenetimForm item : denetimFormListAll) {
                    if (item.getFormItemAdi().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            denetimFormList.clear();
            denetimFormList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public DenetimFormListAdapter(ArrayList<DenetimForm> denetimFormArrayList, Context ctx, ArrayList<DenetimFormItem> denetimFormItemList, int denetimId, OnItemClick listener, int denetimTipId,boolean isRuhsatsizDenetim) {

        this.denetimFormList = denetimFormArrayList;
        this.denetimFormItemList = denetimFormItemList;//DenetimFormItem
        this.context = ctx;
        this.mCallback = listener;
        this.denetimId = denetimId;
        this.denetimFormListAll = new ArrayList<>(denetimFormArrayList);
        this.denetimTipId = denetimTipId;
        dateTimeNow = getDateTimeNow();
        currentUser = getCurrentUser(context);
        this.isRuhsatsizDenetim=isRuhsatsizDenetim;
        updateDenetimFormList();

    }

    private void updateDenetimFormList() {

        if (denetimFormItemList.size() > 0) {
            for (int i = 0; i < denetimFormItemList.size(); i++) {

                for (int j = 0; j < denetimFormList.size(); j++) {
                    if (denetimFormList.get(j).getFormItemId() == denetimFormItemList.get(i).getFormItemId() && !denetimFormItemList.get(i).isSecim()) {
                        denetimFormList.get(j).setSecim(denetimFormItemList.get(i).isSecim());
                        denetimFormItemLineArrayList.add(new DenetimFormItemLine(dateTimeNow, currentUser, 0, denetimId, denetimFormItemList.get(i).getFormItemId(), denetimFormItemList.get(i).isSecim()));//servise gidecek liste tipi
                        //return;
                    }
                }

            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.denetim_form_list_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        DenetimForm denetimForm = denetimFormList.get(position);

        formItemAdiList.add(position, denetimForm.getFormItemAdi());
        formId = denetimFormList.get(position).getFormId();
        holder.denetimFormAdi.setText(denetimForm.getFormItemAdi());
        holder.cbItem.setChecked(denetimForm.isSecim());
        holder.cbItem.setId(denetimFormList.get(position).getFormItemId());
        if (denetimTipId>3) {
            holder.imBtnVaraka.setVisibility(View.GONE);

        }

//        if (denetimFormItemList.size() > 0) {
//            for (int i = 0; i < denetimFormItemList.size(); i++) {
//                if (denetimFormList.get(position).getFormItemId() == denetimFormItemList.get(i).getFormItemId() && !denetimFormItemList.get(i).isSecim()) {
//
//                    denetimFormItemLineArrayList.add(new DenetimFormItemLine(dateTimeNow, currentUser, 0, denetimId, denetimFormItemList.get(i).getFormItemId(), denetimFormItemList.get(i).isSecim()));//servise gidecek liste tipi
//                    holder.cbItem.setChecked(false);
//
//                }
//            }
//        }

        holder.cbItem.setOnCheckedChangeListener((buttonView, isChecked) -> {



            cnt++;
            int index = getIndexInList(holder.cbItem);
            if (buttonView.isPressed()) {

                denetimFormList.get(position).setSecim(isChecked);
//                ArrayList<DenetimFormItem>tempList = (ArrayList<DenetimFormItem>) denetimFormItemList
//                        .stream()
//                        .filter(c -> c.getFormItemId() == denetimFormList.get(position).getFormItemId())
//                        .collect(Collectors.toList());
//                int temp = IntStream.range(0, denetimFormItemList.size())
//                        .filter(i -> denetimFormItemList.get(i).getFormItemId()==denetimFormList.get(position).getFormItemId())
//                        .findFirst().orElse(-1);
//
//                if(denetimFormItemList.size()>0){  denetimFormItemList.get(temp).setSecim(isChecked);}


                // denetimFormItemList.get(index).setSecim(isChecked);

//                for (int i = 0; i < denetimFormItemList.size(); i++) {
//                    if (denetimFormList.get(position).getFormItemId() == denetimFormItemList.get(i).getFormItemId()) {
//
//                        denetimFormItemList.get(i).setSecim(isChecked);// holder.cbItem.setChecked(isChecked);
//
//                    }
//                }
                holder.cbItem.setChecked(isChecked);
                // notifyDataSetChanged();

                if (isChecked) {
                    if (index > -1) {
                        denetimFormItemLineArrayList.remove(index);
//                        denetimFormItemList.get(index).setSecim(false);
                    }
                }

                if (index < 0) {
                    //add to list
                    denetimFormItemLineArrayList.add(new DenetimFormItemLine(dateTimeNow, currentUser, 0, denetimId, holder.cbItem.getId(), false));

                }
                sendCounter();
                sendDenetimForm2List();
            }



        });

    }

    private int getIndexInList(CompoundButton button) {
        for (int i = 0; i < denetimFormItemLineArrayList.size(); i++) {
            if (button.getId() == denetimFormItemLineArrayList.get(i).getFormItemId()) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int getItemCount() {
        return denetimFormList.size();
    }

    @Override
    public Filter getFilter() {
        return denetimFormItemFilter;
    }

    public void SetCount(int cnt) {
        this.cnt = cnt;
    }

    private void sendDenetimForm2List() {

        this.mCallback.onClick(denetimFormItemLineArrayList);
    }

    private void sendCounter() {

        this.mCallback.onClickCounter(cnt);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView denetimFormAdi;
        ImageView imBtnVaraka;
        CheckBox cbItem;

        public ViewHolder(View view) {
            super(view);
            denetimFormAdi = view.findViewById(R.id.tvFormAdi);
            imBtnVaraka = view.findViewById(R.id.imBtnGotoVaraka);
            cbItem = view.findViewById(R.id.cbFormIsChecked);
            imBtnVaraka.setOnClickListener(v -> {

                if (denetimTipId>3)
                {
                    Toasty.warning(context, "Araç Uygunluk Veya Muayene İçin Varaka Düzenlenemez..", Toasty.LENGTH_SHORT, true).show();

                }
                else
                {
                    denetimForm = denetimFormList.get(getAdapterPosition());
                    if (denetimFormItemLineArrayList != null) {
                        denetimFormItemResult = new DenetimFormItemResult(denetimId, denetimFormItemLineArrayList);
                        // denetimFormItemResult=new DenetimFormItemResult(denetimId,itemList2);
                    } else {
                        denetimFormItemResult = new DenetimFormItemResult(denetimId, null);
                    }
                    ArrayList<DenetimForm> formArrayList = new ArrayList<>();
                    Intent i = new Intent(context, VarakaActivity.class);
                    Api api = ClientConfigs.createRetrofit(context).create(Api.class);

                    if (!cbItem.isChecked()) {


                        if (cnt > 0) {

                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getRootView().getContext());
                            // alert dialog başlığını tanımlıyoruz.
                            alertDialogBuilder.setTitle("Dikkat");
                            // alert dialog özelliklerini oluşturuyoruz.
                            alertDialogBuilder
                                    .setMessage("Değişiklik yapıldı Kaydedilsinmi?")
                                    .setCancelable(false)
                                    .setIcon(R.drawable.error_icon)
                                    // Evet butonuna tıklanınca yapılacak işlemleri buraya yazıyoruz.
                                    .setPositiveButton("Evet", (dialog, which) -> {
                                        Call<Void> call = api.denetimFormItemAdd(denetimFormItemResult);
                                        call.enqueue(new Callback<Void>() {
                                            @Override
                                            public void onResponse(Call<Void> call, Response<Void> response) {
                                                if (response.isSuccessful()) {

                                                    Toasty.success(context, "Form Kaydedildi..", Toasty.LENGTH_LONG).show();

                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<Void> call, Throwable t) {

                                                Toasty.error(context, "Hata Oluştu : " + t.toString(), Toasty.LENGTH_LONG).show();
                                            }
                                        });


                                        if (!cbItem.isChecked()) {

                                            cnt = 0;
                                            formArrayList.add(denetimForm);
                                            i.putParcelableArrayListExtra("denetimFormItem", formArrayList);
                                            i.putExtra("denetimId", denetimId);
                                            i.putExtra("isRuhsatsizDenetim", isRuhsatsizDenetim);
                                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            context.startActivity(i);

                                        }


                                    }).setNegativeButton("Hayır", (dialog, which) -> dialog.dismiss());

                            AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();
                        } else {

                            formArrayList.add(denetimForm);
                            i.putParcelableArrayListExtra("denetimFormItem", formArrayList);
                            i.putExtra("denetimId", denetimId);
                            i.putExtra("isRuhsatsizDenetim", isRuhsatsizDenetim);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(i);
                        }

                    }
                }
            });

        }

    }
}