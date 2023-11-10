package com.example.Contacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SingleContactView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_contact_view);
        String uid = getIntent().getExtras().getString("UID");
        ContactTableTasks.SelectAllContactsTask selectAllContactsTask = new ContactTableTasks.SelectAllContactsTask(()->{
            ArrayList<Contacts> contacts = new ArrayList<Contacts>();
            contacts.add(ContactsDAO.getDao(getApplicationContext()).findByUID(uid));
            return contacts;
        }, (ArrayList<Contacts> contacts)->{
            Contacts contact = contacts.get(0);
            ((TextView) findViewById(R.id.Name))    .setText(contact.firstName + " " + contact.lastName);
            ((TextView) findViewById(R.id.Address)) .setText(contact.address);
            ((TextView) findViewById(R.id.City))    .setText(contact.city);
            ((TextView) findViewById(R.id.Age))     .setText(contact.getBirthDate() + "\n" + contact.getAge_str() + " years old");
            ((TextView) findViewById(R.id.PhoneNumber)).setText(contact.get_phone_formatted());
           return null;
        });
        selectAllContactsTask.execute();
        ((Button) findViewById(R.id.back_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(SingleContactView.this, MainActivity.class);
                startActivity(newIntent);
            }
        });
        ((Button) findViewById(R.id.edit_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(SingleContactView.this, AddContact.class);
                newIntent.putExtra("ButtonText", "Done");
                newIntent.putExtra("isEditMode", true);
                newIntent.putExtra("UID", uid);
                startActivity(newIntent);
            }
        });
    }
}
