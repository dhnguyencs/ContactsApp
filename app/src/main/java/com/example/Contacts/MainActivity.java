package com.example.Contacts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView contact_list_recyclerView;
    private AppDatabase db;
    private ContactsDAO contactsDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "sample_database").build();
        contactsDao = db.contactsDAO();
        setContentView(R.layout.activity_main);
        contact_list_recyclerView = (RecyclerView) findViewById(R.id.contact_list_main);
        contact_list_recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        ContactsSampleData sample = new ContactsSampleData();
//        ContactTableTasks.InsertTask insertTask = new ContactTableTasks.InsertTask(contactsDao, 1000);
//        insertTask.execute(sample.getSampleData());
        viewContactsTableData();
    }
    public void viewContactsTableData(){
        contact_list_recyclerView.removeAllViews();
        ContactTableTasks.SelectAllContactsTask newSelectAllTask = new ContactTableTasks.SelectAllContactsTask(
                contactsDao, (ArrayList<Contacts> contacts)->{
                    contactListAdaptor adaptor = new contactListAdaptor(contacts, (Contacts contact, View itemView)-> {
                        TextView textView = (TextView) itemView.findViewById(R.id.ContactNameSingle);
                        textView.setText(String.format("%s %s", contact.firstName, contact.lastName));
                        textView.setOnClickListener(view -> Log.println(Log.VERBOSE, "Message::", String.format("%s %s", contact.firstName, contact.lastName)));
                    });
                    contact_list_recyclerView.setAdapter(adaptor);
                }
        );
        newSelectAllTask.execute();
    }
}