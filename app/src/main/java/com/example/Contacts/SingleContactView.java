package com.example.Contacts;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class SingleContactView extends AppCompatActivity {
    private AppDatabase db;
    private ContactsDAO contactsDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_contact_view);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "sample_database").build();
        contactsDao = db.contactsDAO();

        int uid = getIntent().getExtras().getInt("UID");
        ContactTableTasks.SelectAllContactsTask selectAllContactsTask = new ContactTableTasks.SelectAllContactsTask(contactsDao, (contactsDao)->{
            List<Contacts> contact = new ArrayList<Contacts>();
            contact.add(contactsDao.findByUID(uid));
            return contact;
        }, (ArrayList<Contacts> contacts)->{
            TextView name = (TextView) findViewById(R.id.Name);
            name.setText(contacts.get(0).firstName);
           return null;
        });
        selectAllContactsTask.execute();
    }
}
