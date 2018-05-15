package com.afeka.agile.carpoolagileproject;

/**
 * Created by idoshapira-mbp on 15/05/2018.
 */

public class UserNameHolder {

    private UserNameHolder() {
    }

    private static final UserNameHolder ourInstance = new UserNameHolder();
    public static UserNameHolder getInstance() {
        return ourInstance;
    }

    private String userName = null;
    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}



}
