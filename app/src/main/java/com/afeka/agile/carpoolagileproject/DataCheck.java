package com.afeka.agile.carpoolagileproject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
            int maxSeats = 5;
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
}
