package com.example.pickup;

public class User {
    private boolean hasActiveEvent;
    private String deviceID;
    private String name;

    public User (String givenID, String givenName) {
        hasActiveEvent = false;
        deviceID = givenID;
        name = givenName;
    }

    /**
     * Create an event hosted by the user.
     */
    void create_event() {
        if (hasActiveEvent) {
            System.out.println("Already have an active game");
            return;
        }
        hasActiveEvent = true;

        //Must communicate with server to create geofence

        //

        //create new event

    }

}
