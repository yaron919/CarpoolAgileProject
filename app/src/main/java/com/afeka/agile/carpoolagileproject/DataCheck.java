package com.afeka.agile.carpoolagileproject;

import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class DataCheck {

    public static boolean confirmTime(String textTime){
        String[] times = textTime.split(":");
        if(times.length != 2)
            return false;
        int hour = Integer.parseInt(times[0]);
        if(hour < 0 || hour >24)
            return false;
        double minutes = Double.parseDouble(times[1]);
        if(minutes < 0 || minutes >= 60)
            return false;
        return true;
    }

    public static boolean confirmDate(String textDate){
        String currDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String[] realDate = currDate.split("-");
        String[] userDate = textDate.split("-");



        if(userDate.length != 3)
            return false;
        try {
            //user entered date
            int day = Integer.parseInt(userDate[0]);
            int month = Integer.parseInt(userDate[1]);
            int year = Integer.parseInt(userDate[2]);

            // carpool most happen at the same year
            if (year != Integer.parseInt(realDate[2]))
                return false;
            //car pool most happen at the next 2 months
            if (month - Integer.parseInt(realDate[1]) < 0 ||
                    month - Integer.parseInt(realDate[1]) >1  )
                return false;
            if (day < 1 || day > 31)
                return false;
            switch (month) {
                case 4:
                case 5:
                case 7:
                case 8:
                case 12:
                    if (day > 30)
                        return false;
                    break;
                case 2:
                    if (day > 28)
                        return false;
                    break;
            }
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    public static boolean confirmSeats(String textSeats){
        try {
            int seats = Integer.parseInt(textSeats);
            if(seats < 1)
                return false;
            //TODO getting from db number of max seats in car
            checkSeatsDB();
            if(seats >= maxSeats)
                return false;

            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    public static boolean confirmAge(String textAge){
        try {
            int age = Integer.parseInt(textAge);
            if(age < 18)
                return false;
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    public static boolean confirmSeatNumber(String textSeats){
        try {
            int seats = Integer.parseInt(textSeats);
            if(seats < 1)
                return false;
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    public static int maxSeats = 5;
    public static void checkSeatsDB(){

        //Get datasnapshot at your "users" root node
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users");
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                        maxSeats= getUserSeats((Map<String,Object>) dataSnapshot.getValue());

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });

    }


    public static int getUserSeats(Map<String,Object> users) {
        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()){
            //Get user map
            Map singleUser = (Map) entry.getValue();
            //Get userName field and append to list
            String userName =(String) singleUser.get("userName");
            if(userName.equals(UserNameHolder.getInstance().getUserName()))
                return (int)singleUser.get("seats");
        }
        return -1;

    }
}
