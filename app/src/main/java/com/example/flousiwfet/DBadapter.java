package com.example.flousiwfet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.flousiwfet.achat;

import java.util.ArrayList;

public class DBadapter extends SQLiteOpenHelper {

    public DBadapter(Context context)
    {
        super(context,"mymoney",null,1);
    }

    public void onCreate(SQLiteDatabase db) {
        String creatTable="create table achat(id integer primary key,nom text ,prix float, date text)";
        db.execSQL(creatTable); }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String deleteTable=("drop table if exists achat ");
        db.execSQL(deleteTable);
        onCreate(db); }

    public void ajoutdepense(achat a){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put("nom",a.getNom());
        contentValues.put("prix",a.getPrix());
        contentValues.put("date",a.getDate());
        db.insert("achat",null,contentValues);

    }

    public ArrayList<achat> afficher(){
        SQLiteDatabase db=getReadableDatabase();
        String selectall="SELECT * FROM achat";
        Cursor cursor= db.rawQuery(selectall,null);
        ArrayList<achat> achats=new ArrayList<>();
        if (cursor.moveToFirst()){
            do{
                achat a=new achat(cursor.getString(1),cursor.getFloat(2));
                a.setId(cursor.getInt(0));
                a.setDate(cursor.getString(3));
                achats.add(a); }
            while(cursor.moveToNext()); }
        return achats;
    }

    public void supprimer(int id){
        SQLiteDatabase db=getWritableDatabase();
        db.delete("achat","id="+Integer.toString(id),null);}

    public Float total()
    {   Float s =0f ;

        SQLiteDatabase db=getReadableDatabase();
        String selectall="SELECT * FROM achat";
        Cursor cursor= db.rawQuery(selectall,null);
        ArrayList<achat> depenses=new ArrayList<>();
        if (cursor.moveToFirst()){
            do{
                s+=cursor.getFloat(2);
            }
            while(cursor.moveToNext());
        }

        return s ;}



}