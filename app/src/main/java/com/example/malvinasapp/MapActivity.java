package com.example.malvinasapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;
    List<String> centros_comerciales, cc_longitude, cc_latitude;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_map);


        centros_comerciales = new ArrayList<>();
        cc_longitude = new ArrayList<>();
        cc_latitude = new ArrayList<>();

        //Centros comerciales
        centros_comerciales.add("Mesa Redonda");
        centros_comerciales.add("Mercado Central");
        centros_comerciales.add("Damero");
        centros_comerciales.add("Grau");
        centros_comerciales.add("Malvinas");

        //Ubicacion de los centros comerciales
        cc_latitude.add("-12.051119");
        cc_latitude.add("-12.052509");
        cc_latitude.add("-12.072847");
        cc_latitude.add("-12.058605");
        cc_latitude.add(" -12.043839");

        cc_longitude.add("-77.030569");
        cc_longitude.add("-77.027688");
        cc_longitude.add("-77.018286");
        cc_longitude.add("-77.027798");
        cc_longitude.add("-77.047657 ");

        SupportMapFragment mapFrag = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapita));
        mapFrag.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        MapStyleOptions mapStyleOptions = MapStyleOptions.loadRawResourceStyle(this,R.raw.style);
        map.setMapStyle(mapStyleOptions);

        //map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-12.042048, -77.043181), 15));

        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        for (int i = 0; i < centros_comerciales.size(); i++) {
            LatLng lat_long = new LatLng(Double.parseDouble(cc_latitude.get(i)),Double.parseDouble(cc_longitude.get(i)));
            builder.include(lat_long);
            Marker marker =
                    map.addMarker(new MarkerOptions()
                            .position(lat_long)
                            .title(centros_comerciales.get(i))
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
        }

        LatLngBounds bounds = builder.build();
        map.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100), 2000, null);

        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(MapActivity.this, ExperienciaActivity.class);
                startActivity(intent);
            }
        });

    }


}
