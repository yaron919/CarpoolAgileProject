package com.afeka.agile.carpoolagileproject;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JoinCarpoolActivity extends AppCompatActivity {

    private static final String TAG = JoinCarpoolActivity.class.getSimpleName();
    private ListView rides;
    private String user = UserNameHolder.getInstance().getUserName();
    private ArrayList<Ride> ridesAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_carpool);
        rides = (ListView) findViewById(R.id.listJoinCarpool);
        TextView textView = new TextView(this);

        rides.setBackgroundColor(Color.parseColor("#ffffff"));
        ridesAvailable = new ArrayList<>();

        /***
         *
         * Add firebase conenction to show rides available
         */
        pullRidesData();

        JoinCarpoolAdapter adapter = new JoinCarpoolAdapter(this, R.layout.adapter_view_layout, ridesAvailable);
        rides.setAdapter(adapter);

    }

    private void pullRidesData() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("rides");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                Map<String, Object> rides;
                //Get map of users in datasnapshot
                rides = (Map<String, Object>) dataSnapshot.getValue();
                for (Map.Entry<String, Object> entry : rides.entrySet()){
                    //Get user map
                    Map singleRide = (Map) entry.getValue();
                    //Get userName field and append to list
                    //if (!singleRide.get("driver").toString().equals(user))
                    ridesAvailable.add(new Ride(singleRide.get("time").toString(),singleRide.get("date").toString(),singleRide.get("seats").toString(),singleRide.get("driver").toString()));
                    Log.d(TAG,"time : "+singleRide.get("time").toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }
}

