package com.example.retrofitecommerceapp.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.retrofitecommerceapp.room.entity.UserEntitiesForCart;
import java.util.List;

@androidx.room.Dao
public interface UserDatabaseAccessObject {
    @Query("SELECT * FROM UserInfoCart")
    List<UserEntitiesForCart> getUserCartInfo();

    @Insert
    void insertUserCartInfo(UserEntitiesForCart userEntitiesForCart);
    @Update
    void updateUserCartInfo(UserEntitiesForCart userEntitiesForCart);
    @Delete
    void deleteUserCartInfo(UserEntitiesForCart userEntitiesForCart);

}
