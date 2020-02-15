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
    void createEvent() {
        if (hasActiveEvent) {
            System.out.println("Already have an active game");
            return;
        }
        hasActiveEvent = true;

        //Must communicate with server to create geofence
        //
        //create new event

    }

    /**
     * Destroys the active event of a user.
     */
    void destroyEvent() { //Called to event which then calls here
        hasActiveEvent = false;
    }

    /**
     * Expresses to an event that a user would like to join.
     * @param event the event the user is interested in.
     */
    void expressIntent(Event event) {
        event.addGuestWithIntent(this); //perhaps want to send distance as well?
        hasActiveEvent = true;
    }



}
