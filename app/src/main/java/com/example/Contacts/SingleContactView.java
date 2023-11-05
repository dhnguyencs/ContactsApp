package com.example.Contacts;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

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
            TextView name = (TextView) findViewById(R.id.Name);
            name.setText(contacts.get(0).firstName);
           return null;
        });
        selectAllContactsTask.execute();
    }
}
