package com.example.contact;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactDetailScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactDetailScreen extends Fragment {

   ArrayList<Contact> contactList;
   int position;
   TextView name , phone,phone_2 ,email ,phone_logo ,getPhone_logo_2, email_logo;
   Button edit , delete ;
   ImageButton toolbar;
   SandData sandData;
   View view;
   ContactDetailScreenInterface contactDetailScreenInterface;
   ContactDataBase contactDataBase;
   private static final String TAG = "ContactDetailScreen";

    public ContactDetailScreen() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment ContactDetailScreen.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactDetailScreen newInstance(SandData sandData) {
        ContactDetailScreen fragment = new ContactDetailScreen();
        Bundle args = new Bundle();
        args.putSerializable("sandData",sandData);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        if (getArguments() != null) {
           this.sandData = (SandData) getArguments().getSerializable("sandData");
           this.contactList = sandData.getContactList();
           this.position = sandData.getPosition();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: ");
        view = inflater.inflate(R.layout.fragment_contact_detail_screen, container, false);
        name = view.findViewById(R.id.name1);
        email = view.findViewById(R.id.email1);
        phone = view.findViewById(R.id.number1);
        phone_2 = view.findViewById(R.id.number1_h);
        delete = view.findViewById(R.id.delete);
        edit = view.findViewById(R.id.edit);
        phone_logo = view.findViewById(R.id.phonelogo);
        getPhone_logo_2 = view.findViewById(R.id.phonelogo_h);
        email_logo = view.findViewById(R.id.emaillogo);
        toolbar = view.findViewById(R.id.toolbar1);

        delete.setOnClickListener(view -> DeleteContact());

        edit.setOnClickListener(view -> EditContact());

        phone_logo.setOnClickListener(view -> openPhone(1));

        getPhone_logo_2.setOnClickListener(view -> openPhone(2));
        email_logo.setOnClickListener(view -> openEmail());
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactDetailScreenInterface.backtomain();
            }
        });
        setData();
        return view;
    }
    void openPhone(int type){
        Contact contact = contactList.get(position);
        if(!contact.getPhone_number().equals("number not available") && type==1){
            Call(contact.getPhone_number());

        }
        else if(!contact.getPhone_number_2().equals("number not available") && type==2){
            Call(contact.getPhone_number_2());
        }
        else{
            Toast.makeText(getActivity(), "number not available", Toast.LENGTH_SHORT).show();
        }

    }
    public void Call(String number){
        Intent call = new Intent(Intent.ACTION_DIAL);
        call.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        call.setData(Uri.parse("tel:" + number));

        getActivity().startActivity(call);
    }

    void openEmail(){
        Contact contact = contactList.get(position);
        if(contact.getEmail().equals("email not available")){
            Toast.makeText(getActivity(), "email not available", Toast.LENGTH_SHORT).show();
        }
        else{
            contactDetailScreenInterface.makeEmail(contact.getEmail());
        }

    }

    void setData(){
        Contact contact = contactList.get(position);

        name.setText(contact.getName());
        phone.setText(contact.getPhone_number());
        phone_2.setText(contact.getPhone_number_2());
        email.setText(contact.getEmail());
    }

    void DeleteContact(){
        String id = contactList.get(position).getId();

        contactDataBase.deleteContact(id);
        contactDetailScreenInterface.onDeleteContact();
    }

    void EditContact(){

        contactDetailScreenInterface.onEditContact(contactList,position);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ");
        contactDataBase = new ContactDataBase(context);
        if(context instanceof ContactList.onAddClickListnerInterface){
            contactDetailScreenInterface=(ContactDetailScreenInterface) context;
        }
        else{
            throw new RuntimeException(context
                    +"must implement fragment1Listner");
        }
    }
    interface ContactDetailScreenInterface{
         void onDeleteContact();
         void onEditContact(ArrayList<Contact>contactList,int position);
        void makeEmail(String email);
        void backtomain();
    }
}