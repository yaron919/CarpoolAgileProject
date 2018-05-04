package com.afeka.agile.carpoolagileproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JoinCarpoolActivity extends AppCompatActivity {

    private Intent intentToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_carpool);
        final EditText time = findViewById(R.id.join_time);
        final EditText date = findViewById(R.id.join_date);
        final EditText location = findViewById(R.id.join_location);
        Button confirmButton = findViewById(R.id.join_confirm_b);

        intentToMain = new Intent(this,MainMenuActivity.class);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /** TO DO - ADD DB FireBase connection **/
                String textTime = time.getText().toString();
                String textDate = date.getText().toString();
                String textLocation = location.getText().toString();

                Toast.makeText(getApplicationContext(),R.string.join_sent_request,Toast.LENGTH_LONG).show();

                startActivity(intentToMain);
            }
        });

    }
}
