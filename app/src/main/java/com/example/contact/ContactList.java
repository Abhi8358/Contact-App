package com.example.contact;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ContactList extends Fragment {

    View view;
    ContactViewModel viewModel;
    FloatingActionButton add;
    ArrayList<Contact>contactList;
    static final String TAG = "ContactList";
    onAddClickListnerInterface onaddClickListnerInterface;

    public ContactList() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: ");
        view = inflater.inflate(R.layout.fragment_contact_list, container, false);

        ViewModelProvider viewModelProvider = new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()));
        viewModel = viewModelProvider.get(ContactViewModel.class);

        add = view.findViewById(R.id.fab);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onaddClickListnerInterface.ClickOnAdd(contactList);
            }
        });
        setuplivedata();
        return view;
    }

    void setuplivedata(){

        viewModel.getContacteData().observe((LifecycleOwner) view.getContext(), ContactModel -> handleQuestionList(ContactModel));
    }

    private void handleQuestionList(ArrayList<Contact> ContactModel) {
        this.contactList = ContactModel;
        ContactAdapter adapter = new ContactAdapter(ContactModel,getActivity());
        RecyclerView questionRecyclerView = view.findViewById(R.id.contactContainer);
        questionRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        questionRecyclerView.setAdapter(adapter);

    }

    interface onAddClickListnerInterface{
        void ClickOnAdd(ArrayList<Contact>contactList);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof onAddClickListnerInterface){
            onaddClickListnerInterface=(onAddClickListnerInterface) context;
        }
        else{
            throw new RuntimeException(context
                    +"must implement fragment1Listner");
        }

    }
}