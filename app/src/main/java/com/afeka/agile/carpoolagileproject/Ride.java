package com.afeka.agile.carpoolagileproject;

public class Ride {
   // String user;
    public String time;
    public String date;
    public String seats;
    //Todo need to add the user name in ride

    public Ride(){
        // Default constructor required for calls to DataSnapshot.getValue(Ride.class)
    }

    public Ride(String time,String date,String seats){
        this.time = time;
        this.date = date;
        this.seats = seats;
    }
}
