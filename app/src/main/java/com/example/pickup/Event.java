package com.example.pickup;

public class Event {
    String id;
    int radius;

    double latitude;
    double longitude;

    int minPeople;
    int maxPeople;

    String description;

    public Event(String id, int radius, double latitude, double longitude, int minPeople, int maxPeople, String description) {
        this.id = id;
        this.radius = radius;
        this.latitude = latitude;
        this.longitude = longitude;
        this.minPeople = minPeople;
        this.maxPeople = maxPeople;
        this.description = description;
    }

    public void notifyUsers() {

    }

}
