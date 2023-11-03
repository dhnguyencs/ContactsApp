package com.example.Contacts;

import java.util.ArrayList;

public class ContactsSampleData {
    public ArrayList<Contacts> contacts;
    public ContactsSampleData(){
        Contacts c1 = new Contacts(1, "David", "Nguyen", "123 East Way", "Seattle", 26);
        Contacts c2 = new Contacts(2, "John", "Doe", "1659 Ambaulm", "New York", 45);
        Contacts c3 = new Contacts(3, "Jonathan", "Sparling", "454 South Madrona", "Seattle", 34);
        Contacts c4 = new Contacts(4, "Gill", "Sanchez", "232 Cherry St", "Seattle", 23);
        Contacts c5 = new Contacts(5, "Ann", "Williamson", "5454 Bothell Way NE", "Seattle", 56);

        contacts = new ArrayList<Contacts>();
        contacts.add(c1);
        contacts.add(c2);
        contacts.add(c3);
        contacts.add(c4);
        contacts.add(c5);
    }
    public Contacts[] getSampleData(){
        return contacts.toArray(new Contacts[contacts.size()]);
    }
}
