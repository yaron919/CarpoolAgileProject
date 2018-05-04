package com.afeka.agile.carpoolagileproject;

import android.app.Application;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FireApp extends Application {

    //DatabaseReference  database;

    @Override
    public void onCreate() {
        super.onCreate();
       // database = FirebaseDatabase.getInstance().getReference();
    }
}
