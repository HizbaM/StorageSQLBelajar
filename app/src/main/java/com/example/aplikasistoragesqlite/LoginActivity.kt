package com.example.aplikasistoragesqlite

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    companion object{
        const val SHARED_PREFS="shared_prefs"
        const val EMAIL_KEY="email"
        const val PASSWORD_KEY="password"
    }
    private lateinit var sharedPreferences: SharedPreferences
    private var email: String?=null
    private var sandi: String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        var inputPengguna:EditText=findViewById(R.id.inputUsername)
        var inputSandi:TextInputEditText=findViewById(R.id.inputPassword)
        var btnMasuk:Button=findViewById(R.id.btnLogin)


        sharedPreferences=getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)

        email=sharedPreferences.getString("email",null)
        sandi=sharedPreferences.getString("sandi",null)

        btnMasuk.setOnClickListener {
            if(inputPengguna.text.toString().isEmpty() && inputSandi.text!!.toString().isEmpty()){
                Toast.makeText(this, "Mohon Masukkan Pengguna dan Sandi!", Toast.LENGTH_LONG)
            }else{
                val editor=sharedPreferences.edit()
                editor.putString(EMAIL_KEY, inputPengguna.text.toString())
                editor.putString(PASSWORD_KEY, inputSandi.text.toString())

                editor.apply()

                val i=Intent(this, MainActivity::class.java)
                startActivity(i)
                finish()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        email = sharedPreferences.getString(EMAIL_KEY, null)
        sandi = sharedPreferences.getString(PASSWORD_KEY, null)
//aaa
        if (email != null && sandi != null) {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}