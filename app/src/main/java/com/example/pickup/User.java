package com.example.pickup;

public class User {
    private int activeEvents;
    private String deviceID;
    private String name;

    public User (String givenID, String givenName) {
        activeEvents = 0;
        deviceID = givenID;
        name = givenName;
    }

    /**
     * Create an event hosted by the user.
     */
    void create_event() {
        if (activeEvents != 0) {
            System.out.println("Already have an active game");
            return;
        }
        activeEvents += 1;

        //Must communicate with server to create geofence

        //

        //create new event

    }

}
