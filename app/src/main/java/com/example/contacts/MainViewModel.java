package com.example.contacts;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private static ContactDatabase database;
    private LiveData<List<Contact>> contacts;

    public MainViewModel(@NonNull Application application) {
        super(application);
        database = ContactDatabase.getInstance(getApplication());
        contacts = database.contactDao().getAllContacts();
    }

    public LiveData<List<Contact>> getContacts() {
        return contacts;
    }

    public void insertContact(Contact contact){
        new InsertTask().execute(contact);
    }

    private static class InsertTask extends AsyncTask<Contact,Void,VerifyError>{

        @Override
        protected VerifyError doInBackground(Contact... contacts) {
            if(contacts != null && contacts.length > 0){
                database.contactDao().insertContact(contacts[0]);
            }
            return null;
        }
    }
}
