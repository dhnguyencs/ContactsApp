package com.example.Contacts;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.AsyncTask;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ContactTableTasks {
    public static class DeleteTask extends AsyncTask<Contacts, Void, Void> {
        private ContactsDAO dao;
        public DeleteTask(ContactsDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Contacts... contact) {
            dao.delete(contact[0]);
            return null;
        }
    }
    public static class UpdateTask extends AsyncTask<Contacts, Void, Void> {
        private ContactsDAO dao;
        public UpdateTask(ContactsDAO dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Contacts... contact) {
            for(int i = 0; i < contact.length; i++){
                dao.update(contact[i]);
            }
            return null;
        }
    }
//    public static class InsertListTask extends AsyncTask<ArrayList<Contacts>, Void, ArrayList<Contacts>> {
//        ContactsDAO dao;
//        public InsertListTask(ContactsDAO dao) { this.dao = dao; }
//
//        @Override
//        protected ArrayList<Contacts> doInBackground(ArrayList<Contacts>... arrayLists) {
//            return null;
//        }
//    }
    public static class InsertTask extends AsyncTask<Contacts, Void, Void> {
        ContactsDAO dao;
        public InsertTask(ContactsDAO dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Contacts ... contact) {
            Random rand = new Random();
            contact[0].uid = String_ext.bytesToHex(
                    ((new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date())).toString()
                    + (rand.nextFloat())
                    ).getBytes());
            dao.insertData(contact[0]);
            return null;
        }
    }
    public static class SelectAllContactsTask extends AsyncTask<Void, Void, ArrayList<Contacts>> {
        //private RecyclerView tableLayout;
        private lambda_one_param<ArrayList<Contacts>, Void> customPostExecuteCode;
        private lambda_no_params<ArrayList<Contacts>> customDoInBackGround;

        public SelectAllContactsTask(lambda_no_params<ArrayList<Contacts>> customDoInBackGround, lambda_one_param<ArrayList<Contacts>, Void> customPostExecuteCode){
            this.customDoInBackGround = customDoInBackGround;
            this.customPostExecuteCode = customPostExecuteCode;
        }
        @Override
        protected ArrayList<Contacts> doInBackground(Void ... voids) {
            return customDoInBackGround.execute();
        }
        @Override
        protected void onPostExecute(ArrayList<Contacts> result) {
            if (result == null) return;
            customPostExecuteCode.execute(result);
        }
    }

}
