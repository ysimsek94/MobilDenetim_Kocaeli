package com.dembs.android.mobildenetim.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dembs.android.mobildenetim.R;

import androidx.appcompat.app.AppCompatActivity;

public class SoforBilgisiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sofor_bilgisi);

        ListView listView = (ListView) findViewById(R.id.listViewSoforBilgisi);
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.sofor_bilgisi_header,listView,false);
//        ArrayList<Arac> aracList=new ArrayList<Arac>();
//        Arac arac1=new Arac("34HP4182",2020);
//        arac1.setAracCinsi("Taksi");
//        arac1.setMarka("Merso");
//        aracList.add(arac1);
//
//        SoforBilgisiAdapter adapter = new SoforBilgisiAdapter(SoforBilgisiActivity.this,R.layout.sofor_bilgisi_row,aracList);
//        listView.addHeaderView(header);
//        listView.setAdapter(adapter);
    }
}
