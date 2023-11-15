package com.example.Contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;

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
        inflateList();
    }
    private void seed(ArrayList<Contacts> contacts){
        Date newDate = new Date();
        Random newRand = new Random();
        long x = 10005000;
        Contacts newContact1 = new Contacts("David", "Nguyen", "123 12th AVE SW", "Seattle", newDate.getTime() - (newRand.nextLong() / x), "2064440217");
        Contacts newContact2 = new Contacts("Emily", "Smith", "456 Elm St", "New York", newDate.getTime() - Math.abs((newRand.nextLong() / x)), "2125550123");
        Contacts newContact3 = new Contacts("James", "Johnson", "789 Oak Blvd", "Los Angeles", newDate.getTime() - Math.abs((newRand.nextLong() / x)), "3237894561");
        Contacts newContact4 = new Contacts("Sophia", "Lee", "101 Pine Ln", "San Francisco",newDate.getTime() - Math.abs((newRand.nextLong() / x)), "4151237890");
        Contacts newContact5 = new Contacts("Michael", "Wang", "202 Maple Dr", "Chicago",newDate.getTime() - Math.abs((newRand.nextLong() / x)), "3124567890");
        Contacts newContact6 = new Contacts("Olivia", "Brown", "303 Cedar Ave", "Boston",newDate.getTime() - Math.abs((newRand.nextLong() / x)), "6179876543");
        Contacts newContact7 = new Contacts("Ethan", "Garcia", "404 Birch Rd", "Dallas",newDate.getTime() - Math.abs((newRand.nextLong() / x)), "2148765432");
        Contacts newContact8 = new Contacts("Ava", "Martinez", "505 Willow St", "Houston",newDate.getTime() - Math.abs((newRand.nextLong() / x)), "7132345678");
        Contacts newContact9 = new Contacts("Liam", "Taylor", "606 Spruce Ct", "Miami",newDate.getTime() - Math.abs((newRand.nextLong() / x)), "3057654321");
        Contacts newContact10 = new Contacts("Emma", "Jones", "707 Redwood Ln", "Atlanta",newDate.getTime() - Math.abs((newRand.nextLong() / x)), "4048765432");

        Contacts newContact11 = new Contacts("Ann", "Williamson", "456 Elm St", "New York", newDate.getTime() - Math.abs((newRand.nextLong() / x)), "2125550123");
        Contacts newContact12 = new Contacts("Amanda", "Johnson", "789 Oak Blvd", "Los Angeles", newDate.getTime() - Math.abs((newRand.nextLong() / x)), "3237894561");
        Contacts newContact13 = new Contacts("Jennifer", "Mullally", "101 Pine Ln", "San Francisco", newDate.getTime() - Math.abs((newRand.nextLong() / x)), "4151237890");
        Contacts newContact14 = new Contacts("Micheal", "Moor", "202 Maple Dr", "Chicago", newDate.getTime() - Math.abs((newRand.nextLong() / x)), "3124567890");
        Contacts newContact15 = new Contacts("Katie", "Reynolds", "303 Cedar Ave", "Boston", newDate.getTime() - Math.abs((newRand.nextLong() / x)), "6179876543");
        Contacts newContact16 = new Contacts("Ariana", "Garcia", "404 Birch Rd", "Dallas", newDate.getTime() - Math.abs((newRand.nextLong() / x)), "2148765432");
        Contacts newContact17 = new Contacts("Tully", "Eva", "505 Willow St", "Houston", newDate.getTime() - Math.abs((newRand.nextLong() / x)), "7132345678");
        Contacts newContact18 = new Contacts("Daniel", "Green", "606 Spruce Ct", "Miami", newDate.getTime() - Math.abs((newRand.nextLong() / x)), "3057654321");
        Contacts newContact19 = new Contacts("Ann", "Hart", "707 Redwood Ln", "Atlanta", newDate.getTime() - Math.abs((newRand.nextLong() / x)), "4048765432");

        contacts.add(newContact1);
        contacts.add(newContact2);
        contacts.add(newContact3);
        contacts.add(newContact4);
        contacts.add(newContact5);
        contacts.add(newContact6);
        contacts.add(newContact7);
        contacts.add(newContact8);
        contacts.add(newContact9);
        contacts.add(newContact10);
        contacts.add(newContact11);
        contacts.add(newContact12);
        contacts.add(newContact13);
        contacts.add(newContact14);
        contacts.add(newContact15);
        contacts.add(newContact16);
        contacts.add(newContact17);
        contacts.add(newContact18);
        contacts.add(newContact19);

        for(Contacts contact : contacts){
            ContactTableTasks.InsertTask newInsertTask = new ContactTableTasks.InsertTask(ContactsDAO.getDao(getApplicationContext()));
            newInsertTask.execute(contact);
        }
    }
    public void inflateList(){

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

        lambda_no_params<ArrayList<Contacts>> customDoInBackground = ()-> new ArrayList<Contacts>(ContactsDAO.getDao(getApplicationContext()).getAll());

        lambda_one_param<ArrayList<Contacts>, Void> customPostExecute = (ArrayList<Contacts> contacts)->{
            if(contacts.size() == 0) seed(contacts);
            Collections.sort(contacts, Comparator.comparing(o -> o.lastName));
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