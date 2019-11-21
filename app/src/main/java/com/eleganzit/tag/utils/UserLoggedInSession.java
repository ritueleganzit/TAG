package com.eleganzit.tag.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.eleganzit.tag.HomeActivity;
import com.eleganzit.tag.LetsGetStartedActivity;
import com.eleganzit.tag.LoginActivity;

import java.util.HashMap;

public class UserLoggedInSession {

    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;
    Activity activity;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "TAG";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)


    public static final String EMAIL= "email_id";
    public static final String USER_ID = "user_id";

    public static final String USER_NAME = "user_name";
    public static final String USER_PHOTO = "photo";
    public static final String USER_PHONE = "phone";

    public UserLoggedInSession(Context context){
        this._context = context;

        this.activity = (Activity) context;

        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public void createLoginSession(String email,String user_id,String user_name,String photo,String phone){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref   ,
        editor.putString(USER_ID, user_id);

        editor.putString(EMAIL, email);

        editor.putString(USER_NAME, user_name);

        editor.putString(USER_PHOTO, photo);
        editor.putString(USER_PHONE, phone);


        // commit changes
        editor.commit();
        Intent i = new Intent(_context, HomeActivity.class);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        _context.startActivity(i);
        activity.finish();
        activity.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);




    }

    public void updateData(String email,String user_id,String user_name,String phone){
        // Storing login value as TRUE

        // Storing name in pref   ,
        editor.putString(USER_ID, user_id);

        editor.putString(EMAIL, email);

        editor.putString(USER_NAME, user_name);

        editor.putString(USER_PHONE, phone);


        // commit changes
        editor.commit();





    }    public void updateData(String photo){
        // Storing login value as TRUE

        // Storing name in pref   ,
        editor.putString(USER_PHOTO, photo);




        // commit changes
        editor.commit();





    }

    public void createSignUpSession(String email,String user_id,String user_name,String photo,String phone){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref   ,
        editor.putString(USER_ID, user_id);

        editor.putString(EMAIL, email);

        editor.putString(USER_NAME, user_name);

        editor.putString(USER_PHOTO, photo);
        editor.putString(USER_PHONE, phone);


        // commit changes
        editor.commit();
        Intent i = new Intent(_context, LetsGetStartedActivity.class);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        _context.startActivity(i);
        activity.finish();
        activity.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);




    }


    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }


    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(USER_ID, pref.getString(USER_ID, null));

        user.put(EMAIL, pref.getString(EMAIL, null));
        user.put(USER_NAME, pref.getString(USER_NAME, null));
        user.put(USER_PHOTO, pref.getString(USER_PHOTO, null));
        user.put(USER_PHONE, pref.getString(USER_PHONE, null));





        // return user
        return user;
    }



    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        // Staring Login Activity
        _context.startActivity(i);
        activity.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

}
