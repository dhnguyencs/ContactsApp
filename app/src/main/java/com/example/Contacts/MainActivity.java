package com.example.Contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView contact_list_recyclerView;
    private ImageButton addBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addBtn = (ImageButton) findViewById(R.id.addContactButton);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(MainActivity.this, AddContact.class);
                newIntent.putExtra("ButtonText", "Add");
                newIntent.putExtra("isEditMode", false);
                startActivity(newIntent);
            }
        });
        contact_list_recyclerView = (RecyclerView) findViewById(R.id.contact_list_main);
        contact_list_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewContactsTableData();
    }
    public void viewContactsTableData(){

        lambda_two_param<Contacts, View, Void> customAdaptorCode = (Contacts contact, View itemView)->{
            TextView textView = (TextView) itemView.findViewById(R.id.ContactNameSingle);
            textView.setText(String.format("%s %s", contact.firstName, contact.lastName));

            textView.setOnClickListener((view) -> {
                Intent intent = new Intent(MainActivity.this, SingleContactView.class);
                intent.putExtra("UID",contact.uid);
                startActivity(intent);
            });
            return null;
        };

        lambda_no_params<ArrayList<Contacts>> customDoInBackground = ()->{
            return new ArrayList<Contacts>(ContactsDAO.getDao(getApplicationContext()).getAll());
        };

        lambda_one_param<ArrayList<Contacts>, Void> customPostExecute = (ArrayList<Contacts> contacts)->{
            contactListAdaptor adaptor = new contactListAdaptor(contacts, customAdaptorCode);
            contact_list_recyclerView.setAdapter(adaptor);
            return null;
        };
        //reset the view
        contact_list_recyclerView.removeAllViews();

        //create the task
        ContactTableTasks.SelectAllContactsTask newSelectAllTask = new ContactTableTasks
                .SelectAllContactsTask(customDoInBackground, customPostExecute);

        //execute the task
        newSelectAllTask.execute();
    }
}