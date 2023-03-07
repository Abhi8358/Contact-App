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
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;


public class EditExixtingContactScreen extends Fragment {


    EditText name , phone ,phone_2, email ;
    ImageButton toolbar;
    Button save;
    ArrayList<Contact>contactList;
    int position;
    SandData sandData;
    ContactDataBase contactDataBase;
    View view;
    UpdateDataInEdit updateDataInEdit;
    private static final String TAG = "EditExistingContact";

    public EditExixtingContactScreen() {
        // Required empty public constructor
    }

    public static EditExixtingContactScreen newInstance(SandData sandData) {
        EditExixtingContactScreen fragment = new EditExixtingContactScreen();
        Bundle args = new Bundle();
        args.putSerializable("SandData",sandData);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        if (getArguments() != null) {
            this.sandData = (SandData) getArguments().getSerializable("SandData");
            this.contactList = sandData.getContactList();
            this.position = sandData.getPosition();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: ");
        view = inflater.inflate(R.layout.fragment_edit_exixting_contact_screen, container, false);
        name = view.findViewById(R.id.name2);
        phone = view.findViewById(R.id.phone2);
        phone_2 = view.findViewById(R.id.phone2_h);
        email = view.findViewById(R.id.email2);
        save = view.findViewById(R.id.update);
        toolbar = view.findViewById(R.id.toolbar);
        setData();

        save.setOnClickListener(view -> updateData());
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDataInEdit.exixtingContactUpdate(contactList,position);
            }
        });
        return view;
    }

    void setData(){
        Contact contact = contactList.get(position);

        name.setText(contact.getName());
        phone.setText(contact.getPhone_number());
        phone_2.setText(contact.getPhone_number_2());
        email.setText(contact.getEmail());

    }

    void updateData(){

        Contact contact = contactList.get(position);

        String Name = name.getText().toString();
        String Phone = phone.getText().toString();
        String Email = email.getText().toString();
        String Phone_2 = phone_2.getText().toString();
        String id = contact.getId();

        if(contactDataBase.searchContact(Name)){
            Toast.makeText(getActivity(), "Name Already Exist", Toast.LENGTH_SHORT).show();
        }
        else{
            if (!Name.isEmpty() && (!Phone.isEmpty() || !Phone_2.isEmpty())){


                contact.setEmail(Email);
                contact.setName(Name);
                contact.setPhone_number(Phone);
                contact.setPhone_number_2(Phone_2);


                Toast.makeText(getActivity(), "Successfully Update", Toast.LENGTH_SHORT).show();
                contactDataBase.updateContact(id, contact.getName(), contact.getPhone_number(), contact.getPhone_number_2(), contact.getEmail());
                updateDataInEdit.exixtingContactUpdate(contactList,position);
            }
            else{
                Toast.makeText(getActivity(), "Please Enter AtLeast one Number", Toast.LENGTH_SHORT).show();
            }
        }




    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ");
        contactDataBase = new ContactDataBase(context);
        if(context instanceof ContactList.onAddClickListnerInterface){
            updateDataInEdit=(UpdateDataInEdit) context;
        }
        else {
            throw new RuntimeException(context
                    + "must implement fragment1Listner");
        }
    }
    interface UpdateDataInEdit{
        public void exixtingContactUpdate(ArrayList<Contact>contactList,int position);

    }
}