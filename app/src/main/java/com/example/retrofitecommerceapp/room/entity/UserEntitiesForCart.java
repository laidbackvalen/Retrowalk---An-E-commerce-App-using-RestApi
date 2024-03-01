package com.example.retrofitecommerceapp.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "UserInfoCart")
public class UserEntitiesForCart {
    @PrimaryKey(autoGenerate = true)
    int id_ForCart;
    @ColumnInfo(name = "image")
    String imageUrl_ForCart;
    @ColumnInfo(name = "title")
    String title_ForCart;
    @ColumnInfo(name = "price")
    String price_ForCart;
    @ColumnInfo(name = "category")
    String category_ForCart;
    @ColumnInfo(name = "quantity")
    int qunatity_ForCort = 1;

    public int getQunatity_ForCort() {
        return qunatity_ForCort;
    }
    public void setQunatity_ForCort(int qunatity_ForCort) {
        this.qunatity_ForCort = qunatity_ForCort;
    }
    public UserEntitiesForCart() {
        //default constructor
    }
    public UserEntitiesForCart(String imageUrl_ForCart, String title_ForCart, String price_ForCart, String category_ForCart, int qunatity_ForCort) {
        this.imageUrl_ForCart = imageUrl_ForCart;
        this.title_ForCart = title_ForCart;
        this.price_ForCart = price_ForCart;
        this.category_ForCart = category_ForCart;
        this.qunatity_ForCort = qunatity_ForCort;
    }

    public int getId_ForCart() {
        return id_ForCart;
    }

    public void setId_ForCart(int id_ForCart) {
        this.id_ForCart = id_ForCart;
    }

    public String getImageUrl_ForCart() {
        return imageUrl_ForCart;
    }

    public void setImageUrl_ForCart(String imageUrl_ForCart) {
        this.imageUrl_ForCart = imageUrl_ForCart;
    }

    public String getTitle_ForCart() {
        return title_ForCart;
    }

    public void setTitle_ForCart(String title_ForCart) {
        this.title_ForCart = title_ForCart;
    }

    public String getPrice_ForCart() {
        return price_ForCart;
    }

    public void setPrice_ForCart(String price_ForCart) {
        this.price_ForCart = price_ForCart;
    }

    public String getCategory_ForCart() {
        return category_ForCart;
    }

    public void setCategory_ForCart(String category_ForCart) {
        this.category_ForCart = category_ForCart;
    }
}
