package com.example.contact;

import java.io.Serializable;

public class Contact implements Serializable  {

    String name , email , phone_number, phone_number_2,id;

    public Contact(String id ,String name , String phone_number ,String phone_number_2, String email){
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.phone_number_2 = phone_number_2;
    }

    String getName(){
        return name;
    }
    String getEmail()
    {
        return email;
    }
    String getPhone_number()
    {
        return phone_number;
    }
    String getPhone_number_2(){
        return phone_number_2;
    }
    String getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){

        this.email = email;
        if(email.isEmpty()){
            this.email = "email not available";
        }
    }

    public void setPhone_number(String phone_number){
        this.phone_number=phone_number;
        if(phone_number.isEmpty()){
            this.phone_number = "number not available";
        }
    }
    public void setPhone_number_2(String phone_number_2){
        this.phone_number_2 = phone_number_2;
        if(phone_number_2.isEmpty()){
            this.phone_number_2 = "number not available";
        }
    }
}

