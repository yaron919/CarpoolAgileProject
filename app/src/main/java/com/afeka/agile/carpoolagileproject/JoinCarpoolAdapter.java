package com.afeka.agile.carpoolagileproject;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by idoshapira-mbp on 29/05/2018.
 */

public class JoinCarpoolAdapter extends ArrayAdapter<Ride> {

    private static final String TAG = "JoinCarpool";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;
    private ArrayList<String> ridesId;

    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView time;
        TextView date;
        TextView seats;
        TextView driver;
    }

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public JoinCarpoolAdapter(Context context, int resource, ArrayList<Ride> objects,ArrayList<String> ridesId) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        this.ridesId = ridesId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information
        String driver = getItem(position).getDriver();
        String date = getItem(position).getDate();
        String seats = getItem(position).getSeats();
        String time = getItem(position).getTime();
        String city = getItem(position).getCity();
        final String rideId = ridesId.get(position);


        //Create the person object with the information
        final Ride ride = new Ride(time,date,seats,driver,city);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView driverName = (TextView) convertView.findViewById(R.id.listDriverRide);
        TextView rideTime = (TextView) convertView.findViewById(R.id.listTimeRide);
        TextView rideDate = (TextView) convertView.findViewById(R.id.listDateRide);
        TextView rideSeats = (TextView) convertView.findViewById(R.id.listSeatsRide);
        TextView rideCity = (TextView) convertView.findViewById(R.id.listCityRide);
        Button joinButton = (Button) convertView.findViewById(R.id.listBtnJoinRide);

        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSeats(rideId,ride);
                mContext.startActivity(new Intent(mContext,MainMenuActivity.class));
            }
        });
        driverName.setText(driver);
        rideTime.setText("Arrival: "+time);
        rideDate.setText(date);
        rideSeats.setText("Seats: "+seats);
        rideCity.setText("From: "+city);
        return convertView;
    }

    private void updateSeats(String rideId, Ride ride) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("rides/"+rideId);
        int newSeats = Integer.parseInt(ride.getSeats() )- 1;
        ride.setSeats(""+newSeats);
        ref.setValue(ride);
    }
}

