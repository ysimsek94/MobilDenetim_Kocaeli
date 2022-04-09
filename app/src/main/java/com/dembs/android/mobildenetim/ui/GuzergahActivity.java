package com.dembs.android.mobildenetim.ui;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.dembs.android.mobildenetim.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

public class GuzergahActivity extends FragmentActivity implements OnMapReadyCallback {

    // below are the latitude and longitude of 4 different locations.
    LatLng TamWorth = new LatLng(27.8881286503267, 39.6473758852757);
    LatLng NewCastle = new LatLng(27.8895580734813, 39.6488154130266);
    LatLng Brisbane = new LatLng(39.6117575532726, 27.8915086297481);
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guzergah);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(39.6117575532726, 27.8915086297481),
                        new LatLng(39.6111727921679, 27.8927944913733),
                        new LatLng(39.6124491150581, 27.894726421563),

                        new LatLng(39.6129145315622, 27.895646725675),

                        new LatLng(39.6139405963081, 27.8946619373349),

                        new LatLng(39.6150064388305, 27.8935937106225),

                        new LatLng(39.6161042808706, 27.8926323438901),

                        new LatLng(39.6174006040952, 27.8915136084118),

                        new LatLng(39.6186909503356, 27.8904163184754),

                        new LatLng(39.6198294897973, 27.8893306681951),

                        new LatLng(39.6211441150451, 27.8880991801759),

                        new LatLng(39.6211441150451, 27.8880991801759),

                        new LatLng(39.6223620599546, 27.8869861004055),

                        new LatLng(39.6211441150451, 27.8880991801759),

                        new LatLng(39.6235352706364, 27.8859140001784),

                        new LatLng(39.6247378216993, 27.88488607375538880991801759),

                        new LatLng(39.6260662564805, 27.8836943054112),

                        new LatLng(39.6271332761248, 27.8827192122803),

                        new LatLng(39.6275446908295, 27.8823893756896),

                        new LatLng(39.6278473015525, 27.8821736290442),

                        new LatLng(39.6283179347355, 27.881911900304),
                        new LatLng(39.6288072029042, 27.881789155232),
                        new LatLng(39.6298725062125, 27.8817534790984),
                        new LatLng(39.631070252746, 27.8820390411385),
                        new LatLng(39.6324159346524, 27.8823724132219),
                        new LatLng(39.6338593271962, 27.8826981932371),
                        new LatLng(39.6351595319062, 27.8829582414273),
                        new LatLng(39.6376124119865, 27.8832554193572),
                        new LatLng(39.6394291456988, 27.8834049766801),
                        new LatLng(39.6420682359852, 27.883551817576),
                        new LatLng(39.6436519818877, 27.8837376144426),
                        new LatLng(39.6442574434934, 27.8840005454826),
                        new LatLng(39.6447614123811, 27.8843706007714),
                        new LatLng(39.6450543633194, 27.8846865391877),
                        new LatLng(39.6454628667664, 27.8852210397554),
                        new LatLng(39.6457365409619, 27.8855776464416),
                        new LatLng(39.6461097786783, 27.8861369151804),
                        new LatLng(39.6465353954739, 27.8868677020028),
                        new LatLng(39.6471146197289, 27.8878489800957),
                        new LatLng(39.6471527927081, 27.8881494189944),
                        new LatLng(39.6473346480783, 27.8883186864118),
                        new LatLng(39.6474902991033, 27.8881846461957),
                        new LatLng(39.6478039791026, 27.8878219916798),
                        new LatLng(39.6481160695615, 27.8874577650975),
                        new LatLng(39.6481700797631, 27.8873783120904),
                        new LatLng(39.6485131594724, 27.887725750447),
                        new LatLng(39.6485127165735, 27.8880796217158)


                ).color(Color.RED));
        // inside on map ready method
        // we will be displaying polygon on Google Maps.
        // on below line we will be adding polyline on Google Maps.
//        mMap.addPolyline((new PolylineOptions()).add(Brisbane, NewCastle, TamWorth, Brisbane).
//                // below line is use to specify the width of poly line.
//                        width(5)
//                // below line is use to add color to our poly line.
//                .color(Color.RED)
//                // below line is to make our poly line geodesic.
//                .geodesic(true));
        // on below line we will be starting the drawing of polyline.
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Brisbane, 15));
    }
}