package com.example.Contacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Date;

public class AddContact extends AppCompatActivity {
    private Button save_add_btn, back_btn;
    private TextInputEditText firstName, lastName, address, city;
    private EditText age, phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact_layout);
        save_add_btn = findViewById(R.id.btn1);
        back_btn = findViewById(R.id.back_btn_add_contact_activity);
        save_add_btn.setText(getIntent().getExtras().getString("ButtonText"));
        firstName = findViewById(R.id.first_name_input);
        lastName = findViewById(R.id.last_name_input);
        address = findViewById(R.id.address_input);
        city = findViewById(R.id.city_input);
        age = findViewById(R.id.age_input);
        phone = findViewById(R.id.phone_input);
        back_btn.setText( getIntent().getExtras().getBoolean("isEditMode") ? "Discard" : "Cancel" );
        if(getIntent().getExtras().getBoolean("isEditMode")){
            save_add_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Contacts newContact = getInputs();
                    newContact.uid = getIntent().getExtras().getString("UID");
                    ContactTableTasks.UpdateTask updateTask = new ContactTableTasks.UpdateTask(ContactsDAO.getDao(getApplicationContext()));
                    updateTask.execute(newContact);
                    Intent newIntent = new Intent(AddContact.this, SingleContactView.class);
                    newIntent.putExtra("UID", newContact.uid);
                    startActivity(newIntent);
                }
            });
            ((TextView) findViewById(R.id.title)).setText("Edit contact");
            setTextFields();

        }else{
            save_add_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Contacts newContact = getInputs();
                    if(newContact == null) return;
                    ContactTableTasks.InsertTask newInsertTask = new ContactTableTasks.InsertTask(ContactsDAO.getDao(getApplicationContext()));
                    newInsertTask.execute(newContact);
                    Intent newIntent = new Intent(AddContact.this, MainActivity.class);
                    newIntent.putExtra("UID", newContact.uid);
                    startActivity(newIntent);
                }
            });
        }
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(String_ext.isNullOrEmpty(getIntent().getExtras().getString("UID"))){
                    Intent newIntent = new Intent(AddContact.this, MainActivity.class);
                    startActivity(newIntent);
                }else{
                    Intent newIntent = new Intent(AddContact.this, SingleContactView.class);
                    newIntent.putExtra("UID", getIntent().getExtras().getString("UID"));
                    startActivity(newIntent);
                }
            }
        });
    }
    private Contacts getInputs(){
        String firstName_s, lastName_s, address_s, city_s;
        long age_int;
        firstName_s = firstName.getText().toString().trim();
        lastName_s = lastName.getText().toString().trim();
        address_s = address.getText().toString().trim();
        city_s = city.getText().toString().trim();
        if(firstName_s.isEmpty() || lastName_s.isEmpty()) return null;
        Date newDate = new Date(age.getImeOptions());
        age_int = newDate.getTime();
        String phone_number = phone.getText().toString();
        return new Contacts(firstName_s, lastName_s, address_s, city_s, age_int, phone_number);
    }
    private void setTextFields(){
        ContactTableTasks.SelectAllContactsTask selectAllContactsTask = new ContactTableTasks.SelectAllContactsTask(()->{
            ArrayList<Contacts> contacts = new ArrayList<Contacts>();
            contacts.add(ContactsDAO.getDao(getApplicationContext()).findByUID(getIntent().getExtras().getString("UID")));
            return contacts;
        }, (ArrayList<Contacts> contacts)->{
            Contacts contact = contacts.get(0);
            firstName.setText(contact.firstName);
            lastName.setText(contact.lastName);
            address.setText(contact.address);
            city.setText(contact.city);
            age.setText(contact.getAge_str());
            phone.setText(contact.phone_number);
            return null;
        });
        selectAllContactsTask.execute();
    }
}
