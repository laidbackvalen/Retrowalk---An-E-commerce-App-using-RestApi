package com.example.retrofitecommerceapp.model;

import com.google.gson.annotations.SerializedName;

public class LoginDataModel {
    @SerializedName("username")
    String email;
    @SerializedName("password")
    String password;
    public LoginDataModel(){

    }

    public LoginDataModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
