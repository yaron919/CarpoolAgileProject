package com.afeka.agile.carpoolagileproject;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class FireApp extends Application {

    FirebaseDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = FirebaseDatabase.getInstance();
    }
}
