package com.example.Contacts;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contacts {
    public Contacts(String uid, String firstName, String lastName, String address, String city, int age){
        this.uid        = uid;
        this.firstName  = firstName;
        this.lastName   = lastName;
        this.address    = address;
        this.city       = city;
        this.age        = age;
    }
    public Contacts(String firstName, String lastName, String address, String city, int age){
        this.firstName  = firstName;
        this.lastName   = lastName;
        this.address    = address;
        this.city       = city;
        this.age        = age;
    }
    public Contacts(){};
    @PrimaryKey
    @NonNull
    public String uid;
    public String getUid_str(){
        return (new StringBuilder().append(uid)).toString();
    }
    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo(name = "address")
    public String address;

    @ColumnInfo(name = "city")
    public String city;

    @ColumnInfo(name = "age")
    public int age;

    public String getAge_str(){
        return (new StringBuilder().append(age)).toString();
    }

    @NonNull
    public String toString(){
        return firstName + " " + lastName + " " + address + " " + city + " " + age;
    }
}

