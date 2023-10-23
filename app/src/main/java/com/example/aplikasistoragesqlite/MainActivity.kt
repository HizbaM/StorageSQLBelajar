package com.example.aplikasistoragesqlite

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.aplikasistoragesqlite.db.DBHelper

class MainActivity : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var inputNama:EditText=findViewById(R.id.enterNama)
        var inputUmur:EditText=findViewById(R.id.enterUmur)
        var ibTambah:ImageButton=findViewById(R.id.ibTambahNama)
        var ibPrint:ImageButton=findViewById(R.id.ibPrintNama)
        var tvNamaa:TextView=findViewById(R.id.tvNama)
        var tvUmurr:TextView=findViewById(R.id.tvUmur)
        var namaa:TextView=findViewById(R.id.Nama)
        var umurr:TextView=findViewById(R.id.Umur)


        ibTambah.setOnClickListener {
            val db=DBHelper(this,null)
            val nama=inputNama.text.toString()
            val umur=inputUmur.text.toString()

            db.addName(nama, umur)
            Toast.makeText(this, "Ditambahkan ke database", Toast.LENGTH_LONG)
                .show()

            inputNama.text.clear()
            inputUmur.text.clear()
        }

        ibPrint.setOnClickListener {
            val db=DBHelper(this, null)

            val cursor=db.getName()
            if(cursor!!.moveToFirst()){
                cursor.moveToFirst()

                namaa.append(cursor.getString(cursor.getColumnIndex(DBHelper.Name_Col))+"\n")
                umurr.append(cursor.getString(cursor.getColumnIndex(DBHelper.Age_Col))+"\n")
            }

            if(cursor.moveToNext()){
                namaa.text=""
                umurr.text=""
                //jika data lebih dari 1 maka kita looping datanya
                while (cursor.moveToNext()){
                    namaa.append(cursor.getString(cursor.getColumnIndex(DBHelper.Name_Col))+"\n")
                    umurr.append(cursor.getString(cursor.getColumnIndex(DBHelper.Age_Col))+"\n")
                }
            }

            cursor.close()
        }



    }
}