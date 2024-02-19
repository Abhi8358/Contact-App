package com.example.contact;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddContactScreen extends Fragment {

    ArrayList<Contact>contactList;
    EditText name , number ,number_2, email ;
    Button save;
    View view;
    ContactDataBase contactDataBase;
    static final String TAG = "AddContactScreen";


    public AddContactScreen() {
        
        // Required empty public constructor
    }

    public static AddContactScreen newInstance(SandData sendData) {
        AddContactScreen fragment = new AddContactScreen();
        Bundle args = new Bundle();
        args.putSerializable("SendData",sendData);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        if (getArguments() != null) {
            SandData sandData = (SandData) getArguments().getSerializable("SendData");
            this.contactList = sandData.getContactList();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_edit_contact_screen, container, false);
        name = view.findViewById(R.id.name);
        number = view.findViewById(R.id.number);
        number_2 = view.findViewById(R.id.number_h);
        email = view.findViewById(R.id.email);
        save = view.findViewById(R.id.save);

        save.setOnClickListener(view -> saveData());
        contactDataBase = new ContactDataBase(getActivity());
        return view;
    }

    public void saveData(){

        String Name = name.getText().toString();
        String Number = number.getText().toString();
        String Number_2 = number_2.getText().toString();
        String Email = email.getText().toString();


        if(Email.isEmpty()) Email = "email not available";
        if (!Name.isEmpty() && (!Number.isEmpty() || !Number_2.isEmpty())){

            if(Number.isEmpty()) Number = "number not available";
            if(Number_2.isEmpty()) Number_2 = "number not available";

            //save data ,if user have only one data
            if(contactDataBase.searchContact(Name)){
                Toast.makeText(getActivity(), "Name Already Exist", Toast.LENGTH_SHORT).show();
            }
            else{
                long id =  contactDataBase.insertContact(
                        ""+Name,
                        ""+Number,
                        ""+Number_2,
                        ""+Email
                );

                Toast.makeText(getContext(), "Inserted Successfully.... "+id, Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(getContext(), "Nothing to save....", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ");

    }
}