package com.example.retrofitecommerceapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataModel implements Serializable {
    @SerializedName("id")
    int id;
    @SerializedName("image")
    String imageUrl;
    @SerializedName("title")
    String title;
    @SerializedName("description")
    String description;
    @SerializedName("price")
    Float price;
    @SerializedName("category")
    String category;
    @SerializedName("rating")
    RatingModelClass rating;
    @SerializedName("quantity")
    int quantity = 1;

    public DataModel(int id, String imageUrl, String title, String description, Float price, String category, RatingModelClass rating) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.rating = rating;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public DataModel() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    //    RATING
    public RatingModelClass getRating() {
        return rating;
    }
    public void setRating(RatingModelClass rating) {
        this.rating = rating;
    }
}
