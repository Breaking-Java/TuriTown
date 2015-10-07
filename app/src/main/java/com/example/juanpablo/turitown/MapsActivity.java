package com.example.juanpablo.turitown;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        mMap.setMyLocationEnabled(true);//Activar la localización del usuario
        marcador(new LatLng(20.673719, -103.3354131), "Guadalajara", "UTC −6");
        marcador(new LatLng(25.648795, -100.3030961), "Monterrey", "UTC −6");
        marcador(new LatLng(16.8354901, -99.8622709), "Acapulco", "UTC −6");
        marcador(new LatLng(17.0812981,-96.7357315), "Oaxaca", "UTC −6");
        marcador(new LatLng(18.9318816, -99.240565),"Cuernavaca","UTC -6");
        marcador(new LatLng(19.1787668,-96.1624256),"Veracruz","UTC -6");
        marcador(new LatLng(21.121445,-86.8494402),"Cancún","UTC -6");
        marcador(new LatLng(23.2718562, -109.7669092), "Los Cabos", "UTC -6");
        mMap.getMyLocation();
        LatLng inicio = new LatLng(23.6266557,-102.5377501);
        CameraPosition cam = new CameraPosition.Builder().target(inicio).zoom(5).build();
        CameraUpdate camup =CameraUpdateFactory.newCameraPosition(cam);
        mMap.animateCamera(camup);
    }
    private void marcador(LatLng position, String titulo, String horario) {
        mMap.addMarker(new MarkerOptions().position(position).title(titulo).snippet(horario)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
    }
}