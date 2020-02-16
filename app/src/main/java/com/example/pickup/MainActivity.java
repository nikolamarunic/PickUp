package com.example.pickup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.radar.sdk.Radar;

public class MainActivity extends AppCompatActivity {
    public Button goButton;
    public EditText name;
    public Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Radar.initialize("prj_live_pk_598126e8bc2d50f1da77cf8261a9255c9c7d63fc");
        Radar.startTracking();

        intent = new Intent(this, MapActivity.class);

        name = findViewById(R.id.nameText);
        goButton = findViewById(R.id.bStartButton);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    (new PickUpClient()).nameSet(name.getText().toString());
                } catch (Throwable t) {
                    t.printStackTrace();
                    System.out.println("Cannot set name");
                }
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Radar.stopTracking();
    }
}
