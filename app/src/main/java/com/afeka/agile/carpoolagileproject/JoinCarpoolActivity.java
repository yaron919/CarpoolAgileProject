package com.afeka.agile.carpoolagileproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JoinCarpoolActivity extends AppCompatActivity {

    private RecyclerView rides;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_carpool);
        rides = (RecyclerView) findViewById(R.id.rides_recycle_view);





    }
}
