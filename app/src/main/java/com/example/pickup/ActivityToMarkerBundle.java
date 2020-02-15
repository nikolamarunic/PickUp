package com.example.pickup;

public class ActivityToMarkerBundle {
    public MapActivity mapActivity;
    public Event event;

    public ActivityToMarkerBundle(MapActivity mapActivity, Event event) {
        this.mapActivity = mapActivity;
        this.event = event;
    }
}
