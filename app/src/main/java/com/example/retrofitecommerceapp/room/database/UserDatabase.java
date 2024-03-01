package com.example.retrofitecommerceapp.room.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.retrofitecommerceapp.room.dao.UserDatabaseAccessObject;
import com.example.retrofitecommerceapp.room.entity.UserEntitiesForCart;

@Database(entities = {UserEntitiesForCart.class}, exportSchema = true, version = 2)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDatabaseAccessObject databaseAccessObject();

    static UserDatabase mdb;
    static Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("alter table UserInfoCart add column qunatity_ForCort INTEGER not null default(1)");
        }
    };

    static UserDatabase getDatabase(Context context) {
        if (mdb == null) {
            synchronized (UserDatabase.class) {
                if (mdb == null) {
                    mdb = Room.databaseBuilder(context, UserDatabase.class, "UserInfoCart")
                            .addMigrations(MIGRATION_1_2).build();
                }
            }
        }
        return mdb;
    }
}

