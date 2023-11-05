package com.example.Contacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class AddContact extends AppCompatActivity {
    private Button button;
    private TextInputEditText firstName;
    private TextInputEditText lastName;
    private TextInputEditText address;
    private TextInputEditText city;
    private EditText age;
//    private void setTextFields(){
//        String uid = getIntent().getExtras().getString("uid");
//        Contacts contact = new ContactTableTasks.SelectAllContactsTask(ContactsDAO.getDao(getApplicationContext()),
//                (ContactsDAO dao));
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact_layout);
        button = (Button) findViewById(R.id.btn1);
        button.setText(getIntent().getExtras().getString("ButtonText"));
        firstName = findViewById(R.id.first_name_input);
        lastName = findViewById(R.id.last_name_input);
        address = findViewById(R.id.address_input);
        city = findViewById(R.id.city_input);
        age = findViewById(R.id.age_input);
        if(getIntent().getExtras().getBoolean("isEditMode")); //setTextFields();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contacts newContact = getInputs();
                if(newContact == null) return;
                ContactTableTasks.InsertTask newInsertTask = new ContactTableTasks.InsertTask(ContactsDAO.getDao(getApplicationContext()));
                newInsertTask.execute(newContact);
                Intent newIntent = new Intent(AddContact.this, MainActivity.class);
                startActivity(newIntent);
            }
        });
    }
    private Contacts getInputs(){
        String firstName_s, lastName_s, address_s, city_s;
        int age_int;
        firstName_s = firstName.getText().toString().trim();
        lastName_s = lastName.getText().toString().trim();
        address_s = address.getText().toString().trim();
        city_s = city.getText().toString().trim();
        if(firstName_s.isEmpty() || lastName_s.isEmpty()) return null;
        age_int = Integer.parseInt(age.getText().toString());
        return new Contacts(firstName_s, lastName_s, address_s, city_s, age_int);
    }
}
