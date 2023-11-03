package com.example.Contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kotlin.jvm.internal.Lambda;

public class contactListAdaptor extends RecyclerView.Adapter<contactListAdaptor.myviewholder> {
    ArrayList<Contacts> contacts;
    lambda_two_param<Contacts, View, Void> customViewCode;
    public contactListAdaptor(ArrayList<Contacts> contacts, lambda_two_param<Contacts, View, Void> customViewCode){
        this.customViewCode = customViewCode;
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public contactListAdaptor.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item_single_layout, parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull contactListAdaptor.myviewholder holder, int position) {
        holder.setData(contacts.get(position));
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
    public class myviewholder extends  RecyclerView.ViewHolder {
        View __itemView;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            this.__itemView = itemView;
        }
        public void setData(Contacts contact){
            customViewCode.execute(contact, __itemView);
        }
    }
}
