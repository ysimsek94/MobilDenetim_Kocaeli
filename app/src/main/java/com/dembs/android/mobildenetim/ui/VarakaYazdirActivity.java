package com.dembs.android.mobildenetim.ui;

import static com.dembs.android.mobildenetim.utils.Constants.getDateTimeNow;
import static com.dembs.android.mobildenetim.utils.Constants.userName;
import static com.dembs.android.mobildenetim.utils.DateTimeInit.getFormattedTime;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.models.VarakaPrint;
import com.dembs.android.mobildenetim.models.VarakaResult;
import com.dembs.android.mobildenetim.models.VarakaSms;
import com.dembs.android.mobildenetim.network.Api;
import com.dembs.android.mobildenetim.network.ClientConfigs;
import com.dembs.android.mobildenetim.tsc_printer.BixolonPrint;
import com.dembs.android.mobildenetim.tsc_printer.IMobilePrinter;
import com.dembs.android.mobildenetim.utils.Constants;
import com.dembs.android.mobildenetim.utils.ToolbarInit;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class VarakaYazdirActivity extends AppCompatActivity {
    //private static BixolonPrinter bxlPrinter = null;
    private final ArrayList<CharSequence> bondedDevices = new ArrayList<>();
    private final int REQUEST_PERMISSION = 0;
    private final String DEVICE_ADDRESS_START = " (";
    private final String DEVICE_ADDRESS_END = ")";
    String adSoyad, tcKimlikNo, ruhsatNo, plaka, hatNo, kooperatifAdi, adres, ihlalYeri, tarihString, gun,
            saat, ihlalMaddeAdi, ihlalBendAdi, varakaAciklama, ihlalBendAciklama,
            sicil1, sicil2, sicil3, sicil4, varakaNo, mudur, mudurUnvan, islemTarihi, tebligTarihSaat, surucuTcNo, surucuAd, surucuTelNo;
    Toolbar toolbar;
    ToolbarInit toolbarInit;
    MaterialButton btnYazdir, btnSmsGonder;
    TextView tvTcVergiNo, tvVarakaNo, tvIlgiliAdSoyad, tvRuhsatNo, tvPlakaNo, tvIkametAdresi, tvIhlalAdresi, tvIhlalTarihSaat, tvIhlalBendi, tvIhlalAciklama,
            tvDenetimPersoneli1, tvDenetimPersoneli2, tvDenetimPersoneli3, tvDenetimPersoneli4, tvTanzim,tvYetkili;
    ProgressBar progressBar;
    String madde = " - ";
    int bendId = 0;
//    String ikametAdresi = "SEYMEN MAH. GÜNAYDIN CAD. KAPI:30 Daire:12 Turkiye";
    String tanzim = "";
    List<VarakaResult> varakaResultList = new ArrayList<>();
    int width = 750, alignment = 0, startPage = 0, endPage = 0, brightness = 25;
    String ukomeMetin;
    int denetimId = 0, itemId = 0, kisiId;
    int varakaId;

    Api api;
    Retrofit retrofit;
    VarakaPrint varakaPrint;
    int cnt = 0;
    private ArrayAdapter<CharSequence> arrayAdapter;
    // private BXLConfigLoader bxlConfigLoader = null;
    private String deviceAdres = "";
    //private String logicalName = "SPP-R400";//Bizdeki printer
    private String logicalName = "SPP-R410";//kurum printer model
    private int spinnerDither = 0;

    boolean isRuhsatsizDenetim;

    @RequiresApi(api = Build.VERSION_CODES.S)
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varaka_yazdir);
        Intent i=getIntent();
        isRuhsatsizDenetim=i.getBooleanExtra("isRuhsatsizDenetim",false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_FINE_LOCATION,


                    }, 100);
                }
            }
            if (checkSelfPermission(Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED)
            {requestPermissions(new String[]{
                    Manifest.permission.BLUETOOTH_SCAN,
                    Manifest.permission.BLUETOOTH_CONNECT

            }, 100);

            }
        }
