package com.example.coffeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CoffeeDBHandler extends SQLiteOpenHelper {
    private static final int Database_Version = 1;
    private static final String Database_Name = "coffeebase.db";
    private static final String Table_Coffee = "coffeesales";
    private static final String Column_ID = "id";
    private static final String Column_CustomerName = "name";
    private static final String Column_SaleAmount = "saleamount";


    //constructor
    public CoffeeDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Database_Name, factory, Database_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + Table_Coffee +
                "(" + Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Column_CustomerName + " TEXT, " + Column_SaleAmount + " INTEGER " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Coffee);
        onCreate(db);
    }
    public  void addOrder(Order order){
        ContentValues values = new ContentValues();
        values.put(Column_CustomerName,order.get_custName());
        values.put(Column_SaleAmount, order.get_salesAmount());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(Table_Coffee, null, values);
        db.close();
    }
    public String databaseToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + Table_Coffee + " WHERE 1";
        //create a Cursor object to point to the DB
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        //Loop to read and store the information in a String
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(Column_CustomerName)) != null) {
                dbString += c.getString(c.getColumnIndex(Column_CustomerName)) + " --> $" +
                        c.getString(c.getColumnIndex(Column_SaleAmount));
                dbString += "\n";
            }
            c.moveToNext();

        }
        db.close();
        return dbString;
    }
}
