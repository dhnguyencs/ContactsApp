package com.example.Contacts;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.Date;
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
            ((TextView) findViewById(R.id.Name))    .setText(contacts.get(0).firstName + " " + contacts.get(0).lastName);
            ((TextView) findViewById(R.id.Address)) .setText(contacts.get(0).address);
            ((TextView) findViewById(R.id.City))    .setText(contacts.get(0).city);
            ((TextView) findViewById(R.id.Age))     .setText((new SimpleDateFormat("MM-dd-yyyy").format(new Date(contacts.get(0).age))));
            ((TextView) findViewById(R.id.PhoneNumber)).setText(contacts.get(0).get_phone_formatted());
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
