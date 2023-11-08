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

import java.time.LocalDate;
import java.util.ArrayList;

@Entity
public class Contacts {
    public Contacts(String uid, String firstName, String lastName, String address, String city, long age, String phone_number){
        this.uid        = uid;
        this.firstName  = firstName;
        this.lastName   = lastName;
        this.address    = address;
        this.city       = city;
        this.age        = age;
        this.phone_number = phone_number;
    }
    public Contacts(String firstName, String lastName, String address, String city, long age, String phone_number){
        this.firstName  = firstName;
        this.lastName   = lastName;
        this.address    = address;
        this.city       = city;
        this.age        = age;
        this.phone_number = phone_number;
    }
    public Contacts(){};
    @PrimaryKey
    @NonNull
    public String uid;
    public String getUid_str(){
        return (new StringBuilder().append(uid)).toString();
    }
    @ColumnInfo(name = "first_name")
    @NonNull
    public String firstName;

    @ColumnInfo(name = "last_name")
    @NonNull
    public String lastName;

    @ColumnInfo(name = "address")
    public String address;

    @ColumnInfo(name = "city")
    public String city;

    @ColumnInfo(name = "age")
    public long age;

    @ColumnInfo(name = "phone_number")
    public String phone_number;

    public String get_phone_formatted(){
        String country_code, area_code, block_1, block_2;
        country_code = area_code = block_1 = block_2 = "";
        char[] phone_num = phone_number.toCharArray();
        int offset = 0;
        if(phone_num.length == 11){
            country_code = "+" + Character.toString(phone_num[0]) + " ";
        }else{
            offset = -1;
        }
        area_code = Character.toString(phone_num[1 + offset])
                + Character.toString(phone_num[2 + offset])
                + Character.toString(phone_num[3 + offset]);
        block_1 = Character.toString(phone_num[4 + offset])
                + Character.toString(phone_num[5 + offset])
                + Character.toString(phone_num[6 + offset]);
        block_2 = Character.toString(phone_num[7 + offset])
                + Character.toString(phone_num[8 + offset])
                + Character.toString(phone_num[9 + offset])
                + Character.toString(phone_num[10 + offset]);
        return country_code + "(" + area_code + ")" + " " + block_1 + "-" + block_2;
    }
    public String getAge_str(){
        return (new StringBuilder().append(age)).toString();
    }

    @NonNull
    public String toString(){
        return firstName + " " + lastName + " " + address + " " + city + " " + age;
    }
}

