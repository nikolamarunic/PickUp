package com.example.pickup;

import java.util.ArrayList;

public class MapPresenter {
    static int currID = 0;
    ArrayList<Event> events;

    public void onCreate() {
        events = new ArrayList<>();
    }

    // Called by server
    // Create event object
    // Create marker
    // Give them both IDs
    public void makeEvent(float latitude, float longitude, int minPeople, int maxPeople, String description) {
        events.add(new Event(currID++, latitude, longitude, minPeople, maxPeople, description));
    }
}
