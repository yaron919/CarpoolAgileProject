package com.afeka.agile.carpoolagileproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    private Intent intentToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        final EditText name = findViewById(R.id.reg_name);
        final EditText age = findViewById(R.id.age_reg);
        final EditText carModel = findViewById(R.id.car_reg);
        final EditText seatNumber = findViewById(R.id.seatNumber_reg);
        final EditText userName = findViewById(R.id.username_reg);
        final EditText password = findViewById(R.id.password_reg);
        Button confirmButton = findViewById(R.id.registration_button);

        intentToMain = new Intent(this,MainMenuActivity.class);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /** TO DO - ADD DB FireBase connection **/
                String textName = name.getText().toString();
                String textAge = age.getText().toString();
                String textCarModel = carModel.getText().toString();
                String textSeatNumber = seatNumber.getText().toString();
                String textUuserName= userName.getText().toString();
                String textPassword = password.getText().toString();

                Toast.makeText(getApplicationContext(),R.string.join_sent_request,Toast.LENGTH_LONG).show();

                startActivity(intentToMain);
            }
        });

    }
}
