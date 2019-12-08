package com.example.foodpark;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class AdmindbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Admin.db";
    public static final String TABLE_NAME_1 = "SIGNIN_DETAILS";
    public static final String TABLE_NAME_2 = "STUDENT_DETAILS";
    public static final String TABLE_NAME_3= "ADMIN_DETAILS";
    public static final String TABLE_NAME_4 = "MEALS";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "ITEM";
    public static final String COL_3 = "PRICE";
    public static final String COL_4 = "REG_NO";
    public static final String COL_5 = "NAME";
    public static final String COL_6 = "PASSWORD";
    public static final String COL_7 = "CREDITS";
    public static final String COL_8 = "MEAL";

    SQLiteDatabase db;

    public AdmindbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME_1 + " (REG_NO TEXT PRIMARY KEY,PASSWORD TEXT NOT NULL)");
        db.execSQL("CREATE TABLE " + TABLE_NAME_2 + " (REG_NO TEXT,NAME TEXT NOT NULL,CREDITS INTEGER DEFAULT 5580,FOREIGN KEY(REG_NO) REFERENCES SIGNIN_DETAILS(REG_NO))");
        db.execSQL("CREATE TABLE " + TABLE_NAME_3 + " (REG_NO TEXT,NAME TEXT NOT NULL,FOREIGN KEY(REG_NO) REFERENCES SIGNIN_DETAILS(REG_NO))");
        db.execSQL("CREATE TABLE " + TABLE_NAME_4 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,ITEM TEXT,PRICE INTEGER,MEAL TEXT NOT NULL)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_1);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_2);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_3);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_4);
        onCreate(db);
    }
    public boolean insertSignUpDetails(String regno,String passwd){
        db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(COL_4,regno);
        contentvalues.put(COL_6,passwd);
        long result = db.insert(TABLE_NAME_1,null,contentvalues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public boolean insertStudentDetails(String regno,String name){
        db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(COL_4,regno);
        contentvalues.put(COL_5,name);
        long result = db.insert(TABLE_NAME_2,null,contentvalues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public boolean insertAdminDetails(String regno,String name){
        db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(COL_4,regno);
        contentvalues.put(COL_5,name);
        long result = db.insert(TABLE_NAME_3,null,contentvalues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public boolean insertMealItems(String item,int price,String meal){
        db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(COL_2,item);
        contentvalues.put(COL_3,price);
        contentvalues.put(COL_8,meal);
        long result = db.insert(TABLE_NAME_4,null,contentvalues);
        if(result==-1)
            return false;
        else
            return true;
    }


    public List<Contact> getSignUpItems(){
        List<Contact> contactList = new ArrayList<Contact>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME_1;
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setReg(cursor.getString(0));
                contact.setPasssw(cursor.getString(1));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        return contactList;
    }

    public boolean testSignIn(String reg,String paw){
        String selectQuery = "SELECT PASSWORD FROM " +  TABLE_NAME_1 + " WHERE REG_NO = '" + reg + "'";
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()) {
            if (paw.equals(cursor.getString(0)))
                return true;
        }
        return false;
    }

    public List<Contact> getStudentItems(){
        List<Contact> contactList = new ArrayList<Contact>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME_2;
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setReg(cursor.getString(0));
                contact.setName(cursor.getString(1));
                contact.setCredits(Integer.parseInt(cursor.getString(2)));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        return contactList;
    }

    public String getstudentName(String reg){
        String selectQuery = "SELECT NAME FROM " +  TABLE_NAME_2 + " WHERE REG_NO = '" + reg + "'";

        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }
    public String getAdminName(String reg){
        String selectQuery = "SELECT NAME FROM " +  TABLE_NAME_3 + " WHERE REG_NO = '" + reg + "'";

        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }
    public int getstudentCredits(String reg){
        String selectQuery = "SELECT CREDITS FROM " +  TABLE_NAME_2 + " WHERE REG_NO = '" + reg + "'";
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        cursor.moveToFirst();
        return Integer.parseInt(cursor.getString(0));
    }
    public void updateCredits(String reg,int cutbalance){
        String selectQuery = "UPDATE " + TABLE_NAME_2 + " SET CREDITS = (CREDITS - " + cutbalance + ") WHERE REG = '" + reg + "'";
        db = this.getWritableDatabase();
    }
    public List<Contact> getAdminItems(){
        List<Contact> contactList = new ArrayList<Contact>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME_3;
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setReg(cursor.getString(0));
                contact.setName(cursor.getString(1));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        return contactList;
    }

    public List<Contact> getMealsItems(String meal){
        List<Contact> contactList = new ArrayList<Contact>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME_4 + " WHERE MEAL = '" + meal + "'";
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setItem(cursor.getString(1));
                contact.setPrice(Integer.parseInt(cursor.getString(2)));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        return contactList;
    }



}
