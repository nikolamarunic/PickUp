package com.example.pickup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class CreatePickupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pickup);

        Button lockButton = (Button) findViewById(R.id.createEventButton);

        lockButton.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                //Create the intent to start another activity
                Intent intent = new Intent(view.getContext(), HostEventLockScreen.class);

                TextInputEditText input1 = (TextInputEditText) findViewById(R.id.descriptionField);
                String desc = input1.getText().toString();

//                TextView input = (TextView)  findViewById(R.id.descriptionField);
//                String desc = input.getText().toString();

                String text = ((EditText) findViewById(R.id.minAmt)).getText().toString();
                int min = Integer.parseInt(text);

                text = ((EditText) findViewById(R.id.maxAmt)).getText().toString();

                int max = Integer.parseInt(text);


                intent.putExtra("Min", min);
                intent.putExtra("Max", max);
                intent.putExtra("Desc", desc);
                startActivity(intent);
            }
        });
    }

//    /**
//     * Detects when a button was clicked and determines what to do.
//     *
//     * @param view the view in which the button was pressed.
//     */
//    public void onClick(View view) {
//        Intent intent = new Intent(this, HostEventLockScreen.class);
//        TextInputLayout input = (TextInputLayout) findViewById(R.id.descriptionField);
//        String desc = input.getEditText().toString();
//        int min = Integer.parseInt(((EditText) findViewById(R.id.minAmt)).toString());
//        int max = Integer.parseInt(((EditText) findViewById(R.id.maxAmt)).toString());
//        intent.putExtra("Min", min);
//        intent.putExtra("Max", max);
//        intent.putExtra("Desc", desc);
//        startActivity(intent);
//
//    }
}
