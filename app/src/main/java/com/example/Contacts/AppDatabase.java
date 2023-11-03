package com.example.Contacts;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Contacts.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContactsDAO contactsDAO();
}
