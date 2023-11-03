package com.example.Contacts;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactsDAO {
    @Query("SELECT * FROM contacts")
    List<Contacts> getAll();

    @Query("SELECT * FROM contacts WHERE uid IN (:userIds)")
    List<Contacts> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM contacts WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    Contacts findByName(String first, String last);

    @Query("SELECT * FROM contacts WHERE uid LIKE :uid " +
            "LIMIT 1")
    Contacts findByUID(int uid);

    @Insert
    void insertAll(Contacts... contacts);

    @Insert
    void insertData(Contacts contact);

    @Delete
    void delete(Contacts contact);

    @Update
    void update(Contacts contact);
}
