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

import java.util.Locale;
import java.util.Date;
import java.text.SimpleDateFormat;

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
                /** TO DO - ADD DB FireBase connection **/
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
/*
    private boolean confirmTime(String textTime){
        String[] times = textTime.split(":");
        if(times.length != 2)
            return false;
        int hour = Integer.parseInt(times[0]);
        if(hour < 0 || hour >24)
            return false;
        double minutes = Double.parseDouble(times[1]);
        if(minutes < 0 || minutes >= 60)
            return false;
        return true;
    }

    private boolean confirmDate(String textDate){
        String currDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String[] realDate = currDate.split("-");
        String[] userDate = textDate.split("\\.");



        if(userDate.length != 3)
            return false;
        try {
            //user entered date
            int day = Integer.parseInt(userDate[0]);
            int month = Integer.parseInt(userDate[1]);
            int year = Integer.parseInt(userDate[2]);

            // carpool most happen at the same year
            if (year != Integer.parseInt(realDate[2]))
                return false;
            //car pool most happen at the next 2 months
            if (month - Integer.parseInt(realDate[1]) < 0 ||
                    month - Integer.parseInt(realDate[1]) >1  )
                return false;
            if (day < 1 || day > 31)
                return false;
            switch (month) {
                case 4:
                case 5:
                case 7:
                case 8:
                case 12:
                    if (day > 30)
                        return false;
                    break;
                case 2:
                    if (day > 28)
                        return false;
                    break;
            }
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    private boolean confirmSeats(String textSeats){
        try {
            int seats = Integer.parseInt(textSeats);
            if(seats < 1)
                return false;
            //TODO getting from db number of max seats in car
            int maxSeats = 5;
            if(seats >= maxSeats)
                return false;

            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    */
}