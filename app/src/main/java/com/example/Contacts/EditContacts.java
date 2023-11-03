package com.example.Contacts;

import android.widget.EditText;
import android.widget.TextView;

public class EditContacts {
    TextView uid;
    public EditText firstName_et;
    public EditText lastName_et;
    public EditText address_et;
    public EditText city_et;
    public EditText age;
    public EditContacts(TextView uid, EditText firstname, EditText lastName, EditText address, EditText city, EditText age){
        this.firstName_et = firstname;
        this.lastName_et = lastName;
        this.address_et = address;
        this.age = age;
        this.city_et = city;
        this.uid = uid;
    }
}
