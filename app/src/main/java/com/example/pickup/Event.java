package com.example.pickup;

public class Event {
    int radius;

    double latitude;
    double longitude;

    int minPeople;
    int maxPeople;

    String description;

    public Event(int radius, double latitude, double longitude, int minPeople, int maxPeople, String description) {
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
