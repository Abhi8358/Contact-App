package com.example.contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class ContactDataBase extends SQLiteOpenHelper {

    public ContactDataBase(@Nullable Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        //create table on database
        db.execSQL(Constants.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //upgrade table if any structure change in db

        // drop table if exists
        if(newVersion>oldVersion){
            db.execSQL("DROP TABLE IF EXISTS "+Constants.TABLE_NAME);
            onCreate(db);
        }


    }

    // Insert Function to insert data in database
    public long insertContact(String name,String phone,String phone_2,String email){

        //get writable database to write data on db
        SQLiteDatabase db = this.getWritableDatabase();

        // create ContentValue class object to save data
        ContentValues contentValues = new ContentValues();

        // id will save automatically as we write query
        contentValues.put(Constants.C_NAME,name);
        contentValues.put(Constants.C_PHONE,phone);
        contentValues.put(Constants.C_PHONE_2,phone_2);
        contentValues.put(Constants.C_EMAIL,email);

        //insert data in row, It will return id of record
        long id = db.insert(Constants.TABLE_NAME,null,contentValues);

        // close db
        db.close();

        //return id
        return id;

    }

    // Update Function to update data in database
    public void updateContact(String id,String name,String phone,String phone_2,String email){

        //get writable database to write data on db
        SQLiteDatabase db = this.getWritableDatabase();

        // create ContentValue class object to save data
        ContentValues contentValues = new ContentValues();

        contentValues.put(Constants.C_NAME,name);
        contentValues.put(Constants.C_PHONE,phone);
        contentValues.put(Constants.C_PHONE_2,phone_2);
        contentValues.put(Constants.C_EMAIL,email);


        //update data in row, It will return id of record
        db.update(Constants.TABLE_NAME,contentValues,Constants.C_ID+" =? ",new String[]{id} );

        // close db
        db.close();

    }

    // delete data by id
    public void deleteContact(String id){
        //get writable database
        SQLiteDatabase db =  getWritableDatabase();

        //delete query
        db.delete(Constants.TABLE_NAME,Constants.C_ID+" =?",new String[]{id});

        db.close();
    }





    // get data
    public ArrayList<Contact> getAllData(){
        //create arrayList
        ArrayList<Contact> arrayList = new ArrayList<>();
        //sql command query
        String selectQuery = "SELECT * FROM "+Constants.TABLE_NAME;

        //get readable db
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        // looping through all record and add to list
        if (cursor.moveToFirst()){
            do {
                Contact modelContact = new Contact(
                        // only id is integer type
                        ""+cursor.getInt(cursor.getColumnIndexOrThrow(Constants.C_ID)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_NAME)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_PHONE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_PHONE_2)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_EMAIL))

                );
                arrayList.add(modelContact);
            }while (cursor.moveToNext());
        }
        db.close();
        return arrayList;
    }
    public boolean searchContact(String name){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from "+Constants.TABLE_NAME+" where NAME=?", new String[]{name});

        if(cursor.getCount()>0){
            return true;
        }
        return false;
    }
}
