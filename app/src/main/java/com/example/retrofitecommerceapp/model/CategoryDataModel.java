package com.example.retrofitecommerceapp.model;

import com.google.gson.annotations.SerializedName;

public class CategoryDataModel {
    @SerializedName("category")
    String category;

    public CategoryDataModel(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
