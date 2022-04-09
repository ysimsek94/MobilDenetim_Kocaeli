package com.dembs.android.mobildenetim.ui.fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.airbnb.lottie.LottieAnimationView;
import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.models.BandrolLine;
import com.dembs.android.mobildenetim.models.DuzeltmeTalebi;
import com.dembs.android.mobildenetim.models.SoforBilgisi;
import com.dembs.android.mobildenetim.models.VarakaBekleyen;
import com.dembs.android.mobildenetim.models.VarakaResult;
import com.dembs.android.mobildenetim.network.Api;
import com.dembs.android.mobildenetim.network.ClientConfigs;
import com.dembs.android.mobildenetim.ui.RuhsatDetayActivity;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentMain extends Fragment {
    Api api;
    ArrayList<VarakaResult> varakaBekleyenlerDetayList = new ArrayList<>();
    TextView tvBekleyenVaraka, tvBekleyenDuzeltmeTalebi;
    RuhsatDetayActivity ruhsatDetayActivity = new RuhsatDetayActivity();
    private int denetimTurId;
    private int duzeltmeTalebiBekleyenSize, varakaBekleyenSize;
    private ArrayList<VarakaBekleyen> varakaBekleyenList = new ArrayList<>();
    private ArrayList<BandrolLine> ruhsatBandrolList = new ArrayList<>();
    private ArrayList<SoforBilgisi> soforBilgisiArrayList = new ArrayList<>();
    private ArrayList<DuzeltmeTalebi> duzeltmeTalebiBekleyenList = new ArrayList<>();
    private String ruhsatNo,plaka;
    private LottieAnimationView lottieVarakaBekleyen, lottieGuncellemeTalebi;
    private CardView cvSoforEgitimBilgisi, cvBandrolBilgisi, cvBekleyenVaraka, cvGuncellemeTalebi;
    private ProgressDialog mDialog;

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        api = ClientConfigs.createRetrofit(getActivity()).create(Api.class);
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            denetimTurId = bundle.getInt("denetimTurId", 0);
           ruhsatNo = bundle.getString("ruhsatNo");
            plaka = bundle.getString("plaka");
            varakaBekleyenSize = bundle.getInt("varakaBekleyenListSize");
            duzeltmeTalebiBekleyenSize = bundle.getInt("duzeltmeTalebiBekleyenSize");
            varakaBekleyenList = bundle.getParcelableArrayList("varakaBekleyen");
            duzeltmeTalebiBekleyenList = bundle.getParcelableArrayList("duzeltmeTalebi");

        }

        createLayout(viewGroup);

        if (varakaBekleyenSize > 0) {
            tvBekleyenVaraka.setText(varakaBekleyenSize + " Adet Kontrol Edilmeyi Bekleyen Varaka Bulundu..");
            lottieVarakaBekleyen.setVisibility(View.VISIBLE);

        }
        if (duzeltmeTalebiBekleyenSize > 0) {
            tvBekleyenDuzeltmeTalebi.setText(duzeltmeTalebiBekleyenSize + " Adet Düzeltme Talebi Bulundu..");
            lottieGuncellemeTalebi.setVisibility(View.VISIBLE);
        }

        cvSoforEgitimBilgisi.setOnClickListener(v -> {
            createProgressBar();

            Call<ArrayList<SoforBilgisi>> call = api.getSoforBilgisi(plaka);
            call.enqueue(new Callback<ArrayList<SoforBilgisi>>() {
                @Override
                public void onResponse(Call<ArrayList<SoforBilgisi>> call, Response<ArrayList<SoforBilgisi>> response) {

                    if (response.isSuccessful()) {

                        dismissDialog();
                        soforBilgisiArrayList = response.body();
                        Bundle bundleBekleyenVaraka = new Bundle();
                        bundleBekleyenVaraka.putParcelableArrayList("soforBilgisiList", soforBilgisiArrayList);
                        SoforEgitimBilgisiListFragment soforEgitimBilgisiListFragment = new SoforEgitimBilgisiListFragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        soforEgitimBilgisiListFragment.setArguments(bundleBekleyenVaraka);
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frameLayoutMain, soforEgitimBilgisiListFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                        fragmentTransaction.commit();


                    } else {
                        dismissDialog();
                        Toasty.error(getActivity(), "Geçersiz Parametre..", Toasty.LENGTH_LONG, true).show();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<SoforBilgisi>> call, Throwable t) {
                    Toasty.error(getActivity(), "Hata :" + t.toString(), Toasty.LENGTH_LONG, true).show();
                    dismissDialog();
                }
            });
        });
        cvBandrolBilgisi.setOnClickListener(v -> {
            createProgressBar();
            Call<ArrayList<BandrolLine>> call = api.getBandrolList(plaka);
            call.enqueue(new Callback<ArrayList<BandrolLine>>() {
                @SuppressLint("CheckResult")
                @Override
                public void onResponse(Call<ArrayList<BandrolLine>> call, Response<ArrayList<BandrolLine>> response) {
                    if (response.isSuccessful()) {
                        dismissDialog();
                        ruhsatBandrolList = response.body();
                        Bundle bundleBandrolList = new Bundle();
                        bundleBandrolList.putParcelableArrayList("bandrolList", ruhsatBandrolList);
                        BandrolBilgisiFragment bandrolBilgisiFragment = new BandrolBilgisiFragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        bandrolBilgisiFragment.setArguments(bundleBandrolList);
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frameLayoutMain, bandrolBilgisiFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        fragmentTransaction.setCustomAnimations(R.anim.slide_out_right, R.anim.slide_out_left);
                    } else {
                        dismissDialog();
                        Toasty.error(getActivity(), "Hata : Bilgiler alınamadı..", Toasty.LENGTH_LONG, true);

                    }
                    }

                @Override
                public void onFailure(Call<ArrayList<BandrolLine>> call, Throwable t) {
                    dismissDialog();
                    Toasty.error(getActivity(), "Hata : " + t.toString(), Toasty.LENGTH_LONG, true);

                }
            });

        });
        cvBekleyenVaraka.setOnClickListener(v -> {
            createProgressBar();
            Call<ArrayList<VarakaResult>> call = api.varakaBekleyenlerDetay(plaka);
            call.enqueue(new Callback<ArrayList<VarakaResult>>() {
                @Override
                public void onResponse(Call<ArrayList<VarakaResult>> call, Response<ArrayList<VarakaResult>> response) {

                    if (response.isSuccessful()) {
                       dismissDialog();
                        varakaBekleyenlerDetayList = response.body();
                        Bundle bundleBekleyenVaraka = new Bundle();
                        bundleBekleyenVaraka.putParcelableArrayList("varakaBekleyenDetayList", varakaBekleyenlerDetayList);

                        BekleyenVarakalarFragment bekleyenVarakalarFragment = new BekleyenVarakalarFragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        bekleyenVarakalarFragment.setArguments(bundleBekleyenVaraka);
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frameLayoutMain, bekleyenVarakalarFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                        fragmentTransaction.commit();
                    }
                    else
                        dismissDialog();
                }

                @Override
                public void onFailure(Call<ArrayList<VarakaResult>> call, Throwable t) {
                    dismissDialog();
                    Toasty.error(getActivity(), "Hata :" + t.toString(), Toasty.LENGTH_LONG, true).show();
                }
            });


        });
        cvGuncellemeTalebi.setOnClickListener(v -> {

            Bundle bundleBekleyenVaraka = new Bundle();
            bundleBekleyenVaraka.putParcelableArrayList("duzeltmeTalebiBekleyenList", duzeltmeTalebiBekleyenList);

            GuncellemeTalebiListFragment guncellemeTalebiListFragment = new GuncellemeTalebiListFragment();
            FragmentManager fragmentManager = getFragmentManager();
            guncellemeTalebiListFragment.setArguments(bundleBekleyenVaraka);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayoutMain, guncellemeTalebiListFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);

            fragmentTransaction.commit();


        });

        return viewGroup;//burada fragmentin görüntsünü döndürüyoruz ve create edildiği yer.
    }

    @Override
    public void onPause() {
        super.onPause();
        dismissDialog();
    }

    private void createLayout(ViewGroup viewGroup) {

        tvBekleyenVaraka = viewGroup.findViewById(R.id.tvBekleyenVaraka);
        tvBekleyenDuzeltmeTalebi = viewGroup.findViewById(R.id.tvBekleyenDuzeltmeTalebi);
        cvSoforEgitimBilgisi = viewGroup.findViewById(R.id.cvSoforEgitimBilgisi);
        cvBandrolBilgisi = viewGroup.findViewById(R.id.cvBandrolBilgisi);
        cvBekleyenVaraka = viewGroup.findViewById(R.id.cvBekleyenVaraka);
        cvGuncellemeTalebi = viewGroup.findViewById(R.id.cvGuncellemeTalebi);
        lottieVarakaBekleyen = viewGroup.findViewById(R.id.lottieVarakaBekleyen);
        lottieGuncellemeTalebi = viewGroup.findViewById(R.id.lottieGuncellemeTalebi);
    }

    public void createProgressBar() {


        mDialog = null;
        mDialog = new ProgressDialog(getActivity());
        mDialog.setMessage("Lütfen Bekleyiniz...");
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();


    }

    public void dismissDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

}