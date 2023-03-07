package com.example.contact;

public class Constants {

    public static final String DATABASE_NAME = "CONTACT";
    //database version
    public static final int DATABASE_VERSION = 3;

    // table name
    public static final String TABLE_NAME = "CONTACT_DETAIL";

    // table column or field name
    public static final String C_ID = "ID";

    public static final String C_NAME = "NAME";
    public static final String C_PHONE = "PHONE";
    public static final String C_PHONE_2 = "PHONE_2";
    public static final String C_EMAIL = "EMAIL";


    // query for create table
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "( "
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + C_NAME + " TEXT, "
            + C_PHONE + " TEXT, "
            + C_PHONE_2 + " TEXT, "
            + C_EMAIL + " TEXT "
            + " );";


}
