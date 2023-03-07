package com.example.contact;

import java.io.Serializable;
import java.util.ArrayList;

public class SandData implements Serializable {

    ArrayList<Contact> contactList;
    int position ;

    public SandData(ArrayList<Contact>contactList,int position){
        this.contactList = contactList;
        this.position = position;
    }

    public ArrayList<Contact> getContactList(){
        return contactList;
    }
    public int getPosition(){
        return position;
    }
}
