package com.example.contact;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class ContactViewModel extends AndroidViewModel {

    public MutableLiveData<ArrayList<Contact>> ContactData = new MutableLiveData<>();

    ContactDataBase contactDataBase;
    ArrayList<Contact> contactList;

    public ContactViewModel(@NonNull Application application) {
        super(application);

        contactDataBase = new ContactDataBase(application.getBaseContext());
        contactList = new ArrayList<>();
        fetchDataBase();
    }

    @Override
    protected void onCleared() {

        super.onCleared();
    }

    void fetchDataBase(){
        contactList = contactDataBase.getAllData();

        Collections.sort(contactList , Comparator.comparing(obj -> obj.name.toLowerCase()));  // for sort the list
        ContactData.postValue(contactList);

    }

    public LiveData<ArrayList<Contact>> getContacteData() {
        fetchDataBase();
        return ContactData;
    }

}
