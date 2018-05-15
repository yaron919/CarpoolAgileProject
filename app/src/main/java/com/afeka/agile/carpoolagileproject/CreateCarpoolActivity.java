package com.afeka.agile.carpoolagileproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CreateCarpoolActivity extends AppCompatActivity {

    private Intent intentToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_carpool);
        final EditText time = findViewById(R.id.time_of_ride);
        final EditText date = findViewById(R.id.date_of_ride);
        final EditText numSeats = findViewById(R.id.number_of_seats);
        Button confirmButton = findViewById(R.id.confirm_join);

        intentToMain = new Intent(this,MainMenuActivity.class);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean validData = true;
                String textTime = time.getText().toString();
                String textDate = date.getText().toString();
                String textSeats = numSeats.getText().toString();
                validData = DataCheck.confirmSeats(textSeats)&& DataCheck.confirmTime(textTime)&& DataCheck.confirmDate(textDate);

                if(validData) {
                    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                    Ride ride = new Ride(textTime,textDate,textSeats);
                    database.child("rides").push().setValue(ride);
                    Toast.makeText(getApplicationContext(), "Carpool Created!", Toast.LENGTH_LONG).show();
                    startActivity(intentToMain);
                } else{
                     Toast.makeText(getApplicationContext(), R.string.unValidData, Toast.LENGTH_LONG).show();
                }

            }
        });

    }

}