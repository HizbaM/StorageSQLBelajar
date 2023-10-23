package com.example.aplikasistoragesqlite.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper
import java.util.jar.Attributes.Name

class DBHelper(context: Context, factory:CursorFactory?): SQLiteOpenHelper( context,
    Database_Name,
    factory,
    Database_Version) {
    companion object{
        val Database_Name= "db_employee"
        val Database_Version=1
        val Table_Name="table_employee"
        val Id_Col="id"
        val Name_Col="nama"
        val Age_Col="age"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var query=("CREATE TABLE" + Table_Name+"("+ Id_Col+" INTEGER PRIMARY KEY, "+ Name_Col+"T EXT,"+ Age_Col+" TEXT)")
    db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS"+ Table_Name)
        onCreate(db)
    }
    fun addName(Name:String, age:String){
        val values=ContentValues()
        values.put(Name_Col,Name)
        values.put(Age_Col, age)
        val db=this.writableDatabase
        db.insert(Table_Name, null,values)
        db.close()
    }

    fun getName():Cursor?{
        val db=this.readableDatabase
        return db.rawQuery("SELECT + FROM"+ Table_Name,null)
    }
}