//        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.varaka_print_kocaeli);
        ukomeMetin = getResources().getString(R.string.printUkomeMetin);
        //bxlPrinter = new BixolonPrinter(getApplicationContext());
        //   varakaList=getIntent().getParcelableArrayListExtra("varakaList");
        denetimId = getIntent().getIntExtra("denetimId", 0);
        itemId = getIntent().getIntExtra("itemId", 0);
        varakaId = getIntent().getIntExtra("varakaId", 0);
        varakaNo = getIntent().getStringExtra("varakaNo");

        api = createRetrofit().create(Api.class);
        getVarakaPrintValues();
        setPairedDevices();
        createLayoutElements();
        // setValues(varakaList);

        btnYazdir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseBluetoohtDevice();
            }
        });
        btnSmsGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendSms();
            }
        });

    }

    private void sendSms() {

        Call<String> call = api.varakaSms(getVarakaSmsValue());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Toasty.success(getApplicationContext(), "Sms gönderildi.. ", Toasty.LENGTH_LONG, true).show();

                } else {
                    Toasty.error(getApplicationContext(), "Hata : " + response.errorBody(), Toasty.LENGTH_LONG, true).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toasty.error(getApplicationContext(), "Hata : " + t.toString(), Toasty.LENGTH_LONG, true).show();
            }
        });
    }


    private VarakaSms getVarakaSmsValue() {
        VarakaSms varakaSms = new VarakaSms();
        varakaSms.setDenetimId(denetimId);
        varakaSms.setItemId(itemId);
        varakaSms.setKisiId(kisiId);
        String mesaj = surucuAd + " " + plaka + " Plakalı aracınız ilgili yönetmeliğin ";
        mesaj = mesaj + ihlalMaddeAdi + "-" + ihlalBendAdi + " maddesini ihlal ettiği için " + getFormattedTime(islemTarihi);
        mesaj = mesaj + " varaka düzenlenmiştir. Detay için:http://ukome.kocaeli.bel.tr/RuhsatApp/IhlalBilgi?bendId=" + bendId;
        varakaSms.setMessage(mesaj);
        varakaSms.setTelNo(surucuTelNo);
        //varakaSms.setTelNo("05071858969");
        return varakaSms;
    }

    private void getVarakaPrintValues() {

        Call<VarakaPrint> call = api.getVarakaPrint(varakaId);
        call.enqueue(new Callback<VarakaPrint>() {
            @Override
            public void onResponse(Call<VarakaPrint> call, Response<VarakaPrint> response) {

                if (response.isSuccessful() && response.code() == 200) {

                    varakaPrint = response.body();
                    if (varakaPrint != null)
                        setVarakaPrintValues(varakaPrint);
                } else if (response.isSuccessful() && response.code() == 204) {
                    Toasty.warning(getApplicationContext(), "Bu denetime ait yazdırılacak veri bulunamadı.", Toasty.LENGTH_LONG, true).show();

                } else {
                    Toasty.error(getApplicationContext(), "Hata :  " + response.errorBody().toString(), Toasty.LENGTH_LONG, true).show();
                }
            }

            @Override
            public void onFailure(Call<VarakaPrint> call, Throwable t) {

                Toasty.error(getApplicationContext(), "Hata : " + t.toString(), Toasty.LENGTH_LONG, true).show();
            }
        });
    }

    private void setVarakaPrintValues(VarakaPrint varakaPrint) {

        adSoyad = varakaPrint.getAdSoyad() == null ? " - " : varakaPrint.getAdSoyad();
        tcKimlikNo = varakaPrint.getTcKimlikNo() == null ? " - " : varakaPrint.getTcKimlikNo();
        varakaId=varakaPrint.getVarakaId();
        ruhsatNo = varakaPrint.getRuhsatNo() == null ? " - " : varakaPrint.getRuhsatNo();
        plaka = varakaPrint.getPlaka() == null ? " - " : varakaPrint.getPlaka();
        adres = varakaPrint.getIkametAdres() == null ? " - " : varakaPrint.getIkametAdres();
        ihlalYeri = varakaPrint.getIhlalAdres() == null ? " - " : varakaPrint.getIhlalAdres();
        tarihString = varakaPrint.getIhlalTarihi() == null ? " - " : getFormattedTime(varakaPrint.getIhlalTarihi());
        saat = varakaPrint.getIhlalSaat()== null ? " - " : varakaPrint.getIhlalSaat();
        ihlalMaddeAdi = varakaPrint.getIhlalMaddeAdi()== null ? " - " : varakaPrint.getIhlalMaddeAdi();
        ihlalBendAdi = varakaPrint.getIhlalBendAdi()== null ? " - " : varakaPrint.getIhlalBendAdi();
        bendId = varakaPrint.getBendId();


        varakaAciklama = varakaPrint.getVarakaAciklama()== null ? " - " : varakaPrint.getVarakaAciklama();
        ihlalBendAciklama = varakaPrint.getIhlalBendAciklama()== null ? " - " : varakaPrint.getIhlalBendAciklama();
        String yetkiliPersonelText=userName+"\n"+"Görevli Personel";
        tvYetkili.setText(yetkiliPersonelText);
        sicil1 = sicil1 == null ? " - " : sicil1;
        sicil2 = sicil2 == null ? " - " : sicil2;
        sicil3 = sicil3 == null ? " - " : sicil3;
        sicil4 = sicil4 == null ? " - " : sicil4;

        if (varakaPrint.getKullaniciSicilList() != null) {
            if (varakaPrint.getKullaniciSicilList().size() > 0) {
                sicil1 = varakaPrint.getKullaniciSicilList().get(0).getSicilNo() == null ? " - " : varakaPrint.getKullaniciSicilList().get(0).getSicilNo();
            }
            if (varakaPrint.getKullaniciSicilList().size() > 1) {
                sicil2 = varakaPrint.getKullaniciSicilList().get(1).getSicilNo() == null ? " - " : varakaPrint.getKullaniciSicilList().get(1).getSicilNo();
            }
            if (varakaPrint.getKullaniciSicilList().size() > 2) {
                sicil3 = varakaPrint.getKullaniciSicilList().get(2).getSicilNo() == null ? " - " : varakaPrint.getKullaniciSicilList().get(2).getSicilNo();
            }
            if (varakaPrint.getKullaniciSicilList().size() > 3) {
                sicil4 = varakaPrint.getKullaniciSicilList().get(3).getSicilNo() == null ? " - " : varakaPrint.getKullaniciSicilList().get(3).getSicilNo();
            }

        }

        varakaNo = String.valueOf(varakaNo);
        surucuAd = varakaPrint.getSurucuAdi() == null ? " - " : varakaPrint.getSurucuAdi();
        surucuTcNo = varakaPrint.getSurucuTc() == null ? " - " : varakaPrint.getSurucuTc();
        kisiId = varakaPrint.getKisiId();
        surucuTelNo = varakaPrint.getTelNo() == null ? " - " : varakaPrint.getTelNo();
        // mudur = varakaPrint.getMudur() == null ? " - " : varakaPrint.getMudur();
        mudur = "SALİH MÜLAYİM";
        mudurUnvan=varakaPrint.getMudurUnvan()== null ? " - " : varakaPrint.getMudurUnvan();
        islemTarihi = varakaPrint.getIhlalTarihi() == null ? " - " : varakaPrint.getIhlalTarihi();
        tebligTarihSaat = Constants.getFormattedTime(getDateTimeNow());

        tvVarakaNo.setText(varakaNo);
        tvTcVergiNo.setText(tcKimlikNo);
        tvIlgiliAdSoyad.setText(adSoyad);
        tvRuhsatNo.setText(ruhsatNo);
        tvPlakaNo.setText(plaka);
        tvIkametAdresi.setText(adres);
        tvIhlalAdresi.setText(ihlalYeri);
        tvIhlalTarihSaat.setText(tarihString);
        tanzim = "Yukarıda bilgileri yazılı kişinin ilgili yönetmeliğin " + ihlalMaddeAdi + " maddesinin " + ihlalBendAdi + " bendini ihlal ettiği görüldüğünden hakkında kanuni işlem yapılmak için iş bu varaka tanzim olundu.";
        tvTanzim.setText(tanzim);
        tvIhlalBendi.setText(ihlalBendAciklama);
        tvIhlalAciklama.setText(varakaAciklama);
//        tvDenetimPersoneli1.setText(sicil1);
//        tvDenetimPersoneli2.setText(sicil2);
//        tvDenetimPersoneli3.setText(sicil3);
//        tvDenetimPersoneli4.setText(sicil4);
    }

    private Retrofit createRetrofit() {
        ClientConfigs clientConfigs = new ClientConfigs();

        return ClientConfigs.createRetrofit(getApplicationContext());
    }

    private void createLayoutElements() {


        toolbar = findViewById(R.id.toolbarVarakaYazdir);
        toolbarInit = new ToolbarInit(toolbar, null);
        toolbarInit.getToolbar("Varaka Yazdır", "", Color.WHITE, Color.WHITE);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        btnYazdir = findViewById(R.id.btnYazdir);
        btnSmsGonder = findViewById(R.id.btnSmsGonder);
        if (isRuhsatsizDenetim)
        {
            btnSmsGonder.setVisibility(View.GONE);
        }
        tvTcVergiNo = findViewById(R.id.tvTcVergiNo);
        tvVarakaNo = findViewById(R.id.tvVarakaNo);
        tvIlgiliAdSoyad = findViewById(R.id.tvIlgiliAdSoyad);
        tvRuhsatNo = findViewById(R.id.tvRuhsatNo);
        tvPlakaNo = findViewById(R.id.tvPlakaNo);
        tvIkametAdresi = findViewById(R.id.tvIkametAdresi);
        tvIhlalAdresi = findViewById(R.id.tvIhlalAdresi);
        tvIhlalTarihSaat = findViewById(R.id.tvIhlalTarihSaat);
        tvIhlalBendi = findViewById(R.id.tvIhlalBendi);
        tvIhlalAciklama = findViewById(R.id.tvIhlalAciklama);
//        tvDenetimPersoneli1 = findViewById(R.id.tvDenetimPersoneli1);
//        tvDenetimPersoneli2 = findViewById(R.id.tvDenetimPersoneli2);
//        tvDenetimPersoneli3 = findViewById(R.id.tvDenetimPersoneli3);
//        tvDenetimPersoneli4 = findViewById(R.id.tvDenetimPersoneli4);
        tvYetkili=findViewById(R.id.tvYetkili);
        tvTanzim = findViewById(R.id.tvTanzim);
        progressBar=findViewById(R.id.progressBar);

    }

    private void chooseBluetoohtDevice() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(VarakaYazdirActivity.this);
        //builderSingle.setIcon(R.drawable.ic_launcher);
        builderSingle.setTitle("Yazıcı Seçiniz..");

        arrayAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_list_item_single_choice, bondedDevices);

        builderSingle.setNegativeButton("Kapat", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //progressBar.setVisibility(View.VISIBLE);
                //String strName = (String) arrayAdapter.getItem(which);
                String device = (String) arrayAdapter.getItem(which);
                Bitmap bitmapVarakaLogo = BitmapFactory.decodeResource(getResources(), R.drawable.varaka_print_kocaeli);
                deviceAdres = device.substring(device.indexOf(DEVICE_ADDRESS_START) + DEVICE_ADDRESS_START.length(), device.indexOf(DEVICE_ADDRESS_END));
                IMobilePrinter iMobilePrinter=new BixolonPrint();
                boolean isOpenPrinter=  iMobilePrinter.openPrinter(getApplicationContext(),0,logicalName,deviceAdres,false);
                //boolean rtn = bxlPrinter.printerOpen(0, logicalName, address, false);
                if (isOpenPrinter) {

                    Toasty.success(getApplicationContext(), "Yazıcıya Bağlanıldı..", Toasty.LENGTH_LONG, true).show();

                        iMobilePrinter.print(getApplicationContext(),width,alignment,brightness,spinnerDither,ruhsatNo,tanzim,deviceAdres,bitmapVarakaLogo,varakaNo,tcKimlikNo,adSoyad,plaka,adres,ihlalYeri,tarihString,ihlalBendAdi,ihlalMaddeAdi,ihlalBendAciklama,
                                varakaAciklama,surucuTcNo,surucuAd,sicil1,sicil2,sicil3,sicil4,tebligTarihSaat,mudur,ukomeMetin,cnt);

                    dialog.dismiss();

                } else {

                    Toasty.error(getApplicationContext(), "Yazıcıya Bağlanılamadı..", Toasty.LENGTH_LONG, true).show();



                }

            }

        });
        builderSingle.show();
        builderSingle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String device = ((TextView) view).getText().toString();

                deviceAdres = device.substring(device.indexOf(DEVICE_ADDRESS_START) + DEVICE_ADDRESS_START.length(), device.indexOf(DEVICE_ADDRESS_END));

                // When clicked, show a toast with the TextView text
                Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @SuppressLint("MissingPermission")
    private void setPairedDevices() {

        bondedDevices.clear();

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        @SuppressLint("MissingPermission") Set<BluetoothDevice> bondedDeviceSet = bluetoothAdapter.getBondedDevices();

        for (BluetoothDevice device : bondedDeviceSet) {
            bondedDevices.add(device.getName() + DEVICE_ADDRESS_START + device.getAddress() + DEVICE_ADDRESS_END);
        }

        if (arrayAdapter != null) {
            arrayAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}