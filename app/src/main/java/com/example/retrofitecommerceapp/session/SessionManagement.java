package com.example.retrofitecommerceapp.session;

import static com.example.retrofitecommerceapp.constants.Constants.EMAIL;
import static com.example.retrofitecommerceapp.constants.Constants.IF_LOGGED_IN;
import static com.example.retrofitecommerceapp.constants.Constants.PASSWORD;
import static com.example.retrofitecommerceapp.constants.Constants.PRICE;
import static com.example.retrofitecommerceapp.constants.Constants.USER_FILE_NAME;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManagement {
    Context context;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private final int PRIVATE_MODE = 0;

    public SessionManagement(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(USER_FILE_NAME, Context.MODE_PRIVATE);
//           preferences = context.getSharedPreferences(USER_FILE_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();

    }

    public boolean checkSession() {
        if (preferences.contains(IF_LOGGED_IN)) {
            return true;
        } else {
            return false;
        }
    }

    public void setUserInfo(String email, String password) {
        editor.putString(EMAIL, email);
        editor.putString(PASSWORD, password);
        editor.putBoolean(IF_LOGGED_IN, true);
        editor.commit();
    }

    public HashMap<String, String> getUserInfo() {
        HashMap<String, String> info = new HashMap<>();
        info.put(EMAIL, preferences.getString(EMAIL, null));
        info.put(PASSWORD, preferences.getString(PASSWORD, null));
        return info;
    }

    public void setPriceInfo(String price) {
        editor.putString(EMAIL, price);
        editor.putBoolean(IF_LOGGED_IN, true);
        editor.commit();
    }

    public HashMap<String, String> getPriceInfo() {
        HashMap<String, String> info = new HashMap<>();
        info.put(PRICE, preferences.getString(PRICE, null));
        return info;
    }


    //    public String  getSessionsDetails(String key) {
//        String value = preferences.getString(key, null);
//        return value;
//    }


    public void logOut() {
        editor.clear();
        editor.commit();
    }

}


