package com.example.pickup;

import java.util.ArrayList;

public class Event {
    int latitude;
    int longitude;

    String geofence_id;

    int min_people;
    int max_people;

    User host;
    ArrayList<User> guestsWithIntents;
    ArrayList<User> activeUsers;
    String description;

    public Event(int latitude, int longitude, int min_people, int max_people, User host, String description) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.min_people = min_people;
        this.max_people = max_people;
        this.host = host;
        this.description = description;

        activeUsers.add(host);
    }

    public void addGuestWithIntent(User guest) {
        guestsWithIntents.add(guest);
    }
}
