package com.example.pickup;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class GuestLockScreenActivity extends FragmentActivity implements OnMapReadyCallback {
    private final int color = (150 & 0xff) << 24 | (107 & 0xff) << 16 | (159 & 0xff) << 8 | (242 & 0xff);
    private final int transparent = (0 & 0xff) << 24 | (107 & 0xff) << 16 | (159 & 0xff) << 8 | (242 & 0xff);


    private GoogleMap mMap;
    private Event pickUpEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_lock_screen);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent receivedIntent = getIntent();
        String desc = receivedIntent.getStringExtra("Description");
        int maxPeople = receivedIntent.getIntExtra("MaxPeople", 0);
        int minPeople = receivedIntent.getIntExtra("MinPeople", 0);
        int radius = receivedIntent.getIntExtra("radius", 0);
        double longitude = receivedIntent.getDoubleExtra("longitude", 0);
        double latitude = receivedIntent.getDoubleExtra("latitude", 0);

        pickUpEvent = new Event(radius, latitude, longitude, minPeople, maxPeople, desc);
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
        LatLng toronto = new LatLng(43.6629, -79.3957);
        Event testEvent = new Event(1000, 43.6629, -79.3957, 1, 5, "night-time ball sesh");
        drawPickUpMarker(testEvent);
        mMap.setMinZoomPreference(14.0f);
        mMap.setMaxZoomPreference(20.0f);
        mMap.animateCamera(CameraUpdateFactory.zoomTo(30.0f));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(toronto));
        mMap.setOnMarkerClickListener(new MarkerClickListener());
    }

    public Marker drawPickUpMarker(Event pickUpEvent) {
        LatLng coordinates = new LatLng(pickUpEvent.latitude, pickUpEvent.longitude);
//        ActivityToMarkerBundle bundle = new ActivityToMarkerBundle(this, pickUpEvent);
        mMap.addCircle(new CircleOptions()
                .center(coordinates)
                .radius(pickUpEvent.radius)
                .fillColor(color)
                .strokeColor(transparent));
        Marker pickUpMarker = mMap.addMarker(new MarkerOptions().position(coordinates).title(pickUpEvent.description));
        pickUpMarker.setTag(pickUpEvent);
        return pickUpMarker;
    }
}
