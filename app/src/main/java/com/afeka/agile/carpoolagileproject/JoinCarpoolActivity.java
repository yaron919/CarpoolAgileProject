package com.afeka.agile.carpoolagileproject;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class JoinCarpoolActivity extends AppCompatActivity {

    private ListView rides;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_carpool);
        rides = (ListView) findViewById(R.id.listJoinCarpool);
        TextView textView = new TextView(this);

        rides.setBackgroundColor(Color.parseColor("#ffffff"));
        ArrayList<Ride> ridesAvailable = new ArrayList<>();

        /***
         *
         * Add firebase conenction to show rides available
         */

        JoinCarpoolAdapter adapter= new JoinCarpoolAdapter(this,R.layout.adapter_view_layout,ridesAvailable);
        rides.setAdapter(adapter);

    }
}
