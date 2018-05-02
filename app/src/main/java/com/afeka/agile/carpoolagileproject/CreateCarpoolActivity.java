package com.afeka.agile.carpoolagileproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

public class CreateCarpoolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_carpool);
        final EditText time = (EditText) findViewById(R.id.time_of_ride);
        final EditText date = (EditText) findViewById(R.id.date_of_ride);
        final EditText numSeats = (EditText) findViewById(R.id.number_of_seats);
        Button confirmButton = (Button) findViewById(R.id.confirm_join);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /** TO DO - ADD DB FireBase connection **/
                String textTime = time.getText().toString();
                String textDate = date.getText().toString();
                String textSeats = numSeats.getText().toString();

                Toast.makeText(getApplicationContext(),"Carpool Created!",Toast.LENGTH_LONG).show();

            }
        });



    }
}
