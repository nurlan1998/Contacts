package com.example.contacts;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class ContactDatabase extends RoomDatabase {
    private static ContactDatabase database;
    private static final String DB_NAME = "contact.db";
    private static final Object LOCK = new Object();

    public static ContactDatabase getInstance(Context context) {
        synchronized (LOCK) {
            if (database == null) {
                database = Room.databaseBuilder(context, ContactDatabase.class, DB_NAME)
                        .build();
            }
        }
        return database;
    }

    public abstract ContactDao contactDao();
}
