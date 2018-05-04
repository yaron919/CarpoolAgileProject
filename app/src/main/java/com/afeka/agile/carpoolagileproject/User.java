package com.afeka.agile.carpoolagileproject;

/**
 * Created by idoshapira-mbp on 04/05/2018.
 */

public class User {

    // String user;
    public String name;
    public String age;
    public String carModel;
    public String seatNumbers;
    public String userName;
    public String password;
    //Todo need to add the user name in ride

    public User(){
        // Default constructor required for calls to DataSnapshot.getValue(Ride.class)
    }

    public User(String name,String age,String carModel,String seatNumbers,String userName,String password){
        this.name=name;
        this.age=age;
        this.carModel=carModel;
        this.seatNumbers=seatNumbers;
        this.userName=userName;
        this.password=password;

    }
}
