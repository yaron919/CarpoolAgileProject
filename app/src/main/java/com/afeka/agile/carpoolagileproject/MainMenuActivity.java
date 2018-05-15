package com.afeka.agile.carpoolagileproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;


public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Button joinButton = (Button) findViewById(R.id.join_carpool);
        Button createButton = (Button) findViewById(R.id.create_carpool);
        Button editInfoButton = (Button) findViewById(R.id.edit_info);

        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this,JoinCarpoolActivity.class);
                startActivity(intent);
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this,CreateCarpoolActivity.class);
                startActivity(intent);
            }
        });

        editInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });



    }
}
