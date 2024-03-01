package com.example.retrofitecommerceapp.model;

import java.io.Serializable;

public class RatingModelClass implements Serializable {
    float rate;
    int count;

    public RatingModelClass(float rate, int count) {
        this.rate = rate;
        this.count = count;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
