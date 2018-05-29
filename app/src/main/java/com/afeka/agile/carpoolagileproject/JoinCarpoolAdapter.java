package com.afeka.agile.carpoolagileproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by idoshapira-mbp on 29/05/2018.
 */

public class JoinCarpoolAdapter extends ArrayAdapter<Ride> {

    private static final String TAG = "JoinCarpool";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

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
    public JoinCarpoolAdapter(Context context, int resource, ArrayList<Ride> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information
        String driver = getItem(position).getDriver();
        String date = getItem(position).getDate();
        String seats = getItem(position).getSeats();
        String time = getItem(position).getTime();


        //Create the person object with the information
        Ride ride = new Ride(time,date,seats,driver);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView driverName = (TextView) convertView.findViewById(R.id.listDriverRide);
        TextView rideTime = (TextView) convertView.findViewById(R.id.listTimeRide);
        TextView rideDate = (TextView) convertView.findViewById(R.id.listDateRide);

        driverName.setText(driver);
        rideTime.setText(time);
        rideDate.setText(date);

        return convertView;
    }
}

