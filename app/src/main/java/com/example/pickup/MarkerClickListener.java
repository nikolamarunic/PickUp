package com.example.pickup;

import android.content.Intent;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.Map;

public class MarkerClickListener implements GoogleMap.OnMarkerClickListener {

    @Override
    public boolean onMarkerClick(Marker m) {

        ArrayList<Object> objects = (ArrayList<Object>) m.getTag();
        MapActivity mapActivity = (MapActivity) objects.get(0);

        Intent intent = new Intent(mapActivity, PickupInfoActivity.class);

        Event event = (Event) objects.get(1);

        intent.putExtra("Description", event.description);
        intent.putExtra("MinPeople", event.minPeople);
        intent.putExtra("MaxPeople", event.maxPeople);
        System.out.println("YEET");
        mapActivity.startActivity(intent);
        return false;
    }
}

