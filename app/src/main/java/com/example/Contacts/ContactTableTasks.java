package com.example.Contacts;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public static class InsertTask extends AsyncTask<Contacts, Void, ArrayList<Contacts>> {
        private ContactsDAO dao;
        private int startingUID;
        public InsertTask(ContactsDAO dao, int startingUID){
            this.dao = dao;
            this.startingUID = startingUID;
        }

        @Override
        protected ArrayList<Contacts> doInBackground(Contacts ... contacts) {
            ArrayList<Contacts> contactsArrayList = new ArrayList<Contacts>();
            int i = 0;
            for(Contacts contact : contacts){
                contact.uid = contact.uid + i++;
            }
            dao.insertAll(contacts);
            return null;
        }
    }
    public static class SelectAllContactsTask extends AsyncTask<Void, Void, List<Contacts>> {
        //private RecyclerView tableLayout;
        lambda_one_param<ArrayList<Contacts>> customPostExecuteCode;
        private ContactsDAO dao;

        public SelectAllContactsTask(ContactsDAO dao, lambda_one_param<ArrayList<Contacts>> customPostExecuteCode){
            this.dao = dao;
            this.customPostExecuteCode = customPostExecuteCode;
        }

        @Override
        protected List<Contacts> doInBackground(Void ... voids) {
            return dao.getAll();
        }
        @Override
        protected void onPostExecute(List<Contacts> result) {
            if (result == null) return;
            customPostExecuteCode.execute(new ArrayList<Contacts>(result));
        }
    }

}
