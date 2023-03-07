package com.example.contact;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ContactAdapter.onAdapterClick , ContactList.onAddClickListnerInterface ,ContactDetailScreen.ContactDetailScreenInterface , EditExixtingContactScreen.UpdateDataInEdit{

    static final String TAG = "main";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState==null){
            setFragment(new ContactList());
        }
        Log.d(TAG, "onCreate: ");

    }
    void setFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.mainContainer,fragment);
        fragmentTransaction.commit();
    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d(TAG, "onPostResume: ");

    }

    @Override
    public void ClickOnAdd(ArrayList<Contact>contactList) {
        SandData sandData = new SandData(contactList , 0);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment EditContactObject = AddContactScreen.newInstance(sandData);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.mainContainer,EditContactObject).addToBackStack(null).commit();

    }

    @Override
    public void AdapterClick(ArrayList<Contact> contactList, int position) {

        SandData sandData = new SandData(contactList , position);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment EditContactObject = ContactDetailScreen.newInstance(sandData);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.mainContainer,EditContactObject).addToBackStack(null).commit();

    }

    @Override
    public void onDeleteContact() {
        setFragment(new ContactList());
    }

    @Override
    public void onEditContact(ArrayList<Contact> contactList, int position) {

        SandData sandData = new SandData(contactList , position);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment EditContactObject = EditExixtingContactScreen.newInstance(sandData);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.mainContainer,EditContactObject).addToBackStack(null).commit();

    }


    @Override
    public void makeEmail(String email) {

        Intent Email = new Intent(Intent.ACTION_SEND);
        Email.putExtra(Intent.EXTRA_EMAIL,new String[]{email});
        Email.putExtra(Intent.EXTRA_SUBJECT, "subject");
        Email.putExtra(Intent.EXTRA_TEXT, "message");

        Email.setType("message/rfc822");

        if(Email.resolveActivity(getPackageManager())!=null)
        startActivity(Intent.createChooser(Email, "Choose an Email client :"));

    }

    @Override
    public void backtomain() {
        setFragment(new ContactList());
    }

    @Override
    public void exixtingContactUpdate(ArrayList<Contact> contactList, int position) {
        SandData sandData = new SandData(contactList , position);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment EditContactObject = ContactDetailScreen.newInstance(sandData);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.mainContainer,EditContactObject).commit();
    }
}