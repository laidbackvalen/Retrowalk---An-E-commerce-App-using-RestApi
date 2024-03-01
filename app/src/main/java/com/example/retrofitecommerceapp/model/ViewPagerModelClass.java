package com.example.retrofitecommerceapp.model;

public class ViewPagerModelClass {
    //Here you can use string variable to store url
    //if you want to load image from the internet
    int image;

    public ViewPagerModelClass(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
