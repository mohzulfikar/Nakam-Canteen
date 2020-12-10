package com.example.nk2

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class User_Beranda: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_beranda)

    }

    fun beranda_profil(view: View) {
        val intent = Intent (applicationContext, User_Profil::class.java)
        startActivity(intent)
    }
}