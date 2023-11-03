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
    public Contacts(int uid, String firstName, String lastName, String address, String city, int age){
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.age = age;
    }
    public Contacts(String firstName, String lastName, String address, String city, int age){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.age = age;
    }
    public Contacts(){};
    @PrimaryKey
    public int uid;
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
    public TableRow createTableRow(Context context){

        TableRow newTableRow = new TableRow(context);
        newTableRow.setPadding(20, 20, 20, 20);
//        TextView id = new TextView(context);
//        id.setText(getUid_str());
//        id.setTextColor(Color.BLACK);
//        id.setGravity(Gravity.LEFT);
//        newTableRow.addView(id);

        TextView name = new TextView(context);
        //name.getLayoutParams().height = 12;
        name.setText(firstName + " " + lastName);
        name.setTextColor(Color.BLACK);
        name.setGravity(Gravity.LEFT);
        name.setTextSize(40);
        newTableRow.addView(name);

//        TextView address_tv = new TextView(context);
//        //address_tv.getLayoutParams().height = 12;
//        address_tv.setText(address);
//        address_tv.setTextColor(Color.BLACK);
//        address_tv.setGravity(Gravity.LEFT);
//        newTableRow.addView(address_tv);

//        TextView city_tv = new TextView(context);
//        //city_tv.getLayoutParams().height = 12;
//        city_tv.setText(city);
//        city_tv.setTextColor(Color.BLACK);
//        city_tv.setGravity(Gravity.LEFT);
//        newTableRow.addView(city_tv);

//        TextView age_tv = new TextView(context);
//        //age_tv.getLayoutParams().height = 12;
//        age_tv.setText(getAge_str());
//        age_tv.setTextColor(Color.BLACK);
//        age_tv.setGravity(Gravity.LEFT);
//        newTableRow.addView(age_tv);

        return newTableRow;
    }
}

