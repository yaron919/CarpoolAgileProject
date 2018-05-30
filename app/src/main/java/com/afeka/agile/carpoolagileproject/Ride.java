package com.afeka.agile.carpoolagileproject;

import com.google.firebase.database.DatabaseReference;

public class Ride  {
    //private String rideId;
    private String time;
    private String date;
    private String seats;
    private String driver;
    private String city;


    public Ride(){
        // Default constructor required for calls to DataSnapshot.getValue(Ride.class)
    }
    public Ride(String time, String date, String seats, String driver ,String city) {
        this.time = time;
        this.date = date;
        this.seats = seats;
        this.driver = driver;
        this.city = city;
    }
    public String getDate() {
        return date;
    }

    public String getDriver() {
        return driver;
    }

    public String getSeats() {
        return seats;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

