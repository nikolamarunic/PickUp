package com.example.pickup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;

public class HostEventLockScreen extends ListActivity implements Updateable {
    final int RADIUS = 1000;
    int counter = 0;
    ArrayList<String> listItems=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    public PickUpClient client;
    public ClientRunnable clientRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        client = new PickUpClient();
        clientRunnable = new ClientRunnable(client, this);

        try {
            client.update();
        } catch (Throwable t) {
            t.printStackTrace();
            System.out.println("Cannot update");
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_event_lock_screen);
        final Button leaveButton = findViewById(R.id.leaveBtn);
        leaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    PickUpClient serverCaller = new PickUpClient();
                    serverCaller.destroyGeofence();
                } catch(Throwable t){
                    t.printStackTrace();
                    System.out.println("Server request failed.");
                }
                finish();
            }
        });
        Intent intent = getIntent();
        int max = intent.getIntExtra("Max", 0);
        int min = intent.getIntExtra("Min", 0);
        String desc = intent.getStringExtra("Desc");

        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        setListAdapter(adapter);


        for (PickUpClient.LobbyEntry player:client.players) {
            addItems(player.username);
        }

        try {
            (new PickUpClient()).createGeofence(100, max, desc);
        } catch (Throwable t) {
            System.out.println("Help");
            t.printStackTrace();
        }

        clientRunnable.start();
    }

    public void addItems(View v) {
        listItems.add("Clicked : "+counter++);
        adapter.notifyDataSetChanged();
    }

    public void addItems(String item) {
        listItems.add(item);
        adapter.notifyDataSetChanged();
    }

    public void subtractItems(String item) {
        listItems.remove(item);
        adapter.notifyDataSetChanged();
    }

    public void clearItems() {
        listItems = new ArrayList<>();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            (new PickUpClient()).destroyGeofence();
        } catch (Throwable t) {
            System.out.println("Can't leave event");
            t.printStackTrace();
        }
    }

    @Override
    public void update() {
        try {
            client.update();
        } catch (Throwable t) {
            t.printStackTrace();
            System.out.println("Cannot update");
        }
        clearItems();
        for (PickUpClient.LobbyEntry player:client.players) {
            addItems(player.username);
        }
    }
}
