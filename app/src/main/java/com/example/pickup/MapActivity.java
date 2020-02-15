package com.example.pickup;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button makeEventButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        final Intent intent = new Intent(this, CreatePickupActivity.class);

        makeEventButton = findViewById(R.id.makeEventButton);
        makeEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Button Success!!!");
                startActivity(intent);
            }
        });
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

        // Add a marker in Sydney and move the camera
        LatLng toronto = new LatLng(43.6629, -79.3957);
        drawPickUpMarker(mMap, toronto, 1000);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(toronto));

    }

    public void drawPickUpMarker(GoogleMap gmap, LatLng coordinates, int radius) {
        mMap.addMarker(new MarkerOptions().position(coordinates).title("UofT"));
        int color = (150 & 0xff) << 24 | (107 & 0xff) << 16 | (159 & 0xff) << 8 | (242 & 0xff);
        int transparent = (0 & 0xff) << 24 | (107 & 0xff) << 16 | (159 & 0xff) << 8 | (242 & 0xff);
        Circle circle = mMap.addCircle(new CircleOptions()
                .center(coordinates)
                .radius(100)
                .fillColor(color)
                .strokeColor(transparent));
    }
}
