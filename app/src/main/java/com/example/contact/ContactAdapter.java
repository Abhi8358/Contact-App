package com.example.contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.*;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactHolder> {


    ArrayList<Contact> ContactList ;
    TextView Name;
    onAdapterClick onadapterClick;
    Context context;

    public ContactAdapter(ArrayList<Contact> ContactList, Context context){
        this.ContactList = ContactList;
        this.context = context;
        onadapterClick = (onAdapterClick) context;
    }

    @NonNull
    @Override
    public ContactAdapter.ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contactview,parent,false);
        return new ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ContactHolder holder, int position) {

        Contact contact = ContactList.get(position);
        Name.setText(contact.getName());
    }

    @Override
    public int getItemCount() {
        return ContactList.size();
    }

    public class ContactHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ContactHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            Name = itemView.findViewById(R.id.textView);
        }

        @Override
        public void onClick(View view) {
            onadapterClick.AdapterClick(ContactList,getAdapterPosition());
        }
    }
    interface onAdapterClick{
        void AdapterClick(ArrayList<Contact>contactList,int position);
    }
}
