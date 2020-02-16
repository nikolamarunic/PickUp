package com.example.pickup;

import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

public class MapPresenter {
    static int currID = 0;
    ArrayList<Event> events;
    MapActivity myMap;


    public MapPresenter(MapActivity newMap) {
        events = new ArrayList<>();
        myMap = newMap;
    }

    // Called by server
    // Create event object
    // Create marker
    public void makeEvent(String id, double latitude, double longitude, int minPeople, int maxPeople, String description) {
        Event myEvent = new Event(id, 100, latitude, longitude, minPeople, maxPeople, description);
        events.add(myEvent);
        myMap.drawPickUpMarker(myEvent);
    }
}
