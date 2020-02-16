package com.example.pickup;

import android.content.Intent;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.Map;

public class MarkerClickListener implements GoogleMap.OnMarkerClickListener {

    @Override
    public boolean onMarkerClick(Marker m) {

        ActivityToMarkerBundle bundle = (ActivityToMarkerBundle) m.getTag();

        Intent intent = new Intent(bundle.mapActivity, PickupInfoActivity.class);

        Event event = (Event) bundle.event;

        intent.putExtra("Description", event.description);
        intent.putExtra("MinPeople", event.minPeople);
        intent.putExtra("MaxPeople", event.maxPeople);
        intent.putExtra("latitude", event.latitude);
        intent.putExtra("longitude", event.longitude);
        intent.putExtra("radius", event.radius);

        bundle.mapActivity.startActivity(intent);
        return false;
    }
}

